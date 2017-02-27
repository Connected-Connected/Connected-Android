package com.beta.connected.connected;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.beta.connected.connected.Connection.WebHook;
import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.ProfileTracker;

public class SignUpActivity extends AppCompatActivity {

    private ProfileTracker profileTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final Uri[] thumbNailImage = new Uri[1];
        String userId = null;

        if(AccessToken.getCurrentAccessToken() != null) {
            profileTracker = new ProfileTracker() {

                @Override
                protected void onCurrentProfileChanged(
                        Profile oldProfile,
                        Profile currentProfile) {

                        thumbNailImage[0] = currentProfile.getProfilePictureUri(300,300); // --> 유저 이미지 갖고 오는거
                }
            };

            userId = AccessToken.getCurrentAccessToken().getUserId(); // 유저 아이디 갖고 오는거
        }else{
            //로그인이 안되 있음
        }

        Toast.makeText(getApplicationContext(),"thumbNailImage uri = " + thumbNailImage[0] + " user id = " + userId,Toast.LENGTH_LONG).show();
        //여기서 작업해.


        //그리고 액티비티 이동할 때나 onDestroy()에 profileTracker.stopTracking(); 이거 꼭 넣어줘.
    }
}
