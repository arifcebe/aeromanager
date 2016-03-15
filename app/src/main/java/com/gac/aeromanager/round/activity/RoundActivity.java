package com.gac.aeromanager.round.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.gac.aeromanager.R;
import com.gac.aeromanager.atlet.model.AtletModel;
import com.gac.aeromanager.atlet.handler.AtletTableSqlHandler;
import com.gac.aeromanager.round.model.RoundModel;
import com.gac.aeromanager.round.handler.RoundTableSqlHandler;

import java.util.ArrayList;

/**
 * Created by arifcebe on 8/23/14.
 */
public class RoundActivity extends ActionBarActivity
    implements ActionBar.TabListener{

    private ProgressDialog dialog;
    private ActionBar actionBar;
    private ViewPager viewPager;
    private TabsPagerAdapter adapter;
    private final String[] tabs = new String[]{"Putra","Putri"};
    private Bundle bundle;
    private String roundKe;
    private RoundTableSqlHandler roundSqlHandler;
    private AtletTableSqlHandler atletTableSqlHandler;
    private RoundModel model;
    private Context context = RoundActivity.this;
    private static final String TAG = "Round Act";

    public static ArrayList<AtletModel> itemAtlet = new ArrayList<AtletModel>();
    public static ArrayList<RoundModel> itemModel = new ArrayList<RoundModel>();

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.round_activity);

        dialog = new ProgressDialog(context);
        roundSqlHandler = new RoundTableSqlHandler(context);
        atletTableSqlHandler = new AtletTableSqlHandler(context);
        viewPager = (ViewPager) findViewById(R.id.round_pager);
        actionBar = getActionBar();
        adapter = new TabsPagerAdapter(getSupportFragmentManager());
        bundle = getIntent().getExtras();
        roundKe = bundle.getString("roundKe");
        actionBar.setTitle("ROUND "+roundKe);

        viewPager.setAdapter(adapter);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        /*addAtletToRound();*/

        /*Add Tabs*/
        for (String tabName : tabs){
            actionBar.addTab(actionBar.newTab().setText(tabName)
                    .setTabListener(this));
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
            }

            @Override
            public void onPageSelected(int i) {
                /* on changing page make respected tab selected */
                actionBar.setSelectedNavigationItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    private class TabsPagerAdapter extends FragmentPagerAdapter {

        public TabsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int index) {

            switch (index) {
                case 0:
                    // Top Rated fragment activity
                    return new RoundPutraActivity(roundKe);
                case 1:
                    // Games fragment activity
                    return new RoundPutriActivity(roundKe);

            }

            return null;
        }

        @Override
        public int getCount() {
            // get item count - equal to number of tabs
            return 2;
        }
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
