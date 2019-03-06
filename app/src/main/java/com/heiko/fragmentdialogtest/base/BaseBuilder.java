package com.heiko.fragmentdialogtest.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;

/**
 * @author Heiko
 * @date 2019/3/6
 */
public class BaseBuilder {
    protected Context context;
    protected CharSequence title;
    protected CharSequence content;
    protected int imgResId;
    protected int layoutId;
    protected CharSequence negativeText;
    protected CharSequence neutralText;
    protected CharSequence positiveText;
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

    //input
    protected CharSequence inputHint;
    protected CharSequence inputPrefill;
    protected boolean inputAllowEmpty;
    protected InputCallback inputCallback;
}
