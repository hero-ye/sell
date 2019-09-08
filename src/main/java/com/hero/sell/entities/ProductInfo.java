package com.hero.sell.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hero.sell.utils.serializer.DateToLongSerializer;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息
 * @Description
 * @Author yejx
 * @Date 2019/9/1
 */
@Entity
@Data
@Table(name = "tbl_product_info")
//@JsonInclude(JsonInclude.Include.NON_EMPTY)   //如果某个属性为null，则查询结果不显示，全局设置在application.yml中
public class ProductInfo {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "product_id", length = 40, nullable = false, unique = true)
    private String productId;  //商品ID

    @Column(name = "product_name", length = 11)
    private String productName;     //商品名称

    @Column(name = "product_price", length = 8)
    private BigDecimal productPrice;    //单价

    @Column(name = "product_stock", length = 11)
    private Integer productStock;      //库存

    @Column(name = "product_desc", length = 100)
    private String productDesc;     //描述

    @Column(name = "product_icon", length = 512)
    private String productIcon;     //商品图片

    @Column(name = "product_status", length = 3)
    private Integer productStatus;     //商品状态：0 正常，1 下架

    @Column(name = "category_code", length = 11)
    private Integer categoryCode;   //类目编号

    @Column(name = "create_name", length = 40)
    private String createName;   //创建人

    @Column(name = "create_time")
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;   //创建时间

    @Column(name = "modify_name", length = 40)
    private String modifyName;  //修改人

    @Column(name = "modify_time")
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date modifyTime;   //修改时间

}
