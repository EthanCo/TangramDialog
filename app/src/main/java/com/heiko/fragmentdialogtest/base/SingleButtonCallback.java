package com.heiko.fragmentdialogtest.base;

import android.support.annotation.NonNull;

/**
 * SingleButtonCallback
 *
 * @author Heiko
 * @date 2019/3/6
 */
public interface SingleButtonCallback {
    void onClick(@NonNull BaseDialog dialog, @NonNull DialogAction which);
}
