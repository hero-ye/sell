package com.hero.sell.dao;

import com.hero.sell.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description
 * @Author yejx
 * @Date 2019/8/25
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory, String> {
    /**
     * 根据商品名称查询
     * @param categoryName
     * @return
     */
    List<ProductCategory> findByCategoryName(String categoryName);

    /**
     * 根据商品类目编码查询
     * @param categoryCodeList
     * @return
     */
    List<ProductCategory> findByCategoryCodeIn(List<Integer> categoryCodeList);
}
