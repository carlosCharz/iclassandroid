package com.wedevol.smartclass.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wedevol.smartclass.BuildConfig;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.models.Course;
import com.wedevol.smartclass.utils.dialogs.ChangePriceDialogFragment;
import com.wedevol.smartclass.utils.interfaces.PriceChangeListener;

import java.util.ArrayList;
import java.util.List;

/** Created by paolo on 12/15/16.*/
@SuppressLint("ParcelCreator")
public class ListCourseStateAdapter extends RecyclerView.Adapter implements PriceChangeListener {
    private final List<Course> mItems;
    private final Activity context;
    private final String headerName;
    private final String headerExplanation;
    private final boolean showHourlyRate;
    private final boolean selectable;
    private List<Boolean> positionsSelected;
    private ListCourseStateAdapter self;

    public ListCourseStateAdapter(Activity context, List<Course> list, String headerName,
                                  String headerExplanation, boolean showHourlyRate, boolean selectable) {
        super();
        self = this;
        this.context = context;
        mItems = list;
        this.headerName = headerName;
        this.headerExplanation = headerExplanation;
        this.showHourlyRate = showHourlyRate;
        this.selectable = selectable;
        positionsSelected = new ArrayList<>();
        for (int i = 0; i<mItems.size(); i++){
            positionsSelected.add(false);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v;
        if(i==0){
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.cardview_course_state_header, viewGroup, false);
            return new ListCourseStateAdapter.HeadViewHolder(v);
        }else{
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.cardview_course_state_item, viewGroup, false);
            return new ListCourseStateAdapter.ItemViewHolder(v);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position==0?0:1;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int i) {
        final int finalI = i;
        if(getItemViewType(finalI)==0) {
            ((ListCourseStateAdapter.HeadViewHolder)viewHolder).tv_header_name.setText(headerName);
            ((ListCourseStateAdapter.HeadViewHolder)viewHolder).tv_header_explanation.setText(headerExplanation);
        } else {
            final Course course = mItems.get(finalI-1);
            ((ListCourseStateAdapter.ItemViewHolder)viewHolder).tv_course_name.setText(course.getName());
            if(showHourlyRate) {
                ((ListCourseStateAdapter.ItemViewHolder)viewHolder).tv_course_hourly_rate.setVisibility(View.VISIBLE);
                ((ListCourseStateAdapter.ItemViewHolder)viewHolder).tv_course_hourly_rate.setText(""+course.getPrice());
                ((ListCourseStateAdapter.ItemViewHolder)viewHolder).rl_course_holder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ChangePriceDialogFragment suggestCourseDialogFragment =
                                ChangePriceDialogFragment.newInstance(R.layout.dialog_suggest_price, course.getId(),
                                        course.getCurrency(), course.getStatus(), finalI-1, self);
                        suggestCourseDialogFragment.show(((FragmentActivity)context).getSupportFragmentManager(), "Cambiar Precio");
                    }
                });
                ((ListCourseStateAdapter.ItemViewHolder)viewHolder).iv_pdf_icon.setVisibility(View.VISIBLE);
                ((ListCourseStateAdapter.ItemViewHolder)viewHolder).l_lessons.setVisibility(View.VISIBLE);
                ((ListCourseStateAdapter.ItemViewHolder)viewHolder).l_exercises.setVisibility(View.VISIBLE);

                ((ListCourseStateAdapter.ItemViewHolder)viewHolder).l_lessons.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(course.getClassMaterialUrl()==null){
                            Toast.makeText(context, "No hay material de clase", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Uri uriUrl = Uri.parse(BuildConfig.BASE_URL + course.getClassMaterialUrl());
                        Intent launchBrowser = new Intent(Intent.ACTION_VIEW,uriUrl);
                        context.startActivity(launchBrowser);
                    }
                });

                ((ListCourseStateAdapter.ItemViewHolder)viewHolder).l_exercises.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(course.getExerciseMaterialUrl()==null){
                            Toast.makeText(context, "No hay material de ejercicios", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Uri uriUrl = Uri.parse(course.getClassMaterialUrl());
                        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                        context.startActivity(launchBrowser);
                    }
                });
            } else {
                ((ListCourseStateAdapter.ItemViewHolder)viewHolder).tv_course_hourly_rate.setVisibility(View.GONE);
                ((ListCourseStateAdapter.ItemViewHolder)viewHolder).iv_pdf_icon.setVisibility(View.GONE);
                ((ListCourseStateAdapter.ItemViewHolder)viewHolder).l_lessons.setVisibility(View.GONE);
                ((ListCourseStateAdapter.ItemViewHolder)viewHolder).l_exercises.setVisibility(View.GONE);

            }

            if(selectable){
                if(!positionsSelected.get(finalI-1)){
                    ((ListCourseStateAdapter.ItemViewHolder)viewHolder).rl_course_holder.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                } else {
                    ((ListCourseStateAdapter.ItemViewHolder)viewHolder).rl_course_holder.setBackgroundColor(ContextCompat.getColor(context, R.color.light_iron));
                }

                ((ListCourseStateAdapter.ItemViewHolder)viewHolder).rl_course_holder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        positionsSelected.set(finalI-1, !positionsSelected.get(finalI-1));
                        notifyItemChanged(finalI-1);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size()+1;
    }

    @Override
    public void onPriceChanged(int position, int newPrice) {
        mItems.get(position).setPrice(newPrice);
        notifyItemChanged(position+1);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    private class HeadViewHolder extends RecyclerView.ViewHolder{
        final TextView tv_header_name;
        final TextView tv_header_explanation;

        HeadViewHolder(View itemView) {
            super(itemView);
            tv_header_name = (TextView) itemView.findViewById(R.id.tv_header_name);
            tv_header_explanation = (TextView) itemView.findViewById(R.id.tv_header_explanation);
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder{
        final TextView tv_course_name;
        final TextView tv_course_hourly_rate;
        final RelativeLayout rl_course_holder;
        final ImageView iv_pdf_icon;
        final LinearLayout l_lessons;
        final LinearLayout l_exercises;

        ItemViewHolder(View itemView) {
            super(itemView);
            tv_course_name = (TextView) itemView.findViewById(R.id.tv_course_name);
            tv_course_hourly_rate = (TextView) itemView.findViewById(R.id.tv_course_hourly_rate);
            rl_course_holder = (RelativeLayout) itemView.findViewById(R.id.rl_course_holder);
            iv_pdf_icon = (ImageView) itemView.findViewById(R.id.iv_pdf_icon);
            l_lessons = (LinearLayout) itemView.findViewById(R.id.l_lessons);
            l_exercises = (LinearLayout) itemView.findViewById(R.id.l_exercises) ;
        }
    }
}