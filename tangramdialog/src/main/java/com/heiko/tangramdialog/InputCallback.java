package com.heiko.tangramdialog;

import android.support.annotation.NonNull;

/**
 * InputCallback
 *
 * @author Heiko
 * @date 2019/3/6
 */
public interface InputCallback {
    void onInput(@NonNull BaseDialog dialog, CharSequence input);
}
