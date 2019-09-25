package com.hero.sell.service;

import com.hero.sell.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceTest {

    @Autowired
    private PayService payService;

    @Resource(name = "orderService")
    private OrderService orderService;

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = orderService.findOrderDTOById("402880e86cf2b30d016cf2b36be90000");
        payService.create(orderDTO);
    }

    @Test
    public void refund(){
        OrderDTO orderDTO = orderService.findOrderDTOById("402880e86cf2b30d016cf2b36be90000");
        payService.refund(orderDTO);
    }

}