package com.gac.aeromanager.round.handler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gac.aeromanager.DBHandler;
import com.gac.aeromanager.atlet.handler.AtletTableSqlHandler;
import com.gac.aeromanager.team.handler.TeamTableSqlHandler;

/**
 * Created by arifcebe on 8/23/14.
 */
public class RoundViewSqlHandler {
    private DBHandler dbHandler;
    private SQLiteDatabase db;

    public RoundViewSqlHandler(Context context) {
        // TODO Auto-generated constructor stub
        dbHandler = new DBHandler(context);
    }


    //	get table name from atlet and team
    public static final String TABLE_ATLET = AtletTableSqlHandler.TABLE_NAME_ATLET;
    public static final String TABLE_ROUND = RoundTableSqlHandler.TABLE_NAME_ROUND;
    public static final String TABLE_TEAM = TeamTableSqlHandler.TABLE_NAME_TEAM;

    public static final String VIEW_NAME_ROUND = "view_round";
    public static final String VIEW_NAME_ROUND_SCORE = "view_round_score";
    //	get col name from view atlet
    public static final String VIEW_COL_ROUND_NODADA = TABLE_ROUND+"."+RoundTableSqlHandler.COL_NAME_NO_DADA;
    public static final String VIEW_COL_NAMA = TABLE_ATLET+"."+AtletTableSqlHandler.COL_NAME_NAMA;
    public static final String VIEW_COL_JK = TABLE_ATLET+"."+AtletTableSqlHandler.COL_NAME_JK;
    public static final String VIEW_COL_TEAM_ID = TABLE_ATLET+"."+AtletTableSqlHandler.COL_NAME_TEAM;


    // get col from team
    public static final String VIEW_COL_TEAM = TABLE_TEAM+"."+TeamTableSqlHandler.COL_NAME_TEAM;
    public static final String VIEW_COL_TEAM_STATUS = TABLE_TEAM+"."+TeamTableSqlHandler.COL_NAME_STATUS;

    //	get col name from Round
    public static final String VIEW_COL_ROUND_ID = TABLE_ROUND+"."+RoundTableSqlHandler.COL_NAME_ROUND_ID;
    public static final String VIEW_COL_ROUND_KE = TABLE_ROUND+"."+RoundTableSqlHandler.COL_NAME_ROUND_KE;
    public static final String VIEW_COL_SCORE_1 = TABLE_ROUND+"."+RoundTableSqlHandler.COL_NAME_SCORE_1;
    public static final String VIEW_COL_SCORE_2 = TABLE_ROUND+"."+RoundTableSqlHandler.COL_NAME_SCORE_2;
    public static final String VIEW_COL_SCORE = TABLE_ROUND+"."+RoundTableSqlHandler.COL_NAME_SCORE;
    public static final String VIEW_COL_RANK = TABLE_ROUND+"."+RoundTableSqlHandler.COL_NAME_RANK;


    // get col no dada from view atlet
    public static final String VIEW_COL_ATLET_NODADA= TABLE_ATLET+"."+AtletTableSqlHandler.COL_NAME_NO_DADA;
    public static final String VIEW_COL_TEAM_TEAM_ID = TABLE_TEAM+"."+TeamTableSqlHandler.COL_NAME_ID;

    // create view round
    public static final String CREATE_VIEW_ROUND = "CREATE VIEW "+VIEW_NAME_ROUND+" AS "
            + "SELECT "+VIEW_COL_ROUND_ID+","
            + VIEW_COL_ROUND_KE+","
            + VIEW_COL_ROUND_NODADA+","
            + VIEW_COL_NAMA+","
            + VIEW_COL_TEAM+","
            + VIEW_COL_SCORE+","
            + VIEW_COL_JK+", "
            + VIEW_COL_TEAM_STATUS
            + " FROM "
            + TABLE_ROUND +" JOIN "+ TABLE_ATLET
            + " ON "
            + VIEW_COL_ROUND_NODADA +" = "+ VIEW_COL_ATLET_NODADA
            + " JOIN "+TABLE_TEAM
            + " ON "
            + VIEW_COL_TEAM_ID +" = "+VIEW_COL_TEAM_TEAM_ID;

    // create view for round, when show up form score
    public static final String CREATE_VIEW_ROUND_SCORE = "CREATE VIEW "+VIEW_NAME_ROUND_SCORE+" AS "
            + "SELECT "+VIEW_COL_ROUND_ID+", "
            + VIEW_COL_ROUND_NODADA+","
            + VIEW_COL_NAMA+","
            + VIEW_COL_TEAM+","
            + VIEW_COL_SCORE_1+","
            + VIEW_COL_SCORE_2
            + " FROM "
            + TABLE_ROUND +" JOIN "+TABLE_ATLET
            + " ON "
            + VIEW_COL_ROUND_NODADA +" = "+ VIEW_COL_ATLET_NODADA
            + " JOIN "+TABLE_TEAM
            + " ON "
            + VIEW_COL_TEAM_ID +" = "+VIEW_COL_TEAM_TEAM_ID;

}
