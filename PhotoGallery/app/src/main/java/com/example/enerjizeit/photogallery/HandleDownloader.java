package com.example.enerjizeit.photogallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;


import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class HandleDownloader<T> extends HandlerThread {
    private static final int MESSAGE_DOWNLOAD = 0;

    private Handler requestHandler;
    private Handler responseHandler;
    private ConcurrentMap<T, String> requestMap = new ConcurrentHashMap<>();
    private DownloadInterfaceListener<T> downloadInterfaceListener;

    public interface DownloadInterfaceListener<T>{
        void onImageDownloaded(T target, Bitmap bitmaper);
    }

    public void setDownloadInterfaceListener(DownloadInterfaceListener<T> listener) {
        downloadInterfaceListener = listener;
    }


    public HandleDownloader(Handler responceHandler) {
        super("HandleDownloader");
        this.responseHandler = responceHandler;
    }

    @Override
    protected void onLooperPrepared() {
        L.l("HandleDownloader onLooperPrepared");
        requestHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MESSAGE_DOWNLOAD) {
                    T target = (T) msg.obj;
                    L.l("HandleDownloader Got a request for URL: " + requestMap.get(target));
                    handleRequest(target);
                }
            }
        };
    }

    public void queueThumbnail(T target, String url) {
        L.l("HandleDownloader GOT a URL: " + url);
        if (url == null) {
            requestMap.remove(target);
        } else {
            requestMap.put(target, url);
            requestHandler.obtainMessage(MESSAGE_DOWNLOAD, target).sendToTarget();
        }

    }

    private void handleRequest(final T target) {
        try {
            final String url = requestMap.get(target);

            if(url == null){
                return;
            }

            byte[] bitmapBytes = new FlickrConnection().getUrlBytes(url);
            final Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapBytes,
                    0, bitmapBytes.length);
            L.l("HandleDownloader bitmap created");

            responseHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(requestMap.get(target) != url){
                        return;
                    }
                    requestMap.remove(target);
                    downloadInterfaceListener.onImageDownloaded(target, bitmap);
                }
            });
        } catch (IOException e) {
            L.l("HandleDownloader Error downloading image " + e);
        }

    }

    public void clearQueue(){
        L.l("HandleDownloader removeMessages");
        requestHandler.removeMessages(MESSAGE_DOWNLOAD);
    }
}
