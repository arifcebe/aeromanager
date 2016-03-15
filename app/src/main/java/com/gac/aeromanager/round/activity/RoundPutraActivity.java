package com.gac.aeromanager.round.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.gac.aeromanager.Config;
import com.gac.aeromanager.R;
import com.gac.aeromanager.round.model.RoundModel;
import com.gac.aeromanager.round.handler.RoundTableSqlHandler;
import com.gac.aeromanager.round.handler.RoundAdapter;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;

import java.util.ArrayList;

/**
 * Created by arifcebe on 8/24/14.
 */
public class RoundPutraActivity extends Fragment {
    private String round;
    public static ArrayList<RoundModel> item = new ArrayList<RoundModel>();
    private RoundTableSqlHandler roundTableSqlHandler;
    private RoundAdapter adapter;
    private RoundModel model;
    private final String JK = "Pa";
    private static final String TAG = "ROUND PUTRA";

    ListView lv;
    public RoundPutraActivity(String round) {
        this.round = round;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.round_show_activity,container,false);
        roundTableSqlHandler = new RoundTableSqlHandler(getActivity());
        adapter = new RoundAdapter(getActivity(),item);
        lv = (ListView) view.findViewById(R.id.lv_round);

        /* set animation */
        AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(adapter);
        animationAdapter.setAbsListView(lv);
        lv.setAdapter(animationAdapter);

        Config.TRACE("Round PA","jk "+JK+" round "+round);
        showAllAtlet();
        final ArrayList<RoundModel> list = new ArrayList<RoundModel>();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String nodada = item.get(i).getNoDada().toString();
                String team = item.get(i).getTeamNama().toString();
                String nama = item.get(i).getAtletNama().toString();
                float score = item.get(i).getScore();
                int roundid = item.get(i).getRoundId();

                showFormScore(roundid,nodada,nama,team,score);
                Config.TRACE(TAG,"no dada "+nodada+" round id "+roundid);
            }
        });

        return view;
    }

    private void showAllAtlet(){
        item.clear();
        ArrayList<RoundModel> listAtlet = roundTableSqlHandler.getAllAtletInRound(JK,round);
        for(int i=0;i < listAtlet.size();i++){
            item.add(listAtlet.get(i));
        }
        adapter.notifyDataSetChanged();

    }

    private void showFormScore(int roundId, String nodada,
                               String nama, String team,float score){
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.round_form);
        dialog.setCancelable(false);
        dialog.show();

        RoundModel model = roundTableSqlHandler.getAtletInRound(roundId);

        final int id = roundId;
        TextView tvNodada = (TextView) dialog.findViewById(R.id.round_nodada);
        TextView tvNama = (TextView) dialog.findViewById(R.id.round_nama);
        TextView tvTeam = (TextView) dialog.findViewById(R.id.round_team);
        final EditText score_1 = (EditText) dialog.findViewById(R.id.score_1);
        final EditText score_2 = (EditText) dialog.findViewById(R.id.score_2);
        Button simpan = (Button) dialog.findViewById(R.id.btn_simpan);
        Button batal = (Button) dialog.findViewById(R.id.btn_batal);

        tvNodada.setText(model.getNoDada().toString());
        tvNama.setText(model.getAtletNama().toString());
        tvTeam.setText(model.getTeamNama().toString());
        if (score != 0){
            score_1.setText(String.valueOf(model.getScore_1()));
            score_2.setText(String.valueOf(model.getScore_2()));
        }

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str_score_1 = score_1.getText().toString();
                String str_score_2 = score_2.getText().toString();
                float score_w1,score_w2;

                if(score_1.getText().equals("")){
                    score_1.setError("Score wasit 1 masih kosong!");
                }else if(score_2.getText().equals("")){
                    score_2.setError("Score wasit 2 masih kosong!");
                }else{

                    if(str_score_1.equals("")){
                        str_score_1 = "0";
                        str_score_2 = "0";
                        score_w1 = Float.parseFloat(str_score_1);
                        score_w2 = Float.parseFloat(str_score_2);
                    }else if(str_score_2.equals("")){
                        str_score_1 = "0";
                        str_score_2 = "0";
                        score_w1 = Float.parseFloat(str_score_1);
                        score_w2 = Float.parseFloat(str_score_2);
                    }else{
                        score_w1 = Float.parseFloat(str_score_1);
                        score_w2 = Float.parseFloat(str_score_2);
                    }

                    float score_final = (score_w1 + score_w2) / 2;
                    RoundModel model2 = new RoundModel(id,score_w1,score_w2,score_final);
                    roundTableSqlHandler.updateRound(model2);
                    showAllAtlet();
                    dialog.dismiss();
                }


            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }
}
