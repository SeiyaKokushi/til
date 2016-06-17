package com.kokushiseiya.activitysample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int CALL_RESULT_CODE = 100;
    TextView textView;
    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = SubActivity.createIntent(getApplicationContext());
                intent.putExtra("text", getString(R.string.hello_world));
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = SubActivity.createIntent(getApplicationContext());
                intent.putExtra("text", getString(R.string.hello_world));
                // コードを設定して、Activityの紐付けを行う
                startActivityForResult(intent, CALL_RESULT_CODE);
            }
        });
    }

    /*
        startActivityForResultで始めた別のActivityが終了した際に呼ばれる
        setResultでresultCodeを設定すること
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == CALL_RESULT_CODE && resultCode == RESULT_OK) {
            textView.setText(intent.getStringExtra("text"));
        }
    }
}
