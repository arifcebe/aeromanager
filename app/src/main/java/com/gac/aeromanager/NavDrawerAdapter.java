package com.gac.aeromanager;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by arifcebe on 8/20/14.
 */
public class NavDrawerAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<NavDrawerModel> navDrawerModels;
    private LayoutInflater inflater;

    public NavDrawerAdapter(Context _context, ArrayList<NavDrawerModel> _navDrawerModels) {
        this.context = _context;
        this.navDrawerModels = _navDrawerModels;
    }

    @Override
    public int getCount() {
        return navDrawerModels.size();
    }

    @Override
    public Object getItem(int i) {
        return navDrawerModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_list_item,null);
        }
        ImageView icon = (ImageView) view.findViewById(R.id.icon_menu);
        TextView title = (TextView) view.findViewById(R.id.menu_teks);

        icon.setImageResource(navDrawerModels.get(i).getIcon());
        title.setText(navDrawerModels.get(i).getTitle());

        return view;
    }
}
