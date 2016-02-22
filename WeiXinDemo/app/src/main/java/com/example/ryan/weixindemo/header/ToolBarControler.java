package com.example.ryan.weixindemo.header;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.ryan.weixindemo.R;
import com.example.ryan.weixindemo.infoobject.ToolBarInfo;
import com.example.ryan.weixindemo.util.LogUtil;

/**
 * Created by ryan on 12/27/15.
 */
public class ToolBarControler {

    private AppCompatActivity mContext;
    private Toolbar mToolbar;
    private TextView toolBar_titletext;

    public ToolBarControler(AppCompatActivity mContext, Toolbar mToolbar) {
        this.mContext = mContext;
        this.mToolbar = mToolbar;
        initToolbar();
    }

    private void initToolbar() {
        mContext.setSupportActionBar(mToolbar);
        mContext.getSupportActionBar().setDisplayShowTitleEnabled(false);
        // enable ActionBar app icon to behave as action to toggle nav drawer
        mContext.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mContext.getSupportActionBar().setHomeButtonEnabled(true);
        toolBar_titletext = (TextView) mToolbar.findViewById(R.id.toolbar_title);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    /**
     * @param toolbarInfo
     */
    public void setToolbarInfo(ToolBarInfo toolbarInfo) {

//        solveActionBarAndToolBarConfilt();

        LogUtil.d("title = %s , LOGO = %s ,icon_color = %s , menu = %s", toolbarInfo.getTitleTextContent(), toolbarInfo.getToolbarLogo(), toolbarInfo.getNavigationIcon_color(), toolbarInfo.getMenu());
        if (toolbarInfo.getTitleTextContent() != null) {
            toolBar_titletext.setText(toolbarInfo.getTitleTextContent());
        }
        if (toolbarInfo.getToolbarLogo() != 0) {
            mToolbar.setNavigationIcon(toolbarInfo.getToolbarLogo());

        } else {
            mToolbar.setNavigationIcon(null);
        }

        if (toolbarInfo.getNavigationIcon_color() != 0) {
            mToolbar.setTitleTextColor(toolbarInfo.getNavigationIcon_color());
        }
        if (toolbarInfo.getMenu() != 0) {
            mToolbar.inflateMenu(toolbarInfo.getMenu());
        } else {
            mToolbar.getMenu().clear();
        }
    }

    public void solveActionBarAndToolBarConfilt() {
        mContext.getSupportActionBar().setDisplayShowTitleEnabled(false);
        mContext.getSupportActionBar().setDisplayUseLogoEnabled(false);
    }


}
