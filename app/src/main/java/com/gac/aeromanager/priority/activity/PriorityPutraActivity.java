package com.gac.aeromanager.priority.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gac.aeromanager.R;
import com.gac.aeromanager.priority.handler.PriorityUtils;
import com.gac.aeromanager.priority.handler.PriorityViewSqlHandler;

/**
 * Created by arifcebe on 8/30/14.
 * class fragment untuk menampilkan prioritas atlet putra.
 *
 */
public class PriorityPutraActivity extends Fragment {
    private PriorityViewSqlHandler viewSqlHandler;
    private static final String SEX = "Pa";
    // from layout
    ListView lvPrior;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.priority_show_acitivity,null);

        // instantiate
        viewSqlHandler = new PriorityViewSqlHandler(getActivity());

        //widget
        lvPrior = (ListView) view.findViewById(R.id.lv_priority);

        /* show athlete on priority */
        PriorityUtils.showPriority(SEX, lvPrior, getActivity());

        return view;
    }
}
