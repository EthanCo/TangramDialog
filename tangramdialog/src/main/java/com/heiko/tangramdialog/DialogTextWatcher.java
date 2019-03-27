package com.heiko.tangramdialog;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * @author Heiko
 * @date 2019/3/6
 */
public class DialogTextWatcher implements TextWatcher {
    private final DialogBase dialog;
    private BaseBuilder builder;

    public DialogTextWatcher(DialogBase dialog, BaseBuilder builder) {
        this.dialog = dialog;
        this.builder = builder;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence input, int start, int before, int count) {
        if (builder.inputCallback != null) {
            builder.inputCallback.onInput(dialog, input);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
