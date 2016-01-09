package com.example.ryan.weixindemo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ryan.weixindemo.R;
import com.example.ryan.weixindemo.common.FragmentsType;
import com.example.ryan.weixindemo.fragment.FragmentControler;
import com.example.ryan.weixindemo.fragment.MainFragment;
import com.example.ryan.weixindemo.fragment.tabfragment.BaseFragment;
import com.example.ryan.weixindemo.fragment.tabfragment.ChildPictureFragment;
import com.example.ryan.weixindemo.header.ToolBarControler;
import com.example.ryan.weixindemo.header.ToolBarInfo;

public class MainActivity extends BaseActivity implements BaseFragment.NavigationCallback {
    private ToolBarControler mToolBarControler;
    private FragmentControler fragmentControler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentControler = new FragmentControler(getSupportFragmentManager(), R.id.sample_content_fragment);
        if (savedInstanceState == null) {
            fragmentControler.showMainFragment(createFragment(FragmentsType.MIAN_FRAGMENT));
        }
        initToolBar();
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolBarControler = new ToolBarControler(this, toolbar);
        mToolBarControler.setToolbarInfo(new ToolBarInfo.Builder().setToolBarContentText(getString(R.string.app_name)).build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return mToolBarControler.onCreateOptionsMenu(R.menu.menu_main, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_group_chat) {
            Log.d("Action", "click =" + item.getTitle());
            return true;
        } else if (id == R.id.action_search) {
            Log.d("Action", "click =" + item.getTitle());
            return true;
        } else if (id == R.id.action_scan_qr) {
            Log.d("Action", "click =" + item.getTitle());
            return true;
        } else if (id == R.id.action_help) {
            Log.d("Action", "click =" + item.getTitle());
            return true;
        } else if (id == R.id.action_payment) {
            Log.d("Action", "click =" + item.getTitle());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private BaseFragment currentFragment;

    @Override
    public void nextPage(FragmentsType fragmentsType, Bundle bundle) {
        currentFragment = createFragment(fragmentsType);
        currentFragment.setArguments(bundle);

        fragmentControler.showChildFragment(currentFragment );

    }

    private BaseFragment createFragment(FragmentsType fragmentsType) {
        switch (fragmentsType) {
            case MIAN_FRAGMENT:
                return new MainFragment();
            case PICTURE_FRAGMENT:
                return new ChildPictureFragment();
            default:
                return null;
        }
    }
}
