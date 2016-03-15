package com.gac.aeromanager.team.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gac.aeromanager.Config;
import com.gac.aeromanager.R;
import com.gac.aeromanager.team.handler.TeamAdapter;
import com.gac.aeromanager.team.model.TeamModel;
import com.gac.aeromanager.team.handler.TeamTableSqlHandler;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

/**
 * Created by arifcebe on 8/21/14.
 */
public class TeamActivity extends Activity{
    private TeamTableSqlHandler tsh;
    private TeamModel tm;
    private TeamAdapter tia;
    public static final ArrayList<TeamModel> item = new ArrayList<TeamModel>();
    private Button btnSimpan;
    private EditText edTeamNama;
    private RadioButton teamStatusChanged;
    private RadioButton	teamLawan;
    private RadioButton	teamSendiri;
    private RadioGroup teamStatus;
    private ListView lvTeam;
    private TextView titleTeam;
    public int selectedItem = 0;
    private  final Context context = TeamActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity);

        tsh = new TeamTableSqlHandler(context);
        tia = new TeamAdapter(context, item);

        lvTeam = (ListView) findViewById(R.id.lv_team);

        /* instantiate floating button */
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.button_floating_action);
        button.attachToListView(lvTeam);

        /* set title action bar */
        getActionBar().setTitle("Team");
        /* register context menu */
        registerForContextMenu(lvTeam);
        lvTeam.setAdapter(tia);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showForm(context,tm,"add");
            }
        });

//		show all team
        showAllTeam();
    }

    /**
     * menampilkan semua team
     */
    private void showAllTeam(){
        item.clear();
        ArrayList<TeamModel> listTeam = tsh.getAllTeam();

        for (int i = 0; i < listTeam.size(); i++) {
            item.add(listTeam.get(i));
            Config.TRACE("team", listTeam.get(i).toString());
        }
        tia.notifyDataSetChanged();
    }


    /**
     * dialog message menampilkan pesan ketika akan menghapus team
     *
     * @param _context -> context activity
     * @param pesan -> pesan yang ditampilkan
     * @param tm -> team model
     */
    public void dialogMessage(final Context _context,final String pesan,final TeamModel tm) {
        final Dialog dialog = new Dialog(_context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_message);
        dialog.setCancelable(false);

        final TextView desc = (TextView) dialog.findViewById(R.id.pesan_dialog);
        desc.setText(pesan);
        Button ok = (Button) dialog.findViewById(R.id.btn_dialog_delete_oke);
        Button cancel = (Button) dialog.findViewById(R.id.btn_dialog_delete_batal);
        TextView title = (TextView) dialog.findViewById(R.id.title_dialog);
        final String delName = tm.getTeam_nama();

        title.setText(delName);
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.dismiss();
                tsh.deleteTeam(tm);
                Config.makeToast(_context, "Team "+delName+" berhasil dihapus");
                showAllTeam();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
//				dialog.dismiss();
                dialog.cancel();
            }
        });

        dialog.show();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String idTeam = ((TextView)info.targetView.findViewById(R.id.team_id))
                .getText().toString();
        TeamModel tm = tsh.getTeam(Integer.valueOf(idTeam));

//		selected item menu
        int menuItemSelected = item.getItemId();

//		this variable contain item menu on array menu
        String[] menuItems = getResources().getStringArray(R.array.array_menu);

//		this variable contains the name of the selected menu
        String menuItemsName = menuItems[menuItemSelected];

//		this variable contains the team id on list view
        String listItemIndex = String.valueOf(tm.getTeam_id());
        String teamNama = tm.getTeam_nama();
        String teamStatus = tm.getTeam_status();

        int teamId = tm.getTeam_id();
        if (menuItemSelected == 0) {
            showForm(context,tm,"edit");
        }else{
            dialogMessage(context, "Anda yakin ingin menghapus Team "+teamNama+" ?",tm);
        }

		lvTeam.getItemAtPosition(menuItemSelected);

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.lv_team) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            String[] menuItems = getResources().getStringArray(R.array.array_menu);
            menu.setHeaderTitle(((TextView)info.targetView.findViewById(R.id.team_nama))
                    .getText().toString());
