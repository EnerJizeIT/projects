package com.example.enerjizeit.photogallery;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PhotoGalleryFragment extends VisibleFragment {
public PhotoGalleryFragment() {}
public static PhotoGalleryFragment newInstance(){
    return new PhotoGalleryFragment();
}
    private RecyclerView recyclerView;
    private List<GalleryItem> mItems = new ArrayList<>();
    private HandleDownloader<PhotoHolder> handleDownloader;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        updateItems();

        Handler responceHandler = new Handler();
        handleDownloader = new HandleDownloader<>(responceHandler);
        handleDownloader.setDownloadInterfaceListener(new HandleDownloader.DownloadInterfaceListener<PhotoHolder>() {
            @Override
            public void onImageDownloaded(PhotoHolder target, Bitmap bitmaper) {
                Drawable drawable = new BitmapDrawable(getResources(), bitmaper);
                target.bindImageItem(drawable);
            }
        });

        handleDownloader.start();
        handleDownloader.getLooper();
        L.l("HandleDownloader started!");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_photo_gallery, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                L.l("onQueryTextSubmit " + query);
                QueryPreferences.setStoredQuery(getActivity(), query);
                updateItems();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                L.l("onQueryTextChange " + newText);
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = QueryPreferences.getStoredQuery(getActivity());
                searchView.setQuery(query, false);
            }
        });

        MenuItem toggle = menu.findItem(R.id.menu_item_toggle_polling);
        if(PollService.isServiceAlarmOn(getActivity())){
            toggle.setTitle(R.string.stop_polling);
        } else {
            toggle.setTitle(R.string.start_polling);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_clear:
                QueryPreferences.setStoredQuery(getActivity(), null);
                updateItems();
                return true;
            case R.id.menu_item_toggle_polling:
                boolean shouldStartAlarm = !PollService.isServiceAlarmOn(getActivity());
                PollService.setServiceAlarm(getActivity(), shouldStartAlarm);
                getActivity().invalidateOptionsMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_gallery, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        setupAdapter();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handleDownloader.clearQueue();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handleDownloader.quit();
        L.l("HandleDownloader destroyed!");
    }

    private void setupAdapter() {
        if(isAdded()){/* Проверка  подтверждает, что фрагмент был присоединен к активности, а следовательно, что
результат getActivity() будет отличен от null. */
            recyclerView.setAdapter(new PhotoAdapter(mItems));
        }
    }

    private class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView imageView;
        private GalleryItem galleryItem;

        public PhotoHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.photoId);
            itemView.setOnClickListener(this);
        }

        public void bindImageItem(Drawable drawable){
            imageView.setImageDrawable(drawable);
        }

        public void bindGalleryItem(GalleryItem galleryItem){
            this.galleryItem = galleryItem;
        }

        @Override
        public void onClick(View v) {
            Intent intent = PhotoPageActivity.newIntent(getActivity(), galleryItem.getPhotoPageUri());
            startActivity(intent);
        }
    }
    private class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder>{
        private List<GalleryItem> items;
        private PhotoAdapter(List<GalleryItem> galleryItem) {
            items = galleryItem;
        }

        @Override
        public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.item, parent, false);
            return new PhotoHolder(view);
        }

        @Override
        public void onBindViewHolder(PhotoHolder holder, int position) {
            GalleryItem item = items.get(position);
            holder.bindGalleryItem(item);

            Drawable placeholder = getResources().getDrawable(R.drawable.bill_up_close);
            holder.bindImageItem(placeholder);

            handleDownloader.queueThumbnail(holder, item.getUrl());
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

    }

    private class FetchItemsTask extends AsyncTask<Void, Void, List<GalleryItem>>{
        private String mQuery;

        public FetchItemsTask(String mQuery) {
            this.mQuery = mQuery;
        }

        @Override
        protected List<GalleryItem> doInBackground(Void... params) {
            if(mQuery == null){
                return new FlickrConnection().fetchRecentPhotos();
            } else {
                return new FlickrConnection().searchPhotos(mQuery);
            }
        }
        @Override
        protected void onPostExecute(List<GalleryItem> items) {
            mItems = items;
            setupAdapter();
        }

    }
    private void updateItems() {
        String query = QueryPreferences.getStoredQuery(getActivity());
        new FetchItemsTask(query).execute();
    }

}
