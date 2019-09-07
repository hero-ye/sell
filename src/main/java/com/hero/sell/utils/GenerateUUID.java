package com.hero.sell.utils;

import java.util.UUID;

/**
 * UUID
 * @Description
 * @Author yejx
 * @Date 2019/9/7
 */
public class GenerateUUID {
    /**
     * 返回UUID字符串
     * @return
     */
    public static String getUuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
