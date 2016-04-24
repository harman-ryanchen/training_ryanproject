package com.example.ryan.hkgankio.api;

import com.example.ryan.hkgankio.bean.DailyNewsBean;
import com.example.ryan.hkgankio.bean.StartImageBean;

import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ryan on 4/23/16.
 */
public interface DailyApiService {

    //http://news-at.zhihu.com/api/4/start-image/1080*1776
    @GET("/start-image/{size}")
    void getStartImage(@Path("size") String size , Callback<StartImageBean> cb);

    //http://news-at.zhihu.com/api/4/news/latest
    @GET("/news/latest")
    void getLatestNews(Callback<DailyNewsBean> cb);


    //http://news.at.zhihu.com/api/4/news/before/20131119
    @GET("/before/{date}")
    void getBeforeNews(@Path("beforedate") String beforedate ,Callback<DailyNewsBean> cb);



}
