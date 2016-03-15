package com.gac.aeromanager.team.handler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gac.aeromanager.R;
import com.gac.aeromanager.team.model.TeamModel;

import java.util.ArrayList;

/**
 * Created by arifcebe on 8/21/14.
 */
public class TeamAdapter extends BaseAdapter {
    Context context;
    ArrayList<TeamModel> item;
    LayoutInflater inflater;
    Holder holder;
    static class Holder{
        TextView id, nama, status, no;
        RelativeLayout row;
        ImageView indicator; /* indicator untuk team sendiri */
    }

    public TeamAdapter(Context context,ArrayList<TeamModel> item) {
        // TODO Auto-generated constructor stub
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.item = item;
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(R.layout.team_item, null);
            holder = new Holder();
            view.setTag(holder);

            holder.id = (TextView) view.findViewById(R.id.team_id);
            holder.nama = (TextView) view.findViewById(R.id.team_nama);
            holder.status = (TextView) view.findViewById(R.id.team_status);
            holder.row = (RelativeLayout) view.findViewById(R.id.row);
            holder.no = (TextView) view.findViewById(R.id.no_urut);
            holder.indicator = (ImageView) view.findViewById(R.id.indicator_image);

        }else{
            holder = (Holder) view.getTag();
        }
        if (item.get(position).getTeam_status().equals("Sendiri")){
            holder.row.setBackgroundResource(R.drawable.list_item_selector_own_team);
/*            holder.indicator.setVisibility(View.VISIBLE);
            holder.indicator.setImageResource(R.drawable.ic_mark);*/
        }else{
            holder.row.setBackgroundResource(R.drawable.list_item_selector_default);
            /*holder.indicator.setVisibility(View.GONE);*/
        }
        int nourut = position + 1;
        holder.no.setText(String.valueOf(nourut));
        holder.id.setText(Integer.toString(item.get(position).getTeam_id()));
        holder.nama.setText(item.get(position).getTeam_nama());
        holder.status.setText(item.get(position).getTeam_status());
        return view;
    }
}
