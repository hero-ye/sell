package com.hero.sell.business.ordermain.service;

import com.hero.sell.business.ordermain.dao.OrderMainDao;
import com.hero.sell.entities.OrderMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/2
 */
@Service("orderMainService")
public class OrderMainService  {

    @Autowired
    private OrderMainDao orderMainDao;

    /**
     * 新增/更新
     * @param orderMain
     */
    public void saveOrUpdate(OrderMain orderMain){
        orderMain.setCreateTime(new Date());
        orderMain.setModifyTime(new Date());
        orderMainDao.save(orderMain);
    }

    /**
     * 根据openId查询
     * @param openId
     * @param pageable
     * @return
     */
    public Page<OrderMain> findByBuyerOpenid(String openId, Pageable pageable){
        return orderMainDao.findByBuyerOpenid(openId, pageable);
    }

    /**
     * 根据主键ID查询
     * @param orderId
     * @return
     */
    public OrderMain findById(String orderId){
        return orderMainDao.findById(orderId).get();
    }


}
