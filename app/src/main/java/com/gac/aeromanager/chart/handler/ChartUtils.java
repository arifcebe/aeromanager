package com.gac.aeromanager.chart.handler;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gac.aeromanager.Config;
import com.gac.aeromanager.R;
import com.gac.aeromanager.hasilakhir.handler.HasilAkhirViewSqlHandler;
import com.gac.aeromanager.hasilakhir.model.HasilAkhirDetailModel;
import com.gac.aeromanager.priority.handler.PriorityAdapter;
import com.gac.aeromanager.priority.handler.PriorityViewSqlHandler;
import com.gac.aeromanager.priority.model.PriorityModel;

import java.util.ArrayList;

import it.bradipao.lib.descharts.ChartValue;
import it.bradipao.lib.descharts.ChartValueSerie;
import it.bradipao.lib.descharts.LineChartView;

/**
 * Created by arifcebe on 9/2/14.
 * class ini digunakan untuk menampung method yang akan dipanggil ketika menampilkan graphic
 *
 */
public class ChartUtils {

    /**
     * method ini digunakan untuk menampilkan atlet dari team sendiri
     * method ini menggunakan model dari priority model.
     * karena priorirty udah otomatis menampilkan daftar atlet dari team sendiri
     * di chart
     * @param jk -> jenis kelamin,
     * @param context -> getActivity
     * @return -> ArrayList
     */
    public static ArrayList<PriorityModel> showAtletToChart(String jk,Context context){
        //instantiate
        PriorityViewSqlHandler viewSqlHandler = new PriorityViewSqlHandler(context);
        ArrayList<PriorityModel> item = new ArrayList<PriorityModel>();
        PriorityAdapter adapter = new PriorityAdapter(item,context);
        /* end instantiate */

        ArrayList<PriorityModel> listAtlet = viewSqlHandler.getAtletOnPriority(jk);
        for(int i = 0;i < listAtlet.size();i++){
            item.add(listAtlet.get(i));
        }
        adapter.notifyDataSetChanged();

        return item;
    }


    /**
     * menampilkan score yang telah dilewati oleh atlet
     * method ini mengambil dari hasil akhir, karena bisa
     * menggunakan parameter nodada yang nanti akan dipanggil
     * yang  digunaakn untuk perulangan menampilkan
     * score berdasarkan nodada.
     * @param nodada -> nodada atlet
     * @param context -> getActivity
     * @return -> ArrayList
     */
    public static ArrayList<HasilAkhirDetailModel> showScoreToChart(String nodada,Context context){
        //instantiate
        HasilAkhirViewSqlHandler viewSqlHandler = new HasilAkhirViewSqlHandler(context);
        ArrayList<HasilAkhirDetailModel> item = new ArrayList<HasilAkhirDetailModel>();
        // PriorityAdapter adapter = new PriorityAdapter(item,context);
        /* end instantiate */

        ArrayList<HasilAkhirDetailModel> listAtlet = viewSqlHandler.getDetailRound(nodada);
        for(int i = 0;i < listAtlet.size();i++){
            item.add(listAtlet.get(i));
        }
        //adapter.notifyDataSetChanged();

        return item;
    }

    /**
     * meletakkan hasil pertandingan di chart
     * @param context
     * @param sex       -> jenis kelamin
     * @param layout    -> layout yang digunakan
     * @param chart     -> nama chart
     */
    public static void putScoreOnChart(Context context, String sex,
                                       LinearLayout layout,
                                       LineChartView chart){

        /* color value for every athlete, */
        int[] color = new int[]{context.getResources().getColor(R.color.color_1),
                context.getResources().getColor(R.color.color_2),
                context.getResources().getColor(R.color.color_3),
                context.getResources().getColor(R.color.color_4),
                context.getResources().getColor(R.color.color_5),
                context.getResources().getColor(R.color.color_6),
                context.getResources().getColor(R.color.color_7),
                context.getResources().getColor(R.color.color_8),
                context.getResources().getColor(R.color.color_9),
                context.getResources().getColor(R.color.color_10),
                context.getResources().getColor(R.color.color_11),
                context.getResources().getColor(R.color.color_12),
                context.getResources().getColor(R.color.color_13),
                context.getResources().getColor(R.color.color_14),
                context.getResources().getColor(R.color.color_15),
                context.getResources().getColor(R.color.color_16),
                context.getResources().getColor(R.color.color_17),
                context.getResources().getColor(R.color.color_18),
                context.getResources().getColor(R.color.color_19),
                context.getResources().getColor(R.color.color_20)};

        /* round values, this can loop when select athlete */
        String[] chartValueLabel = new String[]{"1","2","3","4","5","6","7"};

        if(showAtletToChart(sex,context).isEmpty()){
            Config.makeToast(context,"Data Masih Kosong");
            ChartValueSerie cvs = new ChartValueSerie(color[0],3);
            cvs.addPoint(new ChartValue(chartValueLabel[0],0));
            chart.addSerie(cvs);
        }else{
                    //* looping for athlete *//*
            for (int i = 0; i < showAtletToChart(sex, context).size();i++){

                TextView tit = new TextView(context);
            //* to add values on chart *//*
                ChartValueSerie cvs = new ChartValueSerie(color[i],3);
                Config.TRACE("color",""+color[i]);
                String nodada =  showAtletToChart(sex,
                        context).get(i).getNodada().toString();

                for (int j=0; j < chartValueLabel.length;j++){
                    float score = showScoreToChart(nodada,context).get(j).getScore();
                    cvs.addPoint(new ChartValue(chartValueLabel[j],score));
                    Config.TRACE("chart", chartValueLabel[j] + " " + score);
                }

                tit.setTextColor(color[i]);
                tit.setText(showAtletToChart(sex, context).get(i)
                        .getNama().toString());

                layout.addView(tit);
                chart.addSerie(cvs);

            }
        }


    }


}
