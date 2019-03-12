package com.tangramdialog.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.heiko.tangramdialog.BaseDialog;
import com.heiko.tangramdialog.ButtonCallback;
import com.heiko.tangramdialog.InputCallback;
import com.heiko.tangramdialog.OnDismissListener;
import com.heiko.tangramdialog.TangramDialog;

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
                        //.neutralText("中立")
                        //.titleColorRes(R.color.text_red)
                        //.titleTextSize(22)
                        //.contentColorRes(R.color.text_orange)
                        //.contentTextSize(10)
                        //.background(new ColorDrawable(Color.BLUE))
                        //.background(new ColorDrawable(Color.TRANSPARENT)) //背景透明
                        //.backgroundColor(R.color.colorAccent)
                        .canceledOnTouchOutside(false)
                        .onPositive(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull BaseDialog dialog, @NonNull View view) {
                                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onNegative(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull BaseDialog dialog, @NonNull View view) {
                                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        //.onNeutral(new ButtonCallback() {
                        //  @Override
                        //  public void onClick(@NonNull BaseDialog dialog, @NonNull DialogAction which) {
                        //      Toast.makeText(MainActivity.this, "中立", Toast.LENGTH_SHORT).show();
                        //  }
                        //})
                        //.imgResId(R.mipmap.ic_launcher)
                        //.width(ViewGroup.LayoutParams.MATCH_PARENT)
                        //.height(WindowManager.LayoutParams.WRAP_CONTENT)
                        .show();
                dialog.addOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(BaseDialog dialog) {

                    }
                });
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
                            public void onClick(@NonNull BaseDialog dialog, @NonNull View view) {
                                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onNegative(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull BaseDialog dialog, @NonNull View view) {
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
                            public void onClick(@NonNull BaseDialog dialog, @NonNull View view) {
                                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onNegative(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull BaseDialog dialog, @NonNull View view) {
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
                            public void onClick(@NonNull BaseDialog dialog, @NonNull View view) {
                                String input = ((TangramDialog) dialog).getInputEditText().getText().toString();
                                Toast.makeText(MainActivity.this, "确定:" + input, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onNegative(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull BaseDialog dialog, @NonNull View view) {
                                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        /*new TangramDialog.Builder(MainActivity.this)
                .imgRes(R.mipmap.ic_launcher)
                .title("您已成功为好友砍押金")
                .positiveText("我知道了")
                .onPositive(new ButtonCallback() {
                    @Override
                    public void onClick(@NonNull BaseDialog dialog, @NonNull DialogAction which) {
                        Toast.makeText(MainActivity.this, "dismiss", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();*/

        findViewById(R.id.btn_dialog_custom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TangramDialog dialog = new TangramDialog.Builder(MainActivity.this)
                        .customView(R.layout.dialog_custom)
                        .canceledOnTouchOutside(true)
                        .show();
                View rootView = dialog.getRootView();
                TextView tvTitle = rootView.findViewById(R.id.tv_title);
                TextView btnClickMe = rootView.findViewById(R.id.btn_click_me);
                tvTitle.setText("Hello World!");
                btnClickMe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Click Me!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });

        findViewById(R.id.btn_dialog_custom_unify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TangramDialog dialog = new TangramDialog.Builder(MainActivity.this)
                        .customView(R.layout.dialog_custom)
                        .canceledOnTouchOutside(true)
                        .show();
                View rootView = dialog.getRootView();
                TextView tvTitle = rootView.findViewById(R.id.tv_title);
                final TextView btnClickMe = rootView.findViewById(R.id.btn_click_me);
                tvTitle.setText("Hello World!");
                dialog.setOnClickListener(new ButtonCallback() {
                    @Override
                    public void onClick(@NonNull BaseDialog dialog, @NonNull View v) {
                        if (v.getId() == R.id.btn_click_me) {
                            Toast.makeText(MainActivity.this, "Click Me!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss(); //需要手动调用dismiss
                        }
                    }
                });
            }
        });

        findViewById(R.id.btn_dialog_custom_no_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TangramDialog dialog = new TangramDialog.Builder(MainActivity.this)
                        .customView(R.layout.dialog_custom)
                        .canceledOnTouchOutside(true)
                        .show();
                View rootView = dialog.getRootView();
                TextView tvTitle = rootView.findViewById(R.id.tv_title);
                TextView btnClickMe = rootView.findViewById(R.id.btn_click_me);
                tvTitle.setText("Hello World!");
                btnClickMe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Click Me!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });

        findViewById(R.id.btn_dialog_top_margin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TangramDialog dialog = new TangramDialog.Builder(MainActivity.this)
                        .title("标题")
                        .content("这是具体内容")
                        .positiveText("确定")
                        .negativeText("取消")
                        .offsetY(150)
                        .canceledOnTouchOutside(false)
                        .onPositive(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull BaseDialog dialog, @NonNull View view) {
                                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onNegative(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull BaseDialog dialog, @NonNull View view) {
                                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .gravity(Gravity.TOP)
                        .show();
            }
        });
    }
}
