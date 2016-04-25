package com.example.ryan.hkgankio.view.daily;

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
        parentView = View.inflate(getContext(), R.layout.layout_top_navigation,null);
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
                String item = HKCommon.dailyItems[position];
                DailyBaseListFragment fragment = crateDailyFragment(item);
                Bundle bundle = new Bundle();
                bundle.putString(getString(R.string.argument_item_id),item);
                fragment.setArguments(bundle);
                return fragment;
            }
        };
        return pagerAdapter;
    }
    private DailyBaseListFragment crateDailyFragment(String item){
        DailyBaseListFragment fragment = null;
        if (item.equals("Dayily")){
            fragment = new DailyNewsFragment();
        }else if (item.equals("Hot")){
            fragment = new DailyHotsFragment();
        }else{
            fragment = new DailyHotsFragment();
        }
        return fragment;
    }
}
