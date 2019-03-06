package com.heiko.fragmentdialogtest.base;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Utils
 *
 * @author Heiko
 * @date 2019/3/4
 */
public class Utils {
    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
