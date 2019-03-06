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
    private TextView btnCancel;
    private TextView btnConfirm;
    private SingleButtonCallback onPositiveCallback;
    private SingleButtonCallback onNegativeCallback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        tvTipsTitle = root.findViewById(R.id.tv_tips_title);
        tvTipsContent = root.findViewById(R.id.tv_tips_content);
        btnCancel = root.findViewById(R.id.btn_cancel);
        btnConfirm = root.findViewById(R.id.btn_confirm);

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
        if (TextUtils.isEmpty(builder.negativeText)) {
            btnCancel.setVisibility(View.GONE);
        } else {
            btnCancel.setText(builder.negativeText);
        }
        if (TextUtils.isEmpty(builder.positiveText)) {
            btnConfirm.setVisibility(View.GONE);
        } else {
            btnConfirm.setText(builder.positiveText);
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
        this.onPositiveCallback = builder.onPositiveCallback;
        this.onNegativeCallback = builder.onNegativeCallback;

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (onPositiveCallback != null) {
                    onPositiveCallback.onClick(TipsDialog.this, DialogAction.POSITIVE);
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (onNegativeCallback != null) {
                    onPositiveCallback.onClick(TipsDialog.this, DialogAction.POSITIVE);
                }
            }
        });
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

        public Builder layoutId(int layoutId) {
            this.layoutId = layoutId;
            return this;
        }

        public Builder negativeText(String message) {
            this.negativeText = message;
            return this;
        }

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

        public Builder onNegative(SingleButtonCallback callback) {
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
