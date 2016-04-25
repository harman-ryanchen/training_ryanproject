package com.example.ryan.hkgankio.support;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by studio02 on 4/25/16.
 */
public abstract class BaseDailyListAdapter <M,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>{
    protected List<M> mItems;
    protected Context mContext;

    public BaseDailyListAdapter(List<M> mItems, Context mContext) {
        this.mItems = mItems;
        this.mContext = mContext;
    }

    protected M getItem(int position){
        return mItems.get(position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
