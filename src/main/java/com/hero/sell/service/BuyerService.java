package com.hero.sell.service;

import com.hero.sell.dto.OrderDTO;
import com.hero.sell.enums.ResultEnum;
import com.hero.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 买家
 * @Description
 * @Author yejx
 * @Date 2019/9/8
 */
@Service("buyerService")
@Slf4j
public class BuyerService {

    @Resource(name = "orderService")
    private OrderService orderService;

    /**
     * 查询订单
     * @param openid
     * @param orderId
     * @return
     */
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwener(openid, orderId);
    }

    /**
     * 取消订单
     * @return
     */
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwener(openid, orderId);
        if (orderDTO == null) {
            log.error("【取消订单】订单不存在, orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    /**
     * 公共方法
     * @param openid
     * @param orderId
     * @return
     */
    private OrderDTO checkOrderOwener(String openid, String orderId){
        OrderDTO orderDTO = orderService.findOrderDTOById(orderId);
        if (orderDTO == null) {
            return null;
        }
        //判断是否是自己的订单
        if (!openid.equals(orderDTO.getBuyerOpenid())) {
            log.error("【查询订单】订单的openid不一致：openid={}, orderDto={}", openid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
