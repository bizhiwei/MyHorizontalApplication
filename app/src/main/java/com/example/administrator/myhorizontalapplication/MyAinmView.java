package com.example.administrator.myhorizontalapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/4/1 0001.
 */
public class MyAinmView extends RelativeLayout {
    private Context context;

    public MyAinmView(Context context) {
        super(context);
        init(context);
    }

    private TextView tv1;
    private TextView tv2;

    private void init(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.anim_layout, this);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv2.setAlpha(0);
    }

    public MyAinmView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyAinmView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 交替播放的标志位
     */
    boolean flag = true;

    public void ToggleShowAdd(String text) {
        if (flag) {
            tv1ClickToleft(tv1, text);
            flag = !flag;
        } else {
            tv2ClickToleft(tv2, text);
            flag = !flag;
        }
    }

    public void ToggleShowMinus(String text) {
        if (flag) {
            tv1ClickToRight(tv1, text);
            flag = !flag;
        } else {
            tv2ClickToRight(tv2, text);
            flag = !flag;
        }
    }
private static final int DURATION = 200;
    public void tv1ClickToleft(View v, String text) {
        tv2.setText(text);
        AnimatorSet set = new AnimatorSet();
        ValueAnimator va1 = ObjectAnimator.ofFloat(tv1,
                "translationX",
                0f, -this.getWidth()
        );
        ValueAnimator va11 = ObjectAnimator.ofFloat(tv1,
                "alpha",
                1f, 0f);
        ValueAnimator va2 = ObjectAnimator.ofFloat(tv2,
                "translationX",
                this.getWidth()
                , 0);
        ValueAnimator va22 = ObjectAnimator.ofFloat(tv2,
                "alpha",
                0f, 1f);
        va1.setDuration(DURATION);
        va11.setDuration(DURATION);
        va2.setDuration(DURATION);
        va22.setDuration(DURATION);
        set.play(va1).with(va2).with(va11).with(va22);
        set.start();
    }

    public void tv1ClickToRight(View v, String text) {
        tv2.setText(text);
        AnimatorSet set = new AnimatorSet();
        ValueAnimator va1 = ObjectAnimator.ofFloat(tv1,
                "translationX",
                0f, this.getWidth()
        );
        ValueAnimator va11 = ObjectAnimator.ofFloat(tv1,
                "alpha",
                1f, 0f);
        ValueAnimator va2 = ObjectAnimator.ofFloat(tv2,
                "translationX",
                -this.getWidth()
                , 0);
        ValueAnimator va22 = ObjectAnimator.ofFloat(tv2,
                "alpha",
                0f, 1f);
        va1.setDuration(DURATION);
        va11.setDuration(DURATION);
        va2.setDuration(DURATION);
        va22.setDuration(DURATION);
        set.play(va1).with(va2).with(va11).with(va22);
        set.start();
    }

    public void tv2ClickToleft(View v, String text) {
        tv1.setText(text);
        AnimatorSet set = new AnimatorSet();
        ValueAnimator va1 = ObjectAnimator.ofFloat(tv2,
                "translationX",
                0f, -this.getWidth()
        );
        ValueAnimator va11 = ObjectAnimator.ofFloat(tv2,
                "alpha",
                1f, 0f);
        ValueAnimator va2 = ObjectAnimator.ofFloat(tv1,
                "translationX",
                this.getWidth()
                , 0);
        ValueAnimator va22 = ObjectAnimator.ofFloat(tv1,
                "alpha",
                0f, 1f);
        va1.setDuration(DURATION);
        va2.setDuration(DURATION);
        va11.setDuration(DURATION);
        va22.setDuration(DURATION);
        set.play(va1).with(va2)
                .with(va11).with(va22);
        set.start();
    }

    public void tv2ClickToRight(View v, String text) {
        tv1.setText(text);
        AnimatorSet set = new AnimatorSet();
        ValueAnimator va1 = ObjectAnimator.ofFloat(tv2,
                "translationX",
                0f, this.getWidth()
        );
        ValueAnimator va11 = ObjectAnimator.ofFloat(tv2,
                "alpha",
                1f, 0f);
        ValueAnimator va2 = ObjectAnimator.ofFloat(tv1,
                "translationX",
                -this.getWidth()
                , 0);
        ValueAnimator va22 = ObjectAnimator.ofFloat(tv1,
                "alpha",
                0f, 1f);
        va1.setDuration(DURATION);
        va2.setDuration(DURATION);
        va11.setDuration(DURATION);
        va22.setDuration(DURATION);
        set.play(va1).with(va2)
                .with(va11).with(va22);
        set.start();
    }
}
