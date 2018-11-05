package com.bwie.mxy181101;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Bitmap bmCopy;
    private Canvas mCanvas;
    private ImageView iv;
    //按下坐标
    private int startX;
    private int startY;
    //移动坐标
    private int newX;
    private int newY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        iv = findViewById(R.id.iv);

        //加载原图
        Bitmap bmSrc = BitmapFactory.decodeResource(getResources(), R.drawable.bg);

        //创建白纸,设置宽高图片参数
        bmCopy = Bitmap.createBitmap(bmSrc.getWidth(), bmSrc.getHeight(), bmSrc.getConfig());

        //创建画板
        mCanvas = new Canvas(bmCopy);

        //创建画笔
        final Paint paint = new Paint();

        //在纸上画画，白纸在画板上
        mCanvas.drawBitmap(bmSrc,new Matrix(),paint);

        /*手势识别器和画笔的结合使用*/
        iv.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case  MotionEvent.ACTION_DOWN://按下时执行的逻辑
                        startX = (int)event.getX();
                        startY = (int)event.getY();
                        Log.d("mxy","startX:"+startX+"startY:"+startY);

                        break;
                    case  MotionEvent.ACTION_MOVE://移动时执行的逻辑
                        newX =(int)event.getX();
                        newY =(int)event.getY();
                        Log.d("mxy","newX:"+newX+"newY:"+newY);
                        //在背景图中画线
                        mCanvas.drawLine(startX,startY,newX,newY,paint);
                        startX = newX;
                        startY = newY;
                        iv.setImageBitmap(bmCopy);

                        break;
                    case  MotionEvent.ACTION_UP://抬起时执行的逻辑

                        break;


                }
                //咨询实战里面的事件分发机制
                return true;
            }
        });
    }
}
