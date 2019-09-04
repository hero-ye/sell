package com.hero.sell.exception;

import com.hero.sell.enums.ResultEnum;

/**
 * 异常类
 * @Description
 * @Author yejx
 * @Date 2019/9/4
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }
}
