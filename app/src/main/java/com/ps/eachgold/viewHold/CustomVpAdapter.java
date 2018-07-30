package com.ps.eachgold.viewHold;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 8146 on 2018/1/16.
 */

public class CustomVpAdapter extends FragmentPagerAdapter {

    private FragmentManager fm;
    private List<Fragment> fragments;


    public CustomVpAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


}