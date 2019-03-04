package com.heiko.fragmentdialogtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_dialog1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyDialogFragment()
                        .show(getSupportFragmentManager(), "dialog_fragment");
            }
        });

        findViewById(R.id.btn_dialog_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TopDialogFragment()
                        .show(getSupportFragmentManager(), "dialog_fragment");
            }
        });

        findViewById(R.id.btn_dialog_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BottomDialogFragment()
                        .show(getSupportFragmentManager(), "dialog_fragment");
            }
        });
    }
}
