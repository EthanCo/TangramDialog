package com.heiko.fragmentdialogtest;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public class MyDialogFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("tag = " + getTag());
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);

        getDialog().getWindow().getAttributes().windowAnimations = R.style.CenterDialogStyle;

        return inflater.inflate(R.layout.layout_dialog, null);
    }
}
