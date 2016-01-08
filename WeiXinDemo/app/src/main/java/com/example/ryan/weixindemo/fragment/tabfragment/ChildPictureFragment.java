package com.example.ryan.weixindemo.fragment.tabfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.ryan.weixindemo.R;
import com.example.ryan.weixindemo.adapter.NormalRecyclerViewAdapter;
import com.example.ryan.weixindemo.bean.ImageFloderBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryan on 12/30/15.
 */
public class ChildPictureFragment extends BaseFragmen{
    private RecyclerView mRecyclerView;
    private List<String> mBeanList;


    public enum ArgumentKeys {
        CHILD_PIC_FRAGMENT,
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.child_picture_fragment, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.child_pic_container);
        mBeanList = (List<String>) getArguments().getSerializable(ArgumentKeys.CHILD_PIC_FRAGMENT.name());
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));//这里用线性宫格显示 类似于grid view

        mRecyclerView.setAdapter(new NormalRecyclerViewAdapter(getActivity(),mBeanList));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static ChildPictureFragment newInstance(List<String> beanList) {
        ChildPictureFragment pictureFragment = new ChildPictureFragment();
        Bundle args = new Bundle();
        args.putSerializable(ArgumentKeys.CHILD_PIC_FRAGMENT.name(), new ArrayList<>(beanList));
        return pictureFragment;
    }

}
