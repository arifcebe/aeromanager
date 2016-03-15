package com.gac.aeromanager.hasilakhir.handler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gac.aeromanager.R;
import com.gac.aeromanager.hasilakhir.model.HasilAkhirModel;

import java.util.ArrayList;

/**
 * Created by arifcebe on 8/31/14.
 */
public class HasilAkhirAdapter extends BaseAdapter{
    private ArrayList<HasilAkhirModel> item;
    private LayoutInflater inflater;
    private Context context;
    private ViewHolder holder;

    public HasilAkhirAdapter(Context context,ArrayList<HasilAkhirModel> item){
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.item = item;
    }

    static class ViewHolder{
        RelativeLayout row;
        TextView no_urut,nodada,nama,teamNama,teamStatus,score;
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
            view = inflater.inflate(R.layout.hasil_akhir_item,null);
            holder = new ViewHolder();

            holder.no_urut = (TextView) view.findViewById(R.id.no_urut);
            holder.nama = (TextView) view.findViewById(R.id.ha_nama);
            holder.nodada = (TextView) view.findViewById(R.id.ha_nodada);
            holder.teamNama = (TextView) view.findViewById(R.id.ha_team_nama);
            holder.teamStatus = (TextView) view.findViewById(R.id.ha_team_status);
            holder.score = (TextView) view.findViewById(R.id.ha_score);
            holder.row = (RelativeLayout) view.findViewById(R.id.row);

            view.setTag(holder);
        }

        holder = (ViewHolder) view.getTag();

        if(item.get(i).getTeam_status().toString().equals("Sendiri")){
            holder.row.setBackgroundResource(R.drawable.list_item_selector_own_team);
        }else{
            holder.row.setBackgroundResource(R.drawable.list_item_selector_default);
        }

        int no = i+1;
        holder.no_urut.setText(String.valueOf(no));
        holder.nodada.setText(item.get(i).getNodada().toString());
        holder.nama.setText(item.get(i).getNama().toString());
        holder.teamNama.setText(item.get(i).getTeam_nama().toString());
        holder.score.setText(String.valueOf(item.get(i).getScore_total()));


        return view;
    }
}
