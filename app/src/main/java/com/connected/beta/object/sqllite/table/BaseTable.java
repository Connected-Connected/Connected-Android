package com.connected.beta.object.sqllite.table;

import com.connected.beta.library.sqllite.BaseColumn;

import java.util.ArrayList;

/**
 * Created by x-note on 2017-02-15.
 */

public class BaseTable {


    static protected String createTable (String tableName, ArrayList<BaseColumn> columns){

        StringBuilder sCreateTable = new StringBuilder("CREATE TABLE " + tableName + "(\n");

        int colSize = columns.size();
        for( int i=0; i < colSize; i++ ) {
            BaseColumn column = columns.get(i);
            sCreateTable.append(column.getColumnName());
            sCreateTable.append("\t");
            sCreateTable.append(column.getColumnType());
            sCreateTable.append("\t");
            sCreateTable.append(column.getColumnConstraint());

            if(i < colSize - 1)
                sCreateTable.append(",\n");
        }
        sCreateTable.append(");");
        return sCreateTable.toString();
    }

    static protected String deleteTable(String tableName){
        return "DROP TABLE IF EXISTS " + tableName;
    }


}
