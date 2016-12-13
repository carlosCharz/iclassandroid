package com.wedevol.smartclass.utils.interfaces;

import android.widget.ImageView;

/** Created by paolo on 12/13/16.*/
public interface ItemClickListener {
    /**
     * Called when a recipe is clicked
     * @param position The position in the list on which the recipe has been clicked
     */
    void onItemClicked(int position);
}