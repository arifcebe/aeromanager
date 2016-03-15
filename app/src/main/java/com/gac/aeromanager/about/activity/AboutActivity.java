package com.gac.aeromanager.about.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gac.aeromanager.R;

/**
 * Created by arifcebe on 9/9/14.
 */
public class AboutActivity extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_activity,null);
        TextView desc = (TextView) view.findViewById(R.id.description);

        desc.setText(getResources().getString(R.string.app_desc));
        return view;
    }

}
