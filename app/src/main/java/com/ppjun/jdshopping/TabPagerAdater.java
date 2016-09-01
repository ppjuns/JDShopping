package com.ppjun.jdshopping;

import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * @Package :com.ppjun.jdshopping
 * @Description :
 * @Author :Rc3
 * @Created at :2016/8/24 17:25.
 */
public class TabPagerAdater extends PagerAdapter {

String[] titles={"商品","详情","评价"};
    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
