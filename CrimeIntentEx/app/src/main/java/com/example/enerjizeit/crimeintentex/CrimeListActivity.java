package com.example.enerjizeit.crimeintentex;

import android.support.v4.app.Fragment;

public class CrimeListActivity extends HostFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

}

