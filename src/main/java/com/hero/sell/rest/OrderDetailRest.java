package com.hero.sell.rest;

import com.hero.sell.service.OrderDetailService;
import com.hero.sell.entities.OrderDetail;
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
 * @Date 2019/9/2
 */
@RestController
@RequestMapping("/orderdetail")
@Slf4j
public class OrderDetailRest {

    @Resource(name = "orderDetailService")
    private OrderDetailService orderDetailService;

    /**
     * 根据orderId查询
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/findByOrderId", method = RequestMethod.GET)
    public ResultVO findByOrderId(String orderId) {
        ResultVO resultVO = new ResultVO();
        try {
            List<OrderDetail> orderDetailList = orderDetailService.findByOrderId(orderId);
            resultVO.setCode(0);
            resultVO.setMsg("查询成功！");
            resultVO.setData(orderDetailList);
        }catch (Exception e) {
            resultVO.setCode(1);
            resultVO.setMsg("查询失败：" + e.getMessage());
            log.error("查询失败：" + e.getMessage());
        }
        return resultVO;
    }

    /**
     * 新增或更新
     * @param orderDetail
     * @return
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public ResultVO saveOrUpdate(OrderDetail orderDetail) {
        ResultVO resultVO = new ResultVO();
        try {
            orderDetailService.saveOrUpdate(orderDetail);
            resultVO.setCode(0);
            resultVO.setMsg("保存成功！");
        }catch (Exception e) {
            resultVO.setCode(1);
            resultVO.setMsg("保存失败：" + e.getMessage());
            log.error("保存失败：" + e.getMessage());
        }
        return resultVO;
    }

}
