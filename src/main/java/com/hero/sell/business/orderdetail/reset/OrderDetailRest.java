package com.hero.sell.business.orderdetail.reset;

import com.hero.sell.business.orderdetail.service.OrderDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/2
 */
@RestController
@RequestMapping("/orderdetail")
public class OrderDetailRest {

    @Resource(name = "orderDetailService")
    private OrderDetailService orderDetailService;



}
