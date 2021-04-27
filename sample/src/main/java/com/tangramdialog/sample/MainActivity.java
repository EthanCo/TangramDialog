package com.tangramdialog.sample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.heiko.tangramdialog.ButtonCallback;
import com.heiko.tangramdialog.DialogBase;
import com.heiko.tangramdialog.InputCallback;
import com.heiko.tangramdialog.OnDismissListener;
import com.heiko.tangramdialog.TangramDialog;

public class MainActivity extends AppCompatActivity {

    public static TangramDialog dialog;
    public static final String TAG = "Z-Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");

        findViewById(R.id.btn_dialog_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TangramDialog dialog = new TangramDialog.Builder(MainActivity.this)
                        .title("标题")
                        .content("这是具体内容\n1122")
                        .positiveText("确定")
                        .negativeText("取消")
                        .neutralText("中立")
                        //.canPenetrate(true)
                        //.positiveTextStyle(Typeface.BOLD)
                        //.negativeTextColor(R.color.colorPrimaryDark)
                        //.contentTextStyle(Typeface.ITALIC)
                        //.tips("这是一个温馨提示")
                        //.tipsColorRes(R.color.colorPrimaryDark)
                        //.tipsTextSize(12)
                        //.neutralText("中立")
                        //.titleColorRes(R.color.text_red)
                        //.titleTextSize(22)
                        //.contentColorRes(R.color.text_orange)
                        //.contentTextSize(10)
                        //.background(new ColorDrawable(Color.BLUE))
                        //.background(new ColorDrawable(Color.TRANSPARENT)) //背景透明
                        //.backgroundColor(R.color.colorAccent)
                        .contentTextGraviry(Gravity.LEFT)
                        .canceledOnTouchOutside(true)
                        .onPositive(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull DialogBase dialog, @NonNull View view) {
                                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onNegative(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull DialogBase dialog, @NonNull View view) {
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
                    public void onDismiss(DialogBase dialog) {
                        Toast.makeText(MainActivity.this, "dismiss", Toast.LENGTH_SHORT).show();
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
                        .imgCloseVisibility(View.VISIBLE)
                        .onImgClose(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull DialogBase dialog, @NonNull View v) {
                                Toast.makeText(MainActivity.this, "关闭了弹框", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onPositive(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull DialogBase dialog, @NonNull View view) {
                                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onNegative(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull DialogBase dialog, @NonNull View view) {
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
                        //.margin(0)
                        //.background(new ColorDrawable(Color.WHITE))
                        .canceledOnTouchOutside(false)
                        .onPositive(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull DialogBase dialog, @NonNull View view) {
                                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onNegative(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull DialogBase dialog, @NonNull View view) {
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
                            public void onInput(@NonNull DialogBase dialog, CharSequence input) {
                                Toast.makeText(MainActivity.this, input, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .positiveText("确定")
                        .negativeText("取消")
                        .canceledOnTouchOutside(false)
                        .onPositive(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull DialogBase dialog, @NonNull View view) {
                                String input = ((TangramDialog) dialog).getInputEditText().getText().toString();
                                Toast.makeText(MainActivity.this, "确定:" + input, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onNegative(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull DialogBase dialog, @NonNull View view) {
                                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        findViewById(R.id.btn_dialog_over_length).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = "1111111111111111\n";
                content += "222222222222222222222\n";
                content += "333333333333333333333\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";
                content += "222222222222222222222\n";

                new TangramDialog.Builder(MainActivity.this)
                        .title("标题")
                        .content(content)
                        .imgCloseVisibility(View.VISIBLE)
                        .contentTextGraviry(Gravity.LEFT)
                        .negativeText("取消")
                        .positiveText("确定")
                        .onPositive(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull DialogBase dialog, @NonNull View v) {
                                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
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

        findViewById(R.id.btn_custom_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TangramDialog dialog = new TangramDialog.Builder(MainActivity.this)
                        .layoutId(R.layout.dialog_custom_styles)
                        .customTopView(R.layout.dialog_custom_top)
                        .customView(R.layout.dialog_custom)
                        .customBottomView(R.layout.dialog_custom_bottom)
                        .backgroundColor(R.color.transparent)
                        .canceledOnTouchOutside(true)
                        .positiveText("确定")
                        .negativeText("取消")
                        .margin(40)
                        .onPositive(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull DialogBase dialog, @NonNull View v) {
                                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onNegative(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull DialogBase dialog, @NonNull View v) {
                                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                View rootView = dialog.getRootView();
                TextView tvTitle = rootView.findViewById(R.id.tv_title);
                TextView tvContent1 = rootView.findViewById(R.id.tv_content_1);
                TextView tvContent2 = rootView.findViewById(R.id.tv_content_2);
                tvTitle.setText("标题");
                tvContent1.setText("内容1:");
                tvContent2.setText("内容2");
                tvContent2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "内容2", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        findViewById(R.id.btn_dialog_layout_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TangramDialog dialog = new TangramDialog.Builder(MainActivity.this)
                        .layoutId(R.layout.dialog_layout_id)
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

        findViewById(R.id.btn_dialog_layout_id_unify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TangramDialog dialog = new TangramDialog.Builder(MainActivity.this)
                        .layoutId(R.layout.dialog_layout_id)
                        .canceledOnTouchOutside(true)
                        .show();
                View rootView = dialog.getRootView();
                TextView tvTitle = rootView.findViewById(R.id.tv_title);
                tvTitle.setText("Hello World!");
                dialog.setOnClickListener(new ButtonCallback() {
                    @Override
                    public void onClick(@NonNull DialogBase dialog, @NonNull View v) {
                        if (v.getId() == R.id.btn_click_me) {
                            Toast.makeText(MainActivity.this, "Click Me!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss(); //需要手动调用dismiss
                        }
                    }
                });
            }
        });

        findViewById(R.id.btn_dialog_custom_styles).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TangramDialog dialog = new TangramDialog.Builder(MainActivity.this)
                        .layoutId(R.layout.dialog_custom_styles)
                        .title("设置")
                        .titleTextSize(18)
                        //.buttonsPadding(50, 0, 50, 0)
                        //.padding(15)
                        .input("hint", "1234567890", null)
                        .negativeText("取消")
                        .positiveText("保存")
                        .onPositive(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull DialogBase dialog, @NonNull View v) {
                                Toast.makeText(MainActivity.this, "保存完毕", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        findViewById(R.id.btn_dialog_single_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TangramDialog dialog = new TangramDialog.Builder(MainActivity.this)
                        .layoutId(R.layout.dialog_single_button)
                        .title("设置")
                        .titleTextSize(18)
                        .singleButtonWidth(151)
                        .input("hint", "1234567890", null)
                        //.negativeText("取消")
                        .positiveText("保存")
                        .onPositive(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull DialogBase dialog, @NonNull View v) {
                                Toast.makeText(MainActivity.this, "保存完毕", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
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
                            public void onClick(@NonNull DialogBase dialog, @NonNull View view) {
                                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onNegative(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull DialogBase dialog, @NonNull View view) {
                                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .gravity(Gravity.TOP)
                        .show();
            }
        });

        findViewById(R.id.btn_double_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TangramDialog.Builder(MainActivity.this)
                        .title("hello world")
                        .content("111111111111111111111111111111111111111111111111111111111111111" +
                                "1111111111111111111111111111111111111111111111111111111111")
                        .positiveText("确定")
                        .autoDismiss(false)
                        .onPositive(new ButtonCallback() {
                            @Override
                            public void onClick(@NonNull DialogBase dialog, @NonNull View v) {
                                new TangramDialog.Builder(MainActivity.this)
                                        .title("bbbbbbb")
                                        .content("cccccccc")
                                        .positiveText("确定")
                                        .onPositive(new ButtonCallback() {
                                            @Override
                                            public void onClick(@NonNull DialogBase dialog, @NonNull View v) {

                                            }
                                        }).show();
                            }
                        }).show();
            }
        });

        findViewById(R.id.btn_go_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new TangramDialog.Builder(MainActivity.this)
                        .title("标题")
                        .content("内容XXXXX")
                        .positiveText("确定")
                        .canceledOnTouchOutside(false)
                        .show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

        findViewById(R.id.btn_dialog_full_screen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullDialog editNameDialogFragment = new FullDialog();
                editNameDialogFragment.show(getSupportFragmentManager(), "edit");
            }
        });

        findViewById(R.id.btn_dialog_weak_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "weakDialog", Toast.LENGTH_SHORT).show();
                View rootView = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_layout_id, null);
                WeakDialog dialog = new WeakDialog.Builder(MainActivity.this)
                        .setContentView(rootView)
                        .build();
                dialog.show(MainActivity.this);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}

