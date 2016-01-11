package com.example.ryan.weixindemo.fragment.tabfragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ryan.weixindemo.R;
import com.example.ryan.weixindemo.common.AppConfig;
import com.example.ryan.weixindemo.common.ArgumentKeys;
import com.example.ryan.weixindemo.common.FragmentsType;
import com.example.ryan.weixindemo.util.LocalImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryan on 12/30/15.
 */
public class ChildPictureFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private List<String> mBeanList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.child_picture_fragment, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.child_pic_container);
        mBeanList = (List<String>) getArguments().getSerializable(ArgumentKeys.IMAGE_URILS.name());
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));//这里用线性宫格显示 类似于grid view

        NormalRecyclerViewAdapter adapter = new NormalRecyclerViewAdapter(getActivity(), mBeanList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalTextViewHolder> {
        private final LayoutInflater mLayoutInflater;
        private final Context mContext;
        private List<String> mBeanList;
        private Point mPoint = new Point(0, 0);//用来封装ImageView的宽和高的对象

        public NormalRecyclerViewAdapter(Context context, List<String> beanlist) {
            mContext = context;
            mLayoutInflater = LayoutInflater.from(context);
            this.mBeanList = beanlist;
            mPoint.set(100, 100);
        }

        @Override
        public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.child_pic_item, parent, false));
        }

        @Override
        public void onBindViewHolder(final NormalTextViewHolder holder, int position) {
            Bitmap bitmap = LocalImageLoader.getInstance().loadNativeImage(mBeanList.get(position), mPoint, AppConfig.SMALL_IMAGE_TAG, new LocalImageLoader.NativeImageCallBack() {
                @Override
                public void onImageLoader(Bitmap bitmap, String path) {
                    holder.picture.setImageBitmap(bitmap);
                }
            });
            if (bitmap!=null){
                holder.picture.setImageBitmap(bitmap);
            }
        }

        @Override
        public int getItemCount() {
            return mBeanList.size();
        }


        public class NormalTextViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            ImageView picture;

            NormalTextViewHolder(View view) {
                super(view);
                picture = (ImageView) view.findViewById(R.id.child_image);
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                gotoNextPage(getPosition());
            }
        }
    }
    private void gotoNextPage(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ArgumentKeys.IMAGE_URILS.name(), new ArrayList<>(mBeanList));
        bundle.putInt(ArgumentKeys.CURRENT_IMAGE.name(),position);
        getCallback().nextPage(FragmentsType.GALLERY_FRAGMENT,bundle);
}
}
