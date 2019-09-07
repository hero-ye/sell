package com.hero.sell.dto;

import lombok.Data;

/**
 * 购物车
 * @Description
 * @Author yejx
 * @Date 2019/9/7
 */
@Data
public class CartDTO {

    private String productId;   //商品ID

    private Integer productQuantity;    //商品数量

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
