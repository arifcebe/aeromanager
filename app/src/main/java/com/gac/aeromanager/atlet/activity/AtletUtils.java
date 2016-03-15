package com.gac.aeromanager.atlet.activity;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.gac.aeromanager.Config;
import com.gac.aeromanager.R;
import com.gac.aeromanager.atlet.handler.AtletAdapter;
import com.gac.aeromanager.atlet.handler.AtletTableSqlHandler;
import com.gac.aeromanager.atlet.model.AtletModel;
import com.gac.aeromanager.round.handler.RoundTableSqlHandler;
import com.gac.aeromanager.round.model.RoundModel;
import com.gac.aeromanager.team.handler.TeamAdapter;
import com.gac.aeromanager.team.handler.TeamTableSqlHandler;
import com.gac.aeromanager.team.model.TeamModel;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;

import java.util.ArrayList;

/**
 * Created by arifcebe on 9/20/14.
 * class ini digunakan untuk menyimpan method yang berhubungan
 * dengan pengolahan data
 */
public  class AtletUtils {
    private static final String TAG = "ATLET";
    private static final String MODE_EDIT = "edit";
    private static final String MODE_ADD = "add";


    public static void showForm(final Context context,
                                final AtletModel model,
                                final String mode,
                                final String id,String team_nama, final String JK){

        // instatiate for dialog
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.atlet_form);
        dialog.setCancelable(false);
        dialog.show();

        //instantiate class helper
        final AtletTableSqlHandler atletTblSql = new AtletTableSqlHandler(context);

        // instantiate widget from dialog layout
        final EditText ed_no_dada = (EditText) dialog.findViewById(R.id.atlet_ed_no_dada);
        final EditText ed_nama = (EditText) dialog.findViewById(R.id.atlet_ed_nama);
        final TextView ed_team = (TextView) dialog.findViewById(R.id.title_team);
        final TextView tv_team_id = (TextView) dialog.findViewById(R.id.atlet_team_id);
        final Button simpan = (Button) dialog.findViewById(R.id.atlet_btn_simpan);
        final Button selesai = (Button) dialog.findViewById(R.id.atlet_btn_selesai);

        // cek param for add or edit
        if (mode == MODE_EDIT){
            ed_nama.setText(model.getNama());
            ed_no_dada.setEnabled(false);
            ed_no_dada.setText(model.getNo_dada());
            ed_team.setText(model.getTeam_nama());

        }else{
            ed_team.setText(team_nama);

        }

        simpan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String nodada = ed_no_dada.getText().toString();
                String nama = ed_nama.getText().toString();
                int team_id = Integer.valueOf(id);
                String kelamin = JK;
                int cekAtlet;

