package com.wedevol.smartclass.adapters;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.utils.interfaces.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

/** Created by paolo on 12/13/16.*/
public class ListTimesAdapter extends RecyclerView.Adapter<ListTimesAdapter.ViewHolder> {
    private final List<String> mItems;
    private final Activity context;
    private final ItemClickListener itemClickListener;
    private List<Boolean> positionsSelected;

    public ListTimesAdapter(Activity context, List<String> list, ItemClickListener itemClickListener) {
        super();
        this.context = context;
        mItems = list;
        this.itemClickListener = itemClickListener;
        positionsSelected = new ArrayList<>();
        for (int i = 0; i<mItems.size(); i++){
            positionsSelected.add(false);
        }
    }

    @Override
    public ListTimesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_time, viewGroup, false);
        return new ListTimesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListTimesAdapter.ViewHolder viewHolder, int i) {
        if(!positionsSelected.get(i)){
            viewHolder.cd_time_holder.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        }else{
            viewHolder.cd_time_holder.setBackgroundColor(ContextCompat.getColor(context, R.color.light_iron));
        }

        final String time = mItems.get(i);

        viewHolder.tv_time_name.setText(time);

        final int n = i;
        viewHolder.cd_time_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClicked(n);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updatePosition(boolean selected, int position) {
        positionsSelected.set(position, selected);
        notifyItemChanged(position);
    }

    public String getItem(int position) {
        return mItems.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        final TextView tv_time_name;
        final CardView cd_time_holder;

        ViewHolder(View itemView) {
            super(itemView);
            tv_time_name = (TextView) itemView.findViewById(R.id.tv_time_name);
            cd_time_holder = (CardView) itemView.findViewById(R.id.cd_time_holder);
        }
    }
}