package com.beta.connected.connected.Sqlite.Columns;

/**
 * Created 김지광 on 2017-02-15.
 */

public class BaseColumn {

    public BaseColumn(){

    }
    public BaseColumn(String columnName, String columnType){
        this(columnName, columnType, "");
    }
    public BaseColumn(String columnName, String columnType, String columnConstraint){
        this.ColumnName = columnName;
        this.ColumnType = columnType;
        this.ColumnConstraint = columnConstraint;
    }

    public String getColumnName() {
        return ColumnName;
    }

    public void setColumnName(String columnName) {
        ColumnName = columnName;
    }

    private String ColumnName;

    public String getColumnType() {
        return ColumnType;
    }

    public void setColumnType(String columnType) {
        ColumnType = columnType;
    }

    private String ColumnType;

    public String getColumnConstraint() {
        return ColumnConstraint;
    }

    public void setColumnConstraint(String columnConstraint) {
        ColumnConstraint = columnConstraint;
    }

    private String ColumnConstraint;


    public static class ColumnType{
        public static final String Int = "INTEGER"; /*1,2,3,4,6,8bytes의 정수값*/
        public static final String Real = "REAL"; /*8bytes의 부동소수점값*/
        public static final String Text = "TEXT"; /*UTF-8, UTF-16BE, UTF-16LE인코딩의 문자열*/
        public static final String Blob = "BLOB"; /*입력된 그대로 저장, 바이너리 파일 등*/
    }

}
