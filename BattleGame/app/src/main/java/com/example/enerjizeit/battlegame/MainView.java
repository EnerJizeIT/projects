package com.example.enerjizeit.battlegame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import org.xml.sax.helpers.AttributesImpl;

public class MainView extends SurfaceView implements SurfaceHolder.Callback {
    private MyThread myThread;
    private Activity activity;
    private Boolean dialogIsDisplayed = false;

    public static final int TARGET_PIECES = 7;
    public static final int MISS_PENALTY = 2;
    public static final int HIT_REWARD = 3;

    private boolean gameOver;
    private double timeLeft;
    private int shotsFired;
    private double totalElapsedTime;

    private Line blocker;
    private int blockerDistance;
    private int blockerBeginning;
    private int blockerEnd;
    private int initialBlockerVelocity;
    private float blockerVelocity;


    private Line target;
    private int targetDisptance;
    private int targetBeginning;
    private double pieceLenght;
    private int targetEnd;
    private int initialTargetVelocity;
    private float targetVelocity;

    private int lineWidth;
    private boolean[] hitStates;
    private int targetPiecesHit;

    private Point ball;
    private int velocityX;
    private int velocityY;
    private boolean ballOnScreen;
    private int ballRadius;
    private int ballSpeed;
    private int baseRadius;
    private int barrelsLength;
    private Point barrelEnd;
    private int screenWidth;
    private int screenHeight;

    private static final int TARGEN_SOUND_ID = 0;
    private static final int SOUND_ID = 1;
    private static final int BLOCKER_SOUND_ID = 2;
    private SoundPool soundPool;
    private SparseIntArray soundMap;

    private Paint textPaint;
    private Paint ballPaint;
    private Paint canPaint;
    private Paint blockerPaint;
    private Paint targetPaint;
    private Paint backgroundPaint;

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        activity = (Activity)context;

        getHolder().addCallback(this);

        blocker = new Line();
        target = new Line();
        ball = new Point();

        hitStates = new boolean[TARGET_PIECES];

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        soundMap = new SparseIntArray(3);
        soundMap.put(TARGEN_SOUND_ID, soundPool.load(context, R.raw.target_hit, 1));
        soundMap.put(SOUND_ID, soundPool.load(context, R.raw.cannon_fire, 1));
        soundMap.put(BLOCKER_SOUND_ID, soundPool.load(context, R.raw.blocker_hit, 1));

        textPaint = new Paint();
        canPaint = new Paint();
        ballPaint = new Paint();
        blockerPaint = new Paint();
        targetPaint = new Paint();
        backgroundPaint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        screenWidth = w;
        screenHeight = h;
        baseRadius = h/18;
        barrelsLength = w/8;

        ballRadius = w/36;
        ballSpeed = 2*3/2;

        lineWidth = w/24;

        blockerDistance = w*5/8;
        blockerBeginning = h/8;
        blockerEnd = h*3/8;
        initialBlockerVelocity = h/2;
        blocker.start = new Point(blockerDistance, blockerBeginning);
        blocker.end = new Point(blockerDistance, blockerEnd);

        targetDisptance = w*7/8;
        targetBeginning = h/8;
        targetEnd = h*7/8;
        pieceLenght = (targetEnd - targetBeginning)/TARGET_PIECES;
        initialTargetVelocity = -h/4;
        target.start = new Point(targetDisptance, targetBeginning);
        target.end = new Point(targetDisptance, targetEnd);

        barrelEnd = new Point(barrelsLength, h/2);

        textPaint.setTextSize(w/20);
        textPaint.setAntiAlias(true);
        canPaint.setStrokeWidth(lineWidth*1.5f);
        blockerPaint.setStrokeWidth(lineWidth);
        targetPaint.setStrokeWidth(lineWidth);
        backgroundPaint.setColor(Color.WHITE);

        newGame();
    }

    public void newGame(){
        for(int i=0; i<TARGET_PIECES; i++) hitStates[i] = false;

        targetPiecesHit = 0;
        blockerVelocity = initialBlockerVelocity;
        targetVelocity = initialTargetVelocity;
        timeLeft = 10;
        ballOnScreen = false;
        shotsFired = 0;
        totalElapsedTime = 0.0;

        blocker.start.set(blockerDistance, blockerBeginning);
        blocker.end.set(blockerDistance, blockerEnd);
        target.start.set(targetDisptance, targetBeginning);
        target.end.set(targetDisptance, targetEnd);

        if(gameOver){
            gameOver = false;
            myThread = new MyThread(getHolder());
            myThread.start();
        }
    }

    

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    private class MyThread extends Thread {
        private SurfaceHolder surfaceHolder;
        private boolean threadIsRunning = true;

        public MyThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;
            setName("MyThread");
        }

        public void setRunning(boolean running) {
            threadIsRunning = running;
        }

        @Override
        public void run() {
            Canvas canvas = null;
            long previosFrameTime = System.currentTimeMillis();

            while (threadIsRunning) {
                try {
                    canvas = surfaceHolder.lockCanvas(null);

                    synchronized (surfaceHolder) {
                        long currentTime = System.currentTimeMillis();
                        double elapsedTimeMS = currentTime - previosFrameTime;
                        totalElapsedTime += elapsedTimeMS / 1000.0;
                        updatePosinions(elapsedTimeMS);
                        drawGameElements(canvas);
                        previosFrameTime = currentTime;
                    }
                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }
}
