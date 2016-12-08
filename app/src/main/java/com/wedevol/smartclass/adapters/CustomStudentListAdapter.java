package com.wedevol.smartclass.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wedevol.smartclass.R;

import java.util.ArrayList;

/**
 * Created by charz on 8/8/16.
 */
public class CustomStudentListAdapter extends BaseAdapter {

    ArrayList<String> listArray;

    public CustomStudentListAdapter() {
        listArray = new ArrayList<>(5);
        listArray.add("Cálculo 1 - Jue 2:00 - 4:00 PM");
        listArray.add("Física 1 - Mie 6:30 - 9:30 PM");
    }

    @Override
    public int getCount() {
        return listArray.size();    // total number of elements in the list
    }

    @Override
    public Object getItem(int i) {
        return listArray.get(i);    // single item in the list
    }

    @Override
    public long getItemId(int i) {
        return i;                   // index number
    }

    @Override
    public View getView(int index, View view, final ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.activity_student_profile_list_item, parent, false);
        }

        final String courseName = listArray.get(index);

        TextView textView = (TextView) view.findViewById(R.id.label_asesoria);
        textView.setText(courseName);

        return view;
    }
}
