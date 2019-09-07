package com.hero.sell.business.ordermain.service;

import com.hero.sell.business.ordermain.dao.OrderMainDao;
import com.hero.sell.entities.OrderMain;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
@Slf4j
public class OrderMainService {

    @Autowired
    private OrderMainDao orderMainDao;

    /**
     * 新增/更新
     *
     * @param orderMain
     */
    public String saveOrUpdate(OrderMain orderMain) {
        if (StringUtils.isEmpty(orderMain.getOrderId())) {  //新增
            orderMain.setCreateTime(new Date());
            orderMain.setModifyTime(new Date());
        } else {    //更新
            orderMain.setModifyTime(new Date());
        }
        OrderMain result = orderMainDao.save(orderMain);
        if (result == null) {
            return "1";
        }
        return "2";
    }

    /**
     * 根据openId查询
     *
     * @param openId
     * @param pageable
     * @return
     */
    public Page<OrderMain> findByBuyerOpenid(String openId, Pageable pageable) {
        return orderMainDao.findByBuyerOpenid(openId, pageable);
    }

    /**
     * 根据主键ID查询
     *
     * @param orderId
     * @return
     */
    public OrderMain findById(String orderId) {
        return orderMainDao.findById(orderId).get();
    }


}
