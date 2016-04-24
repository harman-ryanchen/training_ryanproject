package com.example.ryan.hkgankio.view.daily;

import android.content.Context;
import android.os.Bundle;

import com.example.ryan.hkgankio.bean.StoriesBean;
import com.example.ryan.hkgankio.view.IBaseFragment;

import java.util.List;

/**
 * Created by ryan on 4/23/16.
 */
public interface IBaseDailyFragment extends IBaseFragment{



    /**
     * get data result after it query
     * @param storiesBeens
     */
    void onLoadDataResult(List<StoriesBean> storiesBeens);
}
