package com.hero.sell.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 商品类目
 * @Description
 * @Author yejx
 * @Date 2019/8/25
 */
@Entity
@DynamicUpdate
@Table(name = "tbl_product_category")
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class ProductCategory {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "category_id", length = 40, nullable = false, unique = true)
    private String categoryId;  //主键ID

    @Column(name = "category_name", length = 80)
    private String categoryName;    //类目名称

    @Column(name = "category_code", length = 11, unique = true)
    private Integer categoryCode;    //类目编号

    @Column(name = "create_name", length = 40)
    private String createName;   //创建人

    @Column(name = "create_time")
    private Date createTime;   //创建时间

    @Column(name = "modify_name", length = 40)
    private String modifyName;  //修改人

    @Column(name = "modify_time")
    private Date modifyTime;   //修改时间

}
