package com.beta.connected.connected;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.beta.connected.connected.LoginSessionController.LoginSessionCheck;
import com.facebook.AccessToken;
import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.AuthService;
import com.kakao.auth.network.response.AccessTokenInfoResponse;
import com.kakao.network.ErrorResult;

public class WriteMessageActivity extends AppCompatActivity {
    private LoginSessionCheck loginSessionCheck;
    private SharedPreferences loginInfo;

    private Toolbar toolbar;

    private RecyclerView recyclerView;


    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_message);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);


        //////////////////////////////////


        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        ////////////////////////////////

        loginInfo = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        loginSessionCheck = new LoginSessionCheck(this);


        if(loginInfo.getInt("loginInfo",0) == 1){
            if(loginSessionCheck.facebookCheckLogin()){
                id = AccessToken.getCurrentAccessToken().getUserId();
                Toast.makeText(getApplicationContext(),"" +id,Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(),"페이스북 토큰이 만료 됬습니다.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(WriteMessageActivity.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
            }
        }else if(loginInfo.getInt("loginInfo",0) == 2){
            requestAccessToken();
        } else{
            Toast.makeText(getApplicationContext(),"이 상황은 므지.",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(WriteMessageActivity.this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();
        }


        /////////////////////////////////


    }

    public void requestAccessToken() {

        AuthService.requestAccessTokenInfo(new ApiResponseCallback<AccessTokenInfoResponse>() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Toast.makeText(getApplicationContext(),"카카이톡 토큰이 만료 됬습니다.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(WriteMessageActivity.this, LoginActivity.class);
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
