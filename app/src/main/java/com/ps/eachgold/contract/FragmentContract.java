package com.ps.eachgold.contract;

import android.support.v4.app.FragmentManager;

/**
 * Created by 8146 on 2017/1/12.
 * Fragment管理 MVP连接层
 */
public interface FragmentContract {
    void changeCurrentFragment(String fragmentValue, FragmentManager needFragmentManager);
}
