package com.gac.aeromanager.hasilakhir.activity;


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
 * Created by arifcebe on 8/21/14.
 */
public class HasilAkhirActivity extends Fragment {

    private ViewPager viewPager;
    private android.app.ActionBar actionBar;
    private TabsPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hasil_akhir_activity,null);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
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

                    return new HasilAkhirPutraActivity();

                case 1:
                    return new HasilAkhirPutriActivity();
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
