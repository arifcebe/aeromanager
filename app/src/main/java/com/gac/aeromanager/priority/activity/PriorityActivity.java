package com.gac.aeromanager.priority.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gac.aeromanager.R;

/**
 * Created by arifcebe on 8/30/14.
 */
public class PriorityActivity extends Fragment{

    private ViewPager viewPager;
    private android.app.ActionBar actionBar;
    private TabsPagerAdapter adapter;
    private final String[] tabs = {"Putra","Putri"};

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.priority_activity,null);
        viewPager = (ViewPager) view.findViewById(R.id.prior_pager);
        /*PagerTabStrip tabs = (PagerTabStrip) view.findViewById(R.id.tabs);*/
        adapter = new TabsPagerAdapter(getChildFragmentManager());

        viewPager.setAdapter(adapter);
        return view;
    }


   private class TabsPagerAdapter extends FragmentPagerAdapter {
        private final String[] TITLE = {"Putra","Putri"};
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
                    // Top Rated fragment activity
                    return new PriorityPutraActivity();
                case 1:
                    // Games fragment activity
                    return new PriorityPutriActivity();
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
