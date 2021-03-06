package com.connected.beta.library.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.connected.beta.object.sqllite.table.User;

/**
 * Created by x-note on 2017-02-13.
 */

public class ConnectedDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "/mnt/sdcard/connected.db";

    public ConnectedDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDbTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        deleteDbTable(db);
        this.onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.onUpgrade(db, oldVersion, newVersion);
    }

    /* Create Table */
    private void createDbTable(SQLiteDatabase db){
        db.execSQL(User.createTable());
//        db.execSQL(ChatRoom.createTable());

    }

    /* Delete Table */
    private void deleteDbTable(SQLiteDatabase db){
        db.execSQL(User.deleteTable());
        //db.execSQL(ChatRoom.deleteTable());
    }
}
