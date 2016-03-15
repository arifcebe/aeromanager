package com.gac.aeromanager.priority.handler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gac.aeromanager.R;
import com.gac.aeromanager.priority.model.PriorityModel;

import java.util.ArrayList;

/**
 * Created by arifcebe on 8/30/14.
 */
public class PriorityAdapter extends BaseAdapter {
    private ArrayList<PriorityModel> item;
    private LayoutInflater inflater;
    private Context context;
    private ViewHolder holder;

    public PriorityAdapter(ArrayList<PriorityModel> item, Context context) {
        this.item = item;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    static class ViewHolder{
        TextView noUrut, nodada, nama, score, jk, team;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int i) {
        return item.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = inflater.inflate(R.layout.priority_item,null);
            holder = new ViewHolder();
            holder.noUrut = (TextView) view.findViewById(R.id.no_urut);
            holder.nodada = (TextView) view.findViewById(R.id.prior_nodada);
            holder.nama = (TextView) view.findViewById(R.id.prior_nama);
            holder.score = (TextView) view.findViewById(R.id.prior_score);
            holder.team = (TextView) view.findViewById(R.id.prior_team_nama);

            view.setTag(holder);
        }
        holder = (ViewHolder) view.getTag();

        int no = i + 1;
        holder.noUrut.setText(String.valueOf(no));
        holder.nodada.setText(item.get(i).getNodada().toString());
        holder.nama.setText(item.get(i).getNama().toString());
        holder.score.setText(String.valueOf(item.get(i).getScore()));
        holder.team.setText(item.get(i).getTeamNama().toString());

        return view;
    }
}