                // cek jika no dada kosong
                if (nodada.isEmpty()) {
                    ed_no_dada.setError("No dada tidak boleh kosong");

                // cek jika nama kosong
                } else if (nama.isEmpty()) {
                    ed_nama.setError("Nama tidak boleh kosong");

                // jalankan fungsi jika no dada dan nama tidak kosong (accepted)
                } else {
                    Config.TRACE(TAG, "mode " + mode + " sex " + kelamin + " other if");

                    //cek mode apakah edit ato menambahkan athelt
                    if (mode == MODE_EDIT) {
                        if (nama.isEmpty()) {
                            ed_nama.setError("Nama tidak boleh kosong");

                        } else {
                            // proses update athlete
                            AtletUtils.updateAtlet(nodada,nama,kelamin,team_id,context);
                        }
                        dialog.dismiss();

                    // mode ketika menambahkan athlete
                    } else {
                        cekAtlet = atletTblSql.getCountAtlet(nodada);

                        /**
                         * prosesnya, jalankan fungsi cek atlet
                         * untuk mengecek apakah atlet yang memiliki
                         * no dada yang diinput sudah ada atau belum
                         * jik YA -> tampilkan pesan error / tidak boleh
                         * jika NO -> jalankan fungsi untuk menambahkan athlete
                         */
                        if (cekAtlet != 0) {
                            ed_no_dada.setError("No dada sudah ada");
                        } else {
                            AtletUtils.saveAtlet(nodada, nama, kelamin, team_id, context); // save athlete
                            AtletUtils.saveAtletToRound(nodada,context); // save athlete to round
                            Config.TRACE(TAG, "id " + team_id + " " + nodada + " " + nama + " " + kelamin);
                            ed_nama.setText("");
                            ed_no_dada.setText("");
                            ed_no_dada.setFocusable(true);
                        }

                    }
                    //atletAdapter.notifyDataSetChanged();
                }
            }

        });

        selesai.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });
    }

    /**
     * for show all team, when you will add atlet,
     * this method show all team before dialog add atlet     *
     * @param context
     * @param JK -> sex
     */
    public static void showTeam(final Context context,final String JK){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.select_team);
        dialog.setCancelable(true);
        dialog.show();


        // instantiate class helper
        final ArrayList<TeamModel> item = new ArrayList<TeamModel>();
        final AtletModel model = new AtletModel();
        TeamTableSqlHandler sqlHandler = new TeamTableSqlHandler(context);
        TeamAdapter adapter = new TeamAdapter(context, item);

        // instantiate widget
        final ListView lv = (ListView) dialog.findViewById(R.id.popup_lv_team);
        ArrayList<TeamModel> teamFromDb = sqlHandler.getAllTeam();
        Button batal = (Button) dialog.findViewById(R.id.btn_batal);

        item.clear();
        for (int i = 0; i < teamFromDb.size(); i++) {
            item.add(teamFromDb.get(i));
            adapter.notifyDataSetChanged();
        }

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,
                                    long arg3) {
                // TODO Auto-generated method stub
                String id = String.valueOf(item.get(position).getTeam_id());
                String nama = item.get(position).getTeam_nama().toString();
                Config.TRACE(TAG, "team id "+id+" name "+nama);
                dialog.dismiss();
                showForm(context,model,"add",id,nama,JK);
            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                dialog.dismiss();
            }
        });

    }

    public static void showAllAtlet(Context context,String JK,ListView listview){
        ArrayList<AtletModel> item_atlet = new ArrayList<AtletModel>();
        item_atlet.clear();


        // instantiate class helper
        AtletTableSqlHandler atletTblSql = new AtletTableSqlHandler(context);
        AtletAdapter atletAdapter = new AtletAdapter(context,item_atlet);
        ArrayList<AtletModel> arrayAtlet = atletTblSql.getAllAtlet(JK);
        AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(atletAdapter);

        animationAdapter.setAbsListView(listview);
        listview.setAdapter(animationAdapter);
        //listView.setAdapter(atletAdapter);
        for (int i = 0; i < arrayAtlet.size(); i++) {
            item_atlet.add(arrayAtlet.get(i));
            Config.TRACE(TAG, "show data "+arrayAtlet.get(i));
        }

        atletAdapter.notifyDataSetChanged();

    }

    /**
     * save atlet to db
     * @param nodada -> no dada atlet
     * @param nama -> name
     * @param jenkel -> sex
     * @param team_id -> team id's atlet
     * @param context -> context app
     */
    public static void saveAtlet(String nodada,String nama,String jenkel,int team_id, Context context){
        // instantiate class helper
        AtletModel model = new AtletModel(nodada, nama, jenkel, team_id);
        AtletTableSqlHandler atletTblSql = new AtletTableSqlHandler(context);

        atletTblSql.addAtlet(model);
        Config.makeToast(context, "Atlet baru berhasil ditambahkan");
    }


    /**
     * method to run query to update table atlet
     * @param nodada
     * @param nama
     * @param jenkel
     * @param team_id
     * @param context
     */
    public static void updateAtlet(String nodada,String nama,String jenkel,int team_id, Context context){

        AtletModel model = new AtletModel(nodada,nama,jenkel,team_id);
        AtletTableSqlHandler atletTblSql = new AtletTableSqlHandler(context);
        atletTblSql.updateAtlet(model);
        Config.makeToast(context, "Perubahan berhasil disimpan");
    }


    /**
     * save atlet to all round, when you add atlet
     * @param nodada
     * @param context
     */
    public static void saveAtletToRound(String nodada, Context context){

        for (int i = 1;i <= 7; i++){
            String roundKe = String.valueOf(i);
            RoundModel model = new RoundModel(roundKe,nodada,0,0,0,"0");
            Config.TRACE(TAG,"round "+roundKe+" nodada "+nodada);
            RoundTableSqlHandler roundTableSqlHandler = new RoundTableSqlHandler(context);
            roundTableSqlHandler.addAtletAndRound(model);
        }
    }


    /**
     * to show dialog message when you will delete athlete
     * this dialog show confirmation, are you sure want to delete
     * this athlete
     * @param context
     * @param pesan
     * @param am
     * @param context
     */
    public static void dialogMessage(final Context context,final String pesan,final AtletModel am) {
        // instantiate dialog method
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_message);
        dialog.setCancelable(false);

        //instantiate class helper
        final AtletTableSqlHandler atletTblSql = new AtletTableSqlHandler(context);

        // instantiate widget
        final TextView desc = (TextView) dialog.findViewById(R.id.pesan_dialog);
//		desc.setText(Config.instance().getMsgUserConflict(context)+" "+pesan);
        desc.setText(pesan);
        Button ok = (Button) dialog.findViewById(R.id.btn_dialog_delete_oke);
        Button cancel = (Button) dialog.findViewById(R.id.btn_dialog_delete_batal);
        TextView title = (TextView) dialog.findViewById(R.id.title_dialog);
        final String delName = am.getNama();

        title.setText(delName);
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                dialog.dismiss();
                atletTblSql.deleteAtlet(am);
                Config.makeToast(context, "Team "+delName+" berhasil dihapus");
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });

        dialog.show();
    }
}
