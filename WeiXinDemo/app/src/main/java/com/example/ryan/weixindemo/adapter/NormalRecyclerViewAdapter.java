package com.example.ryan.weixindemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryan.weixindemo.R;
import com.example.ryan.weixindemo.bean.ImageFloderBean;
import com.example.ryan.weixindemo.util.LocalImageLoader;

import java.util.List;


/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2015/1/15  18:18.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/1/15        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalTextViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<String> mBeanList;
    private Point mPoint = new Point(0, 0);//用来封装ImageView的宽和高的对象

    public NormalRecyclerViewAdapter(Context context,List<String> beanlist) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.mBeanList = beanlist;
        mPoint.set(100,100);
    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.child_pic_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final NormalTextViewHolder holder, int position) {
//        holder.mTextView.setText(mTitles[position]);
        LocalImageLoader.getInstance().loadNativeImage(mBeanList.get(position), mPoint, new LocalImageLoader.NativeImageCallBack() {
            @Override
            public void onImageLoader(Bitmap bitmap, String path) {
                holder.picture.setImageBitmap(bitmap);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeanList.size();
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder {
        ImageView picture;

        NormalTextViewHolder(View view) {
            super(view);
            picture = (ImageView) view.findViewById(R.id.child_image);
        }

    }
}
