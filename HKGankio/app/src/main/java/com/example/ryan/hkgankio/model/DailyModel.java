package com.example.ryan.hkgankio.model;

import com.example.ryan.hkgankio.api.DailyApiService;
import com.example.ryan.hkgankio.bean.DailyNewsBean;
import com.example.ryan.hkgankio.common.HKCommon;
import com.example.ryan.hkgankio.listeners.BaseMultiLoadedListener;
import com.example.ryan.hkgankio.util.HttpMrg;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ryan on 4/24/16.
 */
public class DailyModel implements BaseDailyModel{



    @Override
    public void loadDailyNews(final BaseMultiLoadedListener<DailyNewsBean> newsBeanBaseMultiLoadedListener) {
        DailyApiService dailyApiService = HttpMrg.getInstance().initRetrofit(HKCommon.daily_base_api,DailyApiService.class);
        dailyApiService.getLatestNews().enqueue(new Callback<DailyNewsBean>() {
            @Override
            public void onResponse(Call<DailyNewsBean> call, Response<DailyNewsBean> response) {
                newsBeanBaseMultiLoadedListener.onSuccess(response.code(),response.body());
            }

            @Override
            public void onFailure(Call<DailyNewsBean> call, Throwable t) {
                newsBeanBaseMultiLoadedListener.onError(t.toString());
            }
        });
    }

    @Override
    public void loadDailyHotsNews() {

    }

    @Override
    public void loadDailyStartImage() {

    }

    @Override
    public void loadBeforeNews() {

    }
}
