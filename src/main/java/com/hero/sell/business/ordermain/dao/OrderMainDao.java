package com.hero.sell.business.ordermain.dao;

import com.hero.sell.entities.OrderMain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/2
 */
public interface OrderMainDao extends JpaRepository<OrderMain, String> {

    /**
     * 分页查询
     */
    Page<OrderMain> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
