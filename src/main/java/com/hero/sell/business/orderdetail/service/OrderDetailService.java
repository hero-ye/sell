package com.hero.sell.business.orderdetail.service;

import com.hero.sell.business.orderdetail.dao.OrderDetailDao;
import com.hero.sell.entities.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/2
 */
@Service("orderDetailService")
public class OrderDetailService {

    @Autowired
    private OrderDetailDao orderDetailDao;

    /**
     * 根据orderId查询
     * @param orderId
     * @return
     */
    public List<OrderDetail> findByOrderId(String orderId){
        return orderDetailDao.findByOrderId(orderId);
    }

    /**
     * 保存或更新
     * @param orderDetail
     */
    public void saveOrUpdate(OrderDetail orderDetail){
        orderDetailDao.save(orderDetail);
    }


}
