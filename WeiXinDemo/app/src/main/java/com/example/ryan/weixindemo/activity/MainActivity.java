package com.example.ryan.weixindemo.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.ryan.weixindemo.R;
import com.example.ryan.weixindemo.common.FragmentsType;
import com.example.ryan.weixindemo.fragment.FragmentControler;
import com.example.ryan.weixindemo.fragment.MainFragment;
import com.example.ryan.weixindemo.fragment.tabfragment.BaseFragment;
import com.example.ryan.weixindemo.fragment.tabfragment.ChildPictureFragment;
import com.example.ryan.weixindemo.fragment.tabfragment.GalleryFragment;
import com.example.ryan.weixindemo.header.ToolBarControler;
import com.example.ryan.weixindemo.header.ToolBarInfo;
import com.example.ryan.weixindemo.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity implements BaseFragment.NavigationCallback {
    private ToolBarControler mToolBarControler;
    private FragmentControler fragmentControler;
    private Map<FragmentsType, String> backstackIds = new HashMap();
    private FragmentsType currentFragmentsType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.l();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();
        fragmentControler = new FragmentControler(getSupportFragmentManager(), R.id.sample_content_fragment);
        if (savedInstanceState == null) {
            String backsrackTag = fragmentControler.showMainFragment(createFragment(FragmentsType.MIAN_FRAGMENT));
            currentFragmentsType = FragmentsType.MIAN_FRAGMENT;
            backstackIds.put(FragmentsType.MIAN_FRAGMENT, backsrackTag);
        }
    }

    private void initToolBar() {
        LogUtil.d("test_tool_bar");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolBarControler = new ToolBarControler(this, toolbar);
    }

    private BaseFragment currentFragment;

    @Override
    public void nextPage(FragmentsType fragmentsType, Bundle bundle) {
        currentFragmentsType = fragmentsType;
        currentFragment = createFragment(fragmentsType);
        currentFragment.setArguments(bundle);
        String backstackTag = fragmentControler.showChildFragment(currentFragment);
        backstackIds.put(fragmentsType, backstackTag);
    }

    @Override
    public void setToolBar(ToolBarInfo toolbarInfo) {
        LogUtil.d("test_tool_bar");
        mToolBarControler.setToolbarInfo(toolbarInfo);
        mToolBarControler.getToolbar().setOnMenuItemClickListener(itemClickListener);
        mToolBarControler.getToolbar().setNavigationOnClickListener(navigationClick);
    }

    public void gobackPre(FragmentsType type) {
        String preTag = backstackIds.get(type);
        if (preTag == null) {
            LogUtil.e("the tag is null");
            return;
        }
        LogUtil.d("type = %s ,tag = %s",type.name(),preTag);
        currentFragmentsType = type;
        getSupportFragmentManager().popBackStackImmediate(preTag, 0);
    }

    private BaseFragment createFragment(FragmentsType fragmentsType) {
        switch (fragmentsType) {
            case MIAN_FRAGMENT:
                return new MainFragment();
            case PICTURE_FRAGMENT:
                return new ChildPictureFragment();
            case GALLERY_FRAGMENT:
                return new GalleryFragment();
            default:
                return null;
        }
    }

    Toolbar.OnMenuItemClickListener itemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            LogUtil.l("Click=" + item.getItemId());
            return false;
        }
    };
    View.OnClickListener navigationClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            LogUtil.l();
            gobackPre(getPriviousTag(currentFragmentsType));
        }
    };

    private FragmentsType getPriviousTag(FragmentsType type) {
        FragmentsType fragmentsType = null;
        switch (type) {
            case MIAN_FRAGMENT:
                break;
            case PICTURE_FRAGMENT:
                fragmentsType = FragmentsType.MIAN_FRAGMENT;
                break;
            case GALLERY_FRAGMENT:
                fragmentsType = FragmentsType.PICTURE_FRAGMENT;
                break;
        }
        return fragmentsType;
    }
}
