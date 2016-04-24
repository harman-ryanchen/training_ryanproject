package com.example.ryan.hkgankio.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryan.hkgankio.R;
import com.example.ryan.hkgankio.common.HKCommon;
import com.example.ryan.hkgankio.support.PagerAdapter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

/**
 * Created by ryan on 4/23/16.
 */
public class DailyNavigationFragment extends Fragment{

    protected View parentView;
    protected ViewPager viewPager;
    protected PagerAdapter pagerAdapter;
    private SmartTabLayout smartTabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.layout_top_navigation,null);
        viewPager = (ViewPager) parentView.findViewById(R.id.inner_viewpager);
        smartTabLayout = (SmartTabLayout) parentView.findViewById(R.id.viewpagertab);
        smartTabLayout.setVisibility(View.VISIBLE);
        pagerAdapter = initPagerAdapter();
        viewPager.setAdapter(pagerAdapter);
        smartTabLayout.setViewPager(viewPager);
        return parentView;
    }
    protected PagerAdapter initPagerAdapter() {
        pagerAdapter = new PagerAdapter(getChildFragmentManager(), HKCommon.dailyItems) {
            @Override
            public Fragment getItem(int position) {
                DailyFragment fragment = new DailyFragment();
                Bundle bundle = new Bundle();
                bundle.putString(getString(R.string.argument_item_id),HKCommon.dailyItems[position]);
                bundle.putString(getString(R.string.argument_url_id),HKCommon.dailyurls[position]);
                fragment.setArguments(bundle);
                return fragment;
            }
        };
        return pagerAdapter;
    }
}
