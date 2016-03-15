package com.gac.aeromanager.priority.handler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;

import com.gac.aeromanager.DBHandler;
import com.gac.aeromanager.hasilakhir.handler.HasilAkhirViewSqlHandler;
import com.gac.aeromanager.hasilakhir.model.HasilAkhirDetailModel;
import com.gac.aeromanager.priority.model.PriorityModel;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;

import java.util.ArrayList;

/**
 * Created by arifcebe on 8/30/14.
 * class ini digunakan untuk menampung method yang menghandle
 * data di sqlite yang  digunakan oleh class PriorityPutraActivity dan PriorityPutriActivit
 * method2 yang ada di class ini dapat dipanggil dari kedua class tersebut
 *
 */
public class PriorityUtils {

    /**
     * this method to show priority athlete on your team
     * example : PriorityUtils.showPriority(SEX,listView,context)
     * @param jk -> sex
     */
    public static void showPriority(String jk,ListView lv,Context context){
        //instantiate
        PriorityViewSqlHandler viewSqlHandler = new PriorityViewSqlHandler(context);
        ArrayList<PriorityModel> item = new ArrayList<PriorityModel>();
        PriorityAdapter adapter = new PriorityAdapter(item,context);
        /* end instantiate */

        /* listview set adapter */
        /* set animation */
        AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(adapter);
        animationAdapter.setAbsListView(lv);
        lv.setAdapter(animationAdapter);
        /*lv.setAdapter(adapter);*/

        item.clear();
        ArrayList<PriorityModel> listAtlet = viewSqlHandler.getAtletOnPriority(jk);
        for(int i = 0;i < listAtlet.size();i++){
            item.add(listAtlet.get(i));
        }
        adapter.notifyDataSetChanged();
    }


}
