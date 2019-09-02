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
@Table(name = "tbl_order_detail")
public class OrderDetail {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "detail_id", length = 40, nullable = false, unique = true)
    private String detailId;  //详情ID

    @Column(name = "order_id", length = 40)
    private String orderId;     //订单ID

    @Column(name = "product_id", length = 40)
    private String productId;    //商品ID

    @Column(name = "product_name", length = 80)
    private String productName;      //商品名称

    @Column(name = "product_price", length = 8)
    private BigDecimal productPrice;     //商品价格

    @Column(name = "product_quantity", length = 11)
    private Integer productQuantity;     //商品数量

    @Column(name = "product_icon", length = 512)
    private String productIcon;     //商品图片

    @Column(name = "create_name", length = 40)
    private String createName;   //创建人

    @Column(name = "create_time")
    private Date createTime;   //创建时间

    @Column(name = "modify_name", length = 40)
    private String modifyName;  //修改人

    @Column(name = "modify_time")
    private Date modifyTime;   //修改时间

}
