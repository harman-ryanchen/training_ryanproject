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
import com.example.ryan.weixindemo.common.ArgumentKeys;
import com.example.ryan.weixindemo.fragment.ExtraBaseFragment;
import com.example.ryan.weixindemo.infoobject.ToolBarInfo;
import com.example.ryan.weixindemo.util.LogUtil;

import java.util.List;

/**
 * Created by ryan on 12/30/15.
 */
public class GalleryFragment extends ExtraBaseFragment {
    private ViewPager viewPager;
    private List<String> picPaths;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtil.l();
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.gallery_container);
        picPaths = (List<String>) getArguments().getSerializable(ArgumentKeys.IMAGE_URILS.name());
        int position = getArguments().getInt(ArgumentKeys.CURRENT_IMAGE.name());
        viewPager.setAdapter(new PictureGalleryAdapter(getActivity().getSupportFragmentManager()));
        viewPager.setCurrentItem(position);
        return view;
    }

    @Override
    public void initToolBar() {
        LogUtil.l();
        getCallback().setToolBar(new ToolBarInfo.Builder().setToolBarContentText(getString(R.string.title_child_gallery)).setOptionMenu(0).setToolBarContentLogo(R.drawable.back_button).build());
    }

    @Override
    public void onShow() {

    }

    @Override
    public void onHide() {

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
