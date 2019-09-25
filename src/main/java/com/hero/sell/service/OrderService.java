package com.hero.sell.service;

import com.hero.sell.converter.OrderMainToOrderDTOConverter;
import com.hero.sell.dto.CartDTO;
import com.hero.sell.dto.OrderDTO;
import com.hero.sell.entities.OrderDetail;
import com.hero.sell.entities.OrderMain;
import com.hero.sell.entities.ProductInfo;
import com.hero.sell.enums.OrderStatusEnum;
import com.hero.sell.enums.PayStatusEnum;
import com.hero.sell.enums.ResultEnum;
import com.hero.sell.exception.SellException;
import com.hero.sell.utils.GenerateUUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/4
 */
@Service("orderService")
@Slf4j
public class OrderService {

    @Resource(name = "productInfoSerivce")
    private ProductInfoSerivce productInfoSerivce;

    @Resource(name = "orderDetailService")
    private OrderDetailService orderDetailService;

    @Resource(name = "orderMainService")
    private OrderMainService orderMainService;

    @Resource(name = "payService")
    private PayService payService;

    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     */
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = GenerateUUID.getUuid();
        BigDecimal orderAmount = new BigDecimal(0);
        //扣库存方法一
//        List<CartDTO> cartDTOList = new ArrayList<>();

        //1.查询商品（数量、价格等），计算总价时，每件商品的单价必须从数据库查不能从前端入参
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoSerivce.findById(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);  //商品不存在
            }
            //2.计算总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //订单详情(OrderDetail)入库
            orderDetail.setOrderId(orderId);
//            orderDetail.setProductName(productInfo.getProductName());
//            orderDetail.setProductPrice(productInfo.getProductPrice());
//            orderDetail.setProductIcon(productInfo.getProductIcon());

            //使用spring的此方法将productInfo的属性拷贝至orderDetail
            BeanUtils.copyProperties(productInfo, orderDetail);

            orderDetail.setCreateName(orderDTO.getCreateName());
            orderDetail.setCreateTime(new Date());
            orderDetail.setModifyName(orderDTO.getModifyName());
            orderDetail.setModifyTime(new Date());
            orderDetailService.saveOrUpdate(orderDetail);

            //扣库存方法一
//            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);

        }

        //3.订单写入到数据库
        OrderMain orderMain = new OrderMain();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMain);  //使用此操作时需要放在设置参数的第一步，否则可能会被覆盖掉
        orderMain.setOrderAmount(orderAmount);
        orderMain.setOrderStatus(0);
        orderMain.setPayStatus(0);
        orderMain.setCreateTime(new Date());
        orderMain.setModifyTime(new Date());
        orderMainService.saveOrUpdate(orderMain);

        //4.扣库存方法二
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList()
                                    .stream()
                                    .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                                    .collect(Collectors.toList());
        productInfoSerivce.decreaseStock(cartDTOList);
        return orderDTO;
    }

    /**
     * 查询单个订单
     * @param orderId
     * @return
     */
    public OrderDTO findOrderDTOById(String orderId) {
        OrderMain orderMain = orderMainService.findById(orderId);
        if (orderMain == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);    //订单不存在
        }
        List<OrderDetail> orderDetailList = orderDetailService.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);  //订单详情不存在
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMain, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    /**
     * 查询订单列表
     * @param buyerOpenId
     * @param pageable
     * @return
     */
    public Page<OrderDTO> findOrderList(String buyerOpenId, Pageable pageable) {
        Page<OrderMain> orderMainPage = orderMainService.findByBuyerOpenid(buyerOpenId, pageable);
        List<OrderDTO> orderDTOList = OrderMainToOrderDTOConverter.convert(orderMainPage.getContent());
        return new PageImpl<>(orderDTOList, pageable, orderMainPage.getTotalElements());
    }

    /**
     * 取消订单
     *
     * @param orderDTO
     * @return
     */
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        //判断订单状态
        if (!OrderStatusEnum.NEW.getCode().equals(orderDTO.getOrderStatus())) {
            log.error("【取消订单】订单状态不正确，orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMain orderMain = new OrderMain();
        BeanUtils.copyProperties(orderDTO, orderMain);
        String updateResult = orderMainService.saveOrUpdate(orderMain);
        if ("1".equals(updateResult)) {
            log.error("【取消订单】更新失败, orderMain={}", orderMain);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //恢复库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品, orderDto={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAILD_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList()
                .stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoSerivce.increaseStock(cartDTOList);

        //如果已支付，需要退款
        if (PayStatusEnum.SUCCESS.equals(orderMain.getPayStatus())) {
            payService.refund(orderDTO);
        }
        return orderDTO;
    }

    /**
     * 完结订单
     *
     * @param orderDTO
     * @return
     */
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!OrderStatusEnum.NEW.getCode().equals(orderDTO.getOrderStatus())) {
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMain orderMain = new OrderMain();
        BeanUtils.copyProperties(orderDTO, orderMain);
        String result = orderMainService.saveOrUpdate(orderMain);
        if ("1".equals(result)) {
            log.error("【完结订单】更新失败, orderMain={}", orderMain);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    /**
     * 支付订单
     *
     * @param orderDTO
     * @return
     */
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (!OrderStatusEnum.NEW.getCode().equals(orderDTO.getOrderStatus())) {
            log.error("【支付订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!PayStatusEnum.WAIT.getCode().equals(orderDTO.getPayStatus())) {
            log.error("【订单已支付】订单支付状态不正确, orderId={}, payStatus={}", orderDTO.getOrderId(), orderDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMain orderMain = new OrderMain();
        BeanUtils.copyProperties(orderDTO, orderMain);
        String result = orderMainService.saveOrUpdate(orderMain);
        if ("1".equals(result)) {
            log.error("【支付订单】更新失败, orderMain={}", orderMain);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

}
