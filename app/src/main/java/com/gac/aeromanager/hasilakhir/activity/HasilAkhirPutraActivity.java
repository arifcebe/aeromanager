package com.gac.aeromanager.hasilakhir.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gac.aeromanager.R;
import com.gac.aeromanager.hasilakhir.handler.HasilAkhirUtils;

/**
 * Created by arifcebe on 8/31/14.
 */
public class HasilAkhirPutraActivity extends Fragment {
    private static final String SEX = "Pa";

    /* widget from layout */
    ListView lv;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hasil_akhir_show_atlet,null);
        /* instantiate widget */
        lv = (ListView) view.findViewById(R.id.lv_hasil_akhir);

        /* show final result */
        HasilAkhirUtils.showHasilAkhir(SEX, lv, getActivity());


        return view;
    }
}
