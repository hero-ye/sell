package com.hero.sell.business.orderdetail.service;

import com.hero.sell.business.orderdetail.dao.OrderDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/2
 */
@Service("orderDetailService")
public class OrderDetailService {

    @Autowired
    private OrderDetailDao orderDetailDao;



}
