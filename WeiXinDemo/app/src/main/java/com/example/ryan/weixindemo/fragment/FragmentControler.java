package com.example.ryan.weixindemo.fragment;

import android.app.Fragment;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;

import com.example.ryan.weixindemo.fragment.tabfragment.BaseFragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryan on 12/30/15.
 */
public class FragmentControler {

    /**
     * Backstack tag for the main fragments (ones that have the hamburger for the navigation icon on the toolbar)
     */
    public static final String MAIN_TAG = "MAIN";
    /**
     * Backstack tag prefix for child fragments (ones that have an up arrow on for the navigation icon on the toolbar)
     */
    private static final String CHILD_TAG_PREFIX = "CHILD_";
    /**
     * Backstack tag pre for tabs (ones that are considered to be one page as far as the backstack is concerned)
     */
    private static final String TABS_TAG_PREFIX = "TABS_";

    private FragmentManager manager;
    private int containnerID;


    private static class FragmentEntry {
        private final String mBackstackTag;
        private final int mBackstackId;
        private final WeakReference<Fragment> mFragment;
        private final int mMusicServiceId;
        private final WeakReference<FragmentManager> mManager;
        private final
        @IdRes
        int mContainerId;
        private final boolean mCallBackpressOnClear;

        private List<FragmentEntry> fragmentEntries = new ArrayList<>();

        /**
         * store date for each fragment which was created.
         *
         * @param manager
         * @param backstackTag
         * @param backstackId
         * @param fragment
         * @param musicServiceId
         * @param containerId
         * @param callBackpressOnClear
         */
        private FragmentEntry(FragmentManager manager,
                              String backstackTag,
                              int backstackId,
                              Fragment fragment,
                              int musicServiceId,
                              @IdRes int containerId,
                              boolean callBackpressOnClear) {
            mBackstackTag = backstackTag;
            mBackstackId = backstackId;
            mFragment = new WeakReference<>(fragment);
            mMusicServiceId = musicServiceId;
            mContainerId = containerId;
            mCallBackpressOnClear = callBackpressOnClear;
            mManager = new WeakReference<>(manager);
        }

        public Fragment getFragment() {
            return mFragment.get();
        }

        public String getBackstackTag() {
            return mBackstackTag;
        }

        public int getBackstackId() {
            return mBackstackId;
        }

        public int getMusicServiceId() {
            return mMusicServiceId;
        }

        public boolean isCallBackpressOnClear() {
            return mCallBackpressOnClear;
        }

        public int getContainerId() {
            return mContainerId;
        }

        public FragmentManager getManager() {
            return mManager.get();
        }
    }

    public FragmentControler(FragmentManager manager, int containnerID) {
        this.manager = manager;
        this.containnerID = containnerID;
    }

    public void showMainTabFragment() {

    }


    public void showMainFragment(BaseFragment currentFragment) {
        manager.beginTransaction().add(containnerID, currentFragment, currentFragment.getClass().getName()).commit();
    }

    public void showTabFragment() {

    }

    public void showChildFragment(BaseFragment currentFragment) {
        manager.beginTransaction().add(containnerID, currentFragment, currentFragment.getClass().getName()).commit();
    }

    private void addFragment() {

    }
}
