package com.hero.sell.rest;

import com.hero.sell.dto.OrderDTO;
import com.hero.sell.enums.ResultEnum;
import com.hero.sell.exception.SellException;
import com.hero.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 支付
 * @Author yejx
 * @Date 2019/9/17
 */
@RestController
@RequestMapping("/pay")
@Slf4j
public class PayRest {

    @Resource(name = "orderService")
    private OrderService orderService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public void creata(@RequestParam("orderId") String orderId,
                       @RequestParam("returnUrl") String returnUrl){
        //1.查询订单
        OrderDTO orderDTO = orderService.findOrderDTOById(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //2.发起支付

    }

}
