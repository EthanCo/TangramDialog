package com.heiko.tangramdialog;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Dialog通用样式
 */
public class DialogBase extends DialogFragment {
    protected BaseBuilder builder;
    protected View rootView;
    protected List<OnDismissListener> onDismissListeners;
    protected List<OnShowListener> onShowListeners;

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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onShowListeners();
    }

    @Override
    public void onStart() {
        super.onStart();
        initParams();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //dismiss();
    }

    private void initParams() {
        if (builder == null) {
            dismiss();
            return;
        }
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();

            if (builder.dimAmount != null) {
                params.dimAmount = builder.dimAmount;
            }

            if (builder.canPenetrate) {
                params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            }
            //设置dialog显示位置
            params.gravity = builder.gravity;
            if (builder.gravity == Gravity.CENTER && builder.margin == null) {
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

            // x y 是相对值 相对自身位置的偏移量 负值无效
            // 显而易见 x 是代表水平方向的偏移 y 代表竖直方向的偏移
            if (builder.offsetX != null) {
                params.x = builder.offsetX;
            }
            if (builder.offsetY != null) {
                params.y = builder.offsetY;
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

    public boolean isShowing() {
        return getDialog() != null && getDialog().isShowing();
    }

    public void addOnDismissListener(OnDismissListener listener) {
        if (onDismissListeners == null) {
            onDismissListeners = new ArrayList<>();
        }
        if (!onDismissListeners.contains(listener)) {
            onDismissListeners.add(listener);
        }
    }

    public void remvoeOnDismissListener(OnDismissListener listener) {
        if (onDismissListeners == null) return;
        onDismissListeners.remove(listener);
    }

    public void addOnShowListener(OnShowListener listener) {
        if (onShowListeners == null) {
            onShowListeners = new ArrayList<>();
        }
        if (!onShowListeners.contains(listener)) {
            onShowListeners.add(listener);
        }
    }

    public void remvoeOnShowListener(OnShowListener listener) {
        if (onShowListeners == null) return;
        onShowListeners.remove(listener);
    }

    @Override
    public void dismiss() {
        super.dismissAllowingStateLoss();
    }

    public void dismissNotAllowingStateLoss() {
        super.dismiss();
    }

    @Override
    public void onDestroy() {
        onDismissListeners();
        super.onDestroy();
    }

    private void onDismissListeners() {
        if (onDismissListeners == null) return;
        for (OnDismissListener onDismissListener : onDismissListeners) {
            onDismissListener.onDismiss(this);
        }
    }

    private void onShowListeners() {
        if (onShowListeners == null) return;
        for (OnShowListener onShowListener : onShowListeners) {
            onShowListener.onShow(this);
        }
    }

    private DialogBase show(FragmentManager manager) {
        try {
            super.show(manager, getDialogTag());
        } catch (Exception e) {
            Log.w("BaseDialog", e);
        }
        return this;
    }

    @NonNull
    private String getDialogTag() {
        return getClass().getSimpleName() + String.valueOf(System.currentTimeMillis());
    }

    public DialogBase show(FragmentActivity activity) {
        return show(activity.getSupportFragmentManager());
    }

    /*@Override
    public void show(FragmentManager manager, String tag) {
        try {
            Field mDismissed = this.getClass().getSuperclass().getDeclaredField("mDismissed");
            Field mShownByMe = this.getClass().getSuperclass().getDeclaredField("mShownByMe");
            mDismissed.setAccessible(true);
            mShownByMe.setAccessible(true);
            mDismissed.setBoolean(this, false);
            mShownByMe.setBoolean(this, true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }*/

    public void setOnClickListener(ButtonCallback buttonCallback) {
        List<Integer> ignoreIds = new ArrayList<>();
        DialogUtils.assignClickListenerRecursively(this, rootView, ignoreIds, buttonCallback);
    }
}
