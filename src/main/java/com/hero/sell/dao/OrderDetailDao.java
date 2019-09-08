package com.hero.sell.dao;

import com.hero.sell.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/2
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail, String> {

    /**
     * 根据订单ID查询
     */
    List<OrderDetail> findByOrderId(String orderId);
}
