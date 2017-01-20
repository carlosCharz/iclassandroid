package com.wedevol.smartclass.utils.interfaces;

import android.os.Parcelable;

/** Created by paolo on 1/20/17.*/
public interface PriceChangeListener extends Parcelable{
    void onPriceChanged(int position, int newPrice);
}
