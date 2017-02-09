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
import com.wedevol.smartclass.models.Instructor;
import com.wedevol.smartclass.utils.interfaces.ItemClickListener;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/** Created by paolo on 12/13/16.*/
public class ListInstructorsAdapter extends RecyclerView.Adapter<ListInstructorsAdapter.ViewHolder> {
    private final List<Instructor> mItems;
    private final List<Boolean> positionsSelected;
    private final Activity context;
    private ItemClickListener itemClickListener;

    public ListInstructorsAdapter(Activity context, List<Instructor> list, ItemClickListener itemClickListener) {
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
    public ListInstructorsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_elligible_instructor, viewGroup, false);
        return new ListInstructorsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListInstructorsAdapter.ViewHolder viewHolder, int i) {
        final Instructor instructor = mItems.get(i);

        if(!positionsSelected.get(i)){
            viewHolder.cd_counsellor_holder.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        }else{
            viewHolder.cd_counsellor_holder.setBackgroundColor(ContextCompat.getColor(context, R.color.light_iron));
        }

        viewHolder.tv_counsellor_name.setText(instructor.getName());

        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.DOWN);
        String formatedDecimal = df.format(instructor.getRating());
        viewHolder.tv_counsellor_rating.setText(formatedDecimal);
        viewHolder.tv_counsellor_hourly_rate.setText("S/./H "+ instructor.getPrice());
        viewHolder.tv_counsellor_level.setText("" + instructor.getLevel());

        final int n = i;
        viewHolder.cd_counsellor_holder.setOnClickListener(new View.OnClickListener() {
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

    public Instructor getItemInPosition(int position) {
        return mItems.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        final TextView tv_counsellor_name;
        final TextView tv_counsellor_rating;
        final TextView tv_counsellor_hourly_rate;
        final TextView tv_counsellor_level;
        final CardView cd_counsellor_holder;

        ViewHolder(View itemView) {
            super(itemView);
            tv_counsellor_name = (TextView) itemView.findViewById(R.id.tv_counsellor_name);
            tv_counsellor_rating = (TextView) itemView.findViewById(R.id.tv_counsellor_rating);
            tv_counsellor_hourly_rate = (TextView) itemView.findViewById(R.id.tv_counsellor_hourly_rate);
            tv_counsellor_level = (TextView) itemView.findViewById(R.id.tv_counsellor_level);
            cd_counsellor_holder = (CardView) itemView.findViewById(R.id.cd_counsellor_holder);
        }
    }
}