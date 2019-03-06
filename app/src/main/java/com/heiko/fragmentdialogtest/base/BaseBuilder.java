package com.heiko.fragmentdialogtest.base;

import android.graphics.drawable.Drawable;
import android.view.Gravity;

/**
 * @author Heiko
 * @date 2019/3/6
 */
public class BaseBuilder {
    protected String title;
    protected String content;
    protected int imgResId;
    protected int layoutId;
    protected String negativeText;
    protected String neutralText;
    protected String positiveText;
    protected int gravity = Gravity.CENTER;
    protected int margin;
    protected int width;
    protected int height;
    protected int animStyle;
    protected float dimAmount = 0.5f;//背景昏暗度
    protected Drawable backgroundDrawable; //背景
    protected boolean canceledOnTouchOutside;
    protected SingleButtonCallback onPositiveCallback;
    protected SingleButtonCallback onNegativeCallback;
    protected SingleButtonCallback onNeutralCallback;
}
