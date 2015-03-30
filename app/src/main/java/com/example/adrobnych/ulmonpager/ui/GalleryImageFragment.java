package com.example.adrobnych.ulmonpager.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adrobnych.ulmonpager.GalleryApp;
import com.example.adrobnych.ulmonpager.R;
import com.example.adrobnych.ulmonpager.model.GalleryImageManager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class GalleryImageFragment extends Fragment {

    private int page;
    static Bitmap bmp;
    ImageView im;
    URL url;

    public static GalleryImageFragment newInstance(int page) {
        GalleryImageFragment f = new GalleryImageFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery_image, container, false);
        im = (ImageView) view.findViewById(R.id.imageView);

        GalleryImageManager gm = ((GalleryApp)getActivity().getApplication()).getGalleryManager();

        TextView messageTV = (TextView) view.findViewById(R.id.tvMessageTitle);
        messageTV.setText(gm.getGalleryItemById(page).get(GalleryImageManager.MESSAGE));

        TextView pageTV = (TextView) view.findViewById(R.id.tvPageCounter);
        pageTV.setText(""+page+"/"+gm.getGallerySize());

        url = null;
        try {
            url = new URL(gm.getGalleryItemById(page).get(GalleryImageManager.IMAGE_URL));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new LongOperation().execute("");

        return view;
    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            bmp = null;
            try {
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {

            im.setImageBitmap(bmp);
        }

    }



}
