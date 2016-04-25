package com.example.ryan.hkgankio.view.daily;

import com.example.ryan.hkgankio.bean.DailyNewsBean;
import com.example.ryan.hkgankio.bean.StoriesBean;

import java.util.List;

/**
 * Created by ryan on 4/23/16.
 */
public interface IBaseDailyFragment extends IBaseFragment{



    /**
     * get data result after it query
     * @param
     */
    void onLoadDataResult(DailyNewsBean newsBean);
}
