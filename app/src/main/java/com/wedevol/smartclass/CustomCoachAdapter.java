package com.wedevol.smartclass;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by charz on 8/8/16.
 */
public class CustomCoachAdapter extends BaseAdapter {

    private static final String TAG = CustomCoachAdapter.class.getSimpleName();
    ArrayList<String> listArray;

    public CustomCoachAdapter() {
        listArray = new ArrayList<>(5);
        listArray.add("Cálculo 1");
        listArray.add("Física 1");
        listArray.add("Química 1");
        listArray.add("Dibujo en ingeniería");
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
            view = inflater.inflate(R.layout.activity_coach_profile_list_item, parent, false);
        }

        final String courseName = listArray.get(index);

        TextView textView = (TextView) view.findViewById(R.id.label_course);
        textView.setText(courseName);

        view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                //ItemClicked item = adapter.getItemAtPosition(position);

                Intent intent = new Intent(parent.getContext(), CourseActivity.class);

                parent.getContext().startActivity(intent);

            }


        });

        return view;
    }
}
