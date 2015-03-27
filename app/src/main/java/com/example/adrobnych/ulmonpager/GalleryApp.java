package com.example.adrobnych.ulmonpager;

import android.app.Application;

import com.example.adrobnych.ulmonpager.model.GalleryImageHTTPHelper;
import com.example.adrobnych.ulmonpager.model.GalleryImageManager;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by adrobnych on 3/27/15.
 */
public class GalleryApp extends Application {

    private static final String TAG = "GalleryApp";
    private volatile GalleryImageManager gManager = null;

    public GalleryImageManager getGalleryManager(){
        if (null == gManager)
            synchronized(this){
                if (null == gManager) {

                    gManager = new GalleryImageManager();
                    gManager.setGalleryHTTPHelper(new GalleryImageHTTPHelper(gManager));
                    gManager.setGalleryItems(new TreeMap<Integer, Map<String, String>>());

                }

            }
        return gManager;
    }
}
