package com.hero.sell.converter;

import com.hero.sell.dto.OrderDTO;
import com.hero.sell.entities.OrderMain;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 将OrderMain转换为OrderDTO
 *
 * @Description
 * @Author yejx
 * @Date 2019/9/7
 */
public class OrderMainToOrderDTOConverter {

    /**
     * OrderMain转为OrderDTO
     * @param orderMain
     * @return
     */
    public static OrderDTO convert(OrderMain orderMain) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMain, orderDTO);
        return orderDTO;
    }

    /**
     * List<OrderMain>转为List<OrderDTO>
     * @param orderMainList
     * @return
     */
    public static List<OrderDTO> convert(List<OrderMain> orderMainList) {
        return orderMainList.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
