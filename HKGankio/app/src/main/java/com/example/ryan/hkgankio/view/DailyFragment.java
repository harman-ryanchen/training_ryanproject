package com.example.ryan.hkgankio.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryan.hkgankio.R;
import com.example.ryan.hkgankio.bean.StoriesBean;
import com.example.ryan.hkgankio.presenter.BaseDailyPresenter;
import com.example.ryan.hkgankio.view.daily.IBaseDailyFragment;

import java.util.List;

/**
 * Created by ryan on 4/23/16.
 */
public class DailyFragment extends Fragment implements IBaseDailyFragment{
    private View mRootView;
    private String mCategory;
    private String mUrl;
    private BaseDailyPresenter dailyPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_daily,null);
        mUrl = getArguments().getString(getString(R.string.argument_url_id));
        mCategory = getArguments().getString(getString(R.string.argument_item_id));
        return mRootView;
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void HideProgress() {

    }

    @Override
    public void onLoadDataResult(List<StoriesBean> storiesBeens) {

    }
}
