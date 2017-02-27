package com.beta.connected.connected;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.beta.connected.connected.IntroFragment.IntroFragment1;
import com.beta.connected.connected.IntroFragment.IntroFragment2;
import com.beta.connected.connected.IntroFragment.IntroFragment3;
import com.beta.connected.connected.IntroFragment.IntroFragment4;
import com.beta.connected.connected.KakaoLogin.KakaoSignUpActivity;
import com.beta.connected.connected.LoginSessionController.LoginSessionCheck;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;

import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private SessionCallback callback;

    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private Button facebookLoginButton;

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 4;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager viewPager;
    private TabLayout tabLayout;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter viewPagerAdapter;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (AccessToken.getCurrentAccessToken() != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            callback = new SessionCallback();
            Session.getCurrentSession().addCallback(callback);
            if (!Session.getCurrentSession().checkAndImplicitOpen()) {
                setContentView(R.layout.activity_login);
            }
        }

        ////////////////////////////////////////////////////////////////////////////

        facebookLoginButton = (Button)findViewById(R.id.facebook_login2);

        facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackManager = CallbackManager.Factory.create();

                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this,
                        Arrays.asList("public_profile"));

                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult result) {
                        /*
                        페이스북 정보 뽑아오기.
                        new WebHook().execute("페북 사진 " + Profile.getCurrentProfile().getProfilePictureUri(300,300),null,null);
                        new WebHook().execute("토큰 정보 " + result.getAccessToken().toString(),null,null);
                        new WebHook().execute("토큰 유저 아이디 " + result.getAccessToken().getUserId(),null,null);
                        new WebHook().execute("토큰 권한 " + result.getAccessToken().getPermissions(),null,null);
                        */

                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(getApplicationContext(),"페이스북 정보를 얻어오는 도중 에러가 발생하였습니다. 다시 시도해야 주십시오.",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancel() {
                    }
                });
            }
        });

        ////////////////////////////////////////////////////////////////////////////

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager, true);
        //viewPager.addOnPageChangeListener(new DetailOnPageChangeListener());

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
            //Toast.makeText(getApplicationContext(),"카카오 로그인 request code = " +requestCode,Toast.LENGTH_LONG).show();
            if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
                return;
            }
        }else{
            //Toast.makeText(getApplicationContext(),"페북 로그인 request code = " +requestCode,Toast.LENGTH_LONG).show();
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
            Intent intent = new Intent(LoginActivity.this, KakaoSignUpActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                //exception을 찍어준다.
            }

            setContentView(R.layout.activity_login);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                return new IntroFragment1();
            }else if(position == 1){
                return new IntroFragment2();
            }else if(position == 2){
                return new IntroFragment3();
            }else{
                return new IntroFragment4();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public class DetailOnPageChangeListener extends ViewPager.SimpleOnPageChangeListener {

        private int currentPage;

        @Override
        public void onPageSelected(int position) {
            /*
            currentPage = position;

            if(currentPage == 3){
                tabLayout.setVisibility(TabLayout.INVISIBLE);
            }else{
                tabLayout.setVisibility(TabLayout.VISIBLE);
            }
            */
        }

        public final int getCurrentPage() {
            return currentPage;
        }
    }
}
