package com.example.adrobnych.ulmonpager.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adrobnych.ulmonpager.GalleryApp;
import com.example.adrobnych.ulmonpager.R;
import com.example.adrobnych.ulmonpager.model.GalleryImageManager;
import com.example.adrobnych.ulmonpager.services.GalleryDataLoaderService;


public class MainActivity extends ActionBarActivity {
    private GalleryImageManager gm;
    private TextView tv;

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                String result = bundle.getString(GalleryDataLoaderService.RESULT);
                if (result != null) {
                    Toast.makeText(MainActivity.this,
                            "Download complete",
                            Toast.LENGTH_LONG).show();
                    //todo notify views...
                    tv.setText(result);
                } else {
                    Toast.makeText(MainActivity.this, "Download failed",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startButton = (Button) findViewById(R.id.startButton);
        gm = ((GalleryApp) getApplication()).getGalleryManager();
        tv = (TextView) findViewById(R.id.tvmain);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoadGalleryMessagesAndURLs();

                //Intent i = new Intent(MainActivity.this, ImagePagerActivity.class);
                //startActivity(i);
            }
        });
    }

    private void LoadGalleryMessagesAndURLs() {
        Intent intent = new Intent(this, GalleryDataLoaderService.class);
        startService(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(GalleryDataLoaderService.NOTIFICATION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
