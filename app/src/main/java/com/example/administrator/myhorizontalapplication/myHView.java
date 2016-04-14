package com.example.administrator.myhorizontalapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/3/30 0030.
 */
public class myHView extends HorizontalScrollView {
    private Context context;
    private int heightMeasureSpec;

    public myHView(Context context) {
        super(context);
        init(context);
    }

    private int windowWidth;
    private Paint paint;
    private Paint paintText;
    private int eachWidth;
    private float density;
    private TextView tv;

    @TargetApi(Build.VERSION_CODES.M)
    private void init(Context context) {
        this.context = context;
        density = context.getResources().getDisplayMetrics().density;
        windowWidth = context.getResources().getDisplayMetrics().widthPixels;
        eachWidth = windowWidth / 90;
        paint = new Paint();
        paintText = new Paint();
        paint.setColor(0xff000000);
        paintText.setColor(0xff000000);
        paintText.setAntiAlias(true);
        paintText.setTextSize(10f * density);
        paint.setStrokeWidth(1f * density);
    }

    public myHView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public myHView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(windowWidth * 390, heightMeasureSpec);
        this.heightMeasureSpec = heightMeasureSpec;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        Log.i("aaaaaaaa", "l:" + l + "t:" + t + "oldl:" + oldl + "oldt:" + oldt);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    private int numDays = 365 * 192;
    private String[] times = {"0", "15", "30", "45"};

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < numDays; i++) {
            if (i % 2 == 0) {
                canvas.drawLine(eachWidth * i, 0, eachWidth * i, 40, paint);
                canvas.drawLine(eachWidth * i, 0, eachWidth * i, 40, paint);
            } else {
                canvas.drawLine(eachWidth * i, 0, eachWidth * i, 20, paint);
                canvas.drawLine(eachWidth * i, 0, eachWidth * i, 20, paint);
            }
            if (i % 96 == 0) {
                canvas.drawLine(eachWidth * i, 0, eachWidth * i, 60, paint);
                canvas.drawText("第 " + (i / 96) + " 天", i * eachWidth, 80, paintText);
            }
        }
    }
}
