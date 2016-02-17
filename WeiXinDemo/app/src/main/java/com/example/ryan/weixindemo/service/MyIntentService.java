package com.example.ryan.weixindemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

import com.example.ryan.weixindemo.common.AppConfig;
import com.example.ryan.weixindemo.util.LogUtil;

/**
 * Created by ryan on 2/7/16.
 */
public class MyIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        LogUtil.l();
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.l();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        LogUtil.l();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.l();
        return super.onBind(intent);
    }

    @Override
    public void setIntentRedelivery(boolean enabled) {
        LogUtil.l();
        super.setIntentRedelivery(enabled);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        LogUtil.l("-onHandleIntent---开始---");
        // 非首次运行IntentServiceSub服务时，执行下面操作
        // 目的是将index设为0
        if ( mCountThread != null) {
            index = 0;
            return;
        }

        // 首次运行IntentServiceSub时，创建并启动线程
        mCountThread = new CountThread();
        mCountThread.start();

    }
    int index;

    // 线程：用来实现每隔200ms发送广播
    private static CountThread mCountThread = null;

    private class CountThread extends Thread {
        @Override
        public void run() {
            index = 0;
            try {
                while (true) {
                    // 将数字+2,
                    index += 2;

                    // 将index通过广播发送出去
                    Intent intent = new Intent(AppConfig.COUNT_ACTION);
                    intent.putExtra("count", index);
                    sendBroadcast(intent);
//					Log.d(TAG, "CountThread index:"+index);

                    // 若数字>=100 则退出
                    if (index >= 100) {
                        if ( mCountThread != null)
                            mCountThread = null;

                        return ;
                    }

                    // 200ms
                    this.sleep(200);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
