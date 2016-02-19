package com.example.ryan.weixindemo.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.ryan.weixindemo.R;
import com.example.ryan.weixindemo.common.FragmentsType;
import com.example.ryan.weixindemo.fragment.FragmentControler;
import com.example.ryan.weixindemo.fragment.MainFragment;
import com.example.ryan.weixindemo.fragment.tabfragment.BaseFragment;
import com.example.ryan.weixindemo.fragment.tabfragment.ChildPictureFragment;
import com.example.ryan.weixindemo.fragment.tabfragment.FirstFragment;
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
    private DrawerLayout mDrawerLayout;
    private NavigationView nvDrawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.l();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();
        initDrawerLaout();
        fragmentControler = new FragmentControler(getSupportFragmentManager(), R.id.sample_content_fragment);
        if (savedInstanceState == null) {
            String backsrackTag = fragmentControler.showMainFragment(createFragment(FragmentsType.MIAN_FRAGMENT));
            currentFragmentsType = FragmentsType.MIAN_FRAGMENT;
            backstackIds.put(FragmentsType.MIAN_FRAGMENT, backsrackTag);
        }
    }

    private void initDrawerLaout() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Find our drawer view
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(nvDrawer);

        drawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(drawerToggle);


        // Inflate the header view at runtime
        View headerLayout = nvDrawer.inflateHeaderView(R.layout.view_drawerlayout_head);
        // We can now look up items within the header if needed
        ImageView view_draw_onhead = (ImageView) headerLayout.findViewById(R.id.view_draw_onhead);
        view_draw_onhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtil.l();
                mDrawerLayout.closeDrawers();
            }
        });

    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectNavigationItm(item);
                return true;
            }
        });
    }

    private void selectNavigationItm(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_first_section:
                nextPage(FragmentsType.FRAGMENT_FIRST, null);
                break;
            case R.id.nav_second_section:
                break;
            case R.id.nav_third_section:
                break;
        }
    }

    private void initToolBar() {
        LogUtil.d("test_tool_bar");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        LogUtil.d("type = %s ,tag = %s", type.name(), preTag);
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
            case FRAGMENT_FIRST:
                return new FirstFragment();
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
