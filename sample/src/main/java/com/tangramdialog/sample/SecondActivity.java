package com.tangramdialog.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @author Heiko
 * @date 2019/10/22
 */
public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViewById(R.id.btn_close_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.dialog.dismiss();
            }
        });

        findViewById(R.id.btn_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
