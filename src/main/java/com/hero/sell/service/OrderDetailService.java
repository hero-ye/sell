package com.hero.sell.service;

import com.hero.sell.dao.OrderDetailDao;
import com.hero.sell.entities.OrderDetail;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        if (StringUtils.isEmpty(orderDetail.getDetailId())) {  //新增
            orderDetail.setCreateTime(new Date());
            orderDetail.setModifyTime(new Date());
        } else {    //更新
            orderDetail.setModifyTime(new Date());
        }
        orderDetailDao.save(orderDetail);
    }


}
