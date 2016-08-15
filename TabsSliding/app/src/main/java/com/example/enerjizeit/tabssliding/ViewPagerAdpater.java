package com.example.enerjizeit.tabssliding;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdpater extends FragmentStatePagerAdapter {
    private CharSequence titles[];
    int numbOfTabs;

    public ViewPagerAdpater(FragmentManager fm, CharSequence mTitles[], int mNumOfTabsum) {
        super(fm);

        this.titles = mTitles;
        this.numbOfTabs = mNumOfTabsum;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            Tab1 tab1 = new Tab1();
            return tab1;
        } else {
            Tab2 tab2 = new Tab2();
            return tab2;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return numbOfTabs;
    }
}
