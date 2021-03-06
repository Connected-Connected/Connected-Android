package com.connected.beta.activity.message;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.connected.beta.activity.login.LoginActivity;
import com.connected.beta.connected.R;
import com.facebook.AccessToken;
import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.AuthService;
import com.kakao.auth.network.response.AccessTokenInfoResponse;
import com.kakao.network.ErrorResult;

public class MessageWriteActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private EditText message;


    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_message);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        message = (EditText)findViewById(R.id.message);

        message.addTextChangedListener(new TextWatcher()
        {
            String previousString = "";

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                previousString= s.toString();
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (message.getLineCount() > 21)
                {
                    message.setText(previousString);
                    message.setSelection(message.length());
                }
            }
        });


        //////////////////////////////////


        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        ////////////////////////////////

        if (AccessToken.getCurrentAccessToken() != null) {
            id = AccessToken.getCurrentAccessToken().getUserId();
            Toast.makeText(getApplicationContext(),"" +id,Toast.LENGTH_LONG).show();
        }else{
            requestAccessToken();
        }

        /////////////////////////////////


    }

    public void requestAccessToken() {

        AuthService.requestAccessTokenInfo(new ApiResponseCallback<AccessTokenInfoResponse>() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Toast.makeText(getApplicationContext(),"카카이톡 토큰이 만료 됬습니다.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MessageWriteActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
            }

            @Override
            public void onNotSignedUp() {
                // not happened
            }

            @Override
            public void onFailure(ErrorResult errorResult) {
                Toast.makeText(getApplicationContext(), "토큰 정보를 받아오는데 실패했음", Toast.LENGTH_LONG).show();
                //Logger.e("failed to get access token info. msg=" + errorResult);
            }

            @Override
            public void onSuccess(AccessTokenInfoResponse accessTokenInfoResponse) {
                id = Long.toString(accessTokenInfoResponse.getUserId());

                Toast.makeText(getApplicationContext(), "" + id, Toast.LENGTH_LONG).show();
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }
}
