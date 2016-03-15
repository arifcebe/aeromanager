package com.gac.aeromanager.round.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gac.aeromanager.Config;
import com.gac.aeromanager.DBHandler;
import com.gac.aeromanager.atlet.handler.AtletTableSqlHandler;
import com.gac.aeromanager.round.model.RoundModel;

import java.util.ArrayList;

/**
 * Created by arifcebe on 8/23/14.
 */
public class RoundTableSqlHandler {
    private DBHandler dbHandler;
    private SQLiteDatabase db;

    public static final String TABLE_NAME_ROUND = "tbl_round";

    public static final String COL_NAME_ROUND_ID = "round_id";
    public static final String COL_NAME_ROUND_KE = "round_ke";
    public static final String COL_NAME_NO_DADA = "round_nodada";
    public static final String COL_NAME_SCORE_1 = "round_score_1";
    public static final String COL_NAME_SCORE_2 = "round_score_2";
    public static final String COL_NAME_SCORE = "round_score";
    public static final String COL_NAME_RANK = "round_rank";


    //	query create table
    public static final String CREATE_TABLE_ROUND = "CREATE TABLE "+TABLE_NAME_ROUND+"(" +
            ""+COL_NAME_ROUND_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            ""+COL_NAME_ROUND_KE+" TEXT," +
            ""+COL_NAME_NO_DADA+" TEXT," +
            ""+COL_NAME_SCORE_1+" REAL," +
            ""+COL_NAME_SCORE_2+" REAL," +
            ""+COL_NAME_SCORE+" REAL," +
            ""+COL_NAME_RANK+" TEXT," +
            " FOREIGN KEY("+COL_NAME_NO_DADA+") REFERENCES " +
            ""+AtletTableSqlHandler.TABLE_NAME_ATLET+"("+AtletTableSqlHandler.COL_NAME_NO_DADA+")" +
            " ON UPDATE CASCADE ON DELETE NO ACTION)";

    public RoundTableSqlHandler(Context ctx) {
        dbHandler = new DBHandler(ctx);
    }

    /**
     * add round score into database
     * @param model -> round model
     */
    public void addAtletAndRound(RoundModel model){
        db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_NAME_ROUND_KE,model.getRoundKe());
        values.put(COL_NAME_NO_DADA,model.getNoDada());
        values.put(COL_NAME_SCORE_1,model.getScore_1());
        values.put(COL_NAME_SCORE_2,model.getScore_2());
        values.put(COL_NAME_SCORE,model.getScore());
        values.put(COL_NAME_RANK,model.getRank());

        db.insert(TABLE_NAME_ROUND,null,values);
        db.close();
    }

    /**
     * show all athlete into listview, this method can call in main class activity
     * @param jk -> sex
     * @param round -> round ke
     * @return -> ArrayLIst athlete
     */
    public ArrayList<RoundModel> getAllAtletInRound(String jk,String round){
        ArrayList<RoundModel> listAtlet = new ArrayList<RoundModel>();

        db = dbHandler.getReadableDatabase();
        String query = "SELECT * FROM "+RoundViewSqlHandler.VIEW_NAME_ROUND
                    +" WHERE atlet_jk = '"+jk+"' AND "
                    +" round_ke = '"+round+"' ORDER BY round_score DESC";

        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do{
                RoundModel model = new RoundModel();
                model.setRoundId(cursor.getInt(0));
                model.setNoDada(cursor.getString(2));
                model.setAtletNama(cursor.getString(3));
                model.setTeamNama(cursor.getString(4));
                model.setScore(cursor.getFloat(5));
                model.setTeamStatus(cursor.getString(7));

                listAtlet.add(model);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listAtlet;
    }

    public ArrayList<RoundModel> getAllAtletInRound(String jk){
        ArrayList<RoundModel> listAtlet = new ArrayList<RoundModel>();

        db = dbHandler.getReadableDatabase();
        String query = "SELECT * FROM "+RoundViewSqlHandler.VIEW_NAME_ROUND
                +" WHERE atlet_jk = '"+jk+"' ORDER BY round_score DESC";

        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do{
                RoundModel model = new RoundModel();
                model.setRoundId(cursor.getInt(0));
                model.setNoDada(cursor.getString(2));
                model.setAtletNama(cursor.getString(3));
                model.setTeamNama(cursor.getString(4));
                model.setScore(cursor.getFloat(5));
                model.setTeamStatus(cursor.getString(7));

                listAtlet.add(model);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listAtlet;
    }

    /**
     * cek athlete. where athlete exist or not
     * @param nodada -> number of athlete
     * @param roundKe -> number of round
     * @return -> integer
     */
    public int cekAtlet(String nodada,String roundKe){
     db = dbHandler.getReadableDatabase();
     String query = "SELECT COUNT("+COL_NAME_NO_DADA+") as count FROM "+TABLE_NAME_ROUND
                +" WHERE "+COL_NAME_NO_DADA+" = '"+nodada+"' AND "
                +COL_NAME_ROUND_ID+" = '"+roundKe+"'";
        Cursor cur = db.rawQuery(query, null);
        int jml = cur.getCount();
        cur.close();
        db.close();
        Config.TRACE("count","nodada "+nodada+" round "+roundKe);
        Config.TRACE("count","jumlah "+jml);
        return jml;
    }

    public RoundModel getAtletInRound(int roundId){
        db = dbHandler.getReadableDatabase();
        String query = "SELECT * FROM "+RoundViewSqlHandler.VIEW_NAME_ROUND_SCORE+
                " WHERE round_id = "+roundId+" ";
        Cursor cursor = db.rawQuery(query,null);

        if(cursor != null)
            cursor.moveToFirst();
        RoundModel model = new RoundModel(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getFloat(4),
                cursor.getFloat(5));

        return  model;
    }

    /**
     * update round database
     * @param model -> round model
     */
    public void updateRound(RoundModel model){
        db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_NAME_SCORE_1,model.getScore_1());
        values.put(COL_NAME_SCORE_2,model.getScore_2());
        values.put(COL_NAME_SCORE,model.getScore());

        db.update(TABLE_NAME_ROUND,values,COL_NAME_ROUND_ID+" = ?",
                new String[]{String.valueOf(model.getRoundId())});
        db.close();
    }

    public void updateRank(RoundModel model){
        db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_NAME_RANK,model.getRank());

        db.update(TABLE_NAME_ROUND,values,COL_NAME_ROUND_ID+" = ?",
                new String[]{String.valueOf(model.getRoundId())});
        db.close();
    }

    public ArrayList<RoundModel> getAtletToCheck(String nodada,String round){
        ArrayList<RoundModel> listAtlet = new ArrayList<RoundModel>();

        db = dbHandler.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME_ROUND
                +" WHERE "+COL_NAME_NO_DADA+" = '"+nodada+"' AND "
                +" "+COL_NAME_ROUND_KE+" = '"+round+"'";
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do{
                RoundModel model = new RoundModel();
                model.setRoundId(cursor.getInt(0));
                model.setRoundKe(cursor.getString(1));
                model.setNoDada(cursor.getString(2));
                model.setScore(cursor.getFloat(3));

                listAtlet.add(model);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listAtlet;
    }


}

