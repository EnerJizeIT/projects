package it.enerjize.zoomtouch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    private ImageView image;
    private Bitmap bitMap;

    private int width, height;
    private double deltaPointerDown, deltaPointerMove;
    private boolean pinch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView)findViewById(R.id.skill);

        bitMap = BitmapFactory.decodeResource(getResources(),R.drawable.skill);

        width = bitMap.getWidth();
        height = bitMap.getHeight();

        deltaPointerDown = 1;
        deltaPointerMove = 1;
        setScaledBitmap();

        image.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float deltaX, deltaY;
        switch(event.getAction()& MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                pinch = false;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                pinch = true;
                deltaX = event.getX(0) - event.getX(1);
                deltaY = event.getY(0) - event.getY(1);
                deltaPointerDown = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                break;
            case MotionEvent.ACTION_MOVE:
                if (pinch) {
                    deltaX = event.getX(0) - event.getX(1);
                    deltaY = event.getY(0) - event.getY(1);
                    deltaPointerMove = Math.sqrt(
                            deltaX * deltaX + deltaY * deltaY);
                    setScaledBitmap();
                }
                break;
            case MotionEvent.ACTION_UP:
                pinch = false;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                pinch = false;
                break;
        }
        return true;
    }

    private void setScaledBitmap() {
        double curScale = deltaPointerMove / deltaPointerDown;
        int newHeight = (int) (height * curScale);
        int newWidth = (int) (width * curScale);
        // Изменяем размер изображения
        Bitmap bmp = Bitmap.createScaledBitmap(bitMap, newWidth, newHeight, false);
        image.setImageBitmap(bmp);

    }
}
