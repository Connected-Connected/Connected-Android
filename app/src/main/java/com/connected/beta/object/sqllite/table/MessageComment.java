package com.connected.beta.object.sqllite.table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.connected.beta.library.sqllite.BaseColumn;

import java.util.ArrayList;

/**
 * Created by x-note on 2017-02-15.
 */

public class MessageComment extends BaseTable {

    public static final String TABLE_NAME = "MessageComment";
    public static final String COLUMN_NAME_MESSAGE_ID = "MESSAGE_ID";
    public static final String COLUMN_NAME_MESSAGE_CMT_ID = "MESSAGE_CMT_ID";
    public static final String COLUMN_NAME_USER_TOKEN = "USER_TOKEN";
    public static final String COLUMN_NAME_CMT = "CMT";
    public static final String COLUMN_NAME_CREATE_DATE = "CREATE_DATE";

    private static ArrayList<BaseColumn> Columns;

    private String msgId;
    private String msgCmtId;
    private String token;
    private String cmt;
    private String createDate;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgCmtId() {
        return msgCmtId;
    }

    public void setMsgCmtId(String msgCmtId) {
        this.msgCmtId = msgCmtId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }



    public MessageComment(){

    }


    public void insert(SQLiteDatabase db){
        db.insert(TABLE_NAME, null,getContentValue());
    }

    public void update(SQLiteDatabase db){
        db.update(TABLE_NAME, getContentValue(), COLUMN_NAME_MESSAGE_CMT_ID + " = ? ", new String[]{msgCmtId});
    }
    public void delete(SQLiteDatabase db){
        db.delete(TABLE_NAME, COLUMN_NAME_MESSAGE_CMT_ID + " = ? ", new String[]{msgCmtId});
    }

    private ContentValues getContentValue(){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_MESSAGE_ID, msgId);
        values.put(COLUMN_NAME_MESSAGE_CMT_ID, msgCmtId);
        values.put(COLUMN_NAME_USER_TOKEN, token);
        values.put(COLUMN_NAME_CMT, cmt);
        values.put(COLUMN_NAME_CREATE_DATE, createDate);
        return values;
    }

    static public String createTable(){
        Columns = new ArrayList<>();

        Columns.add(
                new BaseColumn(COLUMN_NAME_MESSAGE_ID, BaseColumn.ColumnType.Text)
        );
        Columns.add(
                new BaseColumn(COLUMN_NAME_MESSAGE_CMT_ID, BaseColumn.ColumnType.Text)
        );
        Columns.add(
                new BaseColumn(COLUMN_NAME_USER_TOKEN, BaseColumn.ColumnType.Text)
        );

        Columns.add(
                new BaseColumn(COLUMN_NAME_CMT, BaseColumn.ColumnType.Text)
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
