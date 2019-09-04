package com.hero.sell.business.order.service;

import com.hero.sell.business.orderdetail.service.OrderDetailService;
import com.hero.sell.business.ordermain.service.OrderMainService;
import com.hero.sell.business.productinfo.service.ProductInfoSerivce;
import com.hero.sell.dto.OrderDTO;
import com.hero.sell.entities.OrderDetail;
import com.hero.sell.entities.OrderMain;
import com.hero.sell.entities.ProductInfo;
import com.hero.sell.enums.ResultEnum;
import com.hero.sell.exception.SellException;
import com.hero.sell.utils.IdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/4
 */
@Service("orderService")
public class OrderService {

    @Autowired
    private IdWorker idWorker;

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
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = idWorker.nextId() + "";
        BigDecimal orderAmount = new BigDecimal(0);

        //1.查询商品（数量、价格等），计算总价时，每件商品的单价必须从数据库查不能从前端入参
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoSerivce.findById(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);  //商品不存在
            }
            //2.计算总价
            orderAmount = orderDetail.getProductPrice()
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
        }

        //3.订单写入到数据库
        OrderMain orderMain = new OrderMain();
        orderMain.setOrderId(orderId);
        orderMain.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDTO, orderMain);
        orderMain.setOrderStatus(0);
        orderMain.setPayStatus(0);
        orderMain.setCreateName("叶金雄");
        orderMain.setCreateTime(new Date());
        orderMain.setModifyName("叶金雄");
        orderMain.setModifyTime(new Date());


        //4.扣库存

        return null;
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
