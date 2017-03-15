package com.connected.beta.object.sqllite.table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.connected.beta.library.sqllite.BaseColumn;

import java.util.ArrayList;

/**
 * Created by x-note on 2017-02-15.
 */

public class Message extends BaseTable {

    public static final String TABLE_NAME = "Message";
    public static final String COLUMN_NAME_MESSAGE_ID = "MESSAGE_ID";
    public static final String COLUMN_NAME_MESSAGE_TYPE = "MESSAGE_TYPE";
    public static final String COLUMN_NAME_ACCESS_CNT = "ACCESS_CNT";
    public static final String COLUMN_NAME_EVENT_KEY = "EVENT_KEY";
    public static final String COLUMN_NAME_CREATE_DATE = "CREATE_DATE";

    private static ArrayList<BaseColumn> Columns;

    private String msgId;
    private String msgType;
    private String accessCnt;
    private String eventKey;
    private String createDate;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getAccessCnt() {
        return accessCnt;
    }

    public void setAccessCnt(String accessCnt) {
        this.accessCnt = accessCnt;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }



    public Message(){

    }

    public void insert(SQLiteDatabase db){
        db.insert(TABLE_NAME, null,getContentValue());
    }

    public void update(SQLiteDatabase db){
        db.update(TABLE_NAME, getContentValue(), COLUMN_NAME_MESSAGE_ID + " = ? ", new String[]{msgId});
    }
    public void delete(SQLiteDatabase db){
        db.delete(TABLE_NAME, COLUMN_NAME_MESSAGE_ID + " = ? ", new String[]{msgId});
    }

    private ContentValues getContentValue(){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_MESSAGE_ID, msgId);
        values.put(COLUMN_NAME_MESSAGE_TYPE, msgType);
        values.put(COLUMN_NAME_ACCESS_CNT, accessCnt);
        values.put(COLUMN_NAME_EVENT_KEY, eventKey);
        values.put(COLUMN_NAME_CREATE_DATE, createDate);
        return values;
    }

    static public String createTable(){
        Columns = new ArrayList<>();

        Columns.add(
                new BaseColumn(COLUMN_NAME_MESSAGE_ID, BaseColumn.ColumnType.Text)
        );
        Columns.add(
                new BaseColumn(COLUMN_NAME_MESSAGE_TYPE, BaseColumn.ColumnType.Text)
        );
        Columns.add(
                new BaseColumn(COLUMN_NAME_ACCESS_CNT, BaseColumn.ColumnType.Text)
        );

        Columns.add(
                new BaseColumn(COLUMN_NAME_EVENT_KEY, BaseColumn.ColumnType.Text)
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
