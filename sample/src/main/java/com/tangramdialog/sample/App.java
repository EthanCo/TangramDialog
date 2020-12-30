package com.tangramdialog.sample;

import android.app.Application;
import android.util.Log;

import com.heiko.tangramdialog.DialogBase;
import com.heiko.tangramdialog.TangramDialog;

/**
 * App
 *
 * @author Heiko
 * @date 2020/12/30 0030
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        TangramDialog.setErrorListener(new DialogBase.ErrorListener() {
            @Override
            public void onError(String error) {
                Log.i("Sample", "error:" + error);
            }
        });
    }
}
