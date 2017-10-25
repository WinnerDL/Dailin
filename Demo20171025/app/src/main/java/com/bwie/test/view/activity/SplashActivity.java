package com.bwie.test.view.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;

import com.bwie.test.R;
import com.bwie.test.view.widget.MyView;

//启动页
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final MyView myview = (MyView) findViewById(R.id.myview);
        myview.addAnimationListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //动画结束，跳转到主页面
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
