package com.ppjun.jdshopping;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_EXQUEST_EXTERBAL_STORAGE = 1;
    private static String[] PERMISSION_STORAGE = {android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
    LinearLayout mLinearLayout;
    TabLayout mTabLayout;
    ViewPager mViewPager;
    ImageView mMore;
    boolean CanSave = false;

    public static void VerifyStoragePermissions(Activity activity) {

        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSION_STORAGE, REQUEST_EXQUEST_EXTERBAL_STORAGE);
        } else {

            Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
            activity.startActivityForResult(i, 10);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        ininDatas();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i("TAG", "onRequestPermissionsResult");
        if (requestCode == REQUEST_EXQUEST_EXTERBAL_STORAGE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            CanSave = true;
            Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(i, 10);
        }
    }

    private void ininDatas() {
        TabPagerAdater pager = new TabPagerAdater();

        ArrayList<Fragment> mlist = new ArrayList();
        ShopDetialFragment shopDetialFragment = new ShopDetialFragment();
        ShopFragment shopFragment1 = new ShopFragment();
        ShopFragment shopFragment = new ShopFragment();
        mlist.add(shopFragment);
        mlist.add(shopDetialFragment);
        mlist.add(shopFragment1);
        ShopViewAdapter adapter = new ShopViewAdapter(getSupportFragmentManager(), mlist);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(pager);


        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerifyStoragePermissions(MainActivity.this);

            }
        });
    }


    private void initViews() {
        mLinearLayout = (LinearLayout) findViewById(R.id.shop_car);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mMore = (ImageView) findViewById(R.id.more);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap photo = null;
        if (resultCode == RESULT_OK && requestCode == 10) {

            Uri uri = data.getData();
            if (uri != null) {
                photo = BitmapFactory.decodeFile(uri.getPath());
                savePic(photo);
            }
            if (photo == null) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    photo = (Bitmap) bundle.get("data");
                    if (CanSave)
                        savePic(photo);
                }
            }

        }

    }
private Messenger mService;
    private ServiceConnection mConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService=new Messenger(service);
            Message msg=Message.obtain(null,1);
            Bundle data=new Bundle();
            msg.setData(data);
            try {
                mService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void savePic(Bitmap photo) {
        try {
            FileOutputStream fos = null;
            File f=new File(Environment.getExternalStorageDirectory(),"GG");
            if(!f.exists()){
                f.mkdir();
            }
            File file = new File(f, "a.png");

            fos = new FileOutputStream(file.getAbsolutePath());
            if (fos != null) {
                photo.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            Log.i("w", "c");


        }
    }


}
