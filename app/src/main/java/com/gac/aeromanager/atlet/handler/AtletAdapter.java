package com.gac.aeromanager.atlet.handler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gac.aeromanager.Config;
import com.gac.aeromanager.R;
import com.gac.aeromanager.atlet.model.AtletModel;

import java.util.ArrayList;

/**
 * Created by arifcebe on 8/23/14.
 */
public class AtletAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<AtletModel> item;
    private ViewHolder holder;
    private Context context;

    public AtletAdapter(Context _context, ArrayList<AtletModel> _item) {
        this.inflater = LayoutInflater.from(_context);
        this.item = _item;
        this.context = _context;
    }

    static class ViewHolder{
        TextView no,no_dada,nama,nama_team,team_id,team_status;
        ImageView indicator;
        RelativeLayout row;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (view == null) {
            view = inflater.inflate(R.layout.atlet_item, null);
            holder = new ViewHolder();
            holder.no = (TextView) view.findViewById(R.id.atlet_no_urut);
            holder.no_dada = (TextView) view.findViewById(R.id.atlet_no_dada);
            holder.nama = (TextView) view.findViewById(R.id.atlet_nama);
            holder.nama_team = (TextView) view.findViewById(R.id.atlet_nama_team);
            holder.team_id = (TextView) view.findViewById(R.id.atlet_team_id);
            holder.team_status = (TextView) view.findViewById(R.id.atlet_team_status);
            holder.row = (RelativeLayout) view.findViewById(R.id.row);
            holder.indicator = (ImageView) view.findViewById(R.id.icon_indicator);

            view.setTag(holder);
        }
            holder = (ViewHolder) view.getTag();


        if(item.get(position).getTeam_status().toString().equals("Sendiri")){
            holder.row.setBackgroundResource(R.drawable.list_item_selector_own_team);
            Config.TRACE("item atlet",item.get(position).getNama().toString()+" "+holder.team_status.getText().toString()+" "+item.get(position).getTeam_nama().toString());
            /*holder.indicator.setImageResource(R.drawable.ic_mark);
            holder.indicator.setVisibility(View.VISIBLE);*/

        }else{
            holder.row.setBackgroundResource(R.drawable.list_item_selector_default);
            /*holder.indicator.setVisibility(View.GONE);*/
        }

        int no = position + 1;
        holder.no.setText(String.valueOf(no));
        holder.no_dada.setText(item.get(position).getNo_dada().toString());
        holder.nama.setText(item.get(position).getNama().toString());
        holder.nama_team.setText(item.get(position).getTeam_nama().toString());
        holder.team_id.setText(String.valueOf(item.get(position).getTeam_id()));
        holder.team_status.setText(item.get(position).getTeam_status().toString());


        return view;
    }

}
