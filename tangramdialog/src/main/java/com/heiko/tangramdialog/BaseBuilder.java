package com.heiko.tangramdialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;

/**
 * @author Heiko
 * @date 2019/3/6
 */
public class BaseBuilder {
    protected Context context;
    protected CharSequence title;
    protected Integer titleColor;
    protected float titleTextSize;
    protected CharSequence content;
    protected Integer contentColor;
    protected float contentTextSize;
    protected int imgRes;
    protected int layoutId;
    protected CharSequence negativeText;
    protected CharSequence neutralText;
    protected CharSequence positiveText;
    protected int gravity = Gravity.CENTER;
    protected Integer margin;
    protected int width;
    protected int height;
    protected int animStyle;
    protected Float dimAmount;//背景昏暗度
    protected Drawable backgroundDrawable; //背景
    protected boolean canceledOnTouchOutside;
    protected boolean canPenetrate; //点击背景穿透
    protected View customView;

    protected ButtonCallback onPositiveCallback;
    protected ButtonCallback onNegativeCallback;
    protected ButtonCallback onNeutralCallback;

    //input
    protected CharSequence inputHint;
    protected CharSequence inputPrefill;
    protected boolean inputAllowEmpty = true;
    protected InputCallback inputCallback;
}
