package com.gac.aeromanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gac.aeromanager.atlet.handler.AtletTableSqlHandler;
import com.gac.aeromanager.atlet.handler.AtletViewSqlHandler;
import com.gac.aeromanager.hasilakhir.handler.HasilAkhirViewSqlHandler;
import com.gac.aeromanager.priority.handler.PriorityViewSqlHandler;
import com.gac.aeromanager.round.handler.RoundTableSqlHandler;
import com.gac.aeromanager.round.handler.RoundViewSqlHandler;
import com.gac.aeromanager.team.handler.TeamTableSqlHandler;

/**
 * Created by arifcebe on 8/21/14.
 */
public class DBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db_aeromanager.db";

    private static final int DATABASE_VERSION = 1;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String[] createTable = new String[]{TeamTableSqlHandler.CREATE_TABLE_TEAM,
                AtletTableSqlHandler.CREATE_TABLE_ATLET,
                AtletViewSqlHandler.CREATE_VIEW_ATLET,
                RoundTableSqlHandler.CREATE_TABLE_ROUND,
                RoundViewSqlHandler.CREATE_VIEW_ROUND,
                RoundViewSqlHandler.CREATE_VIEW_ROUND_SCORE,
                PriorityViewSqlHandler.CREATE_VIEW_PRIORITY,
                HasilAkhirViewSqlHandler.CREATE_VIEW_HASIL_AKHIR,
                HasilAkhirViewSqlHandler.CREATE_VIEW_DETAIL_HASIL_AKHIR
        };

        for (int i = 0; i < createTable.length; i++) {
            sqLiteDatabase.execSQL(createTable[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
