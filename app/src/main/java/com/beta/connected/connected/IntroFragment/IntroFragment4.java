package com.beta.connected.connected.IntroFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.beta.connected.connected.Connection.WebHook;
import com.beta.connected.connected.IntroActivity;
import com.beta.connected.connected.KakaoLogin.SampleLoginActivity;
import com.beta.connected.connected.KakaoLogin.SignupActivity;
import com.beta.connected.connected.LoginActivity;
import com.beta.connected.connected.MainActivity;
import com.beta.connected.connected.R;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFragment4 extends Fragment {
    private Button btLogin;

    public IntroFragment4() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_intro_fragment4, container, false);

        btLogin = (Button)view.findViewById(R.id.btLogin);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
        return view;
    }

}
