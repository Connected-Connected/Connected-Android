package com.connected.beta.activity.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.connected.beta.connected.R;

public class ChattingDetailActivity extends AppCompatActivity {

    private TextView tmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_detail);
        Intent intent = getIntent();

        tmp = (TextView) findViewById(R.id.textView);
        tmp.setText(intent.getStringExtra("id"));
    }

}
