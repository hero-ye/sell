package com.hero.sell.business.ordermain.service;

import com.hero.sell.business.ordermain.dao.OrderMainDao;
import com.hero.sell.entities.OrderMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
        orderMainDao.save(orderMain);
    }

    /**
     * 根据openId查询
     * @param openId
     * @return
     */
    public Page<OrderMain> findByBuyerOpenid(String openId, int page, int size){
        PageRequest pageRequest = new PageRequest(page, size);
        return orderMainDao.findByBuyerOpenid(openId, pageRequest);

    }


}
