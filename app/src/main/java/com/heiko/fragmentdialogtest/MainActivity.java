package com.heiko.fragmentdialogtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.heiko.fragmentdialogtest.base.BaseDialog;
import com.heiko.fragmentdialogtest.base.DialogAction;
import com.heiko.fragmentdialogtest.base.InputCallback;
import com.heiko.fragmentdialogtest.base.ButtonCallback;
import com.heiko.fragmentdialogtest.base.TangramDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_dialog_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TangramDialog dialog = new TangramDialog.Builder(MainActivity.this)
                        .title("标题")
                        .content("这是具体内容")
                        .positiveText("确定")
                        .negativeText("取消")
                        .neutralText("中立")
                        .titleColorRes(R.color.text_red)
                        .titleTextSize(22)
                        .contentColorRes(R.color.text_orange)
                        .contentTextSize(10)
                        .canceledOnTouchOutside(false)
                        .onPositive(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull BaseDialog dialog, @NonNull DialogAction which) {
                                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onNegative(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull BaseDialog dialog, @NonNull DialogAction which) {
                                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onNeutral(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull BaseDialog dialog, @NonNull DialogAction which) {
                                Toast.makeText(MainActivity.this, "中立", Toast.LENGTH_SHORT).show();
                            }
                        })
                        //.imgResId(R.mipmap.ic_launcher)
                        //.width(ViewGroup.LayoutParams.MATCH_PARENT)
                        //.height(WindowManager.LayoutParams.WRAP_CONTENT)
                        .show();
            }
        });

        findViewById(R.id.btn_dialog_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TangramDialog dialog = new TangramDialog.Builder(MainActivity.this)
                        .title("标题")
                        .content("这是具体内容")
                        .positiveText("确定")
                        .negativeText("取消")
                        .canceledOnTouchOutside(false)
                        .onPositive(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull BaseDialog dialog, @NonNull DialogAction which) {
                                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onNegative(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull BaseDialog dialog, @NonNull DialogAction which) {
                                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .gravity(Gravity.TOP)
                        .show();
            }
        });

        findViewById(R.id.btn_dialog_bottom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TangramDialog dialog = new TangramDialog.Builder(MainActivity.this)
                        .title("标题")
                        .content("这是具体内容")
                        .positiveText("确定")
                        .negativeText("取消")
                        .canceledOnTouchOutside(false)
                        .onPositive(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull BaseDialog dialog, @NonNull DialogAction which) {
                                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onNegative(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull BaseDialog dialog, @NonNull DialogAction which) {
                                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .gravity(Gravity.BOTTOM)
                        .show();
            }
        });

        findViewById(R.id.btn_dialog_input).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TangramDialog dialog = new TangramDialog.Builder(MainActivity.this)
                        .title("标题")
                        .content("这是具体内容")
                        .input("请输入", "预加载", false, new InputCallback() {
                            @Override
                            public void onInput(@NonNull BaseDialog dialog, CharSequence input) {
                                Toast.makeText(MainActivity.this, input, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .positiveText("确定")
                        .negativeText("取消")
                        .canceledOnTouchOutside(false)
                        .onPositive(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull BaseDialog dialog, @NonNull DialogAction which) {
                                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onNegative(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull BaseDialog dialog, @NonNull DialogAction which) {
                                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }
}
