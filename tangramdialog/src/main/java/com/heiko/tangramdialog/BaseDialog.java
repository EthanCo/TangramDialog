package com.heiko.tangramdialog;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
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

            if (builder.dimAmount != null) {
                params.dimAmount = builder.dimAmount;
            }

            //设置dialog显示位置
            params.gravity = builder.gravity;
            if (builder.gravity==Gravity.CENTER && builder.margin == null) {
                //如果是中心弹出，并没没有设置margin，默认margin设为8
                builder.margin = 8;
            }
            if (builder.margin == null) {
                builder.margin = 0;
            }

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
            if (builder.animStyle == 0) {
                if (builder.gravity == Gravity.CENTER) {
                    builder.animStyle = R.style.TangramCenterDialogAnim;
                } else if (builder.gravity == Gravity.TOP) {
                    builder.animStyle = R.style.TangramTopEnterAnim;
                } else if (builder.gravity == Gravity.BOTTOM) {
                    builder.animStyle = R.style.TangramBottomEnterAnim;
                }
            }
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

    public View getRootView() {
        return rootView;
    }

    private BaseDialog show(FragmentManager manager) {
        super.show(manager, String.valueOf(System.currentTimeMillis()));
        return this;
    }

    public BaseDialog show(FragmentActivity activity) {
        return show(activity.getSupportFragmentManager());
    }
}
