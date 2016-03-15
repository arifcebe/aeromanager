package com.gac.aeromanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gac.aeromanager.about.activity.AboutActivity;
import com.gac.aeromanager.archive.activity.ArchiveFragment;
import com.gac.aeromanager.atlet.activity.AtletActivity;
import com.gac.aeromanager.chart.activity.ChartActivity;
import com.gac.aeromanager.hasilakhir.activity.HasilAkhirActivity;
import com.gac.aeromanager.home.activity.HomeActivity;
import com.gac.aeromanager.priority.activity.PriorityActivity;
import com.gac.aeromanager.team.activity.TeamActivity;

import java.util.ArrayList;

/**
 * Created by arifcebe on 8/20/14.
 */
public class MainActivity extends ActionBarActivity {
    private Context context = MainActivity.this;
    /* drawer title */
    private CharSequence drawerTitle, appTitle;

    private final String mTitle = "AeroManager";
    private final String TAG ="HOME";

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ArrayAdapter<String> arrayAdapter;
    private ActionBarDrawerToggle drawerToggle;

    /* slide menu title */
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerModel> items;
    private NavDrawerAdapter adapter;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        appTitle = drawerTitle = getTitle();

        /* load menu titles */
        navMenuTitles = getResources().getStringArray(R.array.drawer_menus);

        /* load menu icon */
        navMenuIcons = getResources().obtainTypedArray(R.array.drawer_icon);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.drawer_list);

        items = new ArrayList<NavDrawerModel>();

        items.add(new NavDrawerModel(navMenuTitles[0],navMenuIcons.getResourceId(0,-1)));
        items.add(new NavDrawerModel(navMenuTitles[1],navMenuIcons.getResourceId(1,-1)));
        items.add(new NavDrawerModel(navMenuTitles[2],navMenuIcons.getResourceId(2,-1)));
        items.add(new NavDrawerModel(navMenuTitles[3],navMenuIcons.getResourceId(3,-1)));
        items.add(new NavDrawerModel(navMenuTitles[4],navMenuIcons.getResourceId(4,-1)));
        items.add(new NavDrawerModel(navMenuTitles[5],navMenuIcons.getResourceId(5,-1)));

        /*arrayAdapter = new ArrayAdapter<String>(getBaseContext(),
                R.layout.drawer_list_item,getResources().getStringArray(R.array.drawer_menus));*/
        navMenuIcons.recycle();

        adapter = new NavDrawerAdapter(getApplicationContext(),items);
        drawerList.setAdapter(adapter);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.drawable.ic_menu, // nav menu toggle icon
                R.string.app_name, // nav drawer open - description
                R.string.app_name){
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(appTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(drawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showContentDrawer(i);
            }
        });
        if (savedInstanceState == null){
            showContentDrawer(0);
        }

    }

    @Override
    public void setTitle(CharSequence title) {
        appTitle = title;
        getActionBar().setTitle(appTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_team:
                Intent intent = new Intent(MainActivity.this, TeamActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_in,R.anim.push_out);
                return true;

            case R.id.action_atlet:
                Intent intentAtlet = new Intent(MainActivity.this, AtletActivity.class);
                startActivity(intentAtlet);
                overridePendingTransition(R.anim.push_in,R.anim.push_out);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void showContentDrawer(int position){
        Fragment fragment = null;

        switch (position){
            case 0 :
                fragment = new HomeActivity();
                break;
            case 1:
                fragment = new HasilAkhirActivity();
                break;
            case 2:
                fragment = new PriorityActivity();
                break;
            case 3:
                fragment = new ChartActivity();
                break;

            case 4:
                fragment = new ArchiveFragment();
                break;

            default:
                fragment = new AboutActivity();
                break;
        }

        if (fragment != null){
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.frame_content, fragment)
                    .commit();

            drawerList.setItemChecked(position,true);
            drawerList.setSelection(position);
            drawerLayout.closeDrawer(drawerList);

            setTitle(navMenuTitles[position]);
        }
    }
}
