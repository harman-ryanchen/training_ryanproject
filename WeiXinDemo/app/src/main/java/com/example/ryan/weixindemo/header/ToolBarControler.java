package com.example.ryan.weixindemo.header;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ryan.weixindemo.R;

/**
 * Created by ryan on 12/27/15.
 */
public class ToolBarControler {

    private AppCompatActivity mContext;
    private Toolbar mToolbar;
    private ToolBarInfo mToolbarInfo;

    public ToolBarControler(AppCompatActivity mContext, Toolbar mToolbar) {
        this.mContext = mContext;
        this.mToolbar = mToolbar;
        mContext.setSupportActionBar(mToolbar);
    }

    /**
     *
     * @param toolbarInfo
     */
    public void setToolbarInfo(ToolBarInfo toolbarInfo) {
        this.mToolbarInfo = toolbarInfo;

        solveActionBarAndToolBarConfilt();

        if (toolbarInfo.getTitleTextContent() !=null){
            mContext.getSupportActionBar().setDisplayShowTitleEnabled(false);
            mToolbar.setTitle(toolbarInfo.getTitleTextContent());
        }
        if (toolbarInfo.getToolbarLogo() != 0){
            mToolbar.setNavigationIcon(toolbarInfo.getToolbarLogo());
        }
        if (toolbarInfo.getNavigationIcon_color() != 0){
            mToolbar.setTitleTextColor(mToolbarInfo.getNavigationIcon_color());
        }
    }

    private void solveActionBarAndToolBarConfilt() {
        mContext.getSupportActionBar().setDisplayShowTitleEnabled(false);
        mContext.getSupportActionBar().setDisplayUseLogoEnabled(false);
    }

    /**
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    /**
     * @param menu
     */
    public boolean onCreateOptionsMenu(int customMenu,Menu menu) {
        mContext.getMenuInflater().inflate(customMenu, menu);
        return true;
    }

}
