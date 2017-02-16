package com.beta.connected.connected;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

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
