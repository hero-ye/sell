package com.hero.sell.business.productinfo.rest;

import com.hero.sell.business.productinfo.service.ProductInfoSerivce;
import com.hero.sell.entities.ProductInfo;
import com.hero.sell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ProductInfoRest {

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
    public ResultVO saveOrUpdate(ProductInfo productInfo){
        ResultVO resultVO = new ResultVO();
        try {
            String result = productInfoSerivce.saveOrUpdate(productInfo);
            if ("1".equals(result)) {
                resultVO.setCode(1);
                resultVO.setMsg("保存失败！");
                log.error("保存失败, productInfo={}", productInfo);
                return resultVO;
            }
            resultVO.setCode(0);
            resultVO.setMsg("保存成功！");
        } catch (Exception e) {
            resultVO.setCode(1);
            resultVO.setMsg("保存失败！");
            log.error("保存失败：" + e.getMessage());
        }
        return resultVO;
    }

}
