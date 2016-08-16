package com.wedevol.smartclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleMainActivity extends AppCompatActivity {

    @BindView(R.id.add_schedule_button)
    FloatingActionButton _addScheduleButton;

    @BindView(R.id.hourExpListView)
    ExpandableListView hourExpListView;
    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> hourCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_main);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Horario");
        actionBar.setDisplayHomeAsUpEnabled(true);

        _addScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
                startActivity(intent);
            }
        });

        createGroupList();

        createCollection();

        final ScheduleExpandListAdapter expListAdapter = new ScheduleExpandListAdapter(this, groupList, hourCollection);
        hourExpListView.setAdapter(expListAdapter);

        hourExpListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                return false;
            }
        });

        for (int i = 0; i < hourCollection.size(); i++) {
            if (hourCollection.get(groupList.get(i)).size() > 0) {
                hourExpListView.expandGroup(i);
            }
        }

    }

    private void createGroupList() {
        groupList = new ArrayList<String>();
        groupList.add("Lunes");
        groupList.add("Martes");
        groupList.add("Miércoles");
        groupList.add("Jueves");
        groupList.add("Viernes");
        groupList.add("Sábado");
        groupList.add("Domingo");
    }

    private void createCollection() {
        // preparing mock hours collection(child)
        String[] lunHours = { };
        String[] marHours = { "9:00 - 11:00 AM", "5:00 - 7:00 PM"};
        String[] mieHours = { };
        String[] jueHours = {"5:00 - 9:00 PM" };
        String[] vieHours = { };
        String[] sabHours = { };
        String[] domHours = { };


        hourCollection = new LinkedHashMap<String, List<String>>();

        for (String day : groupList) {
            if (day.equals("Lunes")) {
                loadChild(lunHours);
            } else if (day.equals("Martes"))
                loadChild(marHours);
            else if (day.equals("Miércoles"))
                loadChild(mieHours);
            else if (day.equals("Jueves"))
                loadChild(jueHours);
            else if (day.equals("Viernes"))
                loadChild(vieHours);
            else if (day.equals("Sábado"))
                loadChild(sabHours);
            else
                loadChild(domHours);

            hourCollection.put(day, childList);
        }
    }

    private void loadChild(String[] hours) {
        childList = new ArrayList<String>();
        for (String hour : hours)
            childList.add(hour);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
