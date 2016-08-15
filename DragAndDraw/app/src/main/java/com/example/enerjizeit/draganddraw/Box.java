package com.example.enerjizeit.draganddraw;

import android.graphics.PointF;

public class Box {
    private PointF mOrigin;
    private PointF mCurrent;

    public Box(PointF mOrigin) {
        this.mOrigin = mOrigin;
        this.mCurrent = mOrigin;
    }

    public PointF getmCurrent() {
        return mCurrent;
    }
    public void setmCurrent(PointF mCurrent) {
        this.mCurrent = mCurrent;
    }

    public PointF getmOrigin() {
        return mOrigin;
    }
}
