package com.tangramdialog.sample;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * WeakDialog
 *
 * @author Heiko
 * @date 2020/9/23 0023
 */
public class WeakDialog {
    private WeakDialog.Builder builder;
    private View contentView;

    public WeakDialog(WeakDialog.Builder builder) {
        this.builder = builder;
    }

    public void show(Activity context) {
        ViewGroup decorView = context.getWindow().getDecorView().findViewById(android.R.id.content);
        FrameLayout rootView = new FrameLayout(context);
        contentView = builder.getContentView();

        if (builder.isFullScreen) {
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }

        rootView.setLayoutParams(builder.getRootLayoutParams());
        rootView.setBackgroundColor(builder.getBackGroundColor());
        rootView.setId(R.id.dialog_root);

        contentView.setFocusable(true);
        contentView.setFocusableInTouchMode(true);

        rootView.addView(contentView);

        contentView.setLayoutParams(builder.getContentLayoutParams());

        //bindView(contentView);
        contentView.requestFocus();

        decorView.addView(rootView);
        //contentView.startAnimation(dialogInAnim);
        //assignClickListenerRecursively(contentView);
        contentView.requestFocus();
        if(dismissListener != null) dismissListener.onShow();
    }

    /*protected void onAttached(View view) {
        decorView.addView(view);
        contentView.startAnimation(dialogInAnim);
        assignClickListenerRecursively(contentView);
        contentView.requestFocus();
        if(dismissListener != null) dismissListener.onShow();
    }*/

    private OnDialogDismissListener dismissListener;

    public void setOnDialogDismissListener(OnDialogDismissListener listener) {
        this.dismissListener = listener;
    }

    public interface OnDialogDismissListener {
        void onDismiss();

        void onShow();
    }

    public interface OnClickListener {
        void onClick(WeakDialog dialog, View v);
    }

    /**
     * 构建dialog
     */
    public static class Builder {
        private OnDialogDismissListener dismissListener;
        private Context context;

        private OnClickListener listener;

        /**
         * 外部区域可取消
         */
        private boolean outSideCancelable = true;

        private int gravity = Gravity.CENTER;

        /**
         * 填充布局
         */
        private View contentView;

        /**
         * 背景颜色
         */
        protected int backGroundColor = R.color.color_black_90;

        /**
         * 设置dialog外间距
         */
        private final int[] outMostMargin = new int[4];

        /**
         * dialog填充布局参数
         */
        private final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM
        );
        /**
         * 事件穿透，设置背景透明和false
         */
        private boolean rootCanClick = true;
        /**
         * 物理返回键是否可用
         */
        private boolean backCancelable = true;
        private boolean isFullScreen;

        public Builder(Context context) {
            this.context = context;
            outMostMargin[1] = dp2px(context, 50);
        }

        public Context getContext() {
            return context;
        }

        public boolean isOutSideCancelable() {
            return outSideCancelable;
        }

        public Builder setOutSideCancelable(boolean outSideCancelable) {
            this.outSideCancelable = outSideCancelable;
            return this;
        }

        public Builder setBackCancelable(boolean backCancelable) {
            this.backCancelable = backCancelable;
            return this;
        }

        public int getGravity() {
            return gravity;
        }

        public Builder setGravity(int gravity) {
            this.gravity = gravity;
            params.gravity = gravity;
            return this;
        }

        public View getContentView() {
            return contentView;
        }

        public Builder setContentView(View contentView) {
            this.contentView = contentView;
            return this;
        }

        public FrameLayout.LayoutParams getRootLayoutParams() {
            FrameLayout.LayoutParams outParams = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            );
            outParams.setMargins(outMostMargin[0], outMostMargin[1], outMostMargin[2], outMostMargin[3]);
            return outParams;
        }

        public FrameLayout.LayoutParams getContentLayoutParams() {
            return params;
        }

        /**
         * Add margins to your dialog root view which contains everything. As default they are 0
         * are applied
         */
        public Builder setOutMargin(int left, int top, int right, int bottom) {
            this.outMostMargin[0] = left;
            this.outMostMargin[1] = top;
            this.outMostMargin[2] = right;
            this.outMostMargin[3] = bottom;
            return this;
        }

        /**
         * usually fullScreen or below title, if top 0, fullScreen, otherwise set title height
         */
        public Builder setOutMarginTop(int top) {
            this.outMostMargin[1] = top;
            return this;
        }

        /**
         * Add margins to your dialog content view. As default they are 0
         * are applied
         * note: dp not px
         */
        public Builder setInnerMargin(int left, int top, int right, int bottom) {
            params.setMargins(dp2px(context, left), dp2px(context, top), dp2px(context, right), dp2px(context, bottom));
            return this;
        }

        public Builder setContentRes(@LayoutRes int layoutRes) {
            this.contentView = View.inflate(this.getContext(), layoutRes, null);
            return this;
        }

        public int getBackGroundColor() {
            return getContext().getResources().getColor(backGroundColor);
        }

        public Builder setBackGroundColor(@ColorRes int backGroundColor) {
            this.backGroundColor = backGroundColor;
            return this;
        }

        public OnClickListener getOnClickListener() {
            return listener;
        }

        public Builder setOnClickListener(OnClickListener listener) {
            this.listener = listener;
            return this;
        }


        public Builder setOnDialogDismissListener(OnDialogDismissListener listener) {
            this.dismissListener = listener;
            return this;
        }

        public WeakDialog build() {
            return new WeakDialog(this);
        }

        public boolean isRootCanClick() {
            return rootCanClick;
        }

        public Builder setRootCanClick(boolean rootCancelClick) {
            this.rootCanClick = rootCancelClick;
            return this;
        }

        public boolean isBackCancelable() {
            return backCancelable;
        }

        public boolean isFullScreen() {
            return isFullScreen;
        }

        public Builder setFullScreen(boolean isFullScreen) {
            this.isFullScreen = isFullScreen;
            if (isFullScreen) outMostMargin[1] = 0;
            return this;
        }
    }

    protected static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
