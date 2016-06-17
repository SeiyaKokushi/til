package com.kokushiseiya.activitysample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity extends AppCompatActivity {

    EditText editText;
    Button button_ok;

    public static Intent createIntent(Context context) {
        return new Intent(context, SubActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        editText = (EditText) findViewById(R.id.editText);
        button_ok = (Button) findViewById(R.id.button_ok);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("text")) {
            editText.setText(intent.getStringExtra("text"));
        }

        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("text", editText.getText().toString());
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
