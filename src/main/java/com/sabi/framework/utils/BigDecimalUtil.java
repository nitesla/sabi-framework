package com.sabi.framework.utils;

import java.math.BigDecimal;

public class BigDecimalUtil {

    public static BigDecimal addBigDecimal(BigDecimal value1,BigDecimal value2) {
        if(value1 == null && value2 == null) return BigDecimal.ZERO;
        if(value1 == null) return value2;
        if(value2 == null) return  value1;
        BigDecimal result = value1.add(value2);
        return result;
    }
}
