/**
 * Copyright 2014-2015 Kakao Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beta.connected.connected.KakaoLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.beta.connected.connected.LoginActivity;
import com.beta.connected.connected.MainActivity;
import com.beta.connected.connected.R;
import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

import java.util.Map;

/**
 * 유효한 세션이 있다는 검증 후
 * me를 호출하여 가입 여부에 따라 가입 페이지를 그리던지 Main 페이지로 이동 시킨다.
 */
public class SignupActivity extends AppCompatActivity {
    /**
     * Main으로 넘길지 가입 페이지를 그릴지 판단하기 위해 me를 호출한다.
     * @param savedInstanceState 기존 session 정보가 저장된 객체
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestMe();
    }

    private void requestSignUp(final Map<String, String> properties) {
        UserManagement.requestSignup(new ApiResponseCallback<Long>() {
            @Override
            public void onNotSignedUp() {
            }

            @Override
            public void onSuccess(Long result) {
                requestMe();
            }

            @Override
            public void onFailure(ErrorResult errorResult) {
                final String message = "UsermgmtResponseCallback : failure : " + errorResult;
                com.kakao.util.helper.log.Logger.w(message);
                //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
            }
        }, properties);
    }

    /**
     * 사용자의 상태를 알아 보기 위해 me API 호출을 한다.
     */
    protected void requestMe() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;

                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    //Toast.makeText(getApplicationContext(), getString(R.string.error_message_for_service_unavailable), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
            }

            @Override
            public void onSuccess(UserProfile userProfile) {
                Intent intent = new Intent(SignupActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
            }

            @Override
            public void onNotSignedUp() {

            }
        });
    }
}
