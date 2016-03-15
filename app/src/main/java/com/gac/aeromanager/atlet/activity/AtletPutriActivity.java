package com.gac.aeromanager.atlet.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import com.gac.aeromanager.atlet.handler.AtletAdapter;
import com.gac.aeromanager.atlet.handler.AtletTableSqlHandler;
import com.gac.aeromanager.atlet.handler.AtletViewSqlHandler;
import com.gac.aeromanager.atlet.model.AtletModel;
import com.gac.aeromanager.round.model.RoundModel;
import com.gac.aeromanager.round.handler.RoundTableSqlHandler;
import com.gac.aeromanager.team.handler.TeamAdapter;
import com.gac.aeromanager.team.model.TeamModel;
import com.gac.aeromanager.team.handler.TeamTableSqlHandler;
import com.melnykov.fab.FloatingActionButton;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;

import java.util.ArrayList;

/**
 * Created by arifcebe on 8/23/14.
 */
public class AtletPutriActivity extends Fragment {

    private AtletModel model;
    private AtletAdapter atletAdapter;
    private AtletTableSqlHandler atletTblSql;
    private AtletViewSqlHandler atletViewSql;
    private static final ArrayList<TeamModel> item = new ArrayList<TeamModel>();
    private static final ArrayList<AtletModel> item_atlet = new ArrayList<AtletModel>();
    private static final String TAG = "ATLET PUTRI";
    private static final String JK = "Pi";
    private static final String MODE_EDIT = "edit";
    private static final String MODE_ADD = "add";

