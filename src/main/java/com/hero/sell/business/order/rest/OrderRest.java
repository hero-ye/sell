package com.hero.sell.business.order.rest;

import com.hero.sell.business.order.service.OrderService;
import com.hero.sell.dto.OrderDTO;
import com.hero.sell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/7
 */
@RestController
@RequestMapping("/orderRest")
@Slf4j
public class OrderRest {

    @Resource(name = "orderService")
    private OrderService orderService;

    /**
     * 创建订单
     *
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

    /**
     * 查询单个订单
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/findOrderDTOById", method = RequestMethod.GET)
    public ResultVO findOrderDTOById(@RequestParam String orderId) {
        ResultVO resultVO = new ResultVO();
        try {
            OrderDTO orderDTO = orderService.findOrderDTOById(orderId);
            resultVO.setCode(0);
            resultVO.setMsg("查询成功！");
            resultVO.setData(orderDTO);
        } catch (Exception e) {
            resultVO.setCode(1);
            resultVO.setMsg("查询成功：" + e.getMessage());
            log.error("查询成功：" + e.getMessage());
        }
        return resultVO;
    }

    /**
     * 分页查询订单
     * @param buyerOpenId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/findOrderList/{page}/{size}", method = RequestMethod.GET)
    public ResultVO findOrderList(String buyerOpenId, @PathVariable Integer page, @PathVariable Integer size) {
        ResultVO resultVO = new ResultVO();
        page = page == null ? 1 : page;
        size = size == null ? 10 : size;
        PageRequest pageRequest = new PageRequest(page, size);
        try {
            Page<OrderDTO> orderDTOPage = orderService.findOrderList(buyerOpenId, pageRequest);
            resultVO.setCode(0);
            resultVO.setMsg("查询成功！");
            resultVO.setData(orderDTOPage);
        } catch (Exception e) {
            resultVO.setCode(1);
            resultVO.setMsg("查询失败：" + e.getMessage());
            log.error("查询失败：" + e.getMessage());
        }
        return resultVO;
    }

    /**
     * 取消订单
     * @param orderDTO
     * @return
     */
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public ResultVO cancel(OrderDTO orderDTO){
        ResultVO resultVO = new ResultVO();
        try {
            orderService.cancel(orderDTO);
            resultVO.setCode(0);
            resultVO.setMsg("取消成功！");
        } catch (Exception e) {
            resultVO.setCode(1);
            resultVO.setMsg("取消失败：" + e.getMessage());
            log.error("取消失败：" + e.getMessage());
        }
        return resultVO;
    }

    /**
     * 完结订单
     * @param orderDTO
     * @return
     */
    @RequestMapping(value = "/finish", method = RequestMethod.POST)
    public ResultVO finish(OrderDTO orderDTO){
        ResultVO resultVO = new ResultVO();
        try {
            orderService.finish(orderDTO);
            resultVO.setCode(0);
            resultVO.setMsg("订单完成！");
        } catch (Exception e) {
            resultVO.setCode(1);
            resultVO.setMsg("完结失败：" + e.getMessage());
            log.error("完结失败：" + e.getMessage());
        }
        return resultVO;
    }

    /**
     * 支付订单
     * @param orderDTO
     * @return
     */
    @RequestMapping(value = "/paid", method = RequestMethod.POST)
    public ResultVO paid(OrderDTO orderDTO){
        ResultVO resultVO = new ResultVO();
        try {
            orderService.paid(orderDTO);
            resultVO.setCode(0);
            resultVO.setMsg("支付完成！");
        } catch (Exception e) {
            resultVO.setCode(1);
            resultVO.setMsg("支付失败：" + e.getMessage());
            log.error("支付失败：" + e.getMessage());
        }
        return resultVO;
    }

}
