package com.gac.aeromanager.hasilakhir.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gac.aeromanager.R;
import com.gac.aeromanager.hasilakhir.handler.HasilAkhirAdapter;
import com.gac.aeromanager.hasilakhir.handler.HasilAkhirDetailAdapter;
import com.gac.aeromanager.hasilakhir.handler.HasilAkhirViewSqlHandler;
import com.gac.aeromanager.hasilakhir.model.HasilAkhirDetailModel;
import com.gac.aeromanager.hasilakhir.model.HasilAkhirModel;

import java.util.ArrayList;

/**
 * Created by arifcebe on 9/26/14.
 */
public class HasilAkhirDetailRound extends Fragment {
    private static ArrayList<HasilAkhirDetailModel> item = new ArrayList<HasilAkhirDetailModel>();
    private HasilAkhirDetailAdapter adapter;
    private HasilAkhirViewSqlHandler viewSqlHandler;

    // widget
    ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hasil_akhir_detail_list,null);

        //class instantiate
        adapter = new HasilAkhirDetailAdapter(getActivity(),item);
        viewSqlHandler = new HasilAkhirViewSqlHandler(getActivity());

        //widget instantiate
        lv = (ListView) view.findViewById(R.id.list_hasil_round);
        lv.setAdapter(adapter);
        String nodada = getActivity().getIntent().getStringExtra("nodada");
        showRound(nodada);
        return view;
    }

    private void showRound(String nodada){
        item.clear();
        ArrayList<HasilAkhirDetailModel> list = viewSqlHandler.getDetailRound(nodada);
        for (int i= 0 ;i < list.size(); i++){
            item.add(list.get(i));
        }
        adapter.notifyDataSetChanged();
    }
}
