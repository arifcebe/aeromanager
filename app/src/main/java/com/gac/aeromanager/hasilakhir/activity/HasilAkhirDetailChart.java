package com.gac.aeromanager.hasilakhir.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gac.aeromanager.Config;
import com.gac.aeromanager.R;
import com.gac.aeromanager.chart.handler.ChartUtils;

import it.bradipao.lib.descharts.ChartValue;
import it.bradipao.lib.descharts.ChartValueSerie;
import it.bradipao.lib.descharts.LineChartView;

/**
 * Created by arifcebe on 9/26/14.
 */
public class HasilAkhirDetailChart extends Fragment {
    private LineChartView lineChartView;
    private String nodada;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hasil_akhir_detail_chart,null);

        lineChartView = (LineChartView) view.findViewById(R.id.chart);
        nodada = getActivity().getIntent().getStringExtra("nodada");
        showChart(getActivity(),lineChartView,nodada);
        return view;
    }

    public static void showChart(Context context, LineChartView chart,String nodada){

        /* color value for every athlete, */
        int[] color = new int[]{context.getResources().getColor(R.color.color_1)};

        /* round values, this can loop when select athlete */
        String[] chartValueLabel = new String[]{"1","2","3","4","5","6","7"};
        ChartValueSerie cvs = new ChartValueSerie(color[0],3);

                for (int j=0; j < chartValueLabel.length;j++){
                    float score = ChartUtils.showScoreToChart(nodada,context).get(j).getScore();
                    cvs.addPoint(new ChartValue(chartValueLabel[j],score));
                    Config.TRACE("chart", chartValueLabel[j] + " " + score);
                }

                chart.addSerie(cvs);

    }
}
