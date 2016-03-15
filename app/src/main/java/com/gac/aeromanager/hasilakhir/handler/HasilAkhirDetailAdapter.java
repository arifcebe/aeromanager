package com.gac.aeromanager.hasilakhir.handler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gac.aeromanager.R;
import com.gac.aeromanager.hasilakhir.model.HasilAkhirDetailModel;

import java.util.ArrayList;

/**
 * Created by arifcebe on 8/31/14.
 */
public class HasilAkhirDetailAdapter extends BaseAdapter {
    private ArrayList<HasilAkhirDetailModel> item;
    private LayoutInflater inflater;
    private Context context;
    private ViewHolder holder;

    public HasilAkhirDetailAdapter(Context context,ArrayList<HasilAkhirDetailModel> item){
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.item = item;
    }

    static class ViewHolder{
        TextView roundKe,score,rank;
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
            view = inflater.inflate(R.layout.hasil_akhir_detail_item,null);

            holder = new ViewHolder();
            holder.roundKe = (TextView) view.findViewById(R.id.round_ke);
            holder.score = (TextView) view.findViewById(R.id.round_score);
            holder.rank = (TextView) view.findViewById(R.id.round_rank);
            view.setTag(holder);

        }
        holder = (ViewHolder) view.getTag();

        holder.roundKe.setText(item.get(i).getRoundKe().toString());
        holder.score.setText(String.valueOf(item.get(i).getScore()));
        holder.rank.setText(item.get(i).getRank().toString());

        return view;
    }
}
