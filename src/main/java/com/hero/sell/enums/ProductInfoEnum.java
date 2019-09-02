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

    UP(0, "在架"), DOWN(1, "下架");

    private Integer code;

    private String message;

    ProductInfoEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
