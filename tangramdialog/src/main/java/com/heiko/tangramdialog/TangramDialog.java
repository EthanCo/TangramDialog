package com.heiko.tangramdialog;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * TangramDialog
 *
 * @author Heiko
 * @date 2019/3/4
 */
public class TangramDialog extends DialogBase {

    private View tvTopBar;
    private TextView tvTitle;
    private TextView tvContent;
    private TextView tvTips;
    private TextView tvNegative;
    private TextView tvPositive;
    private TextView tvNeutral;
    private ImageView imgInfo;
    private EditText etInput;
    private ViewGroup layoutNegative;
    private ViewGroup layoutPositive;
    private ViewGroup layoutNeutral;

    public EditText getInputEditText() {
        return etInput;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        if (root == null) return null;

        tvTopBar = root.findViewById(R.id.tv_top_bar);
        tvTitle = root.findViewById(R.id.tv_title_tangram);
        tvContent = root.findViewById(R.id.tv_content_tangram);
        tvTips = root.findViewById(R.id.tv_tips_tangram);
        imgInfo = root.findViewById(R.id.img_info_tangram);
        tvNegative = root.findViewById(R.id.tv_negative_tangram);
        layoutNegative = root.findViewById(R.id.layout_negative_tangram);
        tvPositive = root.findViewById(R.id.tv_positive_tangram);
        layoutPositive = root.findViewById(R.id.layout_positive_tangram);
        tvNeutral = root.findViewById(R.id.tv_neutral_tangram);
        layoutNeutral = root.findViewById(R.id.layout_neutral_tangram);
        etInput = root.findViewById(R.id.et_input_tangram);

        if (builder == null) return root;
        if (builder.paddingLeft != 0 || builder.paddingTop != 0
                || builder.paddingRight != 0 || builder.paddingBottom != 0) {
            root.setPadding(builder.paddingLeft, builder.paddingTop,
                    builder.paddingRight, builder.paddingBottom);
        }
        if (tvTopBar != null) {
            tvTopBar.setVisibility(builder.topBarVisibility);
        }
        if (tvTitle != null) {
            if (TextUtils.isEmpty(builder.title)) {
                tvTitle.setVisibility(View.GONE);
            } else {
                tvTitle.setText(builder.title);
                if (builder.titleColor != null) {
                    tvTitle.setTextColor(builder.titleColor);
                }
                if (builder.titleTextSize > 0) {
                    tvTitle.setTextSize(builder.titleTextSize);
                }
                if (builder.titleTextStyle != null) {
                    tvTitle.setTypeface(Typeface.defaultFromStyle(builder.titleTextStyle));
                }
            }
        }
        if (tvContent != null) {
            if (TextUtils.isEmpty(builder.content)) {
                tvContent.setVisibility(View.GONE);
            } else {
                tvContent.setText(builder.content);
                if (builder.contentColor != null) {
                    tvContent.setTextColor(builder.contentColor);
                }
                if (builder.contentTextSize > 0) {
                    tvContent.setTextSize(builder.contentTextSize);
                }
                if (builder.contentTextStyle != null) {
                    tvContent.setTypeface(Typeface.defaultFromStyle(builder.contentTextStyle));
                }
                if (builder.contentTextGravity != null) {
                    tvContent.setGravity(builder.contentTextGravity);
                }
            }
        }
        if (tvTips != null) {
            if (TextUtils.isEmpty(builder.tips)) {
                tvTips.setVisibility(View.GONE);
            } else {
                tvTips.setText(builder.tips);
                if (builder.tipsColor != null) {
                    tvTips.setTextColor(builder.tipsColor);
                }
                if (builder.tipsTextSize > 0) {
                    tvTips.setTextSize(builder.tipsTextSize);
                }
                if (builder.tipsTextStyle != null) {
                    tvTips.setTypeface(Typeface.defaultFromStyle(builder.tipsTextStyle));
                }
                if (builder.onTipsCallback != null) {
                    tvTips.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (checkEmptyInput()) return;
                            if (builder.onTipsCallback != null) {
                                builder.onTipsCallback.onClick(TangramDialog.this, tvTips);
                            }
                            if (builder.autoDismiss) {
                                dismiss();
                            }
                        }
                    });
                }
            }
        }
        if (imgInfo != null) {
            if (builder.imgRes != 0) {
                imgInfo.setVisibility(View.VISIBLE);
                imgInfo.setImageResource(builder.imgRes);
            }
        }
        if (etInput != null) {
            if (builder.inputAllowEmpty == null) {
                etInput.setVisibility(View.GONE);
            } else {
                etInput.setVisibility(View.VISIBLE);
                etInput.setHint(builder.inputHint == null ? "" : builder.inputHint);
                CharSequence text = builder.inputPrefill == null ? "" : builder.inputPrefill;
                etInput.setText(text);
                etInput.setSelection(text.length());
                etInput.addTextChangedListener(new DialogTextWatcher(TangramDialog.this, builder));
            }
        }
        if (isNoButton()) {
            View viewDividerHorizontal = root.findViewById(R.id.view_line_horizontal_tangram);
            View layoutButtons = root.findViewById(R.id.layout_buttons_tangram);
            if (viewDividerHorizontal != null) {
                viewDividerHorizontal.setVisibility(View.INVISIBLE);
            }
            if (layoutButtons != null) {
                layoutButtons.setVisibility(View.GONE);
            }
        } else {
            if (layoutNegative != null) {
                if (TextUtils.isEmpty(builder.negativeText)) {
                    layoutNegative.setVisibility(View.GONE);
                } else {
                    if (tvNegative != null) {
                        tvNegative.setText(builder.negativeText);
                    }
                    if (builder.negativeTextColor != null) {
                        tvNegative.setTextColor(builder.negativeTextColor);
                    }
                    if (builder.negativeTextSize != null) {
                        tvNegative.setTextSize(builder.negativeTextSize);
                    }
                    if (builder.negativeTextStyle != null) {
                        tvNegative.setTypeface(Typeface.defaultFromStyle(builder.negativeTextStyle));
                    }
                }
                if (layoutNegative.getVisibility() == View.VISIBLE) {
                    layoutNegative.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (checkEmptyInput()) return;
                            if (builder.onNegativeCallback != null) {
                                builder.onNegativeCallback.onClick(TangramDialog.this, layoutNegative);
                            }
                            if (builder.autoDismiss) {
                                dismiss();
                            }
                        }
                    });
                }
            }
            if (layoutNeutral != null) {
                View lineVertical1 = root.findViewById(R.id.view_line_vertical_1_tangram);
                if (TextUtils.isEmpty(builder.neutralText)) {
                    if (lineVertical1 != null) {
                        lineVertical1.setVisibility(View.GONE);
                    }
                    layoutNeutral.setVisibility(View.GONE);
                } else {
                    if (lineVertical1 != null) {
                        lineVertical1.setVisibility(View.VISIBLE);
                    }
                    if (tvNeutral != null) {
                        tvNeutral.setText(builder.neutralText);
                    }
                    if (builder.neutralTextColor != null) {
                        tvNeutral.setTextColor(builder.neutralTextColor);
                    }
                    if (builder.neutralTextSize != null) {
                        tvNeutral.setTextSize(builder.neutralTextSize);
                    }
                    if (builder.neutralTextStyle != null) {
                        tvNeutral.setTypeface(Typeface.defaultFromStyle(builder.neutralTextStyle));
                    }
                }
                if (layoutNeutral.getVisibility() == View.VISIBLE) {
                    layoutNeutral.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (checkEmptyInput()) return;
                            if (builder.onNeutralCallback != null) {
                                builder.onNeutralCallback.onClick(TangramDialog.this, layoutNeutral);
                            }
                            if (builder.autoDismiss) {
                                dismiss();
                            }
                        }
                    });
                }
            }
            if (layoutPositive != null) {
                View lineVertical2 = root.findViewById(R.id.view_line_vertical_2_tangram);
                if (TextUtils.isEmpty(builder.positiveText)) {
                    if (lineVertical2 != null) {
                        lineVertical2.setVisibility(View.GONE);
                    }
                    layoutPositive.setVisibility(View.GONE);
                } else {
                    if (lineVertical2 != null) {
                        lineVertical2.setVisibility(View.VISIBLE);
                    }
                    if (tvPositive != null) {
                        tvPositive.setText(builder.positiveText);
                    }
                    if (builder.positiveTextColor != null) {
                        tvPositive.setTextColor(builder.positiveTextColor);
                    }
                    if (builder.positiveTextSize != null) {
                        tvPositive.setTextSize(builder.positiveTextSize);
                    }
                    if (builder.positiveTextStyle != null) {
                        tvPositive.setTypeface(Typeface.defaultFromStyle(builder.positiveTextStyle));
                    }
                }
                if (layoutPositive.getVisibility() == View.VISIBLE) {
                    layoutPositive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (checkEmptyInput()) return;
                            if (builder.onPositiveCallback != null) {
                                builder.onPositiveCallback.onClick(TangramDialog.this, layoutPositive);
                            }
                            if (builder.autoDismiss) {
                                dismiss();
                            }
                        }
                    });
                }
            }
        }
        return root;
    }

    private boolean isNoButton() {
        return TextUtils.isEmpty(builder.positiveText)
                && TextUtils.isEmpty(builder.negativeText)
                && TextUtils.isEmpty(builder.neutralText);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (builder != null && builder.inputCallback != null) {
            DialogUtils.showKeyboard(this, builder);
            if (etInput.getText().length() > 0) {
                etInput.setSelection(etInput.getText().length());
            }
        }
    }

    @Override
    public void dismiss() {
        if (builder != null && builder.inputCallback != null) {
            DialogUtils.hideKeyboard(this, builder);
        }
        super.dismiss();
    }

    private boolean checkEmptyInput() {
        if (builder != null || builder.inputAllowEmpty == null) return false;
        return !builder.inputAllowEmpty
                && TextUtils.isEmpty(etInput.getText().toString());
    }

    public static class Builder extends BaseBuilder {
        public Builder(Context context) {
            this.context = context;
        }

        /**
         * 设置标题
         *
         * @param title
         * @return
         */
        public Builder title(CharSequence title) {
            this.title = title;
            return this;
        }

        private String getStringWrapper(int res) {
            if (context == null) {
                onError(TAG, "getString content is NULL.");
                return "";
            }
            return context.getString(res);
        }

        /**
         * 设置标题
         *
         * @param titleRes
         * @return
         */
        public Builder title(@StringRes int titleRes) {
            this.title = getStringWrapper(titleRes);
            return this;
        }

        /**
         * 设置标题TextStyle
         *
         * @param style Typeface.NORMAL, Typeface.BOLD, Typeface.ITALIC, Typeface.BOLD_ITALIC
         * @return
         */
        public Builder titleTextStyle(int style) {
            this.titleTextStyle = style;
            return this;
        }

        /**
         * 标题颜色
         *
         * @param colorRes
         * @return
         */
        public Builder titleColorRes(@ColorRes int colorRes) {
            this.titleColor = DialogUtils.getColor(this.context, colorRes);
            return this;
        }

        /**
         * 标题字体大小
         *
         * @param textSize
         * @return
         */
        public Builder titleTextSize(float textSize) {
            this.titleTextSize = textSize;
            return this;
        }

        /**
         * 内容文字
         *
         * @param content
         * @return
         */
        public Builder content(CharSequence content) {
            this.content = content;
            return this;
        }

        /**
         * 内容文字
         *
         * @param contentRes
         * @return
         */
        public Builder content(@StringRes int contentRes) {
            this.content = getStringWrapper(contentRes);
            return this;
        }

        /**
         * 内容文字TextStyle
         *
         * @param style Typeface.NORMAL, Typeface.BOLD, Typeface.ITALIC, Typeface.BOLD_ITALIC
         * @return
         */
        public Builder contentTextStyle(int style) {
            this.contentTextStyle = style;
            return this;
        }

        /**
         * 内容文字对齐方式
         *
         * @param gravity
         * @return
         */
        public Builder contentTextGraviry(int gravity) {
            this.contentTextGravity = gravity;
            return this;
        }

        /**
         * 内容文字颜色
         *
         * @param colorRes
         * @return
         */
        public Builder contentColorRes(@ColorRes int colorRes) {
            this.contentColor = DialogUtils.getColor(this.context, colorRes);
            return this;
        }

        /**
         * 内容文字字体
         *
         * @param textSize
         * @return
         */
        public Builder contentTextSize(float textSize) {
            this.contentTextSize = textSize;
            return this;
        }

        /**
         * 次要内容文字
         *
         * @return
         */
        public Builder tips(CharSequence content) {
            this.tips = content;
            return this;
        }

        /**
         * 次要内容文字
         *
         * @param contentRes
         * @return
         */
        public Builder tips(@StringRes int contentRes) {
            this.tips = getStringWrapper(contentRes);
            return this;
        }

        /**
         * 次要内容TextStyle
         *
         * @param style Typeface.NORMAL, Typeface.BOLD, Typeface.ITALIC, Typeface.BOLD_ITALIC
         * @return
         */
        public Builder tipsTextStyle(int style) {
            this.tipsTextStyle = style;
            return this;
        }

        /**
         * 次要内容文字颜色
         *
         * @param colorRes
         * @return
         */
        public Builder tipsColorRes(@ColorRes int colorRes) {
            this.tipsColor = DialogUtils.getColor(this.context, colorRes);
            return this;
        }

        /**
         * 次要内容文字字体
         *
         * @param textSize
         * @return
         */
        public Builder tipsTextSize(float textSize) {
            this.tipsTextSize = textSize;
            return this;
        }

        /**
         * 图片资源ID
         *
         * @param imgRes
         * @return
         */
        public Builder imgRes(@DrawableRes int imgRes) {
            this.imgRes = imgRes;
            return this;
        }

        /**
         * 对话框布局，用作替换默认布局样式
         * (id需要和默认布局保持一致，可使用其他通用方法)
         *
         * @param layoutId
         * @return
         */
        public Builder layoutId(int layoutId) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            this.layoutIdView = inflater.inflate(layoutId, null);
            return this;
        }

        /**
         * 对话框布局，用作替换默认布局样式
         * (id需要和默认布局保持一致，可使用其他通用方法)
         *
         * @param root
         * @return
         */
        public Builder layoutId(View root) {
            this.layoutIdView = root;
            return this;
        }

        /**
         * 否定的按钮文字
         *
         * @param message
         * @return
         */
        public Builder negativeText(CharSequence message) {
            this.negativeText = message;
            return this;
        }

        /**
         * 否定的按钮文字
         *
         * @param messageRes
         * @return
         */
        public Builder negativeText(@StringRes int messageRes) {
            this.negativeText = getStringWrapper(messageRes);
            return this;
        }

        /**
         * 否定的按钮字体大小
         *
         * @param textSize
         * @return
         */
        public Builder negativeTextSize(int textSize) {
            this.negativeTextSize = textSize;
            return this;
        }

        /**
         * 否定的按钮文字TextStyle
         *
         * @param style Typeface.NORMAL, Typeface.BOLD, Typeface.ITALIC, Typeface.BOLD_ITALIC
         * @return
         */
        public Builder negativeTextStyle(int style) {
            this.negativeTextStyle = style;
            return this;
        }

        /**
         * 否定的按钮文字颜色
         *
         * @param textColor
         * @return
         */
        public Builder negativeTextColor(@ColorRes int textColor) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                this.negativeTextColor = context.getColor(textColor);
            } else {
                this.negativeTextColor = context.getResources().getColor(textColor);
            }
            return this;
        }


        /**
         * 中立的按钮文字
         *
         * @param message
         * @return
         */
        public Builder neutralText(CharSequence message) {
            this.neutralText = message;
            return this;
        }

        /**
         * 中立的按钮文字
         *
         * @param messageRes
         * @return
         */
        public Builder neutralText(@StringRes int messageRes) {
            this.neutralText = getStringWrapper(messageRes);
            return this;
        }

        /**
         * 中立的按钮字体大小
         *
         * @param textSize
         * @return
         */
        public Builder neutralTextSize(int textSize) {
            this.negativeTextSize = textSize;
            return this;
        }

        /**
         * 中立的按钮文字TextStyle
         *
         * @param style Typeface.NORMAL, Typeface.BOLD, Typeface.ITALIC, Typeface.BOLD_ITALIC
         * @return
         */
        public Builder neutralTextStyle(int style) {
            this.neutralTextStyle = style;
            return this;
        }

        /**
         * 中立的按钮文字颜色
         *
         * @param textColor
         * @return
         */
        public Builder neutralTextColor(@ColorRes int textColor) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                this.neutralTextColor = context.getColor(textColor);
            } else {
                this.neutralTextColor = context.getResources().getColor(textColor);
            }
            return this;
        }

        /**
         * 肯定的按钮文字
         *
         * @param message
         * @return
         */
        public Builder positiveText(CharSequence message) {
            this.positiveText = message;
            return this;
        }

        /**
         * 肯定的按钮文字
         *
         * @param messageRes
         * @return
         */
        public Builder positiveText(@StringRes int messageRes) {
            this.positiveText = getStringWrapper(messageRes);
            return this;
        }

        /**
         * 肯定的按钮字体大小
         *
         * @param textSize
         * @return
         */
        public Builder positiveTextSize(int textSize) {
            this.positiveTextSize = textSize;
            return this;
        }

        /**
         * 肯定的按钮文字
         *
         * @param style Typeface.NORMAL, Typeface.BOLD, Typeface.ITALIC, Typeface.BOLD_ITALIC
         * @return
         */
        public Builder positiveTextStyle(int style) {
            this.positiveTextStyle = style;
            return this;
        }

        /**
         * 肯定的按钮文字颜色
         *
         * @param textColor
         * @return
         */
        public Builder positiveTextColor(@ColorRes int textColor) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                this.positiveTextColor = context.getColor(textColor);
            } else {
                this.positiveTextColor = context.getResources().getColor(textColor);
            }
            return this;
        }

        /**
         * 对话框位置
         *
         * @param gravity {@link android.view.Gravity#TOP}:顶部
         *                {@link android.view.Gravity#CENTER}:居中
         *                {@link android.view.Gravity#BOTTOM}:底部
         * @return
         */
        public Builder gravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        /**
         * 对话框 显示/隐藏 动画
         *
         * @param animStyle
         * @return
         */
        public Builder animStyle(int animStyle) {
            this.animStyle = animStyle;
            return this;
        }

        /**
         * 指定对话框宽度
         *
         * @param width
         * @return
         */
        public Builder width(int width) {
            this.width = width;
            return this;
        }

        /**
         * 指定对话框高度
         *
         * @param height
         * @return
         */
        public Builder height(int height) {
            this.height = height;
            return this;
        }

        /**
         * 肯定的按钮 点击回调
         *
         * @param callback
         * @return
         */
        public Builder onPositive(@NonNull ButtonCallback callback) {
            this.onPositiveCallback = callback;
            return this;
        }

        /**
         * 中立的按钮 点击回调
         *
         * @param callback
         * @return
         */
        public Builder onNeutral(@NonNull ButtonCallback callback) {
            this.onNeutralCallback = callback;
            return this;
        }

        /**
         * 否定的按钮 点击回调
         *
         * @param callback
         * @return
         */
        public Builder onNegative(@NonNull ButtonCallback callback) {
            this.onNegativeCallback = callback;
            return this;
        }

        /**
         * Tips文本 点击回调
         *
         * @param callback
         * @return
         */
        public Builder onTips(@NonNull ButtonCallback callback) {
            this.onTipsCallback = callback;
            return this;
        }

        /**
         * 对话框外围 点击是否可隐藏
         *
         * @param canceledOnTouchOutside
         * @return
         */
        public Builder canceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        /**
         * 对话框 margin - 需要结合background有效
         *
         * @param margin
         * @return
         */
        public Builder margin(int margin) {
            this.margin = margin;
            return this;
        }

        /**
         * 背景的昏暗度
         *
         * @param dimAmount
         * @return
         */
        public Builder dimAmount(@FloatRange(from = 0, to = 1) float dimAmount) {
            this.dimAmount = dimAmount;
            return this;
        }

        /**
         * 设置背景
         * 例如 new ColorDrawable(Color.BLUE) //蓝色
         * new ColorDrawable(Color.TRANSPARENT) //透明
         *
         * @param drawable
         * @return
         */
        public Builder background(Drawable drawable) {
            this.backgroundDrawable = drawable;
            return this;
        }

        /**
         * 设置背景色
         *
         * @param backgroundColor
         * @return
         */
        public Builder backgroundColor(@ColorRes int backgroundColor) {
            this.backgroundDrawable = new ColorDrawable(DialogUtils.getColor(context, backgroundColor));
            return this;
        }

        /**
         * 设置自定义的View
         * 通过{@link DialogBase#getRootView()}获取根View
         *
         * @param customView
         * @return
         */
        public Builder customView(View customView) {
            this.customView = customView;
            return this;
        }

        /**
         * 设置自定义的View
         * 通过{@link DialogBase#getRootView()}获取根View
         *
         * @param layoutRes
         * @return
         */
        public Builder customView(@LayoutRes int layoutRes) {
            this.customView = LayoutInflater.from(context).inflate(layoutRes, null);
            return this;
        }

        /**
         * 设置顶部的自定义的View
         * 通过{@link DialogBase#getRootView()}获取根View
         *
         * @param layoutRes
         * @return
         */
        public Builder customTopView(@LayoutRes int layoutRes) {
            this.customTopView = LayoutInflater.from(context).inflate(layoutRes, null);
            return this;
        }

        /**
         * 设置顶部的自定义的View
         * 通过{@link DialogBase#getRootView()}获取根View
         *
         * @param customView
         * @return
         */
        public Builder customTopView(View customView) {
            this.customTopView = customView;
            return this;
        }

        /**
         * 设置顶部的自定义的View
         * 通过{@link DialogBase#getRootView()}获取根View
         *
         * @param layoutRes
         * @return
         */
        public Builder customBottomView(@LayoutRes int layoutRes) {
            this.customBottomView = LayoutInflater.from(context).inflate(layoutRes, null);
            return this;
        }

        /**
         * 设置底部的自定义的View
         * 通过{@link DialogBase#getRootView()}获取根View
         *
         * @param customView
         * @return
         */
        public Builder customBottomView(View customView) {
            this.customBottomView = customView;
            return this;
        }


        /**
         * 点击按钮是否自动隐藏对话框
         *
         * @param dismiss
         * @return
         */
        public Builder autoDismiss(boolean dismiss) {
            this.autoDismiss = dismiss;
            return this;
        }

        /**
         * 对话框相对自身位置X轴的偏移量
         *
         * @param offsetX
         * @return
         */
        public Builder offsetX(int offsetX) {
            this.offsetX = offsetX;
            return this;
        }

        /**
         * 对话框相对自身位置Y轴的偏移量
         *
         * @param offsetY
         * @return
         */
        public Builder offsetY(int offsetY) {
            this.offsetY = offsetY;
            return this;
        }

        /**
         * 点击背景 点击事件是否穿透
         *
         * @param canPenetrate
         * @return
         */
        public Builder canPenetrate(boolean canPenetrate) {
            this.canPenetrate = canPenetrate;
            return this;
        }

