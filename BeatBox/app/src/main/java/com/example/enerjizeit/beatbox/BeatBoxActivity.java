package com.example.enerjizeit.beatbox;

import android.support.v4.app.Fragment;

public class BeatBoxActivity extends HostFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return BeatBoxFragment.newInstance();
    }

}
