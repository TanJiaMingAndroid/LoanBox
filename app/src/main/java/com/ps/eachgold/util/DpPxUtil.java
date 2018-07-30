package com.ps.eachgold.util;

import android.content.Context;

/**
 * Created by 8146 on 2018/1/15.
 */

public class DpPxUtil {
        /**
         * dpתpx
         *
         */
        public static int dip2px(Context ctx, float dpValue) {
            final float scale = ctx.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        }


        /**
         *	pxתdp
         */
        public static int px2dip(Context ctx,float pxValue) {
            final float scale = ctx.getResources().getDisplayMetrics().density;
            return (int) (pxValue / scale + 0.5f);
        }
}