    FloatingActionButton tambahAtlet;
    ListView lv_team;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.atlet_show_activity, null);

        atletTblSql = new AtletTableSqlHandler(getActivity());
        atletViewSql = new AtletViewSqlHandler(getActivity());
        atletAdapter = new AtletAdapter(getActivity(), item_atlet);
        lv_team = (ListView) view.findViewById(R.id.atlet_lv);
        tambahAtlet = (FloatingActionButton) view.findViewById(R.id.btn_tambah_atlet);
        registerForContextMenu(lv_team);

        /* set animation */
        AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(atletAdapter);
        animationAdapter.setAbsListView(lv_team);
        lv_team.setAdapter(animationAdapter);

        showAllAtlet();
        /*new syncFromDB().execute();*/
        tambahAtlet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                showTeam();
            }
        });

        lv_team.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                model = atletTblSql.getAtlet(item_atlet.get(i).getNo_dada());
                showForm(model, MODE_EDIT, String.valueOf(model.getTeam_id()), model.getTeam_nama(),JK);

            }
        });
        return view;
    }

    /**
     * show form atlet for edit and add
     *
     * @param model     -> atlet model
     * @param mode      -> mode edit or add
     * @param id        -> id atlet
     * @param team_nama -> name team
     */
    private void showForm(final AtletModel model, final String mode,
                          final String id, String team_nama,final String sex) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.atlet_form);
        dialog.setCancelable(false);
        dialog.show();

        final EditText ed_no_dada = (EditText) dialog.findViewById(R.id.atlet_ed_no_dada);
        final EditText ed_nama = (EditText) dialog.findViewById(R.id.atlet_ed_nama);
        final TextView ed_team = (TextView) dialog.findViewById(R.id.title_team);
        final TextView tv_team_id = (TextView) dialog.findViewById(R.id.atlet_team_id);
        final Button simpan = (Button) dialog.findViewById(R.id.atlet_btn_simpan);
        final Button selesai = (Button) dialog.findViewById(R.id.atlet_btn_selesai);
        final Button hapus = (Button) dialog.findViewById(R.id.atlet_btn_hapus);

        Config.TRACE(TAG,"form dialog atlet putri");
        if (mode == "edit") {
            ed_nama.setText(model.getNama());
            ed_no_dada.setEnabled(false);
            ed_no_dada.setText(model.getNo_dada());
            ed_team.setText(model.getTeam_nama());
            Config.TRACE(TAG,"edit form dialog atlet putri");
            /*tv_team_id.setText(String.valueOf(model.getTeam_id()));*/
        } else {
            ed_team.setText(team_nama);
            hapus.setVisibility(View.GONE);
        }

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogMessage("Anda Atlet "+model.getNama()+" dihapus ?",model);
                dialog.dismiss();
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String nodada = ed_no_dada.getText().toString();
                String nama = ed_nama.getText().toString();
                int team_id = Integer.valueOf(id);
                String kelamin = sex;
                int cekAtlet;

                if (nodada.isEmpty()) {
                    ed_no_dada.setError("No dada tidak boleh kosong");

                } else if (nama.isEmpty()) {
                    ed_nama.setError("Nama tidak boleh kosong");
                } else {
                    Config.TRACE(TAG, "mode " + mode + " sex " + kelamin + " putri");
                    if (mode == "edit") {
                        if (nama.isEmpty()) {
                            ed_nama.setError("Nama tidak boleh kosong");

                        } else {
                            updateAtlet(nodada, nama, kelamin, team_id);
                        }
                        dialog.dismiss();

                    } else {
                        cekAtlet = atletTblSql.getCountAtlet(nodada);
                        if (cekAtlet != 0) {
                            /*Config.makeToast(getActivity(),"No dada sudah ada!");*/
                            ed_no_dada.setError("No dada sudah ada");
                        } else {
                            saveAtlet(nodada, nama, kelamin, team_id);
                            saveAtletToRound(nodada);
                            Config.TRACE(TAG, "id " + team_id + " " + nodada + " " + nama + " " + kelamin);
                            ed_nama.setText("");
                            ed_no_dada.setText("");
                            ed_no_dada.setFocusable(true);
                        }

                    }

                    showAllAtlet();
                    atletAdapter.notifyDataSetChanged();
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
     * this method show all team before dialog add atlet
     */
    private void showTeam() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.select_team);
        dialog.setCancelable(true);
        dialog.show();


        final AtletModel model = new AtletModel();
        TeamTableSqlHandler sqlHandler = new TeamTableSqlHandler(getActivity());
        TeamAdapter adapter = new TeamAdapter(getActivity(), item);
        ListView lv = (ListView) dialog.findViewById(R.id.popup_lv_team);
        ArrayList<TeamModel> teamFromDb = sqlHandler.getAllTeam();
        Button batal = (Button) dialog.findViewById(R.id.btn_batal);

        item.clear();
        for (int i = 0; i < teamFromDb.size(); i++) {
//			item.clear();
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
                Config.TRACE(TAG, "team id " + id + " name " + nama);
                dialog.dismiss();
                showForm(model, MODE_ADD, id, nama,"Pi");

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

    /**
     * show all athlete on main fragment or activity
     */
    private void showAllAtlet() {
        item_atlet.clear();
        ArrayList<AtletModel> arrayAtlet = atletTblSql.getAllAtlet(JK);
        for (int i = 0; i < arrayAtlet.size(); i++) {
            item_atlet.add(arrayAtlet.get(i));
            Config.TRACE(TAG, "show data " + arrayAtlet.get(i));
        }
        atletAdapter.notifyDataSetChanged();

    }

    /**
     * save athlete is called on dialog show form,
     * this called when we save new athlete
     *
     * @param nodada
     * @param nama
     * @param jenkel
     * @param team_id
     */
    private void saveAtlet(String nodada, String nama, String jenkel, int team_id) {
        model = new AtletModel(nodada, nama, jenkel, team_id);
        atletTblSql.addAtlet(model);
        Config.makeToast(getActivity(), "Atlet baru berhasil ditambahkan");
    }

    /**
     * update athlete to sqlite
     *
     * @param nodada
     * @param nama
     * @param jenkel
     * @param team_id
     */
    private void updateAtlet(String nodada, String nama, String jenkel, int team_id) {
        model = new AtletModel(nodada, nama, jenkel, team_id);
        atletTblSql.updateAtlet(model);
        Config.makeToast(getActivity(), "Perubahan berhasil disimpan");
    }

    /**
     * save athlete to round, there are 7 round in this game
     *
     * @param nodada -> nodada athlete
     */
    private void saveAtletToRound(String nodada) {

        for (int i = 1; i <= 7; i++) {
            String roundKe = String.valueOf(i);
            RoundModel model = new RoundModel(roundKe, nodada, 0, 0, 0, "0");
            Config.TRACE(TAG, "round " + roundKe + " nodada " + nodada);
            RoundTableSqlHandler roundTableSqlHandler = new RoundTableSqlHandler(getActivity());
            roundTableSqlHandler.addAtletAndRound(model);
        }
    }



    /**
     * this show dialog message when you delete athlete
     *
     * @param pesan
     * @param am
     */
    private void dialogMessage(final String pesan, final AtletModel am) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_message);
        dialog.setCancelable(false);

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
                Config.makeToast(getActivity(), "Team " + delName + " berhasil dihapus");
                showAllAtlet();
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

    private class syncFromDB extends AsyncTask<Void, Void, Void> {
        ProgressDialog loader = new ProgressDialog(getActivity());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loader.setMessage("load data....");
            loader.show();
            loader.setCancelable(false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            loader.dismiss();
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showAllAtlet();
                }
            });
            return null;
        }
    }
}