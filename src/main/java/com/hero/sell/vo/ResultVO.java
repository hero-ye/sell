package com.hero.sell.vo;

import lombok.Data;

/**
 * http请求返回的结果对象
 * @Description
 * @Author yejx
 * @Date 2019/9/1
 */
@Data
public class ResultVO<T> {

    private Integer code;   //状态码

    private String msg;     //提示信息

    private T data;         //返回的具体内容

    public ResultVO() {
    }

    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
