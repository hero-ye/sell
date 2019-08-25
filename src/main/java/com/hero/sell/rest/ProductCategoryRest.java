package com.hero.sell.rest;

import com.hero.sell.entities.ProductCategory;
import com.hero.sell.service.ProductCategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author yejx
 * @Date 2019/8/26
 */
@RestController
@RequestMapping("/productCategoey")
public class ProductCategoryRest {

    @Resource
    private ProductCategoryService productCategoryService;

    @RequestMapping("/findAll")
    public List<ProductCategory> findAll() {
        return productCategoryService.findAll();
    }

    @RequestMapping("/findById")
    public ProductCategory findById(String id) {
        return productCategoryService.findById(id);
    }
}
