package com.example.ryan.weixindemo.fragment.tabfragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.ryan.weixindemo.common.FragmentsType;
import com.example.ryan.weixindemo.header.ToolBarInfo;

/**
 * Created by ryan on 12/30/15.
 */
public class BaseFragment extends Fragment {
    private NavigationCallback callback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (NavigationCallback) activity;
    }


    public NavigationCallback getCallback() {
        return callback;
    }

    public interface NavigationCallback {
        void nextPage(FragmentsType fragmentsType, Bundle bundle);

        void setToolBar(ToolBarInfo toolbarInfo);
    }
}
