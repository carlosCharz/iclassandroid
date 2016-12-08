package com.wedevol.smartclass.navigation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wedevol.smartclass.R;

import java.util.Collections;
import java.util.List;

/** Created by Paolo on 3/14/2016.*/
class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {
    private List<NavDrawerItem> data = Collections.emptyList();
    final private LayoutInflater inflater;
    private static final int TYPE_ITEM_WITH_IMAGE = 1;
    private static final int TYPE_ITEM = 2;
    private OnItemClickListener mItemClickListener;

    NavigationDrawerAdapter(Context context, List<NavDrawerItem> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layout;

        if(viewType == TYPE_ITEM_WITH_IMAGE){
            layout = R.layout.nav_drawer_first_row;
        }else{
            layout = R.layout.nav_drawer_second_row;
        }

        View view = inflater.inflate(layout, parent, false);
        return new MyViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavDrawerItem current = data.get(position);

        if(current.isWithImage()){
            holder.tv_title.setText(current.getTitle());
            holder.iv_item_image.setImageResource(current.getResourceId());

            if(current.isLastItemwithImage()){
                holder.ll_divider.setVisibility(View.VISIBLE);
            }else{
                holder.ll_divider.setVisibility(View.GONE);
            }
        }else{
            holder.tv_title.setText(current.getTitle());
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (data.get(position).isWithImage()) {
            return TYPE_ITEM_WITH_IMAGE;
        }else
            return TYPE_ITEM;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        final TextView tv_title;
        ImageView iv_item_image;
        LinearLayout ll_divider;
        final LinearLayout ll_menu_item;

        MyViewHolder(View itemView, int viewType) {
            super(itemView);

            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            ll_menu_item = (LinearLayout) itemView.findViewById(R.id.ll_menu_item);

            if (viewType == TYPE_ITEM_WITH_IMAGE) {
                iv_item_image = (ImageView) itemView.findViewById(R.id.iv_item_image);
                ll_divider = (LinearLayout) itemView.findViewById(R.id.ll_divider);
            }

            ll_menu_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(view, getAdapterPosition());
                    }
                }
            });

        }
    }

    void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}