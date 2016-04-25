package com.example.ryan.hkgankio.support;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ryan.hkgankio.R;
import com.example.ryan.hkgankio.bean.StoriesBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by studio02 on 4/25/16.
 */
public class DailyNewsListAdapter extends BaseDailyListAdapter<StoriesBean, DailyNewsListAdapter.ViewHolder> {


    public DailyNewsListAdapter(List<StoriesBean> mItems, Context mContext) {
        super(mItems, mContext);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item_layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_daily_news_adapter, parent, false);
        ViewHolder vh = new ViewHolder(item_layout);
        vh.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StoriesBean sb = mItems.get(position);
        holder.news_content.setText(sb.getTitle());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View parentView;
        private TextView news_content;
        private SimpleDraweeView news_image;

        ViewHolder(View itemView) {
            super(itemView);
            parentView = itemView;
            news_content = (TextView) itemView.findViewById(R.id.news_content);
//            news_image = (SimpleDraweeView) itemView.findViewById(R.id.news_image);

        }
    }
}
