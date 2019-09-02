package com.hero.sell.business.productcategory.rest;

import com.hero.sell.business.productcategory.service.ProductCategoryService;
import com.hero.sell.entities.ProductCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @Resource(name = "productCategoryService")
    private ProductCategoryService productCategoryService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<ProductCategory> findAll() {
        return productCategoryService.findAll();
    }

    /**
     * 根据ID查询
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public ProductCategory findById(@RequestParam String categoryId) {
        ProductCategory productCategory = null;
        try {
            productCategory = productCategoryService.findById(categoryId);
        } catch (Exception e) {
            log.error("查询出错："+e.getMessage());
        }
        return productCategory;
    }

    /**
     * 新增/更新
     * @param productCategory
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public void savaOrUpdate(ProductCategory productCategory) {
        productCategoryService.saveOrUpdate(productCategory);
    }
}
