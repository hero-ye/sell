package com.hero.sell.rest;

import com.hero.sell.service.OrderMainService;
import com.hero.sell.entities.OrderMain;
import com.hero.sell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/2
 */
@RestController
@RequestMapping("/ordermain")
@Slf4j
public class OrderMainRest {

    @Resource(name = "orderMainService")
    private OrderMainService orderMainService;

    /**
     * 新增/更新商品
     * @param orderMain
     * @return
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public ResultVO saveOrUpdate(OrderMain orderMain) {
        ResultVO resultVO = new ResultVO();
        try {
            String result = orderMainService.saveOrUpdate(orderMain);
            if ("1".equals(result)) {
                resultVO.setCode(1);
                resultVO.setMsg("保存失败！");
                log.error("【保存订单】保存失败, orderMain={}", orderMain);
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

    /**
     * 根据openId查询
     *
     * @param openId
     * @return
     */
    @RequestMapping(value = "/findByBuyerOpenid", method = RequestMethod.GET)
    public ResultVO findByBuyerOpenid(@RequestParam("openid") String openId,
                                      @RequestParam(value = "page", defaultValue = "0") Integer page,
                                      @RequestParam(value = "size", defaultValue = "10") Integer size) {
        ResultVO resultVO = new ResultVO();
        //PageRequest pageRequest = new PageRequest(page, size);
        PageRequest pageRequest = PageRequest.of(page, size);
        try {
            Page<OrderMain> orderMainPage = orderMainService.findByBuyerOpenid(openId, pageRequest);
            resultVO.setCode(0);
            resultVO.setMsg("查询成功！");
            resultVO.setData(orderMainPage);
        } catch (Exception e) {
            resultVO.setCode(1);
            resultVO.setMsg("查询失败！");
            log.error("查询失败：" + e.getMessage());
        }
        return resultVO;
    }

    /**
     * 根据主键ID查询
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public ResultVO findById(String orderId) {
        ResultVO resultVO = new ResultVO();
        try {
            OrderMain orderMain = orderMainService.findById(orderId);
            resultVO.setCode(0);
            resultVO.setMsg("查询成功！");
            resultVO.setData(orderMain);
        } catch (Exception e) {
            resultVO.setCode(1);
            resultVO.setMsg("查询失败：" + e.getMessage());
            log.error("查询失败：" + e.getMessage());
        }
        return resultVO;
    }

}
