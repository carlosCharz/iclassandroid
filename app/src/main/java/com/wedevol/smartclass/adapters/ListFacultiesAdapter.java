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
import com.wedevol.smartclass.models.Faculty;
import com.wedevol.smartclass.utils.interfaces.Constants;

import java.util.List;

/** Created by paolo on 1/29/17.*/
public class ListFacultiesAdapter extends RecyclerView.Adapter<ListFacultiesAdapter.ViewHolder> {
    private final List<Faculty> mItems;
    private final Activity context;

    public ListFacultiesAdapter(Activity context, List<Faculty> list) {
        super();
        this.context = context;
        mItems = list;
    }

    @Override
    public ListFacultiesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_faculty, viewGroup, false);
        return new ListFacultiesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListFacultiesAdapter.ViewHolder viewHolder, int i) {
        final Faculty faculty = mItems.get(i);

        viewHolder.tv_faculty_name.setText(faculty.getName());

        viewHolder.cd_faculty_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(Constants.BUNDLE_FACULTY_NAME, faculty.getName());
                resultIntent.putExtra(Constants.BUNDLE_FACULTY_ID, faculty.getId());
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
        final TextView tv_faculty_name;
        final CardView cd_faculty_holder;

        ViewHolder(View itemView) {
            super(itemView);
            tv_faculty_name = (TextView) itemView.findViewById(R.id.tv_faculty_name);
            cd_faculty_holder = (CardView) itemView.findViewById(R.id.cd_faculty_holder);
        }
    }
}