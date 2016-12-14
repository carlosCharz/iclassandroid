package com.wedevol.smartclass.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wedevol.smartclass.R;

import java.util.List;

/** Created by paolo on 12/14/16.*/
public class ListScheduleTimeWindowAdapter extends RecyclerView.Adapter{
    private final List<String> mItems;
    private final Activity context;
    private final String headerName;

    public ListScheduleTimeWindowAdapter(Activity context, List<String> list, String headerName) {
        super();
        this.context = context;
        mItems = list;
        this.headerName = headerName;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v;
        if(i==0){
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.cardview_schedule_day_header, viewGroup, false);
            return new HeadViewHolder(v);
        }else{
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.cardview_schedule_time_item, viewGroup, false);
            return new ItemViewHolder(v);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position==0?0:1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if(getItemViewType(i)==0) {
            ((HeadViewHolder)viewHolder).tv_header_name.setText(headerName);
            ((HeadViewHolder)viewHolder).iv_kill_entire_day.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Pronto me borrare pero no es prioritario haha", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            final String date = mItems.get(i-1);
            ((ItemViewHolder)viewHolder).tv_time_in_day.setText(date);
            ((ItemViewHolder)viewHolder).iv_kill_time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Pronto me borrare pero no es prioritario haha", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private class HeadViewHolder extends RecyclerView.ViewHolder{
        final TextView tv_header_name;
        final ImageView iv_kill_entire_day;

        HeadViewHolder(View itemView) {
            super(itemView);
            tv_header_name = (TextView) itemView.findViewById(R.id.tv_header_name);
            iv_kill_entire_day = (ImageView) itemView.findViewById(R.id.iv_kill_entire_day);
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder{
        final TextView tv_time_in_day;
        final ImageView iv_kill_time;

        ItemViewHolder(View itemView) {
            super(itemView);
            tv_time_in_day = (TextView) itemView.findViewById(R.id.tv_time_in_day);
            iv_kill_time = (ImageView) itemView.findViewById(R.id.iv_kill_time);
        }
    }
}