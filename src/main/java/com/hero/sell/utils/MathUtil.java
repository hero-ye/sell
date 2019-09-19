package com.hero.sell.utils;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/19
 */
public class MathUtil {

    private static final Double MONEY_RANGE = 0.01;

    /**
     * 比较金额是否相等
     * @param d1
     * @param d2
     * @return
     */
    public static boolean equals(Double d1, Double d2) {
        Double result = Math.abs(d1 - d2);
        if (result < MONEY_RANGE) {
            return true;
        } else {
            return false;
        }
    }

}
