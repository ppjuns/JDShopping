package com.ppjun.jdshopping.adapter.pageradapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * @Package :com.ppjun.jdshopping.adapter.pageradapter
 * @Description :
 * @Author :Rc3
 * @Created at :2016/8/26 11:05.
 */
public class PicturePagerAdapter extends PagerAdapter {
    ArrayList<ImageView> mImageList;

    public PicturePagerAdapter(ArrayList<ImageView> list) {
        this.mImageList = list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE / 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;

        // position=5002
        if (position % mImageList.size() < 0) {

            view = mImageList.get(mImageList.size() + position);

        } else {

            view = mImageList.get(position % mImageList.size());

        }
        //获取父类。移除父view中的子view
        ViewParent vp = view.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(view);
        }
        ((ViewPager) container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager)container).removeView((View)object);
        object=null;
    }


}
