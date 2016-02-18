package com.example.ryan.weixindemo.fragment.tabfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryan.weixindemo.R;
import com.example.ryan.weixindemo.adapter.MultipleItemAdapter;
import com.example.ryan.weixindemo.fragment.ExtraBaseFragment;


/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2015/2/6  14:42.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/2/6        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class FirstFragment extends ExtraBaseFragment {
    public static final int TYPE_LINEAR_LAYOUT = 1;
    public static final int TYPE_GRID_LAYOUT = 2;
    public static final int TYPE_STAGGERED_GRID_LAYOUT = 3;
    RecyclerView mRecyclerView;

    private int type = TYPE_LINEAR_LAYOUT;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first_section, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.view_recyclerview);
        return rootView;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void onShow() {

    }

    @Override
    public void onHide() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (type == TYPE_GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));//这里用线性宫格显示 类似于grid view
        } else if (type == TYPE_STAGGERED_GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//这里用线性显示 类似于list view
        }
        mRecyclerView.setAdapter(new MultipleItemAdapter(getActivity()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
