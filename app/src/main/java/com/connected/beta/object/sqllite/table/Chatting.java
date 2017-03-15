package com.connected.beta.object.sqllite.table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.connected.beta.library.sqllite.BaseColumn;

import java.util.ArrayList;

/**
 * Created by x-note on 2017-02-15.
 */

public class Chatting extends BaseTable {

    public static final String TABLE_NAME = "Chatting";
    public static final String COLUMN_NAME_CHATROOM_ID = "CHATROOM_ID";
    public static final String COLUMN_NAME_CHATTING_ID = "CHATTING_ID";
    public static final String COLUMN_NAME_USER_TOKEN = "USER_TOKEN";
    public static final String COLUMN_NAME_MSG = "MSG";
    public static final String COLUMN_NAME_READ_CNT = "READ_CNT";
    public static final String COLUMN_NAME_CREATE_DATE = "CREATE_DATE";

    private static ArrayList<BaseColumn> Columns;

    private String chatRoomId;
    private String chatId;
    private String token;
    private String msg;
    private String readCnt;
    private String createDate;

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getReadCnt() {
        return readCnt;
    }

    public void setReadCnt(String readCnt) {
        this.readCnt = readCnt;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }



    public Chatting(){

    }

    public void insert(SQLiteDatabase db){
        db.insert(TABLE_NAME, null,getContentValue());
    }

    public void update(SQLiteDatabase db){
        db.update(TABLE_NAME, getContentValue(), COLUMN_NAME_CHATTING_ID + " = ? ", new String[]{chatId});
    }
    public void delete(SQLiteDatabase db){
        db.delete(TABLE_NAME, COLUMN_NAME_CHATTING_ID + " = ? ", new String[]{chatId});
    }

    private ContentValues getContentValue(){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_CHATROOM_ID, chatRoomId);
        values.put(COLUMN_NAME_CHATTING_ID, chatId);
        values.put(COLUMN_NAME_USER_TOKEN, token);
        values.put(COLUMN_NAME_MSG, msg);
        values.put(COLUMN_NAME_READ_CNT, readCnt);
        values.put(COLUMN_NAME_CREATE_DATE, createDate);
        return values;
    }

    static public String createTable(){
        Columns = new ArrayList<>();

        Columns.add(
                new BaseColumn(COLUMN_NAME_CHATROOM_ID, BaseColumn.ColumnType.Text)
        );
        Columns.add(
                new BaseColumn(COLUMN_NAME_CHATTING_ID, BaseColumn.ColumnType.Text)
        );
        Columns.add(
                new BaseColumn(COLUMN_NAME_USER_TOKEN, BaseColumn.ColumnType.Text)
        );

        Columns.add(
                new BaseColumn(COLUMN_NAME_MSG, BaseColumn.ColumnType.Text)
        );

        Columns.add(
                new BaseColumn(COLUMN_NAME_READ_CNT, BaseColumn.ColumnType.Text)
        );

        Columns.add(
                new BaseColumn(COLUMN_NAME_CREATE_DATE, BaseColumn.ColumnType.Text)
        );

        return createTable(TABLE_NAME, Columns);
    }

    static public String deleteTable(){
        return deleteTable(TABLE_NAME);
    }

}
