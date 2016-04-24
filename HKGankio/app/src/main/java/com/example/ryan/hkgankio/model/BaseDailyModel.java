package com.example.ryan.hkgankio.model;

import com.example.ryan.hkgankio.bean.DailyNewsBean;
import com.example.ryan.hkgankio.bean.StoriesBean;

import java.util.List;

/**
 * Created by ryan on 4/23/16.
 */
public interface BaseDailyModel {
    DailyNewsBean loadDailyNews();
}
