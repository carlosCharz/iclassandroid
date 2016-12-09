package com.wedevol.smartclass.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.models.Class;

import java.util.List;

/** Created by paolorossi on 12/9/16.*/
public class PendingCounselsAdapter extends RecyclerView.Adapter<PendingCounselsAdapter.ViewHolder>{
    private final List<Class> mItems;
    private final Activity context;

    public PendingCounselsAdapter(Activity context, List<Class> list) {
        super();
        this.context = context;
        mItems = list;
    }

    @Override
    public PendingCounselsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_student_pending_counseling, viewGroup, false);
        return new PendingCounselsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PendingCounselsAdapter.ViewHolder viewHolder, int i) {
        final Class classy = mItems.get(i);
        /*
        viewHolder.tv_pending_counsel_course_counseller.setText(classy.getCourse().getName() + " - "
                + classy.getInstructor().getReducedName(18));
        viewHolder.tv_pending_counsel_date.setText(""+classy.getClassDate() + " - "
                + classy.getSchedule().printSchedule());
        */
        viewHolder.tv_pending_counsel_course_counseller.setText("Richard Cancino - IMU");
        viewHolder.tv_pending_counsel_date.setText("12/12/16 - 20 a 24 horas");

        viewHolder.iv_call_counseller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO call with cellphone API

            }
        });

        viewHolder.iv_detail_counseller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO go to assesor detail
            }
        });

        viewHolder.iv_detail_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO go to maps
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        final TextView tv_pending_counsel_course_counseller;
        final TextView tv_pending_counsel_date;
        final ImageView iv_call_counseller;
        final ImageView iv_detail_counseller;
        final ImageView iv_detail_course;

        ViewHolder(View itemView) {
            super(itemView);
            tv_pending_counsel_course_counseller = (TextView) itemView.findViewById(R.id.tv_pending_counsel_course_counseller);
            tv_pending_counsel_date = (TextView) itemView.findViewById(R.id.tv_pending_counsel_date);
            iv_call_counseller = (ImageView) itemView.findViewById(R.id.iv_call_counseller);
            iv_detail_counseller = (ImageView) itemView.findViewById(R.id.iv_detail_counseller);
            iv_detail_course = (ImageView) itemView.findViewById(R.id.iv_detail_course);
        }
    }
}