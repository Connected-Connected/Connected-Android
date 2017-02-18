package com.beta.connected.connected.Sqlite.Table;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.beta.connected.connected.Sqlite.Columns.BaseColumn;

import java.util.ArrayList;

/**
 * Created by x-note on 2017-02-15.
 */

public class User extends BaseTable {

    public static final String TABLE_NAME = "User";
    public static final String COLUMN_NAME_USER_TOKEN = "USER_TOKEN";
    public static final String COLUMN_NAME_USER_ID = "USER_ID";
    public static final String COLUMN_NAME_USER_NM = "USER_NM";
    public static final String COLUMN_NAME_USER_IMG = "COLUMN_NAME_USER_IMG";
    public static final String COLUMN_NAME_USER_MAIL = "COLUMN_NAME_USER_MAIL";
    public static final String COLUMN_NAME_USER_AGE = "COLUMN_NAME_USER_AGE";
    public static final String COLUMN_NAME_USER_CONTRY = "COLUMN_NAME_USER_CONTRY";
    public static final String COLUMN_NAME_USER_PROFILE = "COLUMN_NAME_USER_PROFILE";

    private static ArrayList<BaseColumn> Columns;

    private String token;
    private String id;
    private String img;
    private String mail;
    private int age;
    private String contry;
    private String profile;


    public User(){

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContry() {
        return contry;
    }

    public void setContry(String contry) {
        this.contry = contry;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
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

    static public User[] select(SQLiteDatabase db) {
        return select(db, null, null, null, null);
    }
    static public User[] select(SQLiteDatabase db, String[] tableColumns) {
        return select(db, tableColumns, null, null, null);
    }
    static public User[] select(SQLiteDatabase db, String[] tableColumns, String whereClause, String[] whereArgs) {
        return select(db, tableColumns, whereClause, whereArgs, null);
    }
    static public User[] select(SQLiteDatabase db, String[] tableColumns, String whereClause, String[] whereArgs, String orderBy){


        Cursor curUsers = db.query(TABLE_NAME, tableColumns, whereClause, whereArgs,
                null, null, orderBy);

        return convertCursorToUser(curUsers);

    }

    private static User[] convertCursorToUser(Cursor curUsers) {

        ArrayList<User> users = new ArrayList<>();

        User user;
        while (curUsers.moveToNext())
        {
            user = new User();


            user.setToken(curUsers.getString(curUsers.getColumnIndex(User.COLUMN_NAME_USER_TOKEN)));
            user.setId(curUsers.getString(curUsers.getColumnIndex(User.COLUMN_NAME_USER_ID)));
            user.setImg(curUsers.getString(curUsers.getColumnIndex(User.COLUMN_NAME_USER_IMG)));
            user.setMail(curUsers.getString(curUsers.getColumnIndex(User.COLUMN_NAME_USER_MAIL)));
            user.setAge(curUsers.getInt(curUsers.getColumnIndex(User.COLUMN_NAME_USER_AGE)));
            user.setContry(curUsers.getString(curUsers.getColumnIndex(User.COLUMN_NAME_USER_CONTRY)));
            user.setProfile(curUsers.getString(curUsers.getColumnIndex(User.COLUMN_NAME_USER_PROFILE)));

            users.add(user);
        }


        return (User[]) users.toArray();
    }

    private ContentValues getContentValue(){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_USER_TOKEN, token);
        values.put(COLUMN_NAME_USER_ID, id);
        values.put(COLUMN_NAME_USER_ID, img);
        values.put(COLUMN_NAME_USER_ID, mail);
        values.put(COLUMN_NAME_USER_ID, age);
        values.put(COLUMN_NAME_USER_ID, contry);
        values.put(COLUMN_NAME_USER_ID, profile);
        return values;
    }

    static public String createTable(){
        Columns = new ArrayList<>();

        Columns.add(
                new BaseColumn(COLUMN_NAME_USER_TOKEN, BaseColumn.ColumnType.Text)
        );

        Columns.add(
                new BaseColumn(COLUMN_NAME_USER_ID, BaseColumn.ColumnType.Text)
        );
        Columns.add(
                new BaseColumn(COLUMN_NAME_USER_NM, BaseColumn.ColumnType.Text)
        );
        Columns.add(
                new BaseColumn(COLUMN_NAME_USER_IMG, BaseColumn.ColumnType.Text)
        );

        Columns.add(
                new BaseColumn(COLUMN_NAME_USER_MAIL, BaseColumn.ColumnType.Text)
        );
        Columns.add(
                new BaseColumn(COLUMN_NAME_USER_AGE, BaseColumn.ColumnType.Int)
        );
        Columns.add(
                new BaseColumn(COLUMN_NAME_USER_CONTRY, BaseColumn.ColumnType.Text)
        );
        Columns.add(
                new BaseColumn(COLUMN_NAME_USER_PROFILE, BaseColumn.ColumnType.Text)
        );
        return createTable(TABLE_NAME, Columns);
    }

    static public String deleteTable(){
        return deleteTable(TABLE_NAME);
    }
}

