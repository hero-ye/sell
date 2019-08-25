package com.hero.sell.entities;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
public class ProductCategory {

    @Id
    private String categoryId;  //主键ID

    private String categoryName;    //类目名称

    private Integer categoryCode;    //类目编号

    private String createName;   //创建人

    private Date createTime;   //创建时间

    private String modifyName;  //修改人

    private Date modifyTime;   //修改时间

}
