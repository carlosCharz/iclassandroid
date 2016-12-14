package com.wedevol.smartclass.adapters;

import android.app.Activity;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.models.Class;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/** Created by paolo on 12/13/16.*/
public class ListNotificationsAdapter  extends RecyclerView.Adapter<ListNotificationsAdapter.ViewHolder> {
    private final List<Class> mItems;
    private final Activity context;
    private final List<ViewHolder> viewHoldersList;
    private Handler handler = new Handler();
    private Runnable updateRemainingTimeRunnable = new Runnable() {
        @Override
        public void run() {
            synchronized (viewHoldersList) {
                for (ViewHolder holder : viewHoldersList) {
                    holder.updateTimeRemaining();
                }
            }
        }
    };

    private void startUpdateTimer() {
        Timer tmr = new Timer();
        tmr.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(updateRemainingTimeRunnable);
            }
        }, 1000, 1000);
    }


    public ListNotificationsAdapter(Activity context, List<Class> list) {
        super();
        this.context = context;
        mItems = list;
        viewHoldersList = new ArrayList<>();
        startUpdateTimer();
    }

    @Override
    public ListNotificationsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_notifications, viewGroup, false);
        return new ListNotificationsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListNotificationsAdapter.ViewHolder viewHolder, int i) {
        final Class classy = mItems.get(i);

        viewHolder.tv_notification_counsult_data.setText("Alex - Calculo 1 - S./ 18");
        viewHolder.tv_notification_date_time.setText("Jueves 16/10 de 2 a 4pm");
        synchronized (viewHoldersList) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, new Random().nextInt(3));
            viewHolder.setData(calendar.getTime());
            //viewHolder.setData(classy.getSchedule().getDate());
            if(viewHoldersList.size()< mItems.size()) viewHoldersList.add(viewHolder);
        }

        viewHolder.b_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        viewHolder.b_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        final TextView tv_notification_counsult_data;
        final TextView tv_notification_date_time;
        final TextView tv_countdown_timer;
        final Button b_cancel;
        final Button b_confirm;
        private Date date;

        ViewHolder(View itemView) {
            super(itemView);
            tv_notification_counsult_data = (TextView) itemView.findViewById(R.id.tv_notification_counsult_data);
            tv_notification_date_time = (TextView) itemView.findViewById(R.id.tv_notification_date_time);
            tv_countdown_timer = (TextView) itemView.findViewById(R.id.tv_countdown_timer);
            b_cancel = (Button) itemView.findViewById(R.id.b_cancel);
            b_confirm = (Button) itemView.findViewById(R.id.b_confirm);

        }

        void updateTimeRemaining() {
            try {
                //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar start_date = Calendar.getInstance();

                Calendar end_date = Calendar.getInstance();
                end_date.setTime(date);

                final Calendar today = Calendar.getInstance();

                long timeDiff = end_date.getTimeInMillis() - today.getTimeInMillis();
                if (timeDiff > 0) {
                    long seconds = timeDiff / 1000 % 60;
                    long minutes = timeDiff / (60 * 1000) % 60;
                    long hours = timeDiff / (60 * 60 * 1000) % 24;
                    //long days = (int) diff / (24 * 60 * 60 * 1000);
                    long days = TimeUnit.MILLISECONDS.toDays(timeDiff);


                    String left = "";
                    if (days > 0)
                        left += days + ":";
                    if (hours > 0)
                        left += hours + ":";
                    if (minutes > 0)
                        left += minutes + ":";
                    left += seconds;

                    final String finalLeft = left;
                    tv_countdown_timer.setText(finalLeft);
                } else {
                    tv_countdown_timer.setText("No confirmada");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        public void setData(Date date) {
            this.date = date;
        }
    }
}