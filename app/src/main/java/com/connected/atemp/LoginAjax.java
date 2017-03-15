package com.connected.atemp;

import android.app.Activity;

import com.androidquery.callback.AjaxCallback;
import com.connected.beta.library.connection.web.AjaxAdapter;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by x-note on 2017-02-20.
 */

public class LoginAjax extends AjaxAdapter {

    /*
    * 2017-02-20
    * 김지광
    * 가입 관련 Ajax
    */
    private static String checkOverlapOfName = "/connected/loginsignup.jsp";
    private static String saveUserInfo = "/connected/loginsignup.jsp";
    private static String getUserInfo = "/connected/loginsignup.jsp";

    public LoginAjax(Activity activity){

        super(activity);

    }


    /*
     * 2017-02-20
     * 김지광
     * 이름중복체크 ajax
     */
    public void checkOverlapUserName(String userNm, AjaxCallback callback){

        //parameter 설정
        HashMap<String, Object> map = new HashMap<>();

        map.put("method", "checkOverlapUserName");


        try {
            userNm = URLEncoder.encode(userNm, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        map.put("userNm", userNm);

        //ajax호출
        this.ajax(httpUrl(checkOverlapOfName), map, String.class, callback);
    }

    public void saveUserInfo(String userToken, String userNm,String userImg, int userSex, int userAge, String userContry, String userProfile, AjaxCallback callback)
    {

        //parameter 설정
        HashMap<String, Object> map = new HashMap<>();
        map.put("method", "saveUserInfo");
        try {
            userToken = URLEncoder.encode(userToken, "UTF-8");
            userNm = URLEncoder.encode(userNm, "UTF-8");
            userImg = URLEncoder.encode(userImg, "UTF-8");
            userContry = URLEncoder.encode(userContry, "UTF-8");
            userProfile = URLEncoder.encode(userProfile, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("userToken", userToken);
        map.put("userNm", userNm);
        map.put("userImg", userImg);
        map.put("userSex", userSex);
        map.put("userAge", userAge);
        map.put("userContry", userContry);
        map.put("userProfile", userProfile);

        //ajax호출
        this.ajax(httpUrl(saveUserInfo), map, String.class, callback);
    }

    public void getUserInfo(String userToken, AjaxCallback callback){

        //parameter 설정
        HashMap<String, Object> map = new HashMap<>();
        map.put("method", "getUserInfo");
        try {
            userToken = URLEncoder.encode(userToken, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("userToken", userToken);
        //ajax호출
        this.ajax(httpUrl(getUserInfo), map, JSONObject.class, callback);
    }
}
