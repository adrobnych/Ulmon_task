package com.example.adrobnych.ulmonpager.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adrobnych.ulmonpager.GalleryApp;
import com.example.adrobnych.ulmonpager.R;
import com.example.adrobnych.ulmonpager.model.GalleryImageHTTPHelper;
import com.example.adrobnych.ulmonpager.model.GalleryImageManager;
import com.example.adrobnych.ulmonpager.services.GalleryDataLoaderService;

import java.util.HashMap;
import java.util.Map;


public class ImagePagerActivity extends ActionBarActivity {


    private GalleryImageManager gm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pager);
        gm = ((GalleryApp) getApplication()).getGalleryManager();

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), getAllFragments()));

    }

    private Map<Integer, Fragment> getAllFragments() {
        Map<Integer, Fragment> fragmentMap = new HashMap<>();
        for(int i=0; i<gm.getGallerySize(); i++)
            fragmentMap.put(i, GalleryImageFragment.newInstance(i));


        return fragmentMap;

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_pager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private int NUM_ITEMS;
        private Map<Integer, Fragment> allFragments;

        public MyPagerAdapter(FragmentManager fragmentManager, Map<Integer, Fragment> allFragments) {
            super(fragmentManager);
            NUM_ITEMS = allFragments.size();
            this.allFragments = allFragments;
        }


        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            return allFragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }
}
