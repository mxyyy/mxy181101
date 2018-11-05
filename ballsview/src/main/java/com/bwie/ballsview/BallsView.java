package com.bwie.ballsview;

import android.content.Context;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * date:2018/11/1
 * author:mxy(M)
 * function:自绘式的成员之一
 * 牢记三个方法：onMeasure():测量，该方法检测View组件及他所包含的子组件的大小
 *               onLayout()：layout:位置，当该组件要分配子组件所在位置时调用（基本不用）
 *               onDraw()：绘制，当该组件要绘制他的内容时，回调该方法（使用频率最高，注意不要再onMeasure里做耗时操作和创建对象的操作）
 *
 *   需求：创建一个小球，手按住小球，进行滑动，小球就跟着我的手移动，当我手松开，在空白的地方滑动，小球就不在动了
 *   实现思路：
 *   1.创建一个类，继承View（就从普通类变成一个控件）
 *   2.覆写必须要覆写的三个构造方法,初始化画笔
 *   3.在onMeasure，获取屏幕和控件的宽高，让小球居于屏幕中间的位置
 *   4.在onMeasure中绘制小球
 *   5,触摸事件的监听中，根据用户手指滑动，让小球在对应的坐标重新绘制postInvalidate()
 *   有一个Bug,你会出现当你没有按住小球，在空白的地方滑动，小球依然会跟着动
 *
 *   画一个自定义控件：要有画纸（BallsView），画板(Draw)，画笔()
 */
public class BallsView extends View {

    private int mHeight;
    private int mWidth;
    private int Y;
    private int X;
    private Paint mPaint;
    private boolean mOnBall;

    //这三个方法是让你做初始化的业务逻辑
    //使用控件的时候,在屏幕上显示,有两种方法,一种在XML里去定义控件,一种在Java里面使用控件
    //代码中使用自定义控件（new BallsView）,自动回调此方法
    public BallsView(Context context) {
        this(context, null);

    }

    //XML布局使用此自定义控件，自动回调此方法
    public BallsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //XML布局中使用此自定义控件，且带有样式时，自动回调
    public BallsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    //自定义控件做个初始化的操作
    private void initView() {
        mPaint = new Paint();
    }


    //测试里

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取当前控件的宽高
        mHeight = this.getHeight();
        mWidth = this.getWidth();
        //获取屏幕正中心点
        Y = mHeight / 2;
        X = mWidth / 2;
    }

    private int mRadius = 50;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画笔颜色
        mPaint.setColor(Color.GRAY);
        //画圆
        canvas.drawCircle(X, Y, mRadius, mPaint);

    }

    //手势监听器，可以得到用户手指在屏幕上滑动的坐标
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //二者区别,getX得到的是以自定义控件左上角为原点的X轴坐标,getRawX得到的是以屏幕左上角为原点的X轴坐标
                int downX = (int) event.getX();
                int downY = (int) event.getY();
//                event.getRawX();

                //进行判断,判断用户的手指是否按在了园内
                mOnBall = isOnBall(downX, downY);

                Toast.makeText(getContext(), "用户的手指是否点到圆内了" + mOnBall, Toast.LENGTH_SHORT).show();

                break;
            case MotionEvent.ACTION_MOVE:
                //只用用户点到圆内时,我才让圆动
                if (mOnBall) {
                    X = (int) event.getX();
                    Y = (int) event.getY();
                    //回调OnDrawer方法
                    postInvalidate();
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }

    //,判断用户的手指是否按在了圆内
    private boolean isOnBall(int downX, int downY) {
        //勾股定理的绝对值
        double sqrt = Math.sqrt((downX - X) * (downX - X) + (downY - Y) * (downY - Y));
        //判断绝对值是否小于等于半径
        if (sqrt <= mRadius) {
            return true;
        }

        return false;
    }
}
