package com.example.ryan.weixindemo.fragment.tabfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.ryan.weixindemo.common.FragmentsType;

/**
 * Created by ryan on 12/30/15.
 */
public class BaseFragment extends Fragment {
    private NavigationCallback callback;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.callback = (NavigationCallback) getActivity();
    }

    public NavigationCallback getCallback() {
        return callback;
    }

    public interface NavigationCallback {
        void nextPage(FragmentsType fragmentsType,Bundle bundle);
    }
}
