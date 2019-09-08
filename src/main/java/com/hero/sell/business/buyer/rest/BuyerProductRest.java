package com.hero.sell.business.buyer.rest;

import com.hero.sell.business.buyer.service.BuyerProductService;
import com.hero.sell.vo.ProductVO;
import com.hero.sell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/1
 */
@RestController
@RequestMapping("/buyer/product")
@Slf4j
public class BuyerProductRest {

    @Resource(name = "buyerProductService")
    private BuyerProductService buyerProductService;

    /**
     * 获取上架商品列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultVO list() {
        ResultVO resultVO = new ResultVO();
        try {
            List<ProductVO> productVOList = buyerProductService.list();
            resultVO.setCode(0);
            resultVO.setMsg("查询成功！");
            resultVO.setData(productVOList);
        } catch (Exception e) {
            resultVO.setCode(1);
            resultVO.setMsg("查询失败：" + e.getMessage());
            log.error("查询失败：" + e.getMessage());
        }
        return resultVO;
    }


}
