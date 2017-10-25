package com.bwie.test.view.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * http://blog.csdn.net/scott2017/article/details/52314220
 * 启动页，属性动画
 */

public class MyView extends View {
    private Paint mPaint;
    private Point mPoint;
    private int radius = 60;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private Animator.AnimatorListener animatorListener;
    private ValueAnimator animator;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }
    public MyView(Context context) {
        super(context);
        initPaint();
    }

    private void initPaint() {
        //初始化画笔
        mPaint = new Paint();
        //mPaint.setColor(0xFFF00000);
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true); // 抗锯齿
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        //计算开始和结束位置
        this.startX = left + radius;
        this.startY = top + radius;
        this.endX = right - radius;
        this.endY = bottom - radius;
        //Log.i("xxyy", startX+","+startY+","+endX+","+endY);

        if(animator==null){
            //开始动画
            start();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mPoint.x, mPoint.y, radius, mPaint);
    }

    //开始属性动画
    private ValueAnimator start() {
        animator = ValueAnimator.ofObject(new PointEvaluator(),
                  new Point(startX, startY), new Point(endX, endY));
        //animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        if(animatorListener!=null){
            animator.addListener(animatorListener);
        }
//        final ValueAnimator animator1 = ValueAnimator.ofArgb(0xFFF00000,0xFFFFFF00);
//        animator1.setRepeatCount(ValueAnimator.INFINITE);
//        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                mColor = (int) animation.getAnimatedValue();
//                mPaint.setColor(mColor);
//            }
//        });
        AnimatorSet animationSet = new AnimatorSet();
        animationSet.setDuration(3000);
//        animationSet.setInterpolator(new LgDecelerateInterpolator());
//        animationSet.play(animator).with(animator1);
        animationSet.play(animator);
        animationSet.start();
        return animator;
    }

    public void addAnimationListener(Animator.AnimatorListener animatorListener) {
        this.animatorListener = animatorListener;
    }

    class PointEvaluator implements TypeEvaluator {
        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            Point startPoint = (Point) startValue;
            Point endPoint = (Point) endValue;
            int x = (int) (startPoint.x + fraction * (endPoint.x - startPoint.x));
            int y = (int) (startPoint.y + fraction * (endPoint.y - startPoint.y));
            return new Point(x, y);
        }
    }
//    class LgDecelerateInterpolator implements TimeInterpolator {
//        private float background;
//        public LgDecelerateInterpolator() {
//            background = 10;
//        }
//        @Override
//        public float getInterpolation(float input) {
//            return (1 - (float) Math.pow(background, -input));
//        }
//    }
}