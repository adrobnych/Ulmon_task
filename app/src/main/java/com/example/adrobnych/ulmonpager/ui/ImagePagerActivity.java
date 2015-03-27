package com.example.adrobnych.ulmonpager.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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


public class ImagePagerActivity extends ActionBarActivity {


    private GalleryImageManager gm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pager);
        gm = ((GalleryApp) getApplication()).getGalleryManager();

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
}
