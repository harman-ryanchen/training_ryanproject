package com.example.ryan.hkgankio.view.daily;

import android.support.v7.widget.RecyclerView;

import com.example.ryan.hkgankio.bean.DailyNewsBean;
import com.example.ryan.hkgankio.presenter.BaseDailyPresenter;

/**
 * Created by studio02 on 4/25/16.
 */
public class DailyHotsFragment extends DailyBaseListFragment implements IBaseDailyFragment{


    @Override
    BaseDailyPresenter createPresenter() {
        return null;
    }

    @Override
    void getArg() {

    }

    @Override
    void loadData() {

    }

    @Override
    RecyclerView.Adapter bindAdapter() {
        return null;
    }

    @Override
    public void onLoadDataResult(DailyNewsBean newsBean) {

    }
}
