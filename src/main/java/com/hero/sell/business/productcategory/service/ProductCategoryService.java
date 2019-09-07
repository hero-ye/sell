package com.hero.sell.business.productcategory.service;

import com.hero.sell.business.productcategory.dao.ProductCategoryDao;
import com.hero.sell.entities.ProductCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
     * 根据ID查询(2.0后不能使用findOne()了)
     * @param categoryId
     * @return
     */
    public ProductCategory findById(String categoryId) {
        ProductCategory productCategory = productCategoryDao.findById(categoryId).get();
//        ProductCategory productCategory = productCategoryDao.getOne(categoryId);
        return productCategory;
    }

    /**
     * 根据商品名称查询
     * @param categoryName
     * @return
     */
    public List<ProductCategory> findByCategoryName(String categoryName) {
        return productCategoryDao.findByCategoryName(categoryName);
    }

    /**
     * 根据商品类目编码查询
     * @return
     */
    public List<ProductCategory> findByCategoryCodeIn(List<Integer> categoryCodeList){
        return productCategoryDao.findByCategoryCodeIn(categoryCodeList);
    }

    /**
     * 新增/更新
     * @param productCategory
     */
    public void saveOrUpdate(ProductCategory productCategory){
        if (StringUtils.isEmpty(productCategory.getCategoryId())) {  //新增
            productCategory.setCreateTime(new Date());
            productCategory.setModifyTime(new Date());
        } else {    //更新
            productCategory.setModifyTime(new Date());
        }
        productCategoryDao.save(productCategory);
    }

    /**
     * 删除
     * @param categoryId
     */
    public void deleteById(String categoryId) {
        productCategoryDao.deleteById(categoryId);
    }

}
