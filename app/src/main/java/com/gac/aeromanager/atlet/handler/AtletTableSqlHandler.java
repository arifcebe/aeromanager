package com.gac.aeromanager.atlet.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gac.aeromanager.DBHandler;
import com.gac.aeromanager.atlet.model.AtletModel;
import com.gac.aeromanager.team.handler.TeamTableSqlHandler;

import java.util.ArrayList;

/**
 * Created by arifcebe on 8/23/14.
 */
public class AtletTableSqlHandler {
    private DBHandler dbHandler;
    private SQLiteDatabase db;

    public static final String TABLE_NAME_ATLET = "tbl_atlet";

    public static final String COL_NAME_NO_DADA = "atlet_no_dada";
    public static final String COL_NAME_NAMA = "atlet_nama";
    public static final String COL_NAME_JK = "atlet_jk";
    public static final String COL_NAME_TEAM = "atlet_team_id";

    //	query create table
    public static final String CREATE_TABLE_ATLET = "CREATE TABLE "+TABLE_NAME_ATLET+"("
            + ""+COL_NAME_NO_DADA+" TEXT PRIMARY KEY,"
            + ""+COL_NAME_NAMA+" TEXT,"
            + ""+COL_NAME_JK+" TEXT,"
            + ""+COL_NAME_TEAM+" INTEGER," +
            " FOREIGN KEY("+COL_NAME_TEAM+") REFERENCES " +
                ""+ TeamTableSqlHandler.TABLE_NAME_TEAM+"("+TeamTableSqlHandler.COL_NAME_ID+")" +
            " ON UPDATE CASCADE ON DELETE NO ACTION)";

    public AtletTableSqlHandler(Context ctx) {
        dbHandler = new DBHandler(ctx);
    }


    public void addAtlet(AtletModel model){
        db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        //team = new TeamModel(team_id, team_nama);

        //values.put(COL_NAME_ID, team.getTeam_id());
        values.put(COL_NAME_NO_DADA, model.getNo_dada());
        values.put(COL_NAME_NAMA, model.getNama());
        values.put(COL_NAME_JK, model.getJenKel());
        values.put(COL_NAME_TEAM, model.getTeam_id());

        db.insert(TABLE_NAME_ATLET, null, values);
        db.close();
    }

    /**
     * this method is to show all team from sqlite
     * @return array team
     */
    public ArrayList<AtletModel>  getAllAtlet(String jk) {
        ArrayList<AtletModel> array = new ArrayList<AtletModel>();

        db = dbHandler.getReadableDatabase();
        String query = null;
        Cursor cur = null;
        if (jk.equals("")){
            query = "SELECT * FROM "+ AtletTableSqlHandler.TABLE_NAME_ATLET;
        }else {
            query = "SELECT * FROM " + AtletViewSqlHandler.VIEW_NAME_ATLET
                    + " WHERE " + COL_NAME_JK + " = '" + jk + "'";
        }
        cur = db.rawQuery(query, null);

        if (cur.moveToFirst()) {
            do {
                AtletModel model = new AtletModel();
                model.setNo_dada(cur.getString(0));
                model.setNama(cur.getString(1));
                model.setJenKel(cur.getString(2));
                model.setTeam_nama(cur.getString(3));
                model.setTeam_status(cur.getString(5));

                array.add(model);
            } while (cur.moveToNext());

        }
        cur.close();
        db.close();

        return array;
    }

    /**
     * to count atlet that registered on aeromanager
     * @param nodada
     * @return
     */
    public int getCountAtlet(String nodada){
        db = dbHandler.getReadableDatabase();
        String query = "SELECT * FROM "+AtletViewSqlHandler.VIEW_NAME_ATLET
                +" WHERE "+COL_NAME_NO_DADA+" = '"+nodada+"'";
        Cursor cur = db.rawQuery(query, null);
        int jml = cur.getCount();
        cur.close();
        db.close();

        return jml;
    }

    /**
     * get atlet
     * @param nodada
     * @return
     */
    public AtletModel getAtlet(String nodada){
        db = dbHandler.getReadableDatabase();
        String query = "SELECT * FROM "+AtletViewSqlHandler.VIEW_NAME_ATLET
                +" WHERE "+COL_NAME_NO_DADA+" = '"+nodada+"'";
        Cursor cursor = db.rawQuery(query,null);

        if (cursor !=null)
            cursor.moveToFirst();
            AtletModel am = new AtletModel(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5));

            return am;

    }

    /**
     * update atlet
     * @param model
     */
    public void updateAtlet(AtletModel model) {
        // TODO Auto-generated method stub
        db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_NAME_NO_DADA,model.getNo_dada());
        values.put(COL_NAME_NAMA,model.getNama());
        values.put(COL_NAME_JK,model.getJenKel());
        values.put(COL_NAME_TEAM,model.getTeam_id());

        db.update(TABLE_NAME_ATLET, values, COL_NAME_NO_DADA + " = ? ",
                new String[]{String.valueOf(model.getNo_dada())});
        db.close();
    }

    /**
     * delete atlhete by atlet id
     * @param model
     */
    public void deleteAtlet(AtletModel model){
        db = dbHandler.getWritableDatabase();
        db.delete(TABLE_NAME_ATLET, COL_NAME_NO_DADA + " = ?", new String[]{String.valueOf(model.getNo_dada() )});
        db.close();
    }

    /**
     * delete all atlet
     */
    public void deleteAllAtlet(){
        db = dbHandler.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME_ATLET);
        db.close();
    }


}
