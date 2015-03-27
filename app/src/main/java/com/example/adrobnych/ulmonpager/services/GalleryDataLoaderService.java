package com.example.adrobnych.ulmonpager.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.example.adrobnych.ulmonpager.GalleryApp;
import com.example.adrobnych.ulmonpager.model.GalleryImageHTTPHelper;
import com.example.adrobnych.ulmonpager.model.GalleryImageManager;

import org.json.JSONException;

import java.io.IOException;


public class GalleryDataLoaderService extends IntentService {
    private String result = null;
    public final static String RESULT = "result";
    public static final String NOTIFICATION = "com.example.adrobnych.galleryapp.services.loadgallerydata.receiver";

    public GalleryDataLoaderService() {
        super("GalleryDataLoaderService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GalleryImageManager gm = ((GalleryApp) getApplication()).getGalleryManager();
        GalleryImageHTTPHelper gh = gm.getGalleryHTTPHelper();
        result = "errorFetchingJson";

        try {
            result = gh.fetchRemoteJsonString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
        e.printStackTrace();
        }

        publishResults(result);
    }

    private void publishResults(String result) {
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(RESULT, result);
        sendBroadcast(intent);
    }
}