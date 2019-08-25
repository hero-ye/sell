package com.hero.sell.entities;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
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
public class ProductCategory implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "category_id", length = 40, nullable = false, unique = true)
    private String categoryId;  //主键ID

    @Column(name = "category_name", length = 80, nullable = false)
    private String categoryName;    //类目名称

    @Column(name = "category_code", length = 11, nullable = false, unique = true)
    private Integer categoryCode;    //类目编号

    @Column(name = "create_name", length = 40)
    private String cretaName;   //创建人

    @Column(name = "create_time")
    private Date createTime;   //创建时间

    @Column(name = "modify_name", length = 40)
    private String modifyName;  //修改人

    @Column(name = "modify_time")
    private Date modifyTime;   //修改时间

    private String getCategoryId(){
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(Integer categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCretaName() {
        return cretaName;
    }

    public void setCretaName(String cretaName) {
        this.cretaName = cretaName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryCode=" + categoryCode +
                ", cretaName='" + cretaName + '\'' +
                ", createTime=" + createTime +
                ", modifyName='" + modifyName + '\'' +
                ", modifyTime=" + modifyTime +
                '}';
    }

}
