package com.hero.sell.business.productinfo.service;

import com.hero.sell.business.productinfo.dao.ProductInfoDao;
import com.hero.sell.dto.CartDTO;
import com.hero.sell.entities.ProductInfo;
import com.hero.sell.enums.ProductInfoEnum;
import com.hero.sell.enums.ResultEnum;
import com.hero.sell.exception.SellException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 商品信息业务层
 * @Description
 * @Author yejx
 * @Date 2019/9/1
 */
@Service("productInfoSerivce")
public class ProductInfoSerivce {

    @Autowired
    private ProductInfoDao productInfoDao;

    /**
     * 条件查询+分页
     * @param param
     * @param page
     * @param size
     * @return
     */
    public Page<ProductInfo> findAllPage(Map<String, Object> param, Integer page, Integer size){
        Specification<ProductInfo> specification = createSpecification(param);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return productInfoDao.findAll(specification, pageRequest);
    }

    /**
     *  查询所有在架商品列表
     * @return
     */
    public List<ProductInfo> findUpAll(){
        return productInfoDao.findByProductStatus(ProductInfoEnum.UP.getCode());
    }

    /**
     * 根据主键ID查询
     * @param productId
     * @return
     */
    public ProductInfo findById(String productId) {
        return productInfoDao.findById(productId).get();
    }

    /**
     * 新增/更新
     * @param productInfo
     */
    public String saveOrUpdate(ProductInfo productInfo){
        if (StringUtils.isEmpty(productInfo.getProductId())) {  //新增
            productInfo.setCreateTime(new Date());
            productInfo.setModifyTime(new Date());
        } else {    //更新
            productInfo.setModifyTime(new Date());
        }
        ProductInfo result = productInfoDao.save(productInfo);
        if (result == null) {
            return "1";
        }
        return "2";
    }

    /**
     * 加库存
     * @param cartDTOList
     */
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList){
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoDao.findById(cartDTO.getProductId()).get();
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);  //商品不存在
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            this.saveOrUpdate(productInfo);
        }
    }

    /**
     * 减库存
     * @param cartDTOList
     */
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList){
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoDao.findById(cartDTO.getProductId()).get();
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);  //商品不存在
            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {   //判断扣减后的库存
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoDao.save(productInfo);
        }
    }

/**___________________________________________________分 割 线________________________________________________________*/

    /**
     * 动态条件构建
     * @param param
     * @return
     */
    private Specification<ProductInfo> createSpecification(Map<String, Object> param){
        return new Specification<ProductInfo>() {
            @Override
            public Predicate toPredicate(Root<ProductInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 商品ID
                if (param.get("productId") != null && !"".equals(param.get("productId"))) {
                    predicateList.add(cb.like(root.get("productId").as(String.class), "%" + (String) param.get("productId") + "%"));
                }
                // 商品名称
                if (param.get("productName") != null && !"".equals(param.get("productName"))) {
                    predicateList.add(cb.like(root.get("productName").as(String.class), "%" + (String) param.get("productName") + "%"));
                }
                // 单价
                if (param.get("productPrice") != null && !"".equals(param.get("productPrice"))) {
                    predicateList.add(cb.like(root.get("productPrice").as(String.class), "%" + (String) param.get("productPrice") + "%"));
                }
                // 库存
                if (param.get("productStock") != null && !"".equals(param.get("productStock"))) {
                    predicateList.add(cb.like(root.get("productStock").as(String.class), "%" + (String) param.get("productStock") + "%"));
                }
                // 描述
                if (param.get("productDesc") != null && !"".equals(param.get("productDesc"))) {
                    predicateList.add(cb.like(root.get("productDesc").as(String.class), "%" + (String) param.get("productDesc") + "%"));
                }
                // 商品图片
                if (param.get("productIcon") != null && !"".equals(param.get("productIcon"))) {
                    predicateList.add(cb.like(root.get("productIcon").as(String.class), "%" + (String) param.get("productIcon") + "%"));
                }
                // 商品状态
                if (param.get("productStatus") != null && !"".equals(param.get("productStatus"))) {
                    predicateList.add(cb.like(root.get("productStatus").as(String.class), "%" + (String) param.get("productStatus") + "%"));
                }
                // 类目编码
                if (param.get("categoryCode") != null && !"".equals(param.get("categoryCode"))) {
                    predicateList.add(cb.like(root.get("categoryCode").as(String.class), "%" + (String) param.get("categoryCode") + "%"));
                }
                // 创建人
                if (param.get("createName") != null && !"".equals(param.get("createName"))) {
                    predicateList.add(cb.like(root.get("createName").as(String.class), "%" + (String) param.get("createName") + "%"));
                }
                // 创建时间
                if (param.get("createTime") != null && !"".equals(param.get("createTime"))) {
                    predicateList.add(cb.like(root.get("createTime").as(String.class), "%" + (String) param.get("createTime") + "%"));
                }
                // 修改人
                if (param.get("modifyName") != null && !"".equals(param.get("modifyName"))) {
                    predicateList.add(cb.like(root.get("modifyName").as(String.class), "%" + (String) param.get("modifyName") + "%"));
                }
                // 修改时间
                if (param.get("modifyTime") != null && !"".equals(param.get("modifyTime"))) {
                    predicateList.add(cb.like(root.get("modifyTime").as(String.class), "%" + (String) param.get("modifyTime") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

}
