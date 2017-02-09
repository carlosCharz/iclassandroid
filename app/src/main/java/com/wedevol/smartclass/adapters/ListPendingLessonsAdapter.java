package com.wedevol.smartclass.adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.RateLessonActivity;
import com.wedevol.smartclass.models.Lesson;
import com.wedevol.smartclass.utils.PhoneCallListener;
import com.wedevol.smartclass.utils.interfaces.Constants;

import java.util.Calendar;
import java.util.List;

/** Created by paolorossi on 12/9/16.*/
public class ListPendingLessonsAdapter extends RecyclerView.Adapter<ListPendingLessonsAdapter.ViewHolder> {
    private final List<Lesson> mItems;
    private final Activity context;

    public ListPendingLessonsAdapter(Activity context, List<Lesson> list) {
        super();
        this.context = context;
        mItems = list;
    }

    @Override
    public ListPendingLessonsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_student_pending_counseling, viewGroup, false);
        return new ListPendingLessonsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListPendingLessonsAdapter.ViewHolder viewHolder, int i) {
        final Lesson lesson = mItems.get(i);

        viewHolder.tv_pending_counsel_course_counseller.setText(lesson.getCourseName() + " - " +
                lesson.getSenderFirstName() + " - " + lesson.getCurrency() + lesson.getPrice());
        viewHolder.tv_pending_counsel_date.setText(lesson.getClassDate() + " - " + lesson.getStartTime() +
                " a " + lesson.getEndTime() + " horas");

        viewHolder.iv_call_counseller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add PhoneStateListener
                PhoneCallListener phoneListener = new PhoneCallListener();
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:+51" + lesson.getPhone()));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                context.startActivity(callIntent);
            }
        });

        viewHolder.iv_rate_lesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
                int month = calendar.get(Calendar.MONTH) + 1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                String[] date= lesson.getClassDate().split("/");
                boolean datePassed = lesson.getEndTime() <= hourOfDay && Integer.parseInt(date[0]) <= day && Integer.parseInt(date[1]) <= month;
                if(datePassed){
                    Intent intent = new Intent(context, RateLessonActivity.class);
                    intent.putExtra(Constants.BUNDLE_LESSON_ID, lesson.getId());
                    intent.putExtra(Constants.BUNDLE_COURSE_NAME, lesson.getCourseName());
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Debe llevar la asesoria para poder calificarla", Toast.LENGTH_SHORT).show();
                }
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
        final ImageView iv_rate_lesson;
        final ImageView iv_detail_course;

        ViewHolder(View itemView) {
            super(itemView);
            tv_pending_counsel_course_counseller = (TextView) itemView.findViewById(R.id.tv_pending_counsel_course_counseller);
            tv_pending_counsel_date = (TextView) itemView.findViewById(R.id.tv_pending_counsel_date);
            iv_call_counseller = (ImageView) itemView.findViewById(R.id.iv_call_counseller);
            iv_rate_lesson = (ImageView) itemView.findViewById(R.id.iv_rate_lesson);
            iv_detail_course = (ImageView) itemView.findViewById(R.id.iv_detail_course);
        }
    }
}