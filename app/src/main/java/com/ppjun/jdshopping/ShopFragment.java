package com.ppjun.jdshopping;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.ppjun.jdshopping.Utils.GlideUtil;
import com.ppjun.jdshopping.adapter.pageradapter.PicturePagerAdapter;
import com.ppjun.jdshopping.weight.ChildViewPager;
import com.ppjun.jdshopping.weight.PageTwoWebView;

import java.util.ArrayList;

/**
 * @Package :com.ppjun.jdshopping
 * @Description :
 * @Author :Rc3
 * @Created at :2016/8/24 17:51.
 */
public class ShopFragment extends Fragment {

    PopupWindow mPopupWindow;
    ArrayList<ImageView> imageList;
    Context mContext;
    ChildViewPager mChildViewPager;
    Button mNumber;
    PageTwoWebView mWebview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.main_fragment, container, false);
        final View view2 = inflater.inflate(R.layout.pop_view, null);


        mPopupWindow = new PopupWindow(view2, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_launcher));

        mContext = getActivity();
        initViews(view);
        initData();
        return view;
    }

    private void initViews(View v) {

        mChildViewPager= (ChildViewPager) v.findViewById(R.id.pictureviewpager);
        mNumber= (Button) v.findViewById(R.id.number);
       mWebview = (PageTwoWebView) v.findViewById(R.id.webview_detial);


      mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebview.loadUrl("http://item.m.jd.com/product/1221055.html?sid=12d93e5ca855d0bdefd6ffec46b0d0e7");
    }

    private void initData() {
        imageList = new ArrayList<>();
        String[] productPics = {"http://m.360buyimg.com/n12/jfs/t2908/202/289057952/100194/8f0eb667/570cfc91N12a901b9.jpg!q70.jpg",
                "http://m.360buyimg.com/n12/jfs/t2050/192/2558117994/67354/238a3c35/570cfc9dNcd0cf3fc.jpg!q70.jpg",
                "http://m.360buyimg.com/n12/jfs/t2350/6/2510137437/98117/506f3f75/570cfca2Nfacbbb68.jpg!q70.jpg",
                "http://m.360buyimg.com/n12/jfs/t2155/268/2580895075/144755/c99356b5/570cfcaaN219578e6.jpg!q70.jpg",
                "http://m.360buyimg.com/n12/jfs/t2059/156/2502830411/160188/f9a7ccfb/570cfcabN1061816b.jpg!q70.jpg"};
        for (int i = 0; i < productPics.length; i++) {
            ImageView image = new ImageView(mContext);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            image.setLayoutParams(lp);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            GlideUtil.display(image,productPics[i]);
            imageList.add(image);
        }
        PicturePagerAdapter adapter=new PicturePagerAdapter(imageList);
        mChildViewPager.setAdapter(adapter);
        mChildViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position>=5){
                   position=position%5;
                }
                mNumber.setText((position+1)+"/5");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
