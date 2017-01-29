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
import com.wedevol.smartclass.models.University;
import com.wedevol.smartclass.utils.interfaces.Constants;

import java.util.List;

/** Created by paolo on 1/29/17.*/
public class ListUniversitiesAdapter extends RecyclerView.Adapter<ListUniversitiesAdapter.ViewHolder> {
    private final List<University> mItems;
    private final Activity context;

    public ListUniversitiesAdapter(Activity context, List<University> list) {
        super();
        this.context = context;
        mItems = list;
    }

    @Override
    public ListUniversitiesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_university, viewGroup, false);
        return new ListUniversitiesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListUniversitiesAdapter.ViewHolder viewHolder, int i) {
        final University university = mItems.get(i);

        viewHolder.tv_university_name.setText(university.getName());

        viewHolder.cd_university_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(Constants.BUNDLE_UNIVERSITY_NAME, university.getName());
                resultIntent.putExtra(Constants.BUNDLE_UNIVERSITY_ID, university.getId());
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
        final TextView tv_university_name;
        final CardView cd_university_holder;

        ViewHolder(View itemView) {
            super(itemView);
            tv_university_name = (TextView) itemView.findViewById(R.id.tv_university_name);
            cd_university_holder = (CardView) itemView.findViewById(R.id.cd_university_holder);
        }
    }
}