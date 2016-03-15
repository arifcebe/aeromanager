package com.gac.aeromanager.hasilakhir.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import com.gac.aeromanager.R;
import com.gac.aeromanager.hasilakhir.handler.HasilAkhirDetailAdapter;
import com.gac.aeromanager.hasilakhir.model.HasilAkhirDetailModel;
import com.gac.aeromanager.hasilakhir.handler.HasilAkhirViewSqlHandler;

import java.util.ArrayList;

/**
 * Created by arifcebe on 8/31/14.
 */
public class HasilAkhirDetailActivity extends ActionBarActivity {
    private HasilAkhirViewSqlHandler viewSqlHandler;
    private HasilAkhirDetailAdapter adapter;

    private ArrayList<HasilAkhirDetailModel> item = new ArrayList<HasilAkhirDetailModel>();
    /* widget on layout */
    TextView tv_nodada,tv_rank,tv_nama,tv_team,tv_score;
    ViewPager viewPager;
    TabsPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hasil_akhir_detail_acitivty);

        /* get intent extra */
        String nodada = getIntent().getStringExtra("nodada");
        String rank = getIntent().getStringExtra("rank");
        String team = getIntent().getStringExtra("team");
        String score = getIntent().getStringExtra("score");
        String nama = getIntent().getStringExtra("nama");
        /* class instantiate */
        viewSqlHandler = new HasilAkhirViewSqlHandler(this);
        adapter = new HasilAkhirDetailAdapter(this,item);
        pagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        /* widget instantiate */
        viewPager = (ViewPager) findViewById(R.id.pager);
        tv_nama = (TextView) findViewById(R.id.ha_nama);
        tv_score = (TextView) findViewById(R.id.ha_score);
        tv_team = (TextView) findViewById(R.id.ha_team_nama);
        tv_nodada = (TextView) findViewById(R.id.ha_nodada);
        tv_rank = (TextView) findViewById(R.id.no_urut);

        viewPager.setAdapter(pagerAdapter);
        showRound(nodada);

        tv_nodada.setText(nodada);
        tv_rank.setText(rank);
        tv_nama.setText(nama);
        tv_score.setText(score);
        tv_team.setText(team);
    }

    private void showRound(String nodada){
        item.clear();
        ArrayList<HasilAkhirDetailModel> list = viewSqlHandler.getDetailRound(nodada);
        for (int i= 0 ;i < list.size(); i++){
            item.add(list.get(i));
        }
        adapter.notifyDataSetChanged();
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

    private class TabsPagerAdapter extends FragmentPagerAdapter {
        private final String[] TITLE = {"Hasil Round","Chart"};
        public TabsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLE[position];
        }

        @Override
        public Fragment getItem(int index) {

            switch (index) {
                case 0:

                    return new HasilAkhirDetailRound();

                case 1:
                    return new HasilAkhirDetailChart();
            }

            return null;
        }

        @Override
        public int getCount() {
            // get item count - equal to number of tabs
            return TITLE.length;
        }

    }
}
