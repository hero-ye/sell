package com.hero.sell.dao;

import com.hero.sell.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description
 * @Author yejx
 * @Date 2019/8/25
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory, String> {
}
