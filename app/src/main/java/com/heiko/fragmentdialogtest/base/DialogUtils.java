package com.heiko.fragmentdialogtest.base;

import android.content.Context;
import android.os.IBinder;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;

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
        final TipsDialog dialog = (TipsDialog) di;
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
        final TipsDialog dialog = (TipsDialog) di;
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
}
