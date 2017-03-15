package com.connected.beta.object.sqllite.table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.connected.beta.library.sqllite.BaseColumn;

import java.util.ArrayList;

/**
 * Created by x-note on 2017-02-15.
 */

public class ChatRoom extends BaseTable {

    public static final String TABLE_NAME = "Chatting";
    public static final String COLUMN_NAME_CHATROOM_ID = "CHATROOM_ID";
    public static final String COLUMN_NAME_USER_CNT = "USER_CNT";
    public static final String COLUMN_NAME_CREATE_DATE = "CREATE_DATE";

    private static ArrayList<BaseColumn> Columns;

    private String chatRoomId;
    private String userCnt;
    private String createDate;

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getUserCnt() {
        return userCnt;
    }

    public void setUserCnt(String userCnt) {
        this.userCnt = userCnt;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }



    public ChatRoom(){

    }

    public void insert(SQLiteDatabase db){
        db.insert(TABLE_NAME, null,getContentValue());
    }

    public void update(SQLiteDatabase db){
        db.update(TABLE_NAME, getContentValue(), COLUMN_NAME_CHATROOM_ID + " = ? ", new String[]{chatRoomId});
    }
    public void delete(SQLiteDatabase db){
        db.delete(TABLE_NAME, COLUMN_NAME_CHATROOM_ID + " = ? ", new String[]{chatRoomId});
    }

    private ContentValues getContentValue(){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_CHATROOM_ID, chatRoomId);
        values.put(COLUMN_NAME_USER_CNT, userCnt);
        values.put(COLUMN_NAME_CREATE_DATE, createDate);
        return values;
    }

    static public String createTable(){
        Columns = new ArrayList<>();

        Columns.add(
                new BaseColumn(COLUMN_NAME_CHATROOM_ID, BaseColumn.ColumnType.Text)
        );
        Columns.add(
                new BaseColumn(COLUMN_NAME_USER_CNT, BaseColumn.ColumnType.Text)
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
