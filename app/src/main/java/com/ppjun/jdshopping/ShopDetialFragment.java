package com.ppjun.jdshopping;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @Package :com.ppjun.jdshopping
 * @Description :
 * @Author :Rc3
 * @Created at :2016/8/26 20:08.
 */
public class ShopDetialFragment extends Fragment {
    WebView mWebview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_detial_view, container, false);
        initViews(view);
        initData();
        return view;
    }

    private void initData() {
    }

    private void initViews(View view) {
        mWebview = (WebView) view.findViewById(R.id.shopwebview);

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

}
