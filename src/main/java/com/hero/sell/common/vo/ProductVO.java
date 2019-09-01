package com.hero.sell.common.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品（包含类目）
 * @Description
 * @Author yejx
 * @Date 2019/9/1
 */
@Data
public class ProductVO {

    /**
     * @JsonProperty：该注解作用为前端用“name”接收该值时，将“categoryName”映射为“name”，与前端对应
     */
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryCode;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVO;
}
