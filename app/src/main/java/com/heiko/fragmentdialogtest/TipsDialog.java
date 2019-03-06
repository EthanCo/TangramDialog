package com.heiko.fragmentdialogtest;

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

import com.heiko.fragmentdialogtest.base.BaseDialog;
import com.heiko.fragmentdialogtest.base.DialogAction;

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
    private Builder builder;
    private SingleButtonCallback onPositiveCallback;
    private SingleButtonCallback onNegativeCallback;
    private boolean canceledOnTouchOutside;

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
        if (TextUtils.isEmpty(builder.cancel)) {
            btnCancel.setVisibility(View.GONE);
        } else {
            btnCancel.setText(builder.cancel);
        }
        if (TextUtils.isEmpty(builder.confirm)) {
            btnConfirm.setVisibility(View.GONE);
        } else {
            btnConfirm.setText(builder.confirm);
        }
        if (builder.animStyle != null) {
            animStyle = builder.animStyle;
        } else {
            if (builder.gravity == Gravity.CENTER) {
                animStyle = R.style.CenterDialogStyle;
            } else if (builder.gravity == Gravity.TOP) {
                animStyle = R.style.TopEnterStyle;
            } else if (builder.gravity == Gravity.BOTTOM) {
                animStyle = R.style.BottomEnterStyle;
            }
        }
        this.canceledOnTouchOutside = builder.canceledOnTouchOutside;
        this.margin = builder.margin;

        this.onPositiveCallback = builder.onPositiveCallback;
        this.onNegativeCallback = builder.onNegativeCallback;
        this.outCancel = this.canceledOnTouchOutside;
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

    public static class Builder {
        private String title;
        private String content;
        private int layoutResId;
        private String cancel;
        private String confirm;
        private int gravity = Gravity.CENTER;
        private Integer animStyle;
        private SingleButtonCallback onPositiveCallback;
        private SingleButtonCallback onNegativeCallback;
        private boolean canceledOnTouchOutside;
        private int margin;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder setLayoutResId(int layoutResId) {
            this.layoutResId = layoutResId;
            return this;
        }

        public Builder negativeText(String cancel) {
            this.cancel = cancel;
            return this;
        }

        public Builder positiveText(String confirm) {
            this.confirm = confirm;
            return this;
        }

        public Builder setGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder setAnimStyle(int animStyle) {
            this.animStyle = animStyle;
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

        public Builder setMargin(int margin){
            this.margin = margin;
            return this;
        }

        public TipsDialog build() {
            TipsDialog tipsDialog = TipsDialog.newInstance(layoutResId);
            tipsDialog.builder = this;
            return tipsDialog;
        }
    }

    public static TipsDialog newInstance(int layoutResId) {
        TipsDialog dialog = new TipsDialog();
        Bundle args = new Bundle();
        args.putInt(KEY_LAYOUT_RES_ID, layoutResId);
        dialog.setArguments(args);

        return dialog;
    }
}
