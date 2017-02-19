package com.wedevol.smartclass.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.models.Schedule;
import com.wedevol.smartclass.utils.IClassLinearLayoutManager;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.interfaces.ScheduleClickListener;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/** Created by paolo on 12/14/16.*/
public class ListScheduleTimeWindowAdapter extends RecyclerView.Adapter{
    private final List<Schedule> mItems;
    private final Activity context;
    private final String headerName;
    private final ScheduleClickListener itemClickListener;
    private final IClassLinearLayoutManager iClassLinearLayoutManager;

    public ListScheduleTimeWindowAdapter(Activity context, List<Schedule> list, String headerName,
                                         ScheduleClickListener itemClickListener,
                                         IClassLinearLayoutManager iClassLinearLayoutManager) {
        super();
        this.context = context;
        mItems = list;
        this.headerName = headerName;
        this.itemClickListener = itemClickListener;
        this.iClassLinearLayoutManager = iClassLinearLayoutManager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v;
        if(i==0){
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.cardview_schedule_time_header, viewGroup, false);
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
                    itemClickListener.onItemClicked(headerName, -1);
                }
            });
        } else {
            final int n = i-1;
            final Schedule schedule = mItems.get(i-1);
            ((ItemViewHolder)viewHolder).tv_time_in_day.setText(schedule.getStartTime() + " a "+ schedule.getEndTime());
            ((ItemViewHolder)viewHolder).iv_kill_time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClicked(headerName, n);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size() + 1;
    }

    public void deletePosition(final int position) {
        Schedule schedule = mItems.get(position);
        deleteSchedule(schedule, position);
    }

    public void deleteAll() {
        for(int i=0; i<mItems.size();i++){
            deleteSchedule(mItems.get(i), i);
        }
    }

    private void deleteSchedule(Schedule schedule, final int position) {
        iClassLinearLayoutManager.setScrollEnabled(false);
        RestClient restClient = new RestClient(context);
        restClient.getWebservices().deleteSchedule(SharedPreferencesManager.getInstance(context).getUserInfo().getAccessToken(),
                schedule.getId(), new IClassCallback<JsonObject>(context) {
            @Override
            public void success(JsonObject jsonObject, Response response) {
                super.success(jsonObject, response);
                mItems.remove(position);
                notifyDataSetChanged();
                iClassLinearLayoutManager.setScrollEnabled(true);
            }

            @Override
            public void failure(RetrofitError error){
                super.failure(error);
                iClassLinearLayoutManager.setScrollEnabled(true);
            }
        });
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