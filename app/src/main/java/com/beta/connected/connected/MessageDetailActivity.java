package com.beta.connected.connected;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MessageDetailActivity extends AppCompatActivity {

    private TextView tmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);

        Intent intent = getIntent();

        tmp = (TextView) findViewById(R.id.textView);
        tmp.setText(intent.getStringExtra("id"));
    }

}
