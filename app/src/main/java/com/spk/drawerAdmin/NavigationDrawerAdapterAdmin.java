package com.spk.drawerAdmin;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spk.main.R;

import java.util.List;


public class NavigationDrawerAdapterAdmin extends RecyclerView.Adapter<NavigationDrawerAdapterAdmin.ViewHolder> {

    private List<NavigationItemAdmin> mData;
    private NavigationDrawerCallbacksAdmin mNavigationDrawerCallbacksAdmin;
    private View mSelectedView;
    private int mSelectedPosition;

    public NavigationDrawerAdapterAdmin(List<NavigationItemAdmin> data) {
        mData = data;
    }

    public NavigationDrawerCallbacksAdmin getNavigationDrawerCallbacks() {
        return mNavigationDrawerCallbacksAdmin;
    }

    public void setNavigationDrawerCallbacks(NavigationDrawerCallbacksAdmin navigationDrawerCallbacksAdmin) {
        mNavigationDrawerCallbacksAdmin = navigationDrawerCallbacksAdmin;
    }

    @Override
    public NavigationDrawerAdapterAdmin.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.drawer_row, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.itemView.setClickable(true);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View v) {
                                                       if (mSelectedView != null) {
                                                           mSelectedView.setSelected(false);
                                                       }
                                                       mSelectedPosition = viewHolder.getAdapterPosition();
                                                       v.setSelected(true);
                                                       mSelectedView = v;
                                                       if (mNavigationDrawerCallbacksAdmin != null)
                                                           mNavigationDrawerCallbacksAdmin.onNavigationDrawerItemSelected(viewHolder.getAdapterPosition());
                                                   }
                                               }
        );
        viewHolder.itemView.setBackgroundResource(R.drawable.row_selector);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NavigationDrawerAdapterAdmin.ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(mData.get(i).getText());
        viewHolder.textView.setCompoundDrawablesWithIntrinsicBounds(mData.get(i).getDrawable(), null, null, null);
        if (mSelectedPosition == i) {
            if (mSelectedView != null) {
                mSelectedView.setSelected(false);
            }
            mSelectedPosition = i;
            mSelectedView = viewHolder.itemView;
            mSelectedView.setSelected(true);
        }
    }


    public void selectPosition(int position) {
        mSelectedPosition = position;
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_name);
        }
    }
}