package com.heiko.fragmentdialogtest.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.heiko.fragmentdialogtest.R;

/**
 * TipsDialog
 *
 * @author Heiko
 * @date 2019/3/4
 */
public class TipsDialog extends BaseDialog {

    private TextView tvTipsTitle;
    private TextView tvTipsContent;
    private TextView btnNegative;
    private TextView btnPositive;
    private TextView btnNeutral;
    private ImageView imgTips;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        tvTipsTitle = root.findViewById(R.id.tv_tips_title);
        tvTipsContent = root.findViewById(R.id.tv_tips_content);
        imgTips = root.findViewById(R.id.img_tips);
        btnNegative = root.findViewById(R.id.btn_negative);
        btnPositive = root.findViewById(R.id.btn_positive);
        btnNeutral = root.findViewById(R.id.btn_neutral);

        Log.i("TipsDialog", "builder:" + builder);
        if (TextUtils.isEmpty(builder.title)) {
            tvTipsTitle.setVisibility(View.GONE);
        } else {
            tvTipsTitle.setText(builder.title);
        }
        if (TextUtils.isEmpty(builder.content)) {
            tvTipsContent.setVisibility(View.GONE);
        } else {
            tvTipsContent.setText(builder.content);
        }
        if (builder.imgResId != 0) {
            imgTips.setVisibility(View.VISIBLE);
            imgTips.setImageResource(builder.imgResId);
        }
        if (TextUtils.isEmpty(builder.negativeText) && TextUtils.isEmpty(builder.negativeText)) {
            root.findViewById(R.id.view_divider_button).setVisibility(View.INVISIBLE);
            root.findViewById(R.id.layout_button).setVisibility(View.GONE);
        } else {
            if (TextUtils.isEmpty(builder.negativeText)) {
                btnNegative.setVisibility(View.GONE);
            } else {
                btnNegative.setText(builder.negativeText);
            }
            if (TextUtils.isEmpty(builder.neutralText)) {
                root.findViewById(R.id.view_vertical_line_1).setVisibility(View.GONE);
                btnNeutral.setVisibility(View.GONE);
            } else {
                root.findViewById(R.id.view_vertical_line_1).setVisibility(View.VISIBLE);
                btnNeutral.setText(builder.neutralText);
            }
            if (TextUtils.isEmpty(builder.positiveText)) {
                root.findViewById(R.id.view_vertical_line_2).setVisibility(View.GONE);
                btnPositive.setVisibility(View.GONE);
            } else {
                root.findViewById(R.id.view_vertical_line_2).setVisibility(View.VISIBLE);
                btnPositive.setText(builder.positiveText);
            }
        }
        if (builder.animStyle == 0) {
            if (builder.gravity == Gravity.CENTER) {
                builder.animStyle = R.style.CenterDialogStyle;
            } else if (builder.gravity == Gravity.TOP) {
                builder.animStyle = R.style.TopEnterStyle;
            } else if (builder.gravity == Gravity.BOTTOM) {
                builder.animStyle = R.style.BottomEnterStyle;
            }
        }

        if (btnPositive.getVisibility() == View.VISIBLE) {
            btnPositive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (builder.onPositiveCallback != null) {
                        builder.onPositiveCallback.onClick(TipsDialog.this, DialogAction.POSITIVE);
                    }
                }
            });
        }

        if (btnNegative.getVisibility() == View.VISIBLE) {
            btnNegative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (builder.onNegativeCallback != null) {
                        builder.onPositiveCallback.onClick(TipsDialog.this, DialogAction.POSITIVE);
                    }
                }
            });
        }

        if (btnNeutral.getVisibility() == View.VISIBLE) {
            btnNeutral.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (builder.onNeutralCallback != null) {
                        builder.onNeutralCallback.onClick(TipsDialog.this, DialogAction.NEUTRAL);
                    }
                }
            });
        }
        return root;
    }

    public static class Builder extends BaseBuilder {

        public Builder() {
            this.layoutId = R.layout.dialog_tip;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder imgResId(int imgResId) {
            this.imgResId = imgResId;
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
        public Builder negativeText(String message) {
            this.negativeText = message;
            return this;
        }

        /**
         * 中立的按钮文字
         *
         * @param message
         * @return
         */
        public Builder neutralText(String message) {
            this.negativeText = message;
            return this;
        }

        /**
         * 肯定的按钮文字
         *
         * @param message
         * @return
         */
        public Builder positiveText(String message) {
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

        public Builder onPositive(@NonNull SingleButtonCallback callback) {
            this.onPositiveCallback = callback;
            return this;
        }

        public Builder onNeutral(@NonNull SingleButtonCallback callback) {
            this.onNeutralCallback = callback;
            return this;
        }

        public Builder onNegative(@NonNull SingleButtonCallback callback) {
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

        public TipsDialog build() {
            TipsDialog dialog = new TipsDialog();
            dialog.builder = this;
            return dialog;
        }
    }
}
