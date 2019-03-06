package com.heiko.fragmentdialogtest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.heiko.fragmentdialogtest.base.BaseDialog;
import com.heiko.fragmentdialogtest.base.DialogAction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_dialog1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TipsDialog dialog = new TipsDialog.Builder()
                        .title("标题")
                        .content("这是具体内容")
                        .positiveText("确定")
                        .negativeText("取消")
                        .canceledOnTouchOutside(false)
                        .onPositive(new BaseDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull BaseDialog dialog, @NonNull DialogAction which) {
                                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onNegative(new BaseDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull BaseDialog dialog, @NonNull DialogAction which) {
                                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();
                dialog.show(MainActivity.this);
            }
        });

        findViewById(R.id.btn_dialog_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.btn_dialog_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
