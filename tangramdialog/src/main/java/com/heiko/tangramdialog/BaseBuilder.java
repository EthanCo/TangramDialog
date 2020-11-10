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
    protected Integer titleTextStyle;
    protected Integer titleColor;
    protected float titleTextSize;
    protected CharSequence content;
    protected Integer contentTextStyle;
    protected Integer contentTextGravity;
    protected Integer contentColor;
    protected float contentTextSize;
    protected CharSequence tips;
    protected Integer tipsTextStyle;
    protected Integer tipsColor;
    protected float tipsTextSize;
    protected int imgRes;
    protected View layoutIdView;
    protected CharSequence negativeText;
    protected CharSequence neutralText;
    protected CharSequence positiveText;
    protected Integer negativeTextSize;
    protected Integer neutralTextSize;
    protected Integer positiveTextSize;
    protected Integer negativeTextStyle;
    protected Integer neutralTextStyle;
    protected Integer positiveTextStyle;
    protected Integer negativeTextColor;
    protected Integer neutralTextColor;
    protected Integer positiveTextColor;
    protected int gravity = Gravity.CENTER;
    protected Integer margin;
    protected int topBarVisibility = View.VISIBLE;
    protected int width;
    protected int height;
    protected int animStyle;
    protected Float dimAmount;//背景昏暗度
    protected Drawable backgroundDrawable; //背景
    protected boolean canceledOnTouchOutside = true;
    protected boolean canPenetrate; //点击背景穿透
    protected boolean autoDismiss = true;
    protected View customView;
    protected View customTopView;
    protected View customBottomView;

    protected ButtonCallback onPositiveCallback;
    protected ButtonCallback onNegativeCallback;
    protected ButtonCallback onNeutralCallback;

    //input
    protected CharSequence inputHint;
    protected CharSequence inputPrefill;
    protected Boolean inputAllowEmpty;
    protected InputCallback inputCallback;

    //对话框相对自身位置的偏移量
    protected Integer offsetX;
    protected Integer offsetY;

    //Padding
    protected int paddingLeft;
    protected int paddingRight;
    protected int paddingTop;
    protected int paddingBottom;
}
