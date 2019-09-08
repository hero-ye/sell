package com.hero.sell.business.buyer.service;

import com.hero.sell.business.productcategory.service.ProductCategoryService;
import com.hero.sell.business.productinfo.service.ProductInfoSerivce;
import com.hero.sell.entities.ProductCategory;
import com.hero.sell.entities.ProductInfo;
import com.hero.sell.vo.ProductInfoVO;
import com.hero.sell.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/8
 */
@Service("buyerProductService")
public class BuyerProductService {

    @Resource(name = "productInfoSerivce")
    private ProductInfoSerivce productInfoSerivce;

    @Resource(name = "productCategoryService")
    private ProductCategoryService productCategoryService;

    /**
     * 获取上架商品列表
     * @return
     */
    public List<ProductVO> list(){
        //1.查询所有的上架商品
        List<ProductInfo> productInfoList = productInfoSerivce.findUpAll();

        //2.查询类目（一次性查询）

        //传统方法
        /*List<Integer> categoryTypeList = new ArrayList<>();
        for (ProductInfo productInfo : productInfoList) {
            categoryTypeList.add(productInfo.getCategoryCode());
        }*/
        //精简方法（Java8, lambda）
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryCode()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryCodeIn(categoryTypeList);

        //3.数据拼接
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryCode(productCategory.getCategoryCode());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryCode().equals(productCategory.getCategoryCode())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();

                    //传统方法
                        /*productInfoVO.setProductId(productInfo.getProductId());
                        productInfoVO.setProductName(productInfo.getProductName());
                        productInfoVO.setProductPrice(productInfo.getProductPrice());
                        productInfoVO.setProductDesc(productInfo.getProductDesc());
                        productInfoVO.setProductIcon(productInfo.getProductIcon());*/

                    //精简方法：将“productInfo”的属性复制到“productInfoVO”
                    BeanUtils.copyProperties(productInfo, productInfoVO);

                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVO(productInfoVOList);
            productVOList.add(productVO);
        }
        return productVOList;
    }

}
