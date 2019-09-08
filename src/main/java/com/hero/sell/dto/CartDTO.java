package com.hero.sell.dto;

import lombok.Data;

/**
 * 购物车
 * @Description
 * @Author yejx
 * @Date 2019/9/7
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_EMPTY)   //如果某个属性为null，则查询结果不显示，全局设置在application.yml中
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
