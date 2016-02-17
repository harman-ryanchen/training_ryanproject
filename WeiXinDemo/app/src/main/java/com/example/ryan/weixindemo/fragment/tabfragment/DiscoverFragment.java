package com.example.ryan.weixindemo.fragment.tabfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ryan.weixindemo.R;
import com.example.ryan.weixindemo.common.AppConfig;
import com.example.ryan.weixindemo.common.PostableHandler;
import com.example.ryan.weixindemo.common.ThreadPoolManager;
import com.example.ryan.weixindemo.util.LogUtil;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


/**
 * Created by ryan on 12/30/15.
 */
public class DiscoverFragment extends BaseFragment implements View.OnClickListener {
    private TextView start_bt, stop_bt, schedule_tv, serial_start_bt, serial_stop_bt, serial_content;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover_fragment, container, false);
        start_bt = (TextView) view.findViewById(R.id.start);
        stop_bt = (TextView) view.findViewById(R.id.stop);
        serial_start_bt = (TextView) view.findViewById(R.id.start_serial);
        serial_stop_bt = (TextView) view.findViewById(R.id.stop_serial);
        serial_content = (TextView) view.findViewById(R.id.serial_content);
        schedule_tv = (TextView) view.findViewById(R.id.schedule_content);
        start_bt.setOnClickListener(this);
        stop_bt.setOnClickListener(this);
        serial_start_bt.setOnClickListener(this);
        serial_stop_bt.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                testScheduleThread();
                break;
            case R.id.stop:
                beeperHandle.cancel(true);
                break;
            case R.id.start_serial:
                testHanlderThread();
                break;
            case R.id.stop_serial:
                running = false;
                ThreadPoolManager.clearSerialThread(AppConfig.SERIAL_THREAD);
                break;
        }
    }

    private int serial_count = 0;
    private boolean running = false;
    /**
     * HandlerThread适合在只需要在一个工作线程(非UI线程)+任务的等待队列的形式,优点是不会有堵塞，减少了对性能的消耗，缺点是不能同时进行多任务的处理,需要等待进行处理。处理效率较低。
     * 它继承Thread 但是与Thread 的区别在于多了一个Looper来保持任务队列。
     */
    private void testHanlderThread() {
        running = true;
        ThreadPoolManager.getSerialThread(AppConfig.SERIAL_THREAD).postImmediately(new Runnable() {
            @Override
            public void run() {
                try {
                    if (!running)return;
                    Thread.sleep(2000);
                    serial_count++;
                    PostableHandler.UI_THREAD.postImmediately(new Runnable() {
                        @Override
                        public void run() {
                            LogUtil.l("the serial thread count is " + serial_count);
                            serial_content.setText("the count is " + serial_count);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    int count = 0;
    ScheduledFuture<?> beeperHandle;

    /**
     * schedule(task,initDelay):安排所提交的Callable或Runnable任务在initDelay指定的时间后执行。
     * scheduleAtFixedRate()：安排所提交的Runnable任务按指定的间隔重复执行
     * scheduleWithFixedDelay()：安排所提交的Runnable任务在每次执行完后，等待delay所指定的时间后重复执行。
     */
    private void testScheduleThread() {
        beeperHandle = ThreadPoolManager.getScheduledPool().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                count++;
                LogUtil.l("the thread name is ", Thread.currentThread().getName() + ",count=" + count);
                PostableHandler.UI_THREAD.postImmediately(new Runnable() {
                    @Override
                    public void run() {
                        LogUtil.l("the thread name is ", Thread.currentThread().getName() + ",count=" + count);
                        schedule_tv.setText("the count is " + count);
                    }
                });
            }
        }, 0, 2, TimeUnit.SECONDS);
    }
}
