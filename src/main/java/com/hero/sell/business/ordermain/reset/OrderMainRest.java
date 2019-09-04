package com.hero.sell.business.ordermain.reset;

import com.hero.sell.business.ordermain.service.OrderMainService;
import com.hero.sell.common.vo.ResultVO;
import com.hero.sell.entities.OrderMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/2
 */
@RestController
@RequestMapping("/ordermain")
public class OrderMainRest {

    private static final Logger log = LoggerFactory.getLogger(OrderMainRest.class);

    @Resource(name = "orderMainService")
    private OrderMainService orderMainService;

    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public ResultVO saveOrUpdate(OrderMain orderMain){
        ResultVO resultVO = new ResultVO();
        try {
            orderMain.setCreateTime(new Date());
            orderMain.setModifyTime(new Date());
            orderMainService.saveOrUpdate(orderMain);
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
     * @param openId
     * @return
     */
    @RequestMapping(value = "/findByBuyerOpenid/{page}/{size}", method = RequestMethod.GET)
    public ResultVO findByBuyerOpenid(String openId, @PathVariable Integer page, @PathVariable Integer size){
        ResultVO resultVO = new ResultVO();
        page = page == null ? 1 : page;
        size = size == null ? 10 : size;
        try {
            Page<OrderMain> orderMainPage = orderMainService.findByBuyerOpenid(openId, page, size);
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
