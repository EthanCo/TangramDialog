package com.heiko.tangramdialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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

    private TextView tvTipsTitle;
    private TextView tvTipsContent;
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
        if (root == null) return root;

        tvTipsTitle = root.findViewById(R.id.tv_title_tangram);
        tvTipsContent = root.findViewById(R.id.tv_content_tangram);
        imgInfo = root.findViewById(R.id.img_info_tangram);
        tvNegative = root.findViewById(R.id.tv_negative_tangram);
        layoutNegative = root.findViewById(R.id.layout_negative_tangram);
        tvPositive = root.findViewById(R.id.tv_positive_tangram);
        layoutPositive = root.findViewById(R.id.layout_positive_tangram);
        tvNeutral = root.findViewById(R.id.tv_neutral_tangram);
        layoutNeutral = root.findViewById(R.id.layout_neutral_tangram);
        etInput = root.findViewById(R.id.et_input_tangram);

        if (builder == null) return root;
        if (tvTipsTitle != null) {
            if (TextUtils.isEmpty(builder.title)) {
                tvTipsTitle.setVisibility(View.GONE);
            } else {
                tvTipsTitle.setText(builder.title);
                if (builder.titleColor != null) {
                    tvTipsTitle.setTextColor(builder.titleColor);
                }
                if (builder.titleTextSize > 0) {
                    tvTipsTitle.setTextSize(builder.titleTextSize);
                }
            }
        }
        if (tvTipsContent != null) {
            if (TextUtils.isEmpty(builder.content)) {
                tvTipsContent.setVisibility(View.GONE);
            } else {
                tvTipsContent.setText(builder.content);
                if (builder.contentColor != null) {
                    tvTipsContent.setTextColor(builder.contentColor);
                }
                if (builder.contentTextSize > 0) {
                    tvTipsContent.setTextSize(builder.contentTextSize);
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
            this.layoutId = R.layout.dialog_tangram;
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

        /**
         * 设置标题
         *
         * @param titleRes
         * @return
         */
        public Builder title(@StringRes int titleRes) {
            this.title = context.getString(titleRes);
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
            this.content = context.getString(contentRes);
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
         * 对话框布局，用作替换默认布局样式，id需要和默认布局保持一致
         *
         * @param layoutId
         * @return
         */
        public Builder layoutId(int layoutId) {
            this.layoutId = layoutId;
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
            this.negativeText = context.getString(messageRes);
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
            this.neutralText = context.getString(messageRes);
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
            this.positiveText = context.getString(messageRes);
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
         * 设置自定义的View，此模式下需手动findViewById来设置UI及相关点击回调
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
         * 设置自定义的View，此模式下需手动findViewById来设置UI及相关点击回调
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

        public TangramDialog show() {
            TangramDialog dialog = new TangramDialog();
            dialog.builder = this;
            LayoutInflater inflater = LayoutInflater.from(context);
            dialog.rootView = inflater.inflate(this.layoutId, null);
            if (customView != null) {
                LinearLayout customRoot = dialog.rootView.findViewById(R.id.layout_tangram_custom);
                customRoot.addView(this.customView);
            }
            if (this.context instanceof FragmentActivity) {
                dialog.show((FragmentActivity) this.context);
            } else {
                throw new IllegalArgumentException("content must be FragmentActivity.");
            }

            return dialog;
        }
    }
}
