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
import com.wedevol.smartclass.models.Course;
import com.wedevol.smartclass.utils.interfaces.Constants;

import java.util.List;

/** Created by paolorossi on 12/11/16.*/
public class ListCoursesAdapter  extends RecyclerView.Adapter<ListCoursesAdapter.ViewHolder> {
    private final List<Course> mItems;
    private final Activity context;

    public ListCoursesAdapter(Activity context, List<Course> list) {
        super();
        this.context = context;
        mItems = list;
    }

    @Override
    public ListCoursesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_course, viewGroup, false);
        return new ListCoursesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListCoursesAdapter.ViewHolder viewHolder, int i) {
        final Course course = mItems.get(i);

        viewHolder.tv_course_name.setText(course.getName());
        viewHolder.tv_course_university_career.setText(course.getUniversity());

        viewHolder.cd_course_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(Constants.BUNDLE_COURSE_ID, course.getId());
                resultIntent.putExtra(Constants.BUNDLE_COURSE_NAME, course.getName());
                context.setResult(Activity.RESULT_OK, resultIntent);
                context.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public List<Course> getItems() {
        return mItems;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        final TextView tv_course_name;
        final TextView tv_course_university_career;
        final CardView cd_course_holder;

        ViewHolder(View itemView) {
            super(itemView);
            tv_course_name = (TextView) itemView.findViewById(R.id.tv_course_name);
            tv_course_university_career = (TextView) itemView.findViewById(R.id.tv_course_university_career);
            cd_course_holder = (CardView) itemView.findViewById(R.id.cd_course_holder);
        }
    }
}