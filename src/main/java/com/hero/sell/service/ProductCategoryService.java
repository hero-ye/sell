package com.hero.sell.service;

import com.hero.sell.dao.ProductCategoryDao;
import com.hero.sell.entities.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author yejx
 * @Date 2019/8/26
 */
@Service("productCategoryService")
public class ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    /**
     * 查询所有
     * @return
     */
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public ProductCategory findById(String id) {
        ProductCategory productCategory = productCategoryDao.getOne(id);
        return productCategory;
    }

}
