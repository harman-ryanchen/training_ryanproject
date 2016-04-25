package com.example.ryan.hkgankio.view.daily;

import android.support.v7.widget.RecyclerView;

import com.example.ryan.hkgankio.R;
import com.example.ryan.hkgankio.bean.DailyNewsBean;
import com.example.ryan.hkgankio.bean.StoriesBean;
import com.example.ryan.hkgankio.bean.TopStoriesBean;
import com.example.ryan.hkgankio.presenter.BaseDailyPresenter;
import com.example.ryan.hkgankio.presenter.DailyPresenter;
import com.example.ryan.hkgankio.support.DailyNewsListAdapter;

import java.util.List;

/**
 * Created by ryan on 4/23/16.
 */
public class DailyNewsFragment extends DailyBaseListFragment implements IBaseDailyFragment{
    private String mCategory;
    private String mUrl;
    private List<StoriesBean> storiesBeens;
    private List<TopStoriesBean> topStoriesBeen;

    @Override
    BaseDailyPresenter createPresenter() {
        return  new DailyPresenter(this);
    }

    @Override
    void getArg() {
        mCategory =  getArguments().getString(getString(R.string.argument_item_id));
    }

    @Override
    void loadData() {
        dailyPresenter.loadDailyData(mCategory);
    }

    @Override
    RecyclerView.Adapter bindAdapter() {
        return new DailyNewsListAdapter(storiesBeens,getContext());
    }

    @Override
    public void onLoadDataResult(DailyNewsBean newsBean) {
        hideProgressBar();
        if (newsBean==null)return;
        this.storiesBeens = newsBean.getStories();
        this.topStoriesBeen = newsBean.getTop_stories();
        adapter = bindAdapter();
        recyclerView.setAdapter(adapter);
    }
}
