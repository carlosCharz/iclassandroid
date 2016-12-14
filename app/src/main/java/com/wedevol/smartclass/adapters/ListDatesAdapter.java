package com.wedevol.smartclass.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.utils.interfaces.Constants;

import java.util.List;

/** Created by paolo on 12/13/16.*/
public class ListDatesAdapter extends RecyclerView.Adapter<ListDatesAdapter.ViewHolder> {
    private final List<String> mItems;
    private final Activity context;
    private final boolean is_simple_date;

    public ListDatesAdapter(Activity context, List<String> list, boolean is_simple_date) {
        super();
        this.context = context;
        mItems = list;
        this.is_simple_date = is_simple_date;
    }

    @Override
    public ListDatesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_dates, viewGroup, false);
        return new ListDatesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListDatesAdapter.ViewHolder viewHolder, int i) {
        final String date = mItems.get(i);

        viewHolder.tv_date_name.setText(date);
        if(!is_simple_date) viewHolder.tv_city_name.setText("Lima, Peru");

        viewHolder.cd_date_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(Constants.BUNDLE_DATE, date);
                context.setResult(Activity.RESULT_OK, resultIntent);
                context.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        final TextView tv_date_name;
        final TextView tv_city_name;
        final CardView cd_date_holder;

        ViewHolder(View itemView) {
            super(itemView);
            tv_date_name = (TextView) itemView.findViewById(R.id.tv_date_name);
            tv_city_name = (TextView) itemView.findViewById(R.id.tv_city_name);
            cd_date_holder = (CardView) itemView.findViewById(R.id.cd_date_holder);
        }
    }
}