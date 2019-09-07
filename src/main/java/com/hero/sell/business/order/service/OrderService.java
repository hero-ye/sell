package com.hero.sell.business.order.service;

import com.hero.sell.business.orderdetail.service.OrderDetailService;
import com.hero.sell.business.ordermain.service.OrderMainService;
import com.hero.sell.business.productinfo.service.ProductInfoSerivce;
import com.hero.sell.dto.CartDTO;
import com.hero.sell.dto.OrderDTO;
import com.hero.sell.entities.OrderDetail;
import com.hero.sell.entities.OrderMain;
import com.hero.sell.entities.ProductInfo;
import com.hero.sell.enums.ResultEnum;
import com.hero.sell.exception.SellException;
import com.hero.sell.utils.GenerateUUID;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class OrderService {

    @Resource(name = "productInfoSerivce")
    private ProductInfoSerivce productInfoSerivce;

    @Resource(name = "orderDetailService")
    private OrderDetailService orderDetailService;

    @Resource(name = "orderMainService")
    private OrderMainService orderMainService;

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

            orderDetail.setCreateName("叶金雄");
            orderDetail.setCreateTime(new Date());
            orderDetail.setModifyName("叶金雄");
            orderDetail.setModifyTime(new Date());
            orderDetailService.saveOrUpdate(orderDetail);

            //扣库存方法一
//            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);

        }

        //3.订单写入到数据库
        OrderMain orderMain = new OrderMain();
        BeanUtils.copyProperties(orderDTO, orderMain);  //使用此操作时需要放在设置参数的第一步，否则可能会被覆盖掉
        orderMain.setOrderId(orderId);
        orderMain.setOrderAmount(orderAmount);
        orderMain.setOrderStatus(0);
        orderMain.setPayStatus(0);
        orderMain.setCreateName("叶金雄");
        orderMain.setCreateTime(new Date());
        orderMain.setModifyName("叶金雄");
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
     *
     * @param orderId
     * @return
     */
    public OrderDTO findById(String orderId) {
        return null;
    }

    /**
     * 查询订单列表
     *
     * @param buyerOpenId
     * @param pageable
     * @return
     */
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
        return null;
    }

    /**
     * 取消订单
     *
     * @param orderDTO
     * @return
     */
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    /**
     * 完结订单
     *
     * @param orderDTO
     * @return
     */
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    /**
     * 支付订单
     *
     * @param orderDTO
     * @return
     */
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }

}
