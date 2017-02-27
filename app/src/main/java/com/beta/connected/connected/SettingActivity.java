package com.beta.connected.connected;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.beta.connected.connected.KakaoLogin.KakaoSignUpActivity;
import com.beta.connected.connected.LoginSessionController.LoginSessionCheck;
import com.beta.connected.connected.MainFragment.Ajax.TmpAjax;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.AuthService;
import com.kakao.auth.network.response.AccessTokenInfoResponse;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import org.json.JSONArray;

public class SettingActivity extends AppCompatActivity {

    private String id;

    private Toolbar toolbar;

    private TmpAjax ajax;

    private Button ajaxType1;
    private Button ajaxType2;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        //////////////////////////////


        if (AccessToken.getCurrentAccessToken() != null) {
            id = AccessToken.getCurrentAccessToken().getUserId();
            Toast.makeText(getApplicationContext(),"" +id,Toast.LENGTH_LONG).show();
        }else{
            requestAccessToken();
        }


        /*
        loginInfo = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        loginSessionCheck = new LoginSessionCheck(this,loginInfo.getInt("loginInfo",0));

        loginSessionCheck.checkLogin();
        Toast.makeText(getApplicationContext(),"id =" + loginSessionCheck.getId(),Toast.LENGTH_LONG).show();

        loginInfo = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        */

        logout = (Button)findViewById(R.id.logout);

        ajax = new TmpAjax(this);
        /*
	  * 2017-02-12
	  * 김지광
	  * Ajax Test
	  */
        ajaxType1 = (Button)findViewById(R.id.ajax_type1);
        ajaxType2 = (Button)findViewById(R.id.ajax_type2);
        ajaxType1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajaxTest("type1");
            }
        });
        ajaxType2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajaxTest("type2");
            }
        });

        /*
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginSessionCheck = new LoginSessionCheck(this);

                if (loginSessionCheck.facebookCheckLogin()) {
                    id = AccessToken.getCurrentAccessToken().getUserId();
                    Toast.makeText(getApplicationContext(),"" +id,Toast.LENGTH_LONG).show();
                }else{
                    requestAccessToken();
                }
                if(loginInfo.getInt("loginInfo",0) == 1){
                    LoginManager.getInstance().logOut();

                    Intent intent = new Intent(SettingActivity.this,LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }else if(loginInfo.getInt("loginInfo",0) == 2){
                    UserManagement.requestLogout(new LogoutResponseCallback() {
                        @Override
                        public void onCompleteLogout() {
                            SharedPreferences.Editor editor = loginInfo.edit();
                            editor.putInt("loginInfo", 0); //First라는 key값으로 infoFirst 데이터를 저장한다.
                            editor.apply();

                            Intent intent = new Intent(SettingActivity.this,LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }
                    });
                } else{
                    Toast.makeText(getApplicationContext(),"이 상황은 므지.",Toast.LENGTH_LONG).show();

                    SharedPreferences.Editor editor = loginInfo.edit();
                    editor.putInt("loginInfo", 0); //First라는 key값으로 infoFirst 데이터를 저장한다.
                    editor.apply();

                    Intent intent = new Intent(SettingActivity.this,LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    finish();
                }
            }
        });

*/
        /*
        unlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(loginInfo.getInt("loginInfo",0) == 1){
                    LoginManager.getInstance()

                    Intent intent = new Intent(SettingActivity.this,LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }else if(loginInfo.getInt("loginInfo",0) == 2){
                    final String appendMessage = getString(R.string.com_kakao_confirm_unlink);
                    new AlertDialog.Builder(SettingActivity.this)
                            .setMessage(appendMessage)
                            .setPositiveButton(getString(R.string.com_kakao_ok_button),
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            UserManagement.requestUnlink(new UnLinkResponseCallback() {
                                                @Override
                                                public void onFailure(ErrorResult errorResult) {
                                                }

                                                @Override
                                                public void onSessionClosed(ErrorResult errorResult) {
                                                    Intent intent = new Intent(SettingActivity.this,LoginActivity.class);
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(intent);
                                                    finish();
                                                }

                                                @Override
                                                public void onNotSignedUp() {
                                                    Intent intent = new Intent(SettingActivity.this,KakaoSignUpActivity.class);
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(intent);
                                                    finish();
                                                }

                                                @Override
                                                public void onSuccess(Long result) {
                                                    Intent intent = new Intent(SettingActivity.this,LoginActivity.class);
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            });
                                            dialog.dismiss();
                                        }
                                    })
                            .setNegativeButton(getString(R.string.com_kakao_cancel_button),
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    }).show();
                } else{
                    Intent intent = new Intent(SettingActivity.this,LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    finish();
                }
            }
        });
        */
    }

    public void logout(View v){
        if (AccessToken.getCurrentAccessToken() != null) {
            LoginManager.getInstance().logOut();

            Intent intent = new Intent(SettingActivity.this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }else{
            requestAccessTokenAndLogout();
        }
    }

    private void ajaxTest(String type)
    {
        ajax.getTmpData(type, new AjaxCallback<JSONArray>(){
            @Override
            public void callback(String url, JSONArray data, AjaxStatus status) {
                super.callback(url, data, status);
                if(data != null) {
                    Toast.makeText(getApplicationContext(), data.toString(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "인터넷 연결을 확인하세요", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void requestAccessToken() {

        AuthService.requestAccessTokenInfo(new ApiResponseCallback<AccessTokenInfoResponse>() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Toast.makeText(getApplicationContext(),"카카이톡 토큰이 만료 됬습니다.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
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

    public void requestAccessTokenAndLogout() {

        AuthService.requestAccessTokenInfo(new ApiResponseCallback<AccessTokenInfoResponse>() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Toast.makeText(getApplicationContext(),"카카이톡 토큰이 만료 됬습니다.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
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
                UserManagement.requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        Intent intent = new Intent(SettingActivity.this,LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                });
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
