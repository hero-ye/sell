package com.hero.sell.enums;

import lombok.Getter;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/4
 */
@Getter
public enum ResultEnum {
    PARAM_ERROER(1, "参数不正确"),

    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "商品库存错误"),
    ORDER_NOT_EXIST(12, "订单不存在"),
    ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),
    ORDER_STATUS_ERROR(14, "订单状态不正确"),
    ORDER_UPDATE_FAIL(15, "订单更新失败"),
    ORDER_DETAILD_EMPTY(16, "订单中无商品"),
    ORDER_PAY_STATUS_ERROR(17, "订单支付状态不正确"),
    CART_EMPTY(18, "购物车为空"),
    ORDER_OWNER_ERROR(19, "该订单不属于当前用户"),
    WECHAT_MP_ERROR(20, "微信公众账号相关错误"),
    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21, "微信支付异步通知金额校验不通过"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
