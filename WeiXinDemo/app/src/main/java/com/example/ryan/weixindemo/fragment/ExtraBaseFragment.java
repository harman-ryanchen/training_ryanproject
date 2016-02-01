package com.example.ryan.weixindemo.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryan.weixindemo.fragment.tabfragment.BaseFragment;
import com.example.ryan.weixindemo.util.LogUtil;

/**
 * Created by studio02 on 1/26/16.
 */
public abstract class ExtraBaseFragment extends BaseFragment{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.d("test_tool_bar");
        initToolBar();
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtil.d("test_tool_bar");
        LogUtil.l(hidden);
        if (!hidden){
            initToolBar();
            onShow();
        }else{
            onHide();
        }
    }

    public abstract void initToolBar();
    public abstract void onShow();
    public abstract void onHide();
}
