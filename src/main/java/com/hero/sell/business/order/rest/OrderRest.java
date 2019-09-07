package com.hero.sell.business.order.rest;

import com.hero.sell.business.order.service.OrderService;
import com.hero.sell.dto.OrderDTO;
import com.hero.sell.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/7
 */
@RestController
@RequestMapping("/orderRest")
public class OrderRest {

    private static final Logger log = LoggerFactory.getLogger(OrderRest.class);

    @Resource(name = "orderService")
    private OrderService orderService;

    /**
     * 创建订单
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResultVO create(OrderDTO orderDTO) {
        ResultVO resultVO = new ResultVO();
        try {
            orderService.create(orderDTO);
            resultVO.setCode(0);
            resultVO.setMsg("保存成功！");
        } catch (Exception e) {
            resultVO.setCode(1);
            resultVO.setMsg("保存失败：" + e.getMessage());
            log.error("保存失败：" + e.getMessage());
        }
        return resultVO;
    }


}
