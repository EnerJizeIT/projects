package com.example.enerjizeit.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PageAdapter extends FragmentPagerAdapter{
    private ArrayList<ItemLab> list;

    public PageAdapter(FragmentManager fm, ArrayList<ItemLab> list){
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(list.get(position));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getName();
    }
}
