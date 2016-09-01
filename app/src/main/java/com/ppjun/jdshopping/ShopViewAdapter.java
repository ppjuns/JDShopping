package com.ppjun.jdshopping;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * @Package :com.ppjun.jdshopping
 * @Description :
 * @Author :Rc3
 * @Created at :2016/8/24 17:43.
 */
public class ShopViewAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> mList;
    public ShopViewAdapter(FragmentManager fm,ArrayList<Fragment> mList) {
        super(fm);
        this.mList=mList;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
