package com.hero.sell.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hero.sell.utils.serializer.DateToLongSerializer;
import lombok.Data;
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
//@DynamicUpdate
@Table(name = "tbl_product_category")
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
//@JsonInclude(JsonInclude.Include.NON_EMPTY)   //如果某个属性为null，则查询结果不显示，全局设置在application.yml中
public class ProductCategory {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "category_id", length = 40, nullable = false, unique = true)
    private String categoryId;  //类目ID

    @Column(name = "category_name", length = 80)
    private String categoryName;    //类目名称

    @Column(name = "category_code", length = 11, unique = true)
    private Integer categoryCode;    //类目编号

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
