package com.beta.connected.connected.MainFragment.Ajax;

import android.app.Activity;
import android.util.Log;

import com.androidquery.callback.AjaxCallback;
import com.beta.connected.connected.Ajax.AjaxAdapter;

import org.json.JSONArray;

import java.util.HashMap;

/**
 * Created by x-note on 2017-02-12.
 */

public class TmpAjax extends AjaxAdapter {

    /*
    * 2017-02-12
    * 김지광
    * 퍼미션 체크 관련 Ajax
    */
    private static String getTmpData = "/tmp/getTmpData.jsp";

    public TmpAjax(Activity activity){
        super(activity);
    }


    /*
     * 2017-02-12
     * 김지광
     * 테스트용 ajax
     */
    public void getTmpData(String dataType, AjaxCallback callback){

        //parameter 설정
        HashMap<String, Object> map = new HashMap<>();

        map.put("dataType", dataType);

        //ajax호출
        this.ajax(httpUrl(getTmpData), map, JSONArray.class, callback);
    }

}
