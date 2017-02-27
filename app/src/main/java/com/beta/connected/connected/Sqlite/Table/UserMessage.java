package com.beta.connected.connected.Sqlite.Table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.beta.connected.connected.Sqlite.Columns.BaseColumn;

import java.util.ArrayList;

/**
 * Created by x-note on 2017-02-15.
 */

public class UserMessage extends BaseTable {
    public static final String TABLE_NAME = "UserMessage";
    public static final String COLUMN_NAME_MESSAGE_ID = "MESSAGE_ID";
    public static final String COLUMN_NAME_USER_TOKEN = "USER_TOKEN";
    public static final String COLUMN_NAME_POSS_AUTH = "POSS_AUTH";
    public static final String COLUMN_NAME_ALARM = "ALARM";
    public static final String COLUMN_NAME_REQUEST_STATUS = "REQUEST_STATUS";
    public static final String COLUMN_NAME_REQUEST_CMT = "REQUEST_CMT";

    private static ArrayList<BaseColumn> Columns;

    private String messageId;
    private String token;
    private int auth;
    private int alarm;
    private int status;
    private String cmt;


    public UserMessage(){

    }


    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public int getAlarm() {
        return alarm;
    }

    public void setAlarm(int alarm) {
        this.alarm = alarm;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
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
        values.put(COLUMN_NAME_MESSAGE_ID, messageId);
        values.put(COLUMN_NAME_POSS_AUTH, auth);
        values.put(COLUMN_NAME_ALARM, alarm);
        values.put(COLUMN_NAME_REQUEST_STATUS, status);
        values.put(COLUMN_NAME_REQUEST_CMT, cmt);

        return values;
    }

    static public String createTable(){
        Columns = new ArrayList<>();

        Columns.add(
                new BaseColumn(COLUMN_NAME_USER_TOKEN, BaseColumn.ColumnType.Text)
        );

        Columns.add(
                new BaseColumn(COLUMN_NAME_MESSAGE_ID, BaseColumn.ColumnType.Text)
        );
        Columns.add(
                new BaseColumn(COLUMN_NAME_POSS_AUTH, BaseColumn.ColumnType.Int)
        );
        Columns.add(
                new BaseColumn(COLUMN_NAME_ALARM, BaseColumn.ColumnType.Int)
        );
        Columns.add(
                new BaseColumn(COLUMN_NAME_REQUEST_STATUS, BaseColumn.ColumnType.Int)
        );
        Columns.add(
                new BaseColumn(COLUMN_NAME_REQUEST_CMT, BaseColumn.ColumnType.Text)
        );
        return createTable(TABLE_NAME, Columns);
    }

    static public String deleteTable(){
        return deleteTable(TABLE_NAME);
    }

}
