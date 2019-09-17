package com.hero.sell.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Description json格式化
 * @Author yejx
 * @Date 2019/9/17
 */
public class JsonUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }

}
