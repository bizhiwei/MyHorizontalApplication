package com.example.administrator.myhorizontalapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by Administrator on 2016/4/13 0013.
 */
public class HorRecyclerView extends RecyclerView {

    private Context context;
    private int windowWidth;

    public HorRecyclerView(Context context) {
        super(context);
        init(context);
    }

    private Paint paint;
    private Paint paintRect;
    private float density;
    private static final int PAINTCOLOR = 0xffffffff;
    private static final int PAINTRECTCOLOR = 0x88ffffff;

    private void init(Context context) {
        this.context = context;
        windowWidth = context.getResources().getDisplayMetrics().widthPixels;
        left = windowWidth / 2;
        density = context.getResources().getDisplayMetrics().density;
        paint = new Paint();
        paintRect = new Paint();
        paint.setColor(PAINTCOLOR);
        paintRect.setColor(PAINTRECTCOLOR);
        paint.setStrokeWidth(1f * density);
    }

    public HorRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HorRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private float left;

    public boolean isDrawWhiteShadow() {
        return drawWhiteShadow;
    }

    public void setDrawWhiteShadow(boolean drawWhiteShadow) {
        this.drawWhiteShadow = drawWhiteShadow;
    }

    private boolean drawWhiteShadow = false;

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.drawLine(getMeasuredWidth() / 2, 0, getMeasuredWidth() / 2, getMeasuredHeight(), paint);
        if (drawWhiteShadow) {
            canvas.drawRect(left, 0, getMeasuredWidth() / 2, getMeasuredHeight(), paintRect);
        }
    }

    public void resetEndShadowLeft() {
        this.left = windowWidth / 2;
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        if (drawWhiteShadow) {
            left -= dx;
        }
    }
}
