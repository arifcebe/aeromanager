package com.gac.aeromanager.home.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gac.aeromanager.R;
import com.gac.aeromanager.home.handler.HomeAdapter;
import com.gac.aeromanager.home.model.HomeModel;
import com.gac.aeromanager.round.activity.RoundActivity;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingRightInAnimationAdapter;

/**
 * Created by arifcebe on 8/21/14.
 */
public class HomeActivity extends Fragment {
    private HomeModel model;
    private HomeAdapter adapter;
    ListView lv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_activity,null);

        lv = (ListView) view.findViewById(R.id.lv_home);
        model = new HomeModel();
        adapter = new HomeAdapter(getActivity(),model.getRound());

        SwingRightInAnimationAdapter animationAdapter = new SwingRightInAnimationAdapter(adapter);
        animationAdapter.setAbsListView(lv);
        lv.setAdapter(animationAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String round = "roundKe";
                Intent intent =  new Intent(getActivity(), RoundActivity.class);
                switch (i){
                    case 0 :
                        intent.putExtra(round,"1");
                    break;
                    case 1 :
                        intent.putExtra(round,"2");
                        break;
                    case 2 :
                        intent.putExtra(round,"3");
                        break;
                    case 3 :
                        intent.putExtra(round,"4");
                        break;
                    case 4 :
                        intent.putExtra(round,"5");
                        break;
                    case 5 :
                        intent.putExtra(round,"6");
                        break;
                    default:
                        intent.putExtra(round,"7");
                        break;
                }
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_in,R.anim.push_out);
            }
        });

        return view;
    }
}
