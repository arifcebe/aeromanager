package com.gac.aeromanager.hasilakhir.handler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gac.aeromanager.DBHandler;
import com.gac.aeromanager.atlet.handler.AtletTableSqlHandler;
import com.gac.aeromanager.hasilakhir.model.HasilAkhirModel;
import com.gac.aeromanager.hasilakhir.model.HasilAkhirDetailModel;
import com.gac.aeromanager.round.handler.RoundTableSqlHandler;
import com.gac.aeromanager.team.handler.TeamTableSqlHandler;

import java.util.ArrayList;

/**
 * Created by arifcebe on 8/31/14.
 */
public class HasilAkhirViewSqlHandler {
    /* view name */
    public static final String VIEW_NAME_HASIL_AKHIR = "view_hasil_akhir";
    public static final String VIEW_NAME_DETAIL_HASIL_AKHIR = "view_detail_hasil_akhir";

    /* view for hasil akhir */
    public static final String CREATE_VIEW_HASIL_AKHIR = "CREATE VIEW "+VIEW_NAME_HASIL_AKHIR+" " +
            "AS SELECT a."+ AtletTableSqlHandler.COL_NAME_NO_DADA+"," +
            "a."+AtletTableSqlHandler.COL_NAME_NAMA+"," +
            "t."+ TeamTableSqlHandler.COL_NAME_TEAM+", " +
            "sum(r."+ RoundTableSqlHandler.COL_NAME_SCORE+") AS score, " +
            "t."+TeamTableSqlHandler.COL_NAME_STATUS+", " +
            "a."+AtletTableSqlHandler.COL_NAME_JK+" " +
            "FROM "+AtletTableSqlHandler.TABLE_NAME_ATLET+" AS a " +
            "JOIN "+TeamTableSqlHandler.TABLE_NAME_TEAM+" AS t " +
            "ON a."+AtletTableSqlHandler.COL_NAME_TEAM+" = t."+TeamTableSqlHandler.COL_NAME_ID+" " +
            "JOIN "+RoundTableSqlHandler.TABLE_NAME_ROUND+" AS r " +
            "ON r."+RoundTableSqlHandler.COL_NAME_NO_DADA+" = a."+AtletTableSqlHandler.COL_NAME_NO_DADA+" " +
            "GROUP BY " +
            "a."+ AtletTableSqlHandler.COL_NAME_NO_DADA+"," +
            "a."+AtletTableSqlHandler.COL_NAME_NAMA+"," +
            "t."+ TeamTableSqlHandler.COL_NAME_TEAM+", " +
            "t."+TeamTableSqlHandler.COL_NAME_STATUS+", " +
            "a."+AtletTableSqlHandler.COL_NAME_JK+" " +
            "ORDER BY sum(r."+ RoundTableSqlHandler.COL_NAME_SCORE+") DESC";

    /* view for detail hasil akhir */
    public static final String CREATE_VIEW_DETAIL_HASIL_AKHIR = "CREATE VIEW "+VIEW_NAME_DETAIL_HASIL_AKHIR+"" +
            " AS SELECT r."+RoundTableSqlHandler.COL_NAME_ROUND_KE+"," +
            "a."+AtletTableSqlHandler.COL_NAME_NO_DADA+"," +
            "a."+AtletTableSqlHandler.COL_NAME_NAMA+"," +
            "t."+TeamTableSqlHandler.COL_NAME_TEAM+"," +
            "r."+RoundTableSqlHandler.COL_NAME_SCORE+", " +
            "r."+RoundTableSqlHandler.COL_NAME_RANK+" " +
            "FROM "+AtletTableSqlHandler.TABLE_NAME_ATLET+" AS a " +
            "JOIN "+TeamTableSqlHandler.TABLE_NAME_TEAM+" AS t " +
            "ON a."+AtletTableSqlHandler.COL_NAME_TEAM+" = t."+TeamTableSqlHandler.COL_NAME_ID+" " +
            "JOIN "+RoundTableSqlHandler.TABLE_NAME_ROUND+" AS r " +
            "ON r."+RoundTableSqlHandler.COL_NAME_NO_DADA+" = a."+AtletTableSqlHandler.COL_NAME_NO_DADA+"";

    private DBHandler dbHandler;
    private SQLiteDatabase db;

    public HasilAkhirViewSqlHandler(Context context) {
        this.dbHandler = new DBHandler(context);
    }

    /**
     * to show all atlet in final result menu
     * @param jk -> sex when you change male or female
     * @return listAtlet ->
     */
    public ArrayList<HasilAkhirModel> getAtletOnHasilAkhir(String jk){
        ArrayList<HasilAkhirModel> listAtlet = new ArrayList<HasilAkhirModel>();

        db = dbHandler.getReadableDatabase();
        String query = "SELECT * FROM "+ VIEW_NAME_HASIL_AKHIR
                +" WHERE atlet_jk = '"+jk+"'" ;
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do{
                HasilAkhirModel model = new HasilAkhirModel();
                model.setNodada(cursor.getString(0));
                model.setNama(cursor.getString(1));
                model.setTeam_nama(cursor.getString(2));
                model.setScore_total(cursor.getFloat(3));
                model.setTeam_status(cursor.getString(4));
                model.setJenKel(cursor.getString(5));

                listAtlet.add(model);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listAtlet;
    }

    /**
     * show array list for detail round
     * @param nodada
     * @return
     */
    public ArrayList<HasilAkhirDetailModel> getDetailRound(String nodada){
        ArrayList<HasilAkhirDetailModel> listRound = new ArrayList<HasilAkhirDetailModel>();

        db = dbHandler.getReadableDatabase();
        String query = "SELECT * FROM "+VIEW_NAME_DETAIL_HASIL_AKHIR
                + " WHERE atlet_no_dada = '"+nodada+"' ";
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                HasilAkhirDetailModel model = new HasilAkhirDetailModel();
                model.setRoundKe(cursor.getString(0));
                model.setNodada(cursor.getString(1));
                model.setNama(cursor.getString(2));
                model.setTeam(cursor.getString(3));
                model.setScore(cursor.getFloat(4));
                model.setRank(cursor.getString(5));

                listRound.add(model);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listRound;
    }

}
