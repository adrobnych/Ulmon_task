package com.example.adrobnych.ulmonpager.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adrobnych on 3/27/15.
 */
public class GalleryImageManager {
    public static final String MESSAGE = "message";
    public static final String IMAGE_URL = "image_url";

    public Map<Integer, Map<String, String>> getGalleryItems() {
        return galleryItems;
    }

    public void setGalleryItems(Map<Integer, Map<String, String>> galleryItems) {
        this.galleryItems = galleryItems;
    }

    private Map<Integer, Map<String, String>> galleryItems;

    public void addGalleryItem(String message, String url){
        int nextId = galleryItems.size() + 1;
        Map<String, String> data = new HashMap<>();
        data.put(MESSAGE, message);
        data.put(IMAGE_URL, url);
        galleryItems.put(nextId, data);
    }

    public int getGallerySize(){
        return galleryItems.size();
    }

    public Map<String, String> getGalleryItemById(int id){
        return galleryItems.get(id);
    }
}
