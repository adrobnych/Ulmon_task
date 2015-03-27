package com.example.adrobnych.ulmonpager.model;

/**
 * Created by adrobnych on 3/27/15.
 */
public class GalleryImageHTTPHelper {
    private static final String POST_URL =
        "http://ec2-176-34-216-72.eu-west-1.compute.amazonaws.com/rest/map/discovery?access_token=C3AE7&installation_uuid=6fy&device=x86_64";
    private static final String POST_DATA =
        "{\"mapId\": 1, \"userLang\": \"en\"}";

    public String fetchRemoteJsonString(){
        return "{\"success\": true}";
    }
}
