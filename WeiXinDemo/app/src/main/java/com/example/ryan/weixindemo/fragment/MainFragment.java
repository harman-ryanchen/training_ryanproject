/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.ryan.weixindemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.ryan.weixindemo.R;
import com.example.ryan.weixindemo.common.AppConfig;
import com.example.ryan.weixindemo.fragment.tabfragment.BaseFragment;
import com.example.ryan.weixindemo.fragment.tabfragment.ChatsListFragment;
import com.example.ryan.weixindemo.fragment.tabfragment.ContactsFragment;
import com.example.ryan.weixindemo.fragment.tabfragment.DiscoverFragment;
import com.example.ryan.weixindemo.fragment.tabfragment.MeFragment;
import com.example.ryan.weixindemo.header.ToolBarInfo;
import com.example.ryan.weixindemo.util.LogUtil;
import com.example.ryan.weixindemo.view.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A basic sample which shows how to use
 * to display a custom {@link ViewPager} title strip which gives continuous feedback to the user
 * when scrolling.
 */
public class MainFragment extends ExtraBaseFragment {


    /**
     * A custom {@link ViewPager} title strip which looks much like Tabs present in Android v4.0 and
     * above, but is designed to give continuous feedback to the user when scrolling.
     */
    private SlidingTabLayout mSlidingTabLayout;

    /**
     * A {@link ViewPager} which will be used in conjunction with the {@link SlidingTabLayout} above.
     */
    private ViewPager mViewPager;

    private List<BaseFragment> mTabs = new ArrayList<BaseFragment>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // BEGIN_INCLUDE (populate_tabs)
        /**
         * Populate our tab list with tabs. Each item contains a title, indicator color and divider
         * color, which are used by {@link SlidingTabLayout}.
         */
        initMainTabFragment();
        LogUtil.l();
    }

    private void initMainTabFragment() {
        LogUtil.l();
        ChatsListFragment chatsListFragment = new ChatsListFragment();

        ContactsFragment contactsFragment = new ContactsFragment();

        DiscoverFragment discoverFragment = new DiscoverFragment();

        MeFragment meFragment = new MeFragment();

        mTabs.add(chatsListFragment);
        mTabs.add(contactsFragment);
        mTabs.add(discoverFragment);
        mTabs.add(meFragment);
    }

    /**
     * Inflates the {@link View} which will be displayed by this {@link Fragment}, from the app's
     * resources.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtil.l();
        super.onCreateView(inflater,container,savedInstanceState);
        return inflater.inflate(R.layout.slide_fragment, container, false);
    }

    @Override
    public void initToolBar() {
        LogUtil.l();
        LogUtil.d("test_tool_bar");
        getCallback().setToolBar(new ToolBarInfo.Builder().setToolBarContentText(getString(R.string.app_name)).setOptionMenu(R.menu.menu_main).setToolBarContentLogo(0).build());
    }

    @Override
    public void onShow() {
        LogUtil.l();
    }

    @Override
    public void onHide() {
        LogUtil.l();
    }
    // BEGIN_INCLUDE (fragment_onviewcreated)

    /**
     * This is called after the {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} has finished.
     * Here we can pick out the {@link View}s we need to configure from the content view.
     * <p>
     * We set the {@link ViewPager}'s adapter to be an instance of
     * {@link SampleFragmentPagerAdapter}. The {@link SlidingTabLayout} is then given the
     * {@link ViewPager} so that it can populate itself.
     *
     * @param view View created in {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // BEGIN_INCLUDE (setup_viewpager)
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SampleFragmentPagerAdapter(getChildFragmentManager()));
        // END_INCLUDE (setup_viewpager)

        // BEGIN_INCLUDE (setup_slidingtablayout)
        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setCustomTabView(R.layout.custom_tabview, 0);
        mSlidingTabLayout.setViewPager(mViewPager, AppConfig.TAB_ICONS);


        // BEGIN_INCLUDE (tab_colorizer)
        // Set a TabColorizer to customize the indicator and divider colors. Here we just retrieve
        // the tab at the position, and return it's set color

//        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
//
//            @Override
//            public int getIndicatorColor(int position) {
//                return R.color.colorAccent;
//            }
//
//            @Override
//            public int getDividerColor(int position) {
//                return mTabs.get(position).getDividerColor();
//            }
//
//        });

        // END_INCLUDE (tab_colorizer)
        // END_INCLUDE (setup_slidingtablayout)
    }

    @Override
    public void onResume() {
        LogUtil.l();
        super.onResume();
    }

    @Override
    public void onPause() {
        LogUtil.l();
        super.onPause();
    }

    @Override
    public void onStart() {
        LogUtil.l();
        super.onStart();
    }

    @Override
    public void onDetach() {
        LogUtil.l();
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        LogUtil.l();
        super.onDestroy();
    }

    class SampleFragmentPagerAdapter extends FragmentPagerAdapter {

        SampleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return mTabs.get(i);
        }

        @Override
        public int getCount() {
            return mTabs.size();
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            return super.instantiateItem(container, position);
        }
    }


}