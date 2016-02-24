package com.example.ryan.weixindemo.fragment.tabfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.example.ryan.weixindemo.common.PostableHandler;
import com.example.ryan.weixindemo.common.ThreadPoolManager;
import com.example.ryan.weixindemo.fragment.ExtraBaseFragment;
import com.example.ryan.weixindemo.util.LogUtil;

import java.util.concurrent.TimeUnit;

public class FirstFragment extends ExtraBaseFragment {
    public static final int TYPE_LINEAR_LAYOUT = 1;
    public static final int TYPE_GRID_LAYOUT = 2;
    public static final int TYPE_STAGGERED_GRID_LAYOUT = 3;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeContainer;

    private int type = TYPE_LINEAR_LAYOUT;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first_section, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.view_recyclerview);
        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                LogUtil.l("start refresh");
                ThreadPoolManager.getScheduledPool().schedule(new Runnable() {
                    @Override
                    public void run() {
                        LogUtil.l("end refresh");
                        PostableHandler.UI_THREAD.postImmediately(new Runnable() {
                            @Override
                            public void run() {
                                swipeContainer.setRefreshing(false);

                            }
                        });
                    }
                }, 5, TimeUnit.SECONDS);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

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
