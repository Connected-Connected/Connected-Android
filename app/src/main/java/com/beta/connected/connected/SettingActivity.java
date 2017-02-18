package com.beta.connected.connected;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.beta.connected.connected.KakaoLogin.SignupActivity;
import com.beta.connected.connected.MainFragment.Ajax.TmpAjax;
import com.facebook.login.LoginManager;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;

import org.json.JSONArray;

import java.util.Set;

public class SettingActivity extends AppCompatActivity {
    private TmpAjax ajax;

    private Button ajaxType1;
    private Button ajaxType2;
    private Button logout;
    private Button unlink;
    private Button facebookLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        facebookLogout = (Button)findViewById(R.id.facebookLogout);
        logout = (Button)findViewById(R.id.logout);
        unlink = (Button)findViewById(R.id.unlink);

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

        facebookLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();

                Intent intent = new Intent(SettingActivity.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        unlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                                Intent intent = new Intent(SettingActivity.this,SignupActivity.class);
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
            }
        });
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


}
