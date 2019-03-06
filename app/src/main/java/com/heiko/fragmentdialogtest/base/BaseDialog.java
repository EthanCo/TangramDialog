package com.heiko.fragmentdialogtest.base;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Dialog通用样式
 */
public class BaseDialog extends DialogFragment {
    public static final String KEY_LAYOUT_RES_ID = "layoutResId";
    protected BaseBuilder builder;
    protected View rootView;

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
        Log.i("DialogM", "builder:"+builder+" builder.layoutId:"+builder.layoutId);
        rootView = inflater.inflate(builder.layoutId, container, false);
        return rootView;
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
            params.dimAmount = builder.dimAmount;

            //设置dialog显示位置
            params.gravity = builder.gravity;

            //设置dialog宽度
            if (builder.width == 0) {
                params.width = DialogUtils.getScreenWidth(getContext()) - 2 * DialogUtils.dp2px(getContext(), builder.margin);
            } else if (builder.width < 0) {
                params.width = builder.width;
            } else {
                params.width = DialogUtils.dp2px(getContext(), builder.width);
            }

            //设置dialog高度
            if (builder.height == 0) {
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            } else if (builder.height < 0) {
                params.height = builder.height;
            } else {
                params.height = DialogUtils.dp2px(getContext(), builder.height);
            }

            //设置dialog动画
            if (builder.animStyle != 0) {
                window.setWindowAnimations(builder.animStyle);
            }

            //设置对话框背景
            if (builder.backgroundDrawable != null) {
                window.setBackgroundDrawable(builder.backgroundDrawable);
                //window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }

            window.setAttributes(params);
        }
        setCancelable(builder.canceledOnTouchOutside);
    }

    private BaseDialog show(FragmentManager manager) {
        super.show(manager, String.valueOf(System.currentTimeMillis()));
        return this;
    }

    public BaseDialog show(FragmentActivity activity) {
        return show(activity.getSupportFragmentManager());
    }
}
