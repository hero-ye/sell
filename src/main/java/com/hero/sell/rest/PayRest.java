package com.hero.sell.rest;

import com.hero.sell.dto.OrderDTO;
import com.hero.sell.enums.ResultEnum;
import com.hero.sell.exception.SellException;
import com.hero.sell.service.OrderService;
import com.hero.sell.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description 支付
 * @Author yejx
 * @Date 2019/9/17
 */
@RestController
@RequestMapping("/pay")
@Slf4j
public class PayRest {

    @Resource(name = "orderService")
    private OrderService orderService;

    @Resource(name = "payService")
    private PayService payService;



    /**
     * 发起支付
     *
     * @param orderId
     * @param returnUrl
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView creata(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> param) {
        //1.查询订单
        OrderDTO orderDTO = orderService.findOrderDTOById(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //2.发起支付
        try {
            PayResponse payResponse = payService.create(orderDTO);
            param.put("payResponse", payResponse);
        } catch (Exception e) {
            PayResponse payResponse1 = new PayResponse();
            payResponse1.setAppId("wxd898fcb01713c658");
            payResponse1.setTimeStamp(System.currentTimeMillis() + "");
            payResponse1.setNonceStr("bVsQpcfsKUAzO8r0");
            payResponse1.setPackAge("prepay_id=wx2019091911112036b51eaddc0529394957");
            payResponse1.setPaySign("78CA85306AB823156E1032EFB5BB1C76");
            log.error(e.getMessage());
            param.put("payResponse", payResponse1);
        }
        //支付成功后跳转的URL
        param.put("returnUrl", returnUrl);
        return new ModelAndView("pay/create", param);
    }

    /**
     * 异步通知
     * @param notifyData
     */
    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    public ModelAndView notify(@RequestBody String notifyData) {
        //异步通知
        payService.notify(notifyData);

        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }

}
