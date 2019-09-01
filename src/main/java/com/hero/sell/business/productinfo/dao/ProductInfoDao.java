package com.hero.sell.business.productinfo.dao;

import com.hero.sell.entities.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/1
 */
public interface ProductInfoDao extends JpaRepository<ProductInfo, String>, JpaSpecificationExecutor<ProductInfo> {

    /**
     * 根据商品状态查询
     * @param productStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(String productStatus);

}
