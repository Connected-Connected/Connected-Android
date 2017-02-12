package com.beta.connected.connected;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.beta.connected.connected.Connection.WebHook;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;

import com.beta.connected.connected.KakaoLogin.SampleLoginActivity;
import com.beta.connected.connected.KakaoLogin.SignupActivity;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    private SessionCallback callback;

    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private Button facebookLoginButton;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        if (!Session.getCurrentSession().checkAndImplicitOpen()) {
            setContentView(R.layout.activity_login);
        }

        ////////////////////////////////////////////////////////////////////////////

        facebookLoginButton = (Button)findViewById(R.id.facebook_login2);

        facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackManager = CallbackManager.Factory.create();

                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this,
                        Arrays.asList("public_profile", "email"));
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult result) {

                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                        /*
                        GraphRequest request;
                        request = GraphRequest.newMeRequest(result.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject user, GraphResponse response) {
                                if (response.getError() != null) {

                                } else {
                                    Log.i("TAG", "user: " + user.toString());
                                    Log.i("TAG", "AccessToken: " + result.getAccessToken().getToken());
                                    setResult(RESULT_OK);

                                    Intent i = new Intent(MainActivity.this, SampleActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            }
                        });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender,birthday");
                        request.setParameters(parameters);
                        request.executeAsync();
                        */
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.e("test", "Error: " + error);
                        //finish();
                    }

                    @Override
                    public void onCancel() {
                        //finish();
                    }
                });
            }
        });

        ////////////////////////////////////////////////////////////////////////////


        /*
        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton)findViewById(R.id.facebook_login);
        loginButton.setReadPermissions("email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {


                ProfileTracker profileTracker = new ProfileTracker() {

                    @Override
                    protected void onCurrentProfileChanged(
                            Profile oldProfile,
                            Profile currentProfile) {
                        // App code

                        new WebHook().execute(currentProfile.getProfilePictureUri(30,30) + "페북 사진",null,null);
                        new WebHook().execute(currentProfile.getId() + " 페북 아아디",null,null);
                        new WebHook().execute(currentProfile.getName() + "페북 이름",null,null);


                    }
                };

                new WebHook().execute(loginResult.getAccessToken().toString() + "토큰 정보",null,null);
                new WebHook().execute(loginResult.getAccessToken().getUserId() + "토큰 유저 아이디",null,null);
                new WebHook().execute(loginResult.getAccessToken().getPermissions() + "토큰 권한",null,null);


                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.v("result",object.toString());
                    }
                });



                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

                //Bundle parameters = new Bundle();
                //parameters.putString("fields", "id,name,email,gender,birthday");
                //graphRequest.setParameters(parameters);
                //graphRequest.executeAsync();


            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.e("LoginErr",error.toString());
            }
        });
*/

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            Toast.makeText(getApplicationContext(),"네이버 로그인 request code = " +requestCode,Toast.LENGTH_LONG).show();
            if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
                return;
            }
        }else{
            Toast.makeText(getApplicationContext(),"페북 로그인 request code = " +requestCode,Toast.LENGTH_LONG).show();
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }

            setContentView(R.layout.activity_login);
        }
    }
}
