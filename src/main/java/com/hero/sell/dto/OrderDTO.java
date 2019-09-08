package com.hero.sell.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hero.sell.entities.OrderDetail;
import com.hero.sell.utils.serializer.DateToLongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/4
 */
@Data
public class OrderDTO {
    private String orderId;  //订单ID

    private String buyerName;     //买家姓名

    private String buyerPhone;    //买家电话

    private String buyerAddress;      //买家地址

    private String buyerOpenid;     //买家微信openid

    private BigDecimal orderAmount;     //订单总金额

    private Integer orderStatus;     //订单状态：(默认)0 新下单

    private Integer payStatus;   //支付状态：(默认)0 未支付

    private String createName;   //创建人

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;   //创建时间

    private String modifyName;  //修改人

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date modifyTime;   //修改时间

    private List<OrderDetail> orderDetailList;  //一张订单对应多个商品
}
