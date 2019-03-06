package com.heiko.fragmentdialogtest.base;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.heiko.fragmentdialogtest.R;

/**
 * Dialog通用样式
 */
public class BaseDialog extends DialogFragment {

    public static final String KEY_LAYOUT_RES_ID = "layoutResId";
    protected float dimAmount = 0.5f;//背景昏暗度
    protected int margin = 0;//左右边距
    protected int animStyle = 0;//进入退出动画
    protected boolean outCancel = true;//点击外部取消
    protected int gravity = Gravity.CENTER;
    private Drawable backgroundDrawable;
    protected int width;
    protected int height;


    public interface SingleButtonCallback {
        void onClick(@NonNull BaseDialog dialog, @NonNull DialogAction which);
    }

    public interface InputCallback {
        void onInput(@NonNull BaseDialog dialog, CharSequence input);
    }

    public static BaseDialog newInstance(int layoutResId) {
        BaseDialog dialog = new BaseDialog();
        Bundle args = new Bundle();
        args.putInt(KEY_LAYOUT_RES_ID, layoutResId);
        dialog.setArguments(args);

        return dialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BaseDialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        //int layoutResId = getArguments().getInt(KEY_LAYOUT_RES_ID);
        int layoutResId = R.layout.dialog_tip;
        View view = inflater.inflate(layoutResId, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initParams();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        dismiss();
    }

    private void initParams() {
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.dimAmount = dimAmount;

            //设置dialog显示位置
            params.gravity = gravity;

            //设置dialog宽度
            if (width == 0) {
                params.width = Utils.getScreenWidth(getContext()) - 2 * Utils.dp2px(getContext(), margin);
            } else {
                params.width = Utils.dp2px(getContext(), width);
            }

            //设置dialog高度
            if (height == 0) {
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            } else {
                params.height = Utils.dp2px(getContext(), height);
            }

            //设置dialog动画
            if (animStyle != 0) {
                window.setWindowAnimations(animStyle);
            }

            //设置对话框背景
            if (backgroundDrawable != null) {
                window.setBackgroundDrawable(backgroundDrawable);
                //window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }

            window.setAttributes(params);
        }
        setCancelable(outCancel);
    }

    public BaseDialog show(FragmentManager manager) {
        super.show(manager, String.valueOf(System.currentTimeMillis()));
        return this;
    }

    public void show(FragmentActivity activity) {
        show(activity.getSupportFragmentManager());
    }
}
