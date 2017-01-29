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

/** Created by paolo on 12/20/16.*/
public class ListBanksAdapter extends RecyclerView.Adapter<ListBanksAdapter.ViewHolder> {
    private final List<String> mItems;
    private final Activity context;

    public ListBanksAdapter(Activity context, List<String> list) {
        super();
        this.context = context;
        mItems = list;
    }

    @Override
    public ListBanksAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_bank, viewGroup, false);
        return new ListBanksAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListBanksAdapter.ViewHolder viewHolder, int i) {
        final String bank = mItems.get(i);

        viewHolder.tv_bank_name.setText(bank);

        viewHolder.cd_bank_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(Constants.BUNDLE_BANK_NAME, bank);
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
        final TextView tv_bank_name;
        final CardView cd_bank_holder;

        ViewHolder(View itemView) {
            super(itemView);
            tv_bank_name = (TextView) itemView.findViewById(R.id.tv_bank_name);
            cd_bank_holder = (CardView) itemView.findViewById(R.id.cd_bank_holder);
        }
    }
}