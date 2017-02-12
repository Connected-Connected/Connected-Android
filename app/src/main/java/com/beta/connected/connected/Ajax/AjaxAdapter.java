package com.beta.connected.connected.Ajax;

/**
 * Created by 김지광 on 2017-02-12.
 */

import android.app.Activity;

import com.androidquery.AQuery;
import com.beta.connected.connected.R;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by 김지광 on 2017-02-12.
 */
public class AjaxAdapter {
    protected AQuery aq;
    protected Activity activity;
    /*
	  * 2017-02-12
	  * 김지광
	  * 서버와의 통신을 위한 AQuery 공통함수
	  */
    public AjaxAdapter(){

    }
    public AjaxAdapter(Activity activity){
        this.activity = activity;
        aq = new AQuery(activity);
    }
    /*
	  * 2017-02-12
	  * 김지광
	  * 통신 url 받아오기
	  */
    protected String httpUrl(){
        return activity.getResources().getString(R.string.http_url);
    }

    /*
	  * 2017-02-12
	  * 김지광
	  * 통신 url 받아오기
	  * path : 하위디렉토리 경로
	  */
    protected String httpUrl(String path){
        return activity.getResources().getString(R.string.http_url) + path;
    }

    protected String decoding(String str)
    {
        String enStr = str;
        try {
            enStr = URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return enStr;
    }

    protected String encoding(String str)
    {
        String enStr = str;
        try {
            enStr = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return enStr;
    }
}
