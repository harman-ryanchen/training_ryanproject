package com.example.ryan.weixindemo.fragment.tabfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryan.weixindemo.R;

import java.util.List;

/**
 * Created by ryan on 12/30/15.
 */
public class GalleryFragment extends BaseFragment {
    private ViewPager viewPager;
    private List<String> picPaths;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gallery_fragment, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.gallery_container);
        picPaths = (List<String>) getArguments().getSerializable(ChildPictureFragment.ArgumentKeys.CHILD_PIC_FRAGMENT.name());
        viewPager.setAdapter(new PictureGalleryAdapter(getActivity().getSupportFragmentManager()));
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    private class PictureGalleryAdapter extends FragmentStatePagerAdapter{

        public PictureGalleryAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ImageDetailFragment.newInstance(picPaths.get(position));
        }

        @Override
        public int getCount() {
            return picPaths.size();
        }
    }
}
