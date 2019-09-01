package com.hero.sell.business.productinfo.rest;

import com.hero.sell.business.productinfo.service.ProductInfoSerivce;
import com.hero.sell.entities.ProductInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 商品信息控制器层
 *
 * @Description
 * @Author yejx
 * @Date 2019/9/1
 */
@RestController
@RequestMapping("/productInfo")
public class ProductInfoRest {

    private static final Logger log = LoggerFactory.getLogger(ProductInfoRest.class);

    @Resource(name = "productInfoSerivce")
    private ProductInfoSerivce productInfoSerivce;

    /**
     * 分页条件查询
     * @param param
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/findAllPage/{page}/{size}", method = RequestMethod.POST)
    public Page<ProductInfo> findAllPage(@RequestParam Map<String, Object> param,
                                         @PathVariable Integer page,
                                         @PathVariable Integer size) {
        Page<ProductInfo> productInfoPage = null;
        page = page == null ? 1 : page;
        size = size == null ? 10 : size;
        try {
            productInfoPage = productInfoSerivce.findAllPage(param, page, size);
        } catch (Exception e) {
            log.error("查询出错：" + e.getMessage());
        }
        return productInfoPage;
    }

    /**
     * 查询所有在架商品列表
     * @return
     */
    @RequestMapping(value = "/findUpAll", method = RequestMethod.GET)
    public List<ProductInfo> findUpAll(){
        List<ProductInfo> productInfoList = null;
        try {
            productInfoList = productInfoSerivce.findUpAll();
        } catch (Exception e) {
            log.error("查询出错：" + e.getMessage());
        }
        return productInfoList;
    }

    /**
     * 保存或更新
     * @param productInfo
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public void saveOrUpdate(ProductInfo productInfo){
        try {
            productInfoSerivce.saveOrUpdate(productInfo);
        } catch (Exception e) {
            log.error("新增或更新失败：" + e.getMessage());
        }
    }

}
