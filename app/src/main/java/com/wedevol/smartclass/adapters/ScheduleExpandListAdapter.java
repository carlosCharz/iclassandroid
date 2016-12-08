package com.wedevol.smartclass.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.AlertDialog;

import com.wedevol.smartclass.R;

import java.util.List;
import java.util.Map;

/** Created by charz on 8/16/16.*/
public class ScheduleExpandListAdapter extends BaseExpandableListAdapter {

    private Activity context;
    private Map<String, List<String>> hourCollections;
    private List<String> hours;

    public ScheduleExpandListAdapter(Activity context, List<String> hours,
                                 Map<String, List<String>> hourCollections) {
        this.context = context;
        this.hourCollections = hourCollections;
        this.hours = hours;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return hourCollections.get(hours.get(groupPosition)).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String hour = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_schedule, null);
        }

        TextView item = (TextView) convertView.findViewById(R.id.hourText);

        ImageView delete = (ImageView) convertView.findViewById(R.id.deleteHourSchedule);
        delete.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Deseas remover este horario?");
                builder.setCancelable(false);
                builder.setPositiveButton("Si",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                List<String> child =
                                        hourCollections.get(hours.get(groupPosition));
                                child.remove(childPosition);
                                notifyDataSetChanged();
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        item.setText(hour);
        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
        return hourCollections.get(hours.get(groupPosition)).size();
    }

    public Object getGroup(int groupPosition) {
        return hours.get(groupPosition);
    }

    public int getGroupCount() {
        return hours.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String day = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_schedule,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.dayText);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(day);
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
