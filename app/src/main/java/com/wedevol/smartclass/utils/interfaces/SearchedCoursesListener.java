package com.wedevol.smartclass.utils.interfaces;

import android.os.Parcelable;

/** Created by paolo on 1/29/17.*/
public interface SearchedCoursesListener extends Parcelable{
    void onCourseSearched(int universityId, int facultyId);
}
