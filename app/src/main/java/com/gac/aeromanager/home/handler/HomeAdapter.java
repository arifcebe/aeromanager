package com.gac.aeromanager.home.handler;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gac.aeromanager.R;

import java.util.ArrayList;

/**
 * Created by arifcebe on 8/20/14.
 */
public class HomeAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private String[] items;
    private ViewHolder holder;

    public HomeAdapter(Context _context,String[] _items) {
        inflater = LayoutInflater.from(_context);
        this.items = _items;
        this.context = _context;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            /*inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);*/
            view = inflater.inflate(R.layout.home_item,null);
            holder = new ViewHolder();
            holder.round = (TextView) view.findViewById(R.id.round);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.round.setText(items[i]);

        return view;
    }

    static class ViewHolder{
        TextView round;
    }
}
