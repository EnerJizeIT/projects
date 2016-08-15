package com.example.enerjizeit.locatr;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FlickrConnection {
    private static final String API_KEY = "3a473d72962633bd01d74ba4935c5bdc";
    private static final String TAG = "FlickrConnection";

    private static final String FETCH_RECENTS_METHOD = "flickr.photos.getRecent";
    private static final String SEARCH_METHOD = "flickr.photos.search";
    private static final Uri ENDPOINT = Uri
            .parse("https://api.flickr.com/services/rest/")
            .buildUpon()
            .appendQueryParameter("api_key", API_KEY)
            .appendQueryParameter("format", "json")
            .appendQueryParameter("nojsoncallback", "1")
            .appendQueryParameter("extras", "url_s")
            .build();

    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec); /* Этот код создает объект URL на базе строки — например, https://www.bignerdranch.com.  */
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            InputStream inputStream = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ":with " + urlSpec);
            }
            int bytesRead = 0;
            byte[] bytes = new byte[1024];
            while ((bytesRead = inputStream.read(bytes)) > 0) {
                byteArrayOutputStream.write(bytes, 0, bytesRead);
            }

            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public List<GalleryItem> fetchRecentPhotos(){
        String url = buildUrl(FETCH_RECENTS_METHOD, null);
        return downloadGalleryItems(url);
    }

    public List<GalleryItem> searchPhotos(String query){
        String url = buildUrl(SEARCH_METHOD, query);
        return downloadGalleryItems(url);
    }

    public List<GalleryItem> downloadGalleryItems(String url) {
        List<GalleryItem> items = new ArrayList<>();
        try {

            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items, jsonBody);
        } catch (JSONException ej) {
            Log.i(TAG, "FlickrConnection Failed to parse JSON " + ej);
        } catch (IOException e) {
            Log.i(TAG, "FlickrConnection Exception " + e);
        }
        return items;
    }

    private String buildUrl(String method, String query) {
        Uri.Builder uriBuilder = ENDPOINT.buildUpon().appendQueryParameter("method", method);
        if (method.equals(SEARCH_METHOD)) {
            uriBuilder.appendQueryParameter("text", query);
        }

        return uriBuilder.build().toString();
    }

    private void parseItems(List<GalleryItem> items, JSONObject jsonBody) throws IOException, JSONException {
        JSONObject jsonObjectPhotos = jsonBody.getJSONObject("photos");
        JSONArray photoJsonArray = jsonObjectPhotos.getJSONArray("photo");

        for (int i = 0; i < photoJsonArray.length(); i++) {
            JSONObject photojsonObject = photoJsonArray.getJSONObject(i);

            GalleryItem galleryItem = new GalleryItem();
            galleryItem.setId(photojsonObject.getString("id"));
            galleryItem.setCaption(photojsonObject.getString("title"));

            if (!photojsonObject.has("url_s")) {
                continue;
            }

            galleryItem.setUrl(photojsonObject.getString("url_s"));
            galleryItem.setmOwner(photojsonObject.getString("owner"));
            items.add(galleryItem);
        }
    }
}