//        /**
//         * @Deprecated 低版本系统会有问题
//         *
//         * 设置 Window Type (对话框层级优先级)
//         * WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW (层级最后)
//         * WindowManager.LayoutParams.TYPE_APPLICATION
//         * WindowManager.LayoutParams.LAST_APPLICATION_WINDOW (层级最前)
//         *
//         * @param windowType
//         * @return
//         */
//        @Deprecated
//        public Builder windowType(int windowType) {
//            this.windowType = windowType;
//            return this;
//        }

        /**
         * 显示输入框
         *
         * @param hint     hint文字
         * @param prefill  预加载的文字
         * @param callback 输入改变回调
         * @return
         */
        public Builder input(
                @Nullable CharSequence hint,
                @Nullable CharSequence prefill,
                @NonNull InputCallback callback) {
            return input(hint, prefill, true, callback);
        }

        /**
         * 显示输入框
         *
         * @param hint            hint文字
         * @param prefill         预加载的文字
         * @param allowEmptyInput 是否允许为空
         * @param callback        输入改变回调
         * @return
         */
        public Builder input(
                @Nullable CharSequence hint,
                @Nullable CharSequence prefill,
                boolean allowEmptyInput,
                @NonNull InputCallback callback) {
            this.inputHint = hint;
            this.inputPrefill = prefill;
            this.inputAllowEmpty = allowEmptyInput;
            this.inputCallback = callback;
            return this;
        }

        /**
         * 显示输入框
         *
         * @param hint            hint文字
         * @param prefill         预加载的文字
         * @param allowEmptyInput 是否允许为空
         * @param callback        输入改变回调
         * @return
         */
        public Builder input(
                @StringRes int hint,
                @StringRes int prefill,
                boolean allowEmptyInput,
                @NonNull InputCallback callback) {
            return input(
                    hint == 0 ? null : this.context.getText(hint),
                    prefill == 0 ? null : this.context.getText(prefill),
                    allowEmptyInput,
                    callback);
        }

        /**
         * 显示输入框
         *
         * @param hint     hint文字
         * @param prefill  预加载的文字
         * @param callback 输入改变回调
         * @return
         */
        public Builder input(
                @StringRes int hint, @StringRes int prefill, @NonNull InputCallback callback) {
            return input(hint, prefill, true, callback);
        }

        /**
         * 内部Padding
         *
         * @param padding 单位为DP
         * @return
         */
        public Builder padding(int padding) {
            int paddingPx = DialogUtils.dp2px(this.context, padding);
            this.paddingLeft = paddingPx;
            this.paddingTop = paddingPx;
            this.paddingRight = paddingPx;
            this.paddingBottom = paddingPx;
            return this;
        }

        /**
         * 内部Padding
         *
         * @param paddingLeft   单位为DP
         * @param paddingTop    单位为DP
         * @param paddingRight  单位为DP
         * @param paddingBottom 单位为DP
         * @return
         */
        public Builder padding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
            this.paddingLeft = DialogUtils.dp2px(this.context, paddingLeft);
            this.paddingTop = DialogUtils.dp2px(this.context, paddingTop);
            this.paddingRight = DialogUtils.dp2px(this.context, paddingRight);
            this.paddingBottom = DialogUtils.dp2px(this.context, paddingBottom);
            return this;
        }

        /**
         * 顶部横条是否可见
         *
         * @param visibility
         * @return
         */
        public Builder topBarVisibility(int visibility) {
            this.topBarVisibility = visibility;
            return this;
        }

        public TangramDialog show() {
            final TangramDialog dialog = new TangramDialog();
            dialog.builder = this;
            if (this.layoutIdView != null) {
                dialog.rootView = this.layoutIdView;
            } else {
                LayoutInflater inflater = LayoutInflater.from(context);
                dialog.rootView = inflater.inflate(R.layout.dialog_tangram, null);
            }
            View rootView = dialog.rootView;
            if (customView != null) {
                LinearLayout customRoot = rootView.findViewById(R.id.layout_tangram_custom);
                customRoot.addView(this.customView);
                customRoot.setVisibility(View.VISIBLE);
            }
            if (customTopView != null) {
                LinearLayout customRoot = rootView.findViewById(R.id.layout_tangram_custom_top);
                customRoot.addView(this.customTopView);
                customRoot.setVisibility(View.VISIBLE);
            }
            if (customBottomView != null) {
                LinearLayout customRoot = rootView.findViewById(R.id.layout_tangram_custom_bottom);
                customRoot.addView(this.customBottomView);
                customRoot.setVisibility(View.VISIBLE);
            }
            if (this.context instanceof FragmentActivity) {
                dialog.show((FragmentActivity) this.context);
            } else {
                //throw new IllegalArgumentException("content must be FragmentActivity.");
                onError(TAG, "content must be FragmentActivity.");
            }

            /*final View view = rootView;
            rootView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    float y = event.getRawY();
                    float lastY = 0;
                    float offsetY = 0;
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            lastY = event.getRawY(); //记录手指按下位置
                            break;
                        case MotionEvent.ACTION_MOVE:
                            offsetY = y - lastY;  //记录Y轴移动的距离
                            if (offsetY > 0) {
                                ViewCompat.setTranslationY(view, offsetY); //跟随手指滑动，向下移动View
                            }
                            break;
                        case MotionEvent.ACTION_UP://手指离开
                            if (offsetY > 0) {
                                if (offsetY < view.getHeight() / 3) {//移动距离 < View高度 / 3
                                    ViewCompat.setTranslationY(view, 0);  //回到初始位置
                                } else {
                                    *//*
             *关闭View
             *注：这里dismiss是DialogFragment中的一个方法，用于关闭DialogFragment
             *//*
                                    dialog.dismiss();
                                }
                            }
                            break;
                        default:
                    }
                    return true;
                }
            });*/
            return dialog;
        }
    }
}
