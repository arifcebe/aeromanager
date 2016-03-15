package com.gac.aeromanager.hasilakhir.handler;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gac.aeromanager.hasilakhir.model.HasilAkhirModel;
import com.gac.aeromanager.hasilakhir.activity.HasilAkhirDetailActivity;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;

import java.util.ArrayList;

/**
 * Created by arifcebe on 8/31/14.
 * this class handle sqlite database, and you can call this class
 * on other class when you want to access hasil akhir
 */
public class HasilAkhirUtils {

    /**
     * panggil method ini ketika akan menampilkan
     * hasil akhir dari sqlite
     * @param jk -> sex that you've changed
     * @param lv -> listview
     * @param context -> getActivity
     */
    public static void showHasilAkhir(String jk,ListView lv, final Context context){
       /* instantiate */
        HasilAkhirViewSqlHandler viewSqlHandler = new HasilAkhirViewSqlHandler(context);
        final ArrayList<HasilAkhirModel> item = new ArrayList<HasilAkhirModel>();
        HasilAkhirAdapter adapter = new HasilAkhirAdapter(context,item);

        /* animator adapter */
        AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(adapter);
        animationAdapter.setAbsListView(lv);
        lv.setAdapter(animationAdapter);

        ArrayList<HasilAkhirModel> listAtlet = viewSqlHandler.getAtletOnHasilAkhir(jk);

        item.clear();
        for (int i= 0 ;i < listAtlet.size(); i++ ){
            item.add(listAtlet.get(i));
        }
        adapter.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nodada = item.get(i).getNodada().toString();

                Intent intent = new Intent(context, HasilAkhirDetailActivity.class);
                intent.putExtra("nodada",nodada);
                intent.putExtra("rank",String.valueOf(i+1));
                intent.putExtra("team",item.get(i).getTeam_nama().toString());
                intent.putExtra("nama",item.get(i).getNama().toString());
                intent.putExtra("score",String.valueOf(item.get(i).getScore_total()));
                context.startActivity(intent);

            }
        });
    }

}
