package com.connected.beta.library.connection.web;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/*
 * Created by admin on 2016-04-11.
 *///dd
public class Connect {
    private URL url = null;

    public Connect(){
    }

    public Connect(String in_url){
        try {
            url = new URL(in_url);
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
    }

    public static Boolean isNetWork(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {

            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();

            if (info != null) {
                for (NetworkInfo anInfo : info) {
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public URL getURL(){
        return url;
    }

    public static JSONObject post(URL url){
        DataOutputStream printout;
        try {
            URLConnection urlConn = url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.connect();


            HttpURLConnection c = (HttpURLConnection) urlConn;
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();

                    return new JSONObject(sb.toString());
            }
        }catch (JSONException e) {
            e.printStackTrace();
            return null;
        }catch (MalformedURLException e){
            e.printStackTrace();
            return null;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static JSONArray post2(URL url){
        DataOutputStream printout;
        try {
            URLConnection urlConn = url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.connect();


            HttpURLConnection c = (HttpURLConnection) urlConn;
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();

                    return new JSONArray(sb.toString());
            }
        }catch (JSONException e) {
            e.printStackTrace();
            return null;
        }catch (MalformedURLException e){
            e.printStackTrace();
            return null;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static String post3(URL url){
        DataOutputStream printout;
        try {
            URLConnection urlConn = url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.connect();


            HttpURLConnection c = (HttpURLConnection) urlConn;
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();

                    return sb.toString();
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
            return null;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }


    public static Document post4(URL url){
        DataOutputStream printout;
        try {
            URLConnection urlConn = url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.connect();


            HttpURLConnection c = (HttpURLConnection) urlConn;
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();

                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder;
                    try
                    {
                        builder = factory.newDocumentBuilder();
                        Document doc = builder.parse( new InputSource( new StringReader(sb.toString()) ) );
                        return doc;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }

            }
        }catch (MalformedURLException e){
            e.printStackTrace();
            return null;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static JSONObject postJsonObject(URL url, JSONObject json){
        DataOutputStream printout;
        try {
            URLConnection urlConn = url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.connect();

            printout = new DataOutputStream(urlConn.getOutputStream ());
            String str = json.toString();
            byte[] data=str.getBytes("UTF-8");
            printout.write(data);
            printout.flush();
            printout.close();

            HttpURLConnection c = (HttpURLConnection) urlConn;
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();

                    return new JSONObject(sb.toString());
            }
        }catch (JSONException e) {
            e.printStackTrace();
            return null;
        }catch (MalformedURLException e){
            e.printStackTrace();
            return null;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static JSONArray postJsonArray(URL url, JSONObject json){
        DataOutputStream printout;
        try {
            URLConnection urlConn = url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.connect();

            printout = new DataOutputStream(urlConn.getOutputStream ());
            String str = json.toString();
            byte[] data=str.getBytes("UTF-8");
            printout.write(data);
            printout.flush();
            printout.close();

            HttpURLConnection c = (HttpURLConnection) urlConn;
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();

                    return new JSONArray(sb.toString());
            }
        }catch (JSONException e) {
            e.printStackTrace();
            return null;
        }catch (MalformedURLException e){
            e.printStackTrace();
            return null;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static String postString(URL url, JSONObject json){
        DataOutputStream printout;
        try {
            URLConnection urlConn = url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.connect();

            printout = new DataOutputStream(urlConn.getOutputStream ());
            String str = json.toString();
            byte[] data=str.getBytes("UTF-8");
            printout.write(data);
            printout.flush();
            printout.close();

            HttpURLConnection c = (HttpURLConnection) urlConn;
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();

                    return sb.toString();
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
            return null;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }
}