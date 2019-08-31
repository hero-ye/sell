package com.hero.sell.rest;

import com.hero.sell.entities.ProductCategory;
import com.hero.sell.service.ProductCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    private static final Logger log = LoggerFactory.getLogger(ProductCategoryService.class);

    @Resource
    private ProductCategoryService productCategoryService;

    @RequestMapping("/findAll")
    public List<ProductCategory> findAll() {
        return productCategoryService.findAll();
    }

    @RequestMapping("/findById")
    public ProductCategory findById(@RequestParam String categoryId) {
        ProductCategory productCategory = null;
        try {
            productCategory = productCategoryService.findById(categoryId);
            log.info("查询结果：" + productCategory.toString());
            log.warn("即将出现错误！");
            int error = 1 / 0;
        } catch (Exception e) {
            log.error("查询出错："+e.getMessage());
        }
        return productCategory;
    }
}
