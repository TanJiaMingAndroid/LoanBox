package com.ps.eachgold.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by 8146 on 2018/2/3.
 */

public class MyUtil {

    //格式化数据 不要0
    public static String formatToseparano0(String data) {
        BigDecimal b = new BigDecimal(data);
        DecimalFormat df = new DecimalFormat("#");
        df.setRoundingMode(RoundingMode.FLOOR);
        return df.format(b);
    }
}
