package com.hero.sell.service;

import com.hero.sell.dto.OrderDTO;
import com.hero.sell.enums.ResultEnum;
import com.hero.sell.exception.SellException;
import com.hero.sell.utils.JsonUtil;
import com.hero.sell.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/17
 */
@Service("payService")
@Slf4j
public class PayService {

    private static final String ORDER_NAME = "微信点餐订单";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Resource(name = "orderService")
    private OrderService orderService;

    /**
     * 发起支付
     * @param orderDTO
     * @return
     */
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getOrderId());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);    //微信公众号支付
        log.info("【微信支付】发起支付，request={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】发起支付，response={}", JsonUtil.toJson(payResponse));

        return payResponse;
    }

    /**
     * 异步通知
     * @param notifyData
     * @return
     */
    public PayResponse notify(String notifyData) {
        //1.验证签名
        //2.支付的状态
        //3.支付的金额
        //4.支付人（下单人 == 支付人）
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】异步通知，payResponse={}", JsonUtil.toJson(payResponse));

        //查询订单
        OrderDTO orderDTO = orderService.findOrderDTOById(payResponse.getOrderId());

        //判断订单是否存在
        if (orderDTO == null) {
            log.error("【微信支付】异步通知，订单不存在，orderId={}", payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //判断金额是否一致(判断0.10和0.1是否相等：相减后的值小于某个值即可)
        if (!MathUtil.equals(payResponse.getOrderAmount(), orderDTO.getOrderAmount().doubleValue())) {
            log.error("【微信支付】异步通知，订单金额不一致，orderId={},微信通知金额={},系统金额={}",
                        payResponse.getOrderId(),
                        payResponse.getOrderAmount(),
                        orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }

        //修改订单支付状态
        orderService.paid(orderDTO);

        return payResponse;
    }

    /**
     * 退款
     * @param orderDTO
     * @return
     */
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】,refundRequest={}", JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】,refundResponse={}", JsonUtil.toJson(refundResponse));
        return refundResponse;
    }

}
