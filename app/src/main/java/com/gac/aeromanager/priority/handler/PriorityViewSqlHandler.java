package com.gac.aeromanager.priority.handler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gac.aeromanager.DBHandler;
import com.gac.aeromanager.atlet.handler.AtletTableSqlHandler;
import com.gac.aeromanager.priority.model.PriorityModel;
import com.gac.aeromanager.round.handler.RoundTableSqlHandler;
import com.gac.aeromanager.team.handler.TeamTableSqlHandler;

import java.util.ArrayList;

/**
 * Created by arifcebe on 8/30/14.
 */
public class PriorityViewSqlHandler {
    public static final String VIEW_NAME_PRIORITY = "view_priority";
    public static final String CREATE_VIEW_PRIORITY = "CREATE VIEW "+VIEW_NAME_PRIORITY+" AS " +
            "SELECT a."+ AtletTableSqlHandler.COL_NAME_NO_DADA+"," +
            "a."+AtletTableSqlHandler.COL_NAME_NAMA+"," +
            "sum(r."+ RoundTableSqlHandler.COL_NAME_SCORE+") as score," +
            "a."+AtletTableSqlHandler.COL_NAME_JK+", " +
            "t."+TeamTableSqlHandler.COL_NAME_TEAM+" " +
            "FROM "+AtletTableSqlHandler.TABLE_NAME_ATLET+" AS a " +
            "JOIN "+RoundTableSqlHandler.TABLE_NAME_ROUND+" AS r " +
            "ON a."+AtletTableSqlHandler.COL_NAME_NO_DADA+" = " +
            "r."+RoundTableSqlHandler.COL_NAME_NO_DADA+" " +
            "JOIN "+ TeamTableSqlHandler.TABLE_NAME_TEAM+" AS t " +
            "ON a."+AtletTableSqlHandler.COL_NAME_TEAM+" = " +
            "t."+TeamTableSqlHandler.COL_NAME_ID+" " +
            "WHERE t."+TeamTableSqlHandler.COL_NAME_STATUS+" = 'Sendiri' " +
            "GROUP BY a."+AtletTableSqlHandler.COL_NAME_NAMA+"," +
            "a."+AtletTableSqlHandler.COL_NAME_NAMA+"," +
            "a."+AtletTableSqlHandler.COL_NAME_JK+" " +
            "ORDER BY sum(r."+ RoundTableSqlHandler.COL_NAME_SCORE+")  DESC";

    private DBHandler dbHandler;
    private SQLiteDatabase db;

    public PriorityViewSqlHandler(Context context) {
        this.dbHandler = new DBHandler(context);
    }

    /**
     * to show all atlet in priority menu
     * @param jk -> sex when you change male or female
     * @return
     */
    public ArrayList<PriorityModel> getAtletOnPriority(String jk){
        ArrayList<PriorityModel> listAtlet = new ArrayList<PriorityModel>();

        db = dbHandler.getReadableDatabase();
        String query = "SELECT * FROM "+ PriorityViewSqlHandler.VIEW_NAME_PRIORITY
                +" WHERE atlet_jk = '"+jk+"'" ;
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do{
                PriorityModel model = new PriorityModel();
                model.setNodada(cursor.getString(0));
                model.setNama(cursor.getString(1));
                model.setScore(cursor.getFloat(2));
                model.setJenKel(cursor.getString(3));
                model.setTeamNama(cursor.getString(4));

                listAtlet.add(model);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listAtlet;
    }
}
