package com.example.administrator.myhorizontalapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/3/31 0031.
 */
public class CHorizontalView extends View {
    private Context context;
    private int heightMeasureSpec;

    public CHorizontalView(Context context) {
        super(context);
        init(context );
    }

    private int windowWidth;
    private Paint paint;
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

    public CHorizontalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CHorizontalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            setMeasuredDimension(eachWidth * 192, heightMeasureSpec);
            this.heightMeasureSpec = heightMeasureSpec;
    }

    private int numOfOneDay = 192;//格子的数量

    @Override
    protected void onDraw(Canvas canvas) {
            for (int i = 0; i < numOfOneDay; i++) {
                canvas.drawLine(eachWidth * i, 0, eachWidth * i, getMeasuredHeight() / 3, paint);
                canvas.drawLine(eachWidth * i, getMeasuredHeight() / 3 * 2, eachWidth * i, getMeasuredHeight(), paint);
            }
            canvas.drawText("凌晨", 0, getMeasuredHeight() / 3 * 2 - 6 * density, paint);
            canvas.drawText("上午", eachWidth * 48, getMeasuredHeight() / 3 * 2 - 6 * density, paint);
            canvas.drawText("下午", eachWidth * 96, getMeasuredHeight() / 3 * 2 - 6 * density, paint);
            canvas.drawText("晚上", eachWidth * 144, getMeasuredHeight() / 3 * 2 - 6 * density, paint);
        }
}
