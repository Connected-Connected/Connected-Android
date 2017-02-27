package com.beta.connected.connected.LoginSessionController;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.beta.connected.connected.KakaoLogin.KakaoSignUpActivity;
import com.beta.connected.connected.LoginActivity;
import com.beta.connected.connected.MainActivity;
import com.facebook.AccessToken;
import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.AuthService;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.network.response.AccessTokenInfoResponse;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.helper.log.Logger;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by kirkee on 2017. 2. 21..
 */

public class LoginSessionCheck {
    private AppCompatActivity currentActivity;
    private int loginInfo;
    private String id = null;

    public LoginSessionCheck(AppCompatActivity currentActivity){
        this.currentActivity = currentActivity;
    }

    public LoginSessionCheck(AppCompatActivity currentActivity,int loginInfo){
        this.currentActivity = currentActivity;
        this.loginInfo = loginInfo;
    }

    public void checkLogin() { //유저의 정보를 받아오는 함수
        if(loginInfo == 1){
            if(AccessToken.getCurrentAccessToken() == null){
                //Toast.makeText(getApplicationContext(),"페이스북 토큰 유지 안 됬음 로그인 액티비티",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(currentActivity,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                currentActivity.startActivity(intent);
                currentActivity.finish();
            }else{
                id = AccessToken.getCurrentAccessToken().getUserId();
            }
        }else if(loginInfo == 2){
            AuthService.requestAccessTokenInfo(new ApiResponseCallback<AccessTokenInfoResponse>() {
                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    Intent intent = new Intent(currentActivity,LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    currentActivity.startActivity(intent);
                    currentActivity.finish();
                }

                @Override
                public void onNotSignedUp() {
                    // not happened
                }

                @Override
                public void onFailure(ErrorResult errorResult) {
                    Toast.makeText(getApplicationContext(),"토큰 정보를 받아오는데 실패했음",Toast.LENGTH_LONG).show();
                    //Logger.e("failed to get access token info. msg=" + errorResult);
                }

                @Override
                public void onSuccess(AccessTokenInfoResponse accessTokenInfoResponse) {
                    id = Long.toString(accessTokenInfoResponse.getUserId());
                }
            });
        }else{

        }
    }


    public boolean facebookCheckLogin() { //유저의 정보를 받아오는 함수
        if(AccessToken.getCurrentAccessToken() == null){
                //Toast.makeText(getApplicationContext(),"페이스북 토큰 유지 안 됬음 로그인 액티비티",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(currentActivity,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            currentActivity.startActivity(intent);
            currentActivity.finish();
            return false;
        }else{
            id = AccessToken.getCurrentAccessToken().getUserId();
            return true;
        }
    }

    public void kakaoCheckLogin() {
        AuthService.requestAccessTokenInfo(new ApiResponseCallback<AccessTokenInfoResponse>() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Intent intent = new Intent(currentActivity,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                currentActivity.startActivity(intent);
                currentActivity.finish();
            }

            @Override
            public void onNotSignedUp() {
                // not happened
            }

            @Override
            public void onFailure(ErrorResult errorResult) {
                Toast.makeText(getApplicationContext(),"토큰 정보를 받아오는데 실패했음",Toast.LENGTH_LONG).show();
                //Logger.e("failed to get access token info. msg=" + errorResult);
            }

            @Override
            public void onSuccess(AccessTokenInfoResponse accessTokenInfoResponse) {
                id = Long.toString(accessTokenInfoResponse.getUserId());
            }
        });
    }



        public String getId() {
        return id;
    }
}
