package com.example.ryan.weixindemo.fragment.tabfragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ryan.weixindemo.R;
import com.example.ryan.weixindemo.common.AppConfig;
import com.example.ryan.weixindemo.service.MyIntentService;
import com.example.ryan.weixindemo.util.LogUtil;


/**
 * Created by ryan on 12/30/15.
 */
public class DiscoverFragment extends BaseFragment implements View.OnClickListener {
    private TextView mStart, mCheck;
    private ProgressBar updateBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover_fragment, container, false);
        mStart = (TextView) view.findViewById(R.id.start);
        mCheck = (TextView) view.findViewById(R.id.check_is);
        updateBar = (ProgressBar) view.findViewById(R.id.update_bar);
        mStart.setOnClickListener(this);
        mCheck.setOnClickListener(this);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void startIntentService() {
        LogUtil.l(MyIntentService.class.getName());
        Intent mIntentService = new Intent(MyIntentService.class.getName());
        getActivity().startService(mIntentService);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                startIntentService();
                break;
            case R.id.check_is:
                break;
            default:
                break;
        }
    }

    private class CurrentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (AppConfig.COUNT_ACTION.equals(action)) {
                int index = intent.getIntExtra("count", 0);
                updateProgressBar(index);
            }
        }


    }

    private void updateProgressBar(int index) {
        int max = updateBar.getMax();

        if (index < max){
            updateBar.setVisibility(View.VISIBLE);
            updateBar.setProgress(index);
        }else{
            updateBar.setVisibility(View.INVISIBLE);
        }

    }
}