/*            Config.TRACE(TAG, "item selected "+info.position);
            Config.TRACE(TAG, "item selected lv "+lvTeam.getItemIdAtPosition(info.position));*/
			int selected = (int) lvTeam.getItemIdAtPosition(info.position);

            for (int i = 0; i < menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }

        }
//		super.onCreateContextMenu(menu, v, menuInfo);
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_team,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.add_team:
                Intent intent = new Intent(context,TeamFormActivity.class);
                intent.putExtra("key","add");
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
       *//* return super.onOptionsItemSelected(item);*//*
    }*/

    /**
     * to save team in sqlite
     * @param team -> team name
     * @param status -> changed your team status, your own team or competitor
     */
    private void saveTeam(String team,String status){

		/*if (team.isEmpty()) {
			Config.instance().makeToast(getActivity(), "Nama team masih kosong!");
		}else{*/
        tm = new TeamModel(team, status);
        tsh.addTeam(tm);
        Config.makeToast(context, "Team baru berhasil ditambahkan");
        clearText();
        showAllTeam();
//		}

    }

    /**
     *
     * @param id
     * @param team
     * @param status
     */
    private void updateTeam(int id,String team,String status){
		/*if (team.isEmpty()) {
			Config.instance().makeToast(getActivity(), "Nama team masih kosong!");
		}else{*/
        tm = new TeamModel(id, team, status);
        tsh.updateTeam(tm);
        Config.makeToast(context, "Team "+team+" berhasil diubah");
        clearText();
        showAllTeam();
//		}
    }

    /**
     * this method to clear all text when add team
     */
    private void clearText(){
        edTeamNama.setText("");
//		teamStatusChanged.setChecked(false);
    }

    /**
     * method to add or update team on sqlite,
     * this method check team id on sqlite, when team id already exists
     * this method will update that id,
     * but if team id is empty, a new team id will be added
     * @param _context -> getActivity, or fragment active
     * @param tm -> team model, which had declare before
     * @param mode -> String mode edit or add
     */
    public void showForm(final Context _context,final TeamModel tm,final String mode) {
        final Dialog dialog = new Dialog(_context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.team_form);
        dialog.setCancelable(false);

        edTeamNama = (EditText) dialog.findViewById(R.id.txt_team_nama);
        teamStatus = (RadioGroup) dialog.findViewById(R.id.rdogroup_team_status);
        teamLawan = (RadioButton) dialog.findViewById(R.id.rdobtn_team_status_lawan);
        teamSendiri = (RadioButton) dialog.findViewById(R.id.rdobtn_team_status_sendiri);
        final TextView team_id = (TextView) dialog.findViewById(R.id.team_id);
/*		String teamNama = tm.getTeam_nama();
		String teamStatus2 = tm.getTeam_status();
		int teamId = tm.getTeam_id();*/

        if (mode == "edit") {
            edTeamNama.setText(tm.getTeam_nama());
//			titleTeam.setText("Edit Mode");
            if (tm.getTeam_status().equals("Lawan")) {
                teamLawan.setChecked(true);
                team_id.setText(String.valueOf(tm.getTeam_id()));
            }else{
                teamSendiri.setChecked(true);
            }
            Config.TRACE("trace",""+tm.getTeam_id());
        }


        Button ok = (Button) dialog.findViewById(R.id.btnSimpanTeam);
        Button cancel = (Button) dialog.findViewById(R.id.btn_batal);

        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//				String delName = tm.getTeam_nama();

                int selectedStatus = teamStatus.getCheckedRadioButtonId();
                teamStatusChanged = (RadioButton) dialog.findViewById(selectedStatus);
                String team = edTeamNama.getText().toString();
                String status = teamStatusChanged.getText().toString();

                if (team.equals("")) {
                    Config.makeToast(_context, "Nama team masih kosong!");
                }else{
                    if (mode == "edit") {
                        /*int id = Integer.valueOf(team_id.getText().toString());*/
                        /*int id = Integer.parseInt(team_id.getText().toString());*/
                        updateTeam(tm.getTeam_id(), team, status);
                    }else{
                        saveTeam(team, status);

                    }
                    dialog.dismiss();
                }


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
//				dialog.dismiss();
                dialog.cancel();
            }
        });

        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                onBackPressed();
                overridePendingTransition(R.anim.parent_in,R.anim.child_out);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
