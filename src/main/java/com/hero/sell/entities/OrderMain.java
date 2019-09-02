package com.hero.sell.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/2
 */
@Entity
@Data
@Table(name = "tbl_order_main")
public class OrderMain {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "order_id", length = 40, nullable = false, unique = true)
    private String orderId;  //订单ID

    @Column(name = "buyer_name", length = 40)
    private String buyerName;     //买家姓名

    @Column(name = "buyer_phone", length = 40)
    private String buyerPhone;    //买家电话

    @Column(name = "buyer_address", length = 100)
    private String buyerAddress;      //买家地址

    @Column(name = "buyer_openid", length = 64)
    private String buyerOpenid;     //买家微信openid

    @Column(name = "order_amount", length = 8)
    private BigDecimal orderAmount;     //订单总金额

    @Column(name = "order_status", length = 3)
    private Integer orderStatus;     //订单状态：(默认)0 新下单

    @Column(name = "pay_status", length = 3)
    private Integer payStatus;   //支付状态：(默认)0 未支付

    @Column(name = "create_name", length = 40)
    private String createName;   //创建人

    @Column(name = "create_time")
    private Date createTime;   //创建时间

    @Column(name = "modify_name", length = 40)
    private String modifyName;  //修改人

    @Column(name = "modify_time")
    private Date modifyTime;   //修改时间

}
