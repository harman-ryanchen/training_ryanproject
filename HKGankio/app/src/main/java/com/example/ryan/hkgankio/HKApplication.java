package com.example.ryan.hkgankio;

import android.app.Application;
import android.content.Context;

/**
 * Created by ryan on 4/23/16.
 */
public class HKApplication extends Application{

    public static Context AppContext = null;
    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = getApplicationContext();
    }
}
