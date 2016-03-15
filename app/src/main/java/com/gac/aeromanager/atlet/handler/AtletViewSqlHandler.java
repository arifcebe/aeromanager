package com.gac.aeromanager.atlet.handler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gac.aeromanager.DBHandler;
import com.gac.aeromanager.team.handler.TeamTableSqlHandler;

/**
 * Created by arifcebe on 8/23/14.
 */
public class AtletViewSqlHandler {
    private DBHandler dbHandler;
    private SQLiteDatabase db;

    public AtletViewSqlHandler(Context context) {
        // TODO Auto-generated constructor stub
        dbHandler = new DBHandler(context);
    }


    //	get table name from atlet and team
    public static final String TABLE_TEAM = TeamTableSqlHandler.TABLE_NAME_TEAM;
    public static final String TABLE_ATLET = AtletTableSqlHandler.TABLE_NAME_ATLET;

    public static final String VIEW_NAME_ATLET = "view_atlet";

    //	get col name from atlet
    public static final String VIEW_COL_NODADA = TABLE_ATLET+"."+AtletTableSqlHandler.COL_NAME_NO_DADA;
    public static final String VIEW_COL_NAMA = TABLE_ATLET+"."+AtletTableSqlHandler.COL_NAME_NAMA;
    public static final String VIEW_COL_JK = TABLE_ATLET+"."+AtletTableSqlHandler.COL_NAME_JK;
    //	get col name from team
    public static final String VIEW_COL_TEAM= TABLE_TEAM+"."+TeamTableSqlHandler.COL_NAME_TEAM;
    public static final String VIEW_COL_TEAM_ID= TABLE_TEAM+"."+TeamTableSqlHandler.COL_NAME_ID;
    public static final String VIEW_COL_TEAM_STATUS= TABLE_TEAM+"."+TeamTableSqlHandler.COL_NAME_STATUS;

    public static final String VIEW_COL_ATLET_TEAM_ID= TABLE_ATLET+"."+AtletTableSqlHandler.COL_NAME_TEAM;

    //	create view atlet
    public static final String CREATE_VIEW_ATLET = "CREATE VIEW "+VIEW_NAME_ATLET+" AS "
            +"SELECT "+VIEW_COL_NODADA+","
            +VIEW_COL_NAMA+","
            +VIEW_COL_JK+","
            +VIEW_COL_TEAM+","
            +VIEW_COL_TEAM_ID+","
            +VIEW_COL_TEAM_STATUS
            +" FROM "
            +TABLE_ATLET+" JOIN "+TABLE_TEAM
            +" ON "
            +VIEW_COL_TEAM_ID +" = "+VIEW_COL_ATLET_TEAM_ID;
}
