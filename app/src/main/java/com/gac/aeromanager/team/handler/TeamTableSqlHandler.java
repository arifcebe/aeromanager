package com.gac.aeromanager.team.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gac.aeromanager.DBHandler;
import com.gac.aeromanager.team.model.TeamModel;

import java.util.ArrayList;

/**
 * Created by arifcebe on 8/21/14.
 */
public class TeamTableSqlHandler {
    private DBHandler dbHandler;
    private SQLiteDatabase db;

    public static final String TABLE_NAME_TEAM = "tbl_team";

    public static final String COL_NAME_ID = "team_id";
    public static final String COL_NAME_TEAM = "team_nama";
    public static final String COL_NAME_STATUS = "team_status";

    public static final String CREATE_TABLE_TEAM = "CREATE TABLE "+TABLE_NAME_TEAM+"("
            + ""+COL_NAME_ID+" INTEGER PRIMARY KEY autoincrement,"
            + ""+COL_NAME_TEAM+" TEXT,"
            + ""+COL_NAME_STATUS+" TEXT)";

    public TeamTableSqlHandler(Context ctx) {
        dbHandler = new DBHandler(ctx);
    }

    /**
     * add team to sqlite database
     * @param team -> team model, which had previously declared
     */
    public void addTeam(TeamModel team){
        db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        //team = new TeamModel(team_id, team_nama);

        //values.put(COL_NAME_ID, team.getTeam_id());
        values.put(COL_NAME_TEAM, team.getTeam_nama());
        values.put(COL_NAME_STATUS, team.getTeam_status());

        db.insert(TABLE_NAME_TEAM, null, values);
        db.close();
    }

    /**
     * this method is to show all team from sqlite
     * @return array team
     */
    public ArrayList<TeamModel> getAllTeam() {
        ArrayList<TeamModel> arrayTeam = new ArrayList<TeamModel>();

        db = dbHandler.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME_TEAM;
        Cursor cur = db.rawQuery(query, null);

        if (cur.moveToFirst()) {
            do {
                TeamModel team = new TeamModel();
                team.setTeam_id(cur.getInt(0));
                team.setTeam_nama(cur.getString(1));
                team.setTeam_status(cur.getString(2));

                arrayTeam.add(team);
            } while (cur.moveToNext());

        }
        cur.close();
        db.close();

        return arrayTeam;
    }


    public Cursor cursorQuery(){
        db = dbHandler.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME_TEAM;
        Cursor cur = db.rawQuery(selectQuery, null);
        return cur;

    }

    /**
     * this method is to get team by id. usually used to call team id when edited before
     * @param id -> team id selected
     * @return the result is team model
     */
    public TeamModel getTeam(int id){
        db = dbHandler.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME_TEAM+" WHERE "+COL_NAME_ID+" = ? ";
        Cursor cur = db.rawQuery(query, new String[]{String.valueOf(id)});

        if(cur != null)
            cur.moveToFirst();
        TeamModel tm = new TeamModel(Integer.parseInt(cur.getString(0)),
                cur.getString(1),
                cur.getString(2));

        return tm;
    }

    /**
     *
     * @param tm -> team model which had been declared before
     */
    public void updateTeam(TeamModel tm) {
        // TODO Auto-generated method stub
        db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME_TEAM, tm.getTeam_nama());
        values.put(COL_NAME_STATUS,tm.getTeam_status());

        db.update(TABLE_NAME_TEAM, values, COL_NAME_ID + " = ? ",
                new String[]{String.valueOf(tm.getTeam_id())});
        db.close();
    }

    public void deleteTeam(TeamModel tm){
        db = dbHandler.getWritableDatabase();
        db.delete(TABLE_NAME_TEAM, COL_NAME_ID + " = ?", new String[]{String.valueOf(tm.getTeam_id() )});
        db.close();
    }
}
