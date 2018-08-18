package com.ps.loanbox.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ps.loanbox.R;
import com.ps.loanbox.contract.FragmentContract;
import com.ps.loanbox.fragment.CreditFragment;
import com.ps.loanbox.fragment.InsteadFragment;
import com.ps.loanbox.fragment.LoanFragment;
import com.ps.loanbox.fragment.PersonFragment;
import com.ps.loanbox.gate.NewFragment;

/**
 * Created by 8146 on 2017/1/12.
 * Fragment工具类
 */

public class FragmentUtil implements FragmentContract{

    Fragment lastFragment;

    @Override
    public void changeCurrentFragment(String fragmentValue, FragmentManager needFragmentManager) {
        Fragment fragmentTag=needFragmentManager.findFragmentByTag(fragmentValue);
        if(fragmentTag==null){
            fragmentTag=creatFragmentByTag(fragmentValue);
            addFragment(fragmentTag,needFragmentManager,fragmentValue);
        }else{
            showFragment(fragmentTag, needFragmentManager, fragmentValue);

        }
        lastFragment=fragmentTag;
    }
    private void showFragment(Fragment fragmentTag, FragmentManager needFragmentManager, String fragmentValue) {
        FragmentTransaction transcation=needFragmentManager.beginTransaction();
        //fragment的动画
        //transcation.setCustomAnimations(R.anim.push_left_in,R.anim.push_left_out,R.anim.push_left_in,R.anim.push_left_out);
        if(lastFragment!=null){
            transcation.hide(lastFragment);
        }
        transcation.show(fragmentTag);
        transcation.commitAllowingStateLoss();
    }

    private void addFragment(Fragment fragmentTag, FragmentManager needFragmentManager, String fragmentValue) {
        FragmentTransaction transaction=needFragmentManager.beginTransaction();
        if(lastFragment!=null){
            transaction.hide(lastFragment);
        }
        transaction.add(R.id.mainFragmentLayout,fragmentTag,fragmentValue);
        transaction.commitAllowingStateLoss();

    }
    private Fragment creatFragmentByTag(String fragmentValue) {
        switch (fragmentValue){
            case "FRAGMENT_LOAN":
                return LoanFragment.newInstance();
            case "FRAGMENT_CREDIT":
                return CreditFragment.newInstance();
            case "FRAGMENT_INSTEAD":
               return InsteadFragment.newInstance();
            case "FRAGMENT_PERSON":
                return PersonFragment.newInstance();
            case "FRAGMENT_NEW1":
                return NewFragment.getInstance("1");
            case "FRAGMENT_NEW2":
                return NewFragment.getInstance("2");
            case "FRAGMENT_NEW3":
                return NewFragment.getInstance("3");
        }
        return LoanFragment.newInstance();
    }


}
