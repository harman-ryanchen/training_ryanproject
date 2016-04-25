package com.example.ryan.hkgankio.presenter;

import com.example.ryan.hkgankio.bean.DailyNewsBean;
import com.example.ryan.hkgankio.listeners.BaseMultiLoadedListener;
import com.example.ryan.hkgankio.model.BaseDailyModel;
import com.example.ryan.hkgankio.model.DailyModel;
import com.example.ryan.hkgankio.view.daily.IBaseDailyFragment;

/**
 * Created by ryan on 4/24/16.
 */
public class DailyPresenter implements BaseDailyPresenter{
    private IBaseDailyFragment iBaseDailyFragment;
    private BaseDailyModel baseDailyModel;

    public DailyPresenter(IBaseDailyFragment iBaseDailyFragment) {
        this.iBaseDailyFragment = iBaseDailyFragment;
        this.baseDailyModel = new DailyModel();
    }

    @Override
    public void loadDailyData(String item) {
        baseDailyModel.loadDailyNews(new BaseMultiLoadedListener<DailyNewsBean>() {
            @Override
            public void onSuccess(int event_tag, DailyNewsBean data) {
                iBaseDailyFragment.onLoadDataResult(data);
            }

            @Override
            public void onError(String msg) {

            }

            @Override
            public void onException(String msg) {

            }
        });
    }

}
