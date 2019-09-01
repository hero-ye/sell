package com.hero.sell.enums;

import lombok.Getter;

/**
 * 枚举：商品状态
 * @Description
 * @Author yejx
 * @Date 2019/9/1
 */
@Getter
public enum ProductInfoEnum {

    up("0", "在架"), down("1", "下架");

    private String code;

    private String message;

    ProductInfoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
