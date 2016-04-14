package com.example.administrator.myhorizontalapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/4/14 0014.
 */
public class EmptyView extends CHorizontalView {
    private Context context;

    public EmptyView(Context context) {
        super(context);
        init(context);
    }

    private int windowWidth;
    public int eachWidth;
    private float density;

    private void init(Context context) {
        this.context = context;
        density = context.getResources().getDisplayMetrics().density;
        windowWidth = context.getResources().getDisplayMetrics().widthPixels;
        eachWidth = windowWidth / 90;
        paint = new Paint();
        paint.setColor(0xffffffff);
        paint.setStrokeWidth(1f * density);
        paint.setTextSize(12f * density);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(context.getResources().getDisplayMetrics().widthPixels / 2, heightMeasureSpec);
    }

    private Paint paint;
    private int numOfOneDay = 96;//格子的数量

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < numOfOneDay; i++) {
            canvas.drawLine(eachWidth * i, 0, eachWidth * i, getMeasuredHeight() / 3, paint);
            canvas.drawLine(eachWidth * i, getMeasuredHeight() / 3 * 2, eachWidth * i, getMeasuredHeight(), paint);
        }
    }
}
