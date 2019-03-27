package com.heiko.tangramdialog;

import android.content.Context;
import android.os.IBinder;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

/**
 * Utils
 *
 * @author Heiko
 * @date 2019/3/4
 */
public class DialogUtils {
    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static void showKeyboard(
            @NonNull final DialogFragment di, @NonNull final BaseBuilder builder) {
        final TangramDialog dialog = (TangramDialog) di;
        if (dialog.getInputEditText() == null) {
            return;
        }
        dialog.getInputEditText()
                .post(new Runnable() {
                    @Override
                    public void run() {
                        dialog.getInputEditText().requestFocus();
                        InputMethodManager imm =
                                (InputMethodManager)
                                        builder.context.getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm != null) {
                            imm.showSoftInput(dialog.getInputEditText(), InputMethodManager.SHOW_IMPLICIT);
                        }
                    }
                });
    }

    public static void hideKeyboard(
            @NonNull final DialogFragment di, @NonNull final BaseBuilder builder) {
        final TangramDialog dialog = (TangramDialog) di;
        if (dialog.getInputEditText() == null) {
            return;
        }
        InputMethodManager imm =
                (InputMethodManager) builder.context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            IBinder windowToken = null;
            /*final View currentFocus = dialog.getCurrentFocus();
            if (currentFocus != null) {
                windowToken = currentFocus.getWindowToken();
            } else*/
            if (dialog.getView() != null) {
                windowToken = dialog.getView().getWindowToken();
            }
            if (windowToken != null) {
                imm.hideSoftInputFromWindow(windowToken, 0);
            }
        }
    }

    @ColorInt
    public static int getColor(Context context, @ColorRes int colorId) {
        return ContextCompat.getColor(context, colorId);
    }

    /**
     * Loop among the views in the hierarchy and assign listener to them
     */
    public static void assignClickListenerRecursively(DialogBase dialog, View parent, List<Integer> ignoreIds, ButtonCallback buttonCallback) {
        if (buttonCallback == null || parent == null) {
            return;
        }

        if (parent instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) parent;
            int childCount = viewGroup.getChildCount();
            for (int i = childCount - 1; i >= 0; i--) {
                View child = viewGroup.getChildAt(i);
                assignClickListenerRecursively(dialog, child, ignoreIds, buttonCallback);
            }
        }
        setClickListener(dialog, parent, ignoreIds, buttonCallback);
    }

    private static void setClickListener(final DialogBase dialog, View view, List<Integer> ignoreIds, final ButtonCallback buttonCallback) {
        if (view.getId() == View.NO_ID) {
            return;
        }
        for (int ignoreId : ignoreIds) {
            if (view.getId() == ignoreId) {
                return;
            }
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonCallback != null) {
                    buttonCallback.onClick(dialog, v);
                }
            }
        });
    }
}
