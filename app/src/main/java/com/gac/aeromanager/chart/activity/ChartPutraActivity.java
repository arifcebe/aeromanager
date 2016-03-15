package com.gac.aeromanager.chart.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gac.aeromanager.R;
import com.gac.aeromanager.chart.handler.ChartUtils;

import it.bradipao.lib.descharts.LineChartView;

/**
 * Created by arifcebe on 9/2/14.
 */
public class ChartPutraActivity extends Fragment {

    private LineChartView chart;
    private static String SEX = "Pa";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chart_show_activity,null);
        chart = (LineChartView) view.findViewById(R.id.chart);
        TextView title = (TextView) view.findViewById(R.id.chart_title);

        LinearLayout layout = (LinearLayout) view.findViewById(R.id.chart_layout);

        ChartUtils.putScoreOnChart(getActivity(),SEX,layout,chart);


        return view;

    }

}
