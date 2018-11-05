package com.bwie.tupian;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * date:2018/11/3
 * author:mxy(M)
 * function:
 */
public class TouchMoveView extends View {
    private String TAG = "TouchMoveView";
    /**
     * the default bitmap for the TouchMoveView
     */
    private Bitmap defaultBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);

    /**
     * the width of the default bitmap
     */
    private int width = defaultBitmap.getWidth();

    /**
     * the height of the default bitmap
     */
    private int height = defaultBitmap.getHeight();

    /**
     * the x-Location of the bitmap
     */
    private float xLocation = 0;

    /**
     * the y-Location of the bitmap
     */
    private float yLocation = 0;

    public TouchMoveView(Context context) {
        this(context,null);
    }

    public TouchMoveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TouchMoveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(defaultBitmap, xLocation, yLocation, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                if (xLocation <= x && x <= xLocation + width && yLocation <= y && y <= yLocation + height) {
                    //continue
                } else {
                    return false;//end the event
                }
                break;
            case MotionEvent.ACTION_MOVE:
                xLocation = event.getX();
                yLocation =  event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }

    public Bitmap getDefaultBitmap() {
        return defaultBitmap;
    }

    public void setDefaultBitmap(Bitmap defaultBitmap) {
        this.defaultBitmap = defaultBitmap;
        //update the width and the height of the default bitmap
        width = defaultBitmap.getWidth();
        height = defaultBitmap.getHeight();
    }

    public float getxLocation() {
        return xLocation;
    }

    /**
     * set the initialized X-Location
     */
    public void setxLocation(float xLocation) {
        this.xLocation = xLocation;
    }

    public float getyLocation() {
        return yLocation;
    }

    /**
     * set the initialized y-Location
     */
    public void setyLocation(float yLocation) {
        this.yLocation = yLocation;
    }

}
