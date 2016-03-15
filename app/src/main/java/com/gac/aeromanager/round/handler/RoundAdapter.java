package com.gac.aeromanager.round.handler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gac.aeromanager.Config;
import com.gac.aeromanager.DBHandler;
import com.gac.aeromanager.R;
import com.gac.aeromanager.round.model.RoundModel;

import java.util.ArrayList;

/**
 * Created by arifcebe on 8/23/14.
 */
public class RoundAdapter extends BaseAdapter {
    private ArrayList<RoundModel> item;
    private LayoutInflater inflater;
    private Context context;
    private ViewHolder holder;

    public RoundAdapter(Context _context, ArrayList<RoundModel> _item){
        this.item = _item;
        this.inflater = LayoutInflater.from(_context);
        this.context = _context;
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
            view = inflater.inflate(R.layout.round_item,null);
            holder = new ViewHolder();

            holder.no = (TextView) view.findViewById(R.id.no_urut);
            holder.no_dada = (TextView) view.findViewById(R.id.round_no_dada);
            holder.nama = (TextView) view.findViewById(R.id.round_nama);
            holder.nama_team = (TextView) view.findViewById(R.id.round_team);
            holder.score = (TextView) view.findViewById(R.id.round_score);
            holder.round_id = (TextView) view.findViewById(R.id.round_id);
            holder.row = (RelativeLayout) view.findViewById(R.id.row);

            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        if (item.get(i).getTeamStatus().toString().equals("Sendiri")){
            holder.row.setBackgroundResource(R.drawable.list_item_selector_own_team);
        }else{
            holder.row.setBackgroundResource(R.drawable.list_item_selector_default);
        }


        int no = i + 1;
        holder.no.setText(String.valueOf(no));
        holder.no_dada.setText(item.get(i).getNoDada().toString());
        holder.nama.setText(item.get(i).getAtletNama().toString());
        holder.nama_team.setText(item.get(i).getTeamNama().toString());
        holder.score.setText(String.valueOf(item.get(i).getScore()));
        holder.round_id.setText(String.valueOf(item.get(i).getRoundId()));

        /* update athlete rank */
        RoundModel model = new RoundModel(Integer.valueOf(holder.round_id.getText().toString()),
                holder.no.getText().toString());
        Config.TRACE("Item Rank",holder.no_dada.getText().toString()+" - "+holder.no.getText().toString());
        RoundTableSqlHandler sqlHandler = new RoundTableSqlHandler(context);
        sqlHandler.updateRank(model);

        return view;
    }

    static class ViewHolder{
        TextView no,round_id,
                no_dada,nama,
                nama_team,score;
        RelativeLayout row;
    }
}
