package com.connected.beta.object.sqllite.table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.connected.beta.library.sqllite.BaseColumn;

import java.util.ArrayList;

/**
 * Created by x-note on 2017-02-15.
 */

public class UserChat extends BaseTable {
    public static final String TABLE_NAME = "UserChat";
    public static final String COLUMN_NAME_CHATROOM_ID = "CHATROOM_ID";
    public static final String COLUMN_NAME_POSS_AUTH = "POSS_AUTH";
    public static final String COLUMN_NAME_ALARM = "ALARM";
    public static final String COLUMN_NAME_USER_TOKEN = "USER_TOKEN";

    private static ArrayList<BaseColumn> Columns;

    private String token;
    private int alarm;
    private int auth;
    private String chatId;

    public UserChat(){

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getAlarm() {
        return alarm;
    }

    public void setAlarm(int alarm) {
        this.alarm = alarm;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public void insert(SQLiteDatabase db){
        db.insert(TABLE_NAME, null,getContentValue());
    }

    public void update(SQLiteDatabase db){
        db.update(TABLE_NAME, getContentValue(), COLUMN_NAME_USER_TOKEN + " = ? ", new String[]{token});
    }
    public void delete(SQLiteDatabase db){
        db.delete(TABLE_NAME, COLUMN_NAME_USER_TOKEN + " = ? ", new String[]{token});
    }


    private ContentValues getContentValue(){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_USER_TOKEN, token);
        values.put(COLUMN_NAME_CHATROOM_ID, chatId);
        values.put(COLUMN_NAME_POSS_AUTH, auth);
        values.put(COLUMN_NAME_ALARM, alarm);
        return values;
    }

    static public String createTable(){
        Columns = new ArrayList<>();

        Columns.add(
                new BaseColumn(COLUMN_NAME_USER_TOKEN, BaseColumn.ColumnType.Text)
        );

        Columns.add(
                new BaseColumn(COLUMN_NAME_CHATROOM_ID, BaseColumn.ColumnType.Text)
        );
        Columns.add(
                new BaseColumn(COLUMN_NAME_POSS_AUTH, BaseColumn.ColumnType.Int)
        );
        Columns.add(
                new BaseColumn(COLUMN_NAME_ALARM, BaseColumn.ColumnType.Int)
        );
        return createTable(TABLE_NAME, Columns);
    }

    static public String deleteTable(){
        return deleteTable(TABLE_NAME);
    }
}
