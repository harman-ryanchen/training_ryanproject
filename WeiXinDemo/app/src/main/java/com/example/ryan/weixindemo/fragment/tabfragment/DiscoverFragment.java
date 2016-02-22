package com.example.ryan.weixindemo.fragment.tabfragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import com.example.ryan.weixindemo.fragment.DialogFragmentContainer;
import com.example.ryan.weixindemo.infoobject.DialogFragmentInfo;
import com.example.ryan.weixindemo.util.LogUtil;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


/**
 * Created by ryan on 12/30/15.
 */
public class DiscoverFragment extends BaseFragment implements View.OnClickListener {
    private TextView start_bt, stop_bt, schedule_tv, serial_start_bt, serial_stop_bt, serial_content;
    private TextView dialog_normal_bt, dialog_progress_bt, dialog_sinnper_bt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        start_bt = (TextView) view.findViewById(R.id.start);
        stop_bt = (TextView) view.findViewById(R.id.stop);
        serial_start_bt = (TextView) view.findViewById(R.id.start_serial);
        serial_stop_bt = (TextView) view.findViewById(R.id.stop_serial);
        serial_content = (TextView) view.findViewById(R.id.serial_content);
        schedule_tv = (TextView) view.findViewById(R.id.schedule_content);

        dialog_normal_bt = (TextView) view.findViewById(R.id.dialog_normal_bt);
        dialog_progress_bt = (TextView) view.findViewById(R.id.dialog_progress_bt);
        dialog_sinnper_bt = (TextView) view.findViewById(R.id.dialog_sinnper_bt);
        start_bt.setOnClickListener(this);
        stop_bt.setOnClickListener(this);
        serial_start_bt.setOnClickListener(this);
        serial_stop_bt.setOnClickListener(this);

        dialog_normal_bt.setOnClickListener(this);
        dialog_progress_bt.setOnClickListener(this);
        dialog_sinnper_bt.setOnClickListener(this);
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
            case R.id.dialog_normal_bt:
                buildProgressDialogFragment(DialogFragmentInfo.DialogType.DIALOG_NORMAL);
                break;
            case R.id.dialog_progress_bt:
                buildProgressDialogFragment(DialogFragmentInfo.DialogType.DAILOG_PROGRESS);
                break;
            case R.id.dialog_sinnper_bt:
                break;
        }
    }

    private void buildProgressDialogFragment(DialogFragmentInfo.DialogType type) {
        if (type == DialogFragmentInfo.DialogType.DIALOG_NORMAL) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.view_dialog_normal, null);
            showCostmerDialogFragmeny(new DialogFragmentInfo.Builder().setConstomContianer(v).setTitleText(getString(R.string.dialog_normal_message)).build());
        } else if (type == DialogFragmentInfo.DialogType.DAILOG_PROGRESS) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.view_dialog_progress, null);
            showCostmerDialogFragmeny(new DialogFragmentInfo.Builder().setConstomContianer(v).setTitleText(getString(R.string.dialog_progress_message)).build());
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
                    if (!running) return;
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

    private void showCostmerDialogFragmeny(DialogFragmentInfo info) {
        FragmentManager fm = getActivity().getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = (DialogFragmentContainer) fm.findFragmentByTag(getClass().getName() + AppConfig.DIALOG_TAG);
        if (fragment != null) {
            ft.remove(fragment);
        }
        ft.addToBackStack(null);
        DialogFragmentContainer dialog_fragmet = DialogFragmentContainer.newInstance(info);
        dialog_fragmet.show(ft, getClass().getName() + AppConfig.DIALOG_TAG);


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
