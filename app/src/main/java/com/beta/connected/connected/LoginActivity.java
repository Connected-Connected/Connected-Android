package com.beta.connected.connected;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.beta.connected.connected.Ajax.Login.LoginAjax;
import com.beta.connected.connected.IntroFragment.IntroFragment1;
import com.beta.connected.connected.IntroFragment.IntroFragment2;
import com.beta.connected.connected.IntroFragment.IntroFragment3;
import com.beta.connected.connected.IntroFragment.IntroFragment4;
import com.beta.connected.connected.KakaoLogin.KakaoSignUpActivity;
import com.beta.connected.connected.LoginSessionController.LoginSessionCheck;
import com.beta.connected.connected.Sqlite.ConnectedDbHelper;
import com.beta.connected.connected.Sqlite.Table.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;

import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;

import org.json.JSONException;
import org.json.JSONObject;

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

    private LoginAjax ajax;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ajax = new LoginAjax(this);

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        if (!Session.getCurrentSession().checkAndImplicitOpen()) {
            setContentView(R.layout.activity_login);
        }

        ////////////////////////////////////////////////////////////////////////////


        //region 페이스북 로그인 로직

        // 페이스북 기존 토큰 확인
        FacebookSdk.sdkInitialize(getApplicationContext(), new FacebookSdk.InitializeCallback() {
            @Override
            public void onInitialized() {
                if(com.facebook.AccessToken.getCurrentAccessToken() == null){

                } else {
                    //토큰이 있을 경우
                    String userToken = com.facebook.AccessToken.getCurrentAccessToken().getUserId();
                    facebookLoginLogic(userToken);

                }
            }
        });
        facebookLoginButton = (Button)findViewById(R.id.facebook_login2);

        facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackManager = CallbackManager.Factory.create();

                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this,
                        Arrays.asList("public_profile", "email"));
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult result) {

                        GraphRequest graphRequest = GraphRequest.newMeRequest(result.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                try {

                                    Toast.makeText(getBaseContext(), object.toString(), Toast.LENGTH_LONG).show();
                                    String userToken = object.get("id").toString();

                                    facebookLoginLogic(userToken);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender,birthday");
                        graphRequest.setParameters(parameters);
                        graphRequest.executeAsync();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(getBaseContext(), "Error: " + error, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getBaseContext(), "Cancel: " , Toast.LENGTH_LONG).show();
                        //finish();
                    }
                });
            }
        });

        //endregion


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


    //메인페이지로 이동하는 함수
    public void goToMainActivity(String userToken){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("userToken", userToken);
        startActivity(intent);
        finish();
    }
    //회원가입페이지로 이동하는 함수
    public void goToSignActivity(String userToken, String userImg){
        Intent intent = new Intent(LoginActivity.this, LoginSignUpActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("userToken", userToken);
        bundle.putString("userImg", userImg);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
    //내부 DB에서 user 불러오는 함수
    public User checkUserInfo(String userToken){

        ConnectedDbHelper conn = new ConnectedDbHelper(getBaseContext());
        SQLiteDatabase db = conn.getWritableDatabase();
        User[] users = User.select(db, null, User.COLUMN_NAME_USER_TOKEN +" = ?", new String[] { userToken });

        if(users.length > 0)
            return users[0];
        else
            return null;
    }
    public void facebookLoginLogic(String userToken){
        //내부 DB 체크
        User user = checkUserInfo(userToken);

        //내부 DB에 있을 경우 Main으로 이동
        if(user != null) {
            Toast.makeText(getBaseContext(), user.getName() + "님, 환영합니다.", Toast.LENGTH_LONG).show();
            Toast.makeText(getBaseContext(), "토큰 만료 일 : " + com.facebook.AccessToken.getCurrentAccessToken().getExpires().toString(), Toast.LENGTH_LONG).show();
            goToMainActivity(user.getToken());
        }
        else {

            Toast.makeText(getBaseContext(), "getUserInfo", Toast.LENGTH_LONG).show();

            //서버 DB체크
            ajax.getUserInfo(userToken, new AjaxCallback<JSONObject>(){
                @Override
                public void callback(String url, JSONObject data, AjaxStatus status) {
                    super.callback(url, data, status);

                    try {
                        if(data != null) {
                            //서버 DB에 있을 경우 내부DB에 저장하고 Main으로 이동
                            saveUserInfo(data);
                            goToMainActivity(data.getString("userToken"));

                        }
                        else {
                            //페이스북 키는 있지만 DB에 없을 경우 Sign 페이지로 이동
                            goToSignActivity(com.facebook.AccessToken.getCurrentAccessToken().getUserId(), "");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public void saveUserInfo(JSONObject obj){

        ConnectedDbHelper conn = new ConnectedDbHelper(getBaseContext());
        SQLiteDatabase db = conn.getWritableDatabase();

        User user = new User();
        try {
            user.setToken(obj.getString(User.COLUMN_NAME_USER_TOKEN));
            user.setName(obj.getString(User.COLUMN_NAME_USER_NM));
            user.setImg(obj.getString(User.COLUMN_NAME_USER_IMG));
            user.setSex(obj.getInt(User.COLUMN_NAME_USER_SEX));
            user.setAge(obj.getInt(User.COLUMN_NAME_USER_AGE));
            user.setContry(obj.getString(User.COLUMN_NAME_USER_CONTRY));
            user.setProfile(obj.getString(User.COLUMN_NAME_USER_PROFILE));

            user.insert(db);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
