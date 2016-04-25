package com.example.ryan.hkgankio.api;

import com.example.ryan.hkgankio.bean.DailyNewsBean;
import com.example.ryan.hkgankio.bean.StartImageBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ryan on 4/23/16.
 */
public interface DailyApiService {

    //http://news-at.zhihu.com/api/4/start-image/1080*1776
    @GET("/api/4/start-image/{size}")
    Call<StartImageBean> getStartImage(@Path("size") String size);

    //http://news-at.zhihu.com/api/4/news/latest
    @GET("/api/4/news/latest")
    Call<DailyNewsBean> getLatestNews();

    //http://news-at.zhihu.com/api/3/news/hot
    @GET("/api/3/news/hot")
    Call<DailyNewsBean> getHotNews();


    //http://news.at.zhihu.com/api/4/news/before/20131119
    @GET("/api/4/news/before/{date}")
    Call<DailyNewsBean> getBeforeNews(@Path("beforedate") String beforedate );



}
