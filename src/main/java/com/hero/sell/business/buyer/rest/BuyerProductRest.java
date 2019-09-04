package com.hero.sell.business.buyer.rest;

import com.hero.sell.business.productcategory.service.ProductCategoryService;
import com.hero.sell.business.productinfo.service.ProductInfoSerivce;
import com.hero.sell.vo.ProductInfoVO;
import com.hero.sell.vo.ProductVO;
import com.hero.sell.vo.ResultVO;
import com.hero.sell.entities.ProductCategory;
import com.hero.sell.entities.ProductInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/1
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductRest {

    private static final Logger log = LoggerFactory.getLogger(BuyerProductRest.class);

    @Resource(name = "productInfoSerivce")
    private ProductInfoSerivce productInfoSerivce;

    @Resource(name = "productCategoryService")
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultVO list() {
        ResultVO resultVO = new ResultVO();
        try {
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
            resultVO.setCode(0);
            resultVO.setMsg("查询成功！");
            resultVO.setData(productVOList);
        } catch (Exception e) {
            resultVO.setCode(1);
            resultVO.setMsg("查询成功失败：" + e.getMessage());
            log.error("查询成功失败：" + e.getMessage());
        }

        return resultVO;
    }


}
