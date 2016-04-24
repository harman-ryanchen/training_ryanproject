package com.example.ryan.hkgankio.util;

import com.example.ryan.hkgankio.api.BaseApi;
import com.example.ryan.hkgankio.api.DailyApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ryan on 4/23/16.
 */
public class HttpMrg {

    private Retrofit retrofit;

    private static HttpMrg httpMrg;

    public static HttpMrg getInstance(){
        if (httpMrg==null){
            httpMrg = new HttpMrg();
        }
        return httpMrg;
    }
    public <T> T initRetrofit(String baseurl,Class<T> clazz){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);
    }
    public DailyApiService getDailyApi() {
        return initRetrofit(BaseApi.daily_news_api,DailyApiService.class);
    }


}
