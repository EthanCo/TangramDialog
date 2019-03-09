package com.heiko.tangramdialog;

import android.content.Context;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * TangramDialog
 *
 * @author Heiko
 * @date 2019/3/4
 */
public class TangramDialog extends BaseDialog {

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

        Log.i("TipsDialog", "builder:" + builder);
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
            if (builder.inputCallback == null) {
                etInput.setVisibility(View.GONE);
            } else {
                etInput.setVisibility(View.VISIBLE);
                etInput.setHint(builder.inputHint == null ? "" : builder.inputHint);
                etInput.setText(builder.inputPrefill == null ? "" : builder.inputPrefill);
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
                                builder.onNegativeCallback.onClick(TangramDialog.this, DialogAction.NEGATIVE);
                            }
                            dismiss();
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
                                builder.onNeutralCallback.onClick(TangramDialog.this, DialogAction.NEUTRAL);
                            }
                            dismiss();
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
                                builder.onPositiveCallback.onClick(TangramDialog.this, DialogAction.POSITIVE);
                            }
                            dismiss();
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

        if (builder.inputCallback != null) {
            DialogUtils.showKeyboard(this, builder);
            if (etInput.getText().length() > 0) {
                etInput.setSelection(etInput.getText().length());
            }
        }
    }

    @Override
    public void dismiss() {
        if (builder.inputCallback != null) {
            DialogUtils.hideKeyboard(this, builder);
        }
        super.dismiss();
    }

    private boolean checkEmptyInput() {
        return !builder.inputAllowEmpty && TextUtils.isEmpty(etInput.getText().toString());
    }

    public static class Builder extends BaseBuilder {
        public Builder(Context context) {
            this.layoutId = R.layout.dialog_tangram;
            this.context = context;
        }

        public Builder title(CharSequence title) {
            this.title = title;
            return this;
        }

        public Builder titleColorRes(@ColorRes int colorRes) {
            this.titleColor = DialogUtils.getColor(this.context, colorRes);
            return this;
        }

        public Builder titleTextSize(float textSize) {
            this.titleTextSize = textSize;
            return this;
        }

        public Builder content(CharSequence content) {
            this.content = content;
            return this;
        }

        public Builder contentColorRes(@ColorRes int colorRes) {
            this.contentColor = DialogUtils.getColor(this.context, colorRes);
            return this;
        }

        public Builder contentTextSize(float textSize) {
            this.contentTextSize = textSize;
            return this;
        }

        public Builder imgRes(@DrawableRes int imgRes) {
            this.imgRes = imgRes;
            return this;
        }

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
         * 肯定的按钮文字
         *
         * @param message
         * @return
         */
        public Builder positiveText(CharSequence message) {
            this.positiveText = message;
            return this;
        }

        public Builder gravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder animStyle(int animStyle) {
            this.animStyle = animStyle;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder onPositive(@NonNull ButtonCallback callback) {
            this.onPositiveCallback = callback;
            return this;
        }

        public Builder onNeutral(@NonNull ButtonCallback callback) {
            this.onNeutralCallback = callback;
            return this;
        }

        public Builder onNegative(@NonNull ButtonCallback callback) {
            this.onNegativeCallback = callback;
            return this;
        }

        public Builder canceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public Builder margin(int margin) {
            this.margin = margin;
            return this;
        }

        public Builder dimAmount(@FloatRange(from = 0, to = 1) float dimAmount) {
            this.dimAmount = dimAmount;
            return this;
        }

        public Builder background(Drawable drawable) {
            this.backgroundDrawable = drawable;
            return this;
        }

        public Builder customView(View customView) {
            this.customView = customView;
            return this;
        }

        public Builder customView(@LayoutRes int layoutRes) {
            this.customView = LayoutInflater.from(context).inflate(layoutRes, null);
            return this;
        }

        /**
         * 点击背景是否穿透
         *
         * @param canPenetrate
         * @return
         */
        public Builder canPenetrate(boolean canPenetrate) {
            this.canPenetrate = canPenetrate;
            return this;
        }

        public Builder input(
                @Nullable CharSequence hint,
                @Nullable CharSequence prefill,
                @NonNull InputCallback callback) {
            return input(hint, prefill, true, callback);
        }

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

        public Builder input(
                @StringRes int hint, @StringRes int prefill, @NonNull InputCallback callback) {
            return input(hint, prefill, true, callback);
        }

        public TangramDialog show() {
            TangramDialog dialog = new TangramDialog();
            dialog.builder = this;
            if (customView != null) {
                dialog.rootView = customView;
            } else {
                LayoutInflater inflater = LayoutInflater.from(context);
                dialog.rootView = inflater.inflate(this.layoutId, null);
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
