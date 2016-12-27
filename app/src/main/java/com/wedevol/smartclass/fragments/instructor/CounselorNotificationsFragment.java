package com.wedevol.smartclass.fragments.instructor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.adapters.ListNotificationsAdapter;
import com.wedevol.smartclass.models.Lesson;

import java.util.ArrayList;
import java.util.List;

/** Created by paolorossi on 12/8/16.*/
public class CounselorNotificationsFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_counselor_notifications, container, false);
        setElements(view);
        setActions();
        return view;
    }

    private void setElements(View view) {
        List<Lesson> lessonList = new ArrayList<>();
        Lesson classy = new Lesson();
        lessonList.add(classy);
        classy = new Lesson();
        lessonList.add(classy);
        classy = new Lesson();
        lessonList.add(classy);
        classy = new Lesson();
        lessonList.add(classy);

        RecyclerView rv_notifications = (RecyclerView) view.findViewById(R.id.rv_notifications);
        rv_notifications.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_notifications.setAdapter(new ListNotificationsAdapter(getActivity(), lessonList));
    }

    private void setActions() {

    }
}