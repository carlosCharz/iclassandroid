package com.wedevol.smartclass.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/** Created by paolo on 2/5/17.*/

public class IClassLinearLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnabled = true;

    public IClassLinearLayoutManager(Context context) {
        super(context);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}

