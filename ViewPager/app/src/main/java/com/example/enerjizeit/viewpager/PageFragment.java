package com.example.enerjizeit.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PageFragment extends Fragment {

    public ItemLab itemLab;

    public static PageFragment newInstance(ItemLab item){
        // Создаем экземпляр PageFragment
        PageFragment pageFragment = new PageFragment();
        // Передаем экземпляр контакта
        pageFragment.itemLab = item;
        return pageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail, null);

        ImageView photo = (ImageView) view.findViewById(R.id.detailsPhoto);
        TextView name = (TextView)view.findViewById(R.id.detailsName);
        TextView phone = (TextView)view.findViewById(R.id.detailsPhone);
        TextView about = (TextView)view.findViewById(R.id.detailsAbout);

        photo.setImageResource(itemLab.getPhotoID());
        name.setText(itemLab.getName());
        phone.setText(itemLab.getPhone());
        about.setText(itemLab.getAbout());
        return  view;
    }
}
