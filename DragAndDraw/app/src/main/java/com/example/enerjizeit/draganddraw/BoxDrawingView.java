package com.example.enerjizeit.draganddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BoxDrawingView extends View {
    private static final String TAG = "BoxDrawingView";
    private Box mCurrentBox;
    private List<Box> mBoxes = new ArrayList<>();
    private Paint mBoxPaint;
    private Paint mBackgroundPaint;

    /* Используется при создании представления в коде */
    public BoxDrawingView(Context context) {
        this(context, null);
    }

    /* Используется при создании представления по разметке XML */
    public BoxDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mBoxPaint = new Paint();
        mBoxPaint.setColor(getResources().getColor(R.color.colorPrimary));

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(getResources().getColor(R.color.colorAccent));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /* Заполнение фона */
        canvas.drawPaint(mBackgroundPaint);

        for(Box box:mBoxes){
            float left = Math.min(box.getmOrigin().x, box.getmCurrent().x);
            float right = Math.max(box.getmOrigin().x, box.getmCurrent().x);
            float top = Math.min(box.getmOrigin().y, box.getmCurrent().y);
            float bottom = Math.max(box.getmOrigin().y, box.getmCurrent().y);

            canvas.drawRect(left, top, right, bottom, mBoxPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF pointF = new PointF(event.getX(), event.getY());
        String action = "";
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                action = "Action Down";
                /* Сброс текущего состояния */
                mCurrentBox = new Box(pointF);
                mBoxes.add(mCurrentBox);
                break;
            case MotionEvent.ACTION_MOVE:
                action = "Action move";
                if(mCurrentBox != null){
                    mCurrentBox.setmCurrent(pointF);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                action = "Action Up";
                mCurrentBox = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                action = "Action Cancel";
                mCurrentBox = null;
                break;
        }

        Log.i(TAG, action + " at x=" + pointF.x + ", y=" + pointF.y);
        return true;
    }


}
