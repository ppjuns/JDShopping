package com.ppjun.jdshopping.Utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SizeReadyCallback;

/**
 * @Package :com.ppjun.jdshopping.Utils
 * @Description :
 * @Author :Rc3
 * @Created at :2016/8/26 11:24.
 */
public class GlideUtil {

    public static final GlideUtil mInstance=null;


    public static GlideUtil getInstance(){
        return new GlideUtil();
    }
    public GlideUtil(){


    }

    public static final String TAG="GlideUtils";


    public static void display(ImageView view, Object url){
        displayUrl(view,url);


    }
    public static  void displayUrl(final ImageView view,Object url){
        if(view==null){
            Log.e(TAG,"GlideUtils->displayUrl-> imageview is null");
            return;
        }
        //不能崩，判断当前activity finish了吗
        Context context=view.getContext();
        if(context instanceof Activity){
            if(((Activity) context).isFinishing()){
                return;
            }
        }

        try {


            Glide.with(context)
                    .load(url)

                    .diskCacheStrategy(DiskCacheStrategy.ALL)

                    .crossFade()
                    .centerCrop()
                    .into(view)
                    .getSize(new SizeReadyCallback() {
                        @Override
                        public void onSizeReady(int width, int height) {
                            if(!view.isShown()){
                                view.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayNative(final ImageView view,int resId){
        if(view==null){
            Log.e(TAG,"GlideUtils->displayUrl-> imageview is null");
            return;
        }
        //不能崩，判断当前activity finish了吗
        Context context=view.getContext();
        if(context instanceof Activity){
            if(((Activity) context).isFinishing()){
                return;
            }
        }

        try {
            Glide.with(context)
                    .load(resId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .centerCrop()
                    .into(view)
                    .getSize(new SizeReadyCallback() {
                        @Override
                        public void onSizeReady(int width, int height) {
                            if(!view.isShown()){
                                view.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
