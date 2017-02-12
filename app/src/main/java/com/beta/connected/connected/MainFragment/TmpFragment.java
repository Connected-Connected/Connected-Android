package com.beta.connected.connected.MainFragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.beta.connected.connected.KakaoLogin.SignupActivity;
import com.beta.connected.connected.LoginActivity;
import com.beta.connected.connected.MainActivity;
import com.beta.connected.connected.R;
import com.facebook.login.LoginManager;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;

/**
 * A simple {@link Fragment} subclass.
 */
public class TmpFragment extends Fragment {

    private Button logout;
    private Button unlink;
    private Button facebookLogout;

    public TmpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tmp, container, false);

        facebookLogout = (Button)view.findViewById(R.id.facebookLogout);
        logout = (Button)view.findViewById(R.id.logout);
        unlink = (Button)view.findViewById(R.id.unlink);

        facebookLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();

                Intent intent = new Intent(getActivity(),LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManagement.requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {

                        Intent intent = new Intent(getActivity(),LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });
            }
        });

        unlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String appendMessage = getString(R.string.com_kakao_confirm_unlink);
                new AlertDialog.Builder(getActivity())
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
                                                Intent intent = new Intent(getActivity(),LoginActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                                getActivity().finish();
                                            }

                                            @Override
                                            public void onNotSignedUp() {
                                                Intent intent = new Intent(getActivity(),SignupActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                                getActivity().finish();
                                            }

                                            @Override
                                            public void onSuccess(Long result) {
                                                Intent intent = new Intent(getActivity(),LoginActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                                getActivity().finish();
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

        return view;
    }

}
