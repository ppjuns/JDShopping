package com.ppjun.jdshopping.Utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * @Package :com.ppjun.jdshopping.Utils
 * @Description :
 * @Author :Rc3
 * @Created at :2016/8/30 09:53.
 */
public class UiUtils {

    public static int getHeight(Context context){
        WindowManager windowManager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics=new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    public static int getStatusBarHeight(Context context){
     Class<?> c=null;
        Object obj=null;
        Field field=null;
        int x=0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
            return 75;
        }

    }

    public static int dp2px(Context context,int dip){
        return (int)(dip*getScreenDensity(context)+0.f);
    }

    private static float getScreenDensity(Context context) {
        try {
            DisplayMetrics dm=new DisplayMetrics();
            WindowManager windowManager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(dm);
            return dm.density;
        } catch (Exception e) {
            e.printStackTrace();
            return DisplayMetrics.DENSITY_DEFAULT;
        }
    }
}
