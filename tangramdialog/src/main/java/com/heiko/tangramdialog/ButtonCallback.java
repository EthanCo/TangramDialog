package com.heiko.tangramdialog;

import android.support.annotation.NonNull;

/**
 * SingleButtonCallback
 *
 * @author Heiko
 * @date 2019/3/6
 */
public interface ButtonCallback {
    void onClick(@NonNull BaseDialog dialog, @NonNull DialogAction which);
}
