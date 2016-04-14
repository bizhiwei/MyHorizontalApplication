package com.example.administrator.myhorizontalapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/4/14 0014.
 */
public class jiantouView extends View {
    private Context context;

    public jiantouView(Context context) {
        super(context);
        init(context);
    }

    private Paint paint;
    float density;
    private static final float STROKEWIDTH = 1f;
    private static final int COLOROFPAINT = 0xff000000;

    private void init(Context context) {
        this.context = context;
        density = context.getResources().getDisplayMetrics().density;
        paint.setStrokeWidth(STROKEWIDTH * density);
        paint.setColor(COLOROFPAINT);
        paint.setAntiAlias(true);
    }

    public jiantouView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public jiantouView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setSinParameter(float degree) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
//画一条竖线
        canvas.drawLine(
                //得到控件本身宽度的一半减去线条宽度的一半
                getMeasuredWidth() / 2 - STROKEWIDTH * density / 2,
                //y起始值为0
                0,
                //得到控件本身宽度的一半加上线条宽度的一半
                getMeasuredWidth() / 2 - STROKEWIDTH * density / 2,
                //设置控件本身高度的2/3
                getMeasuredHeight() / 3 * 2,
                paint);
//画路径
        Path path = new Path();
        path.moveTo(getMeasuredWidth() / 2 - STROKEWIDTH * density / 2, 0);
        path.moveTo(getMeasuredWidth(), getMeasuredHeight() / 4);//最右边
        path.moveTo(0, getMeasuredHeight() / 4);
        path.moveTo(getMeasuredWidth(), getMeasuredHeight() / 2);
        path.moveTo(0, getMeasuredHeight() / 2);
        path.moveTo(getMeasuredWidth(), getMeasuredHeight() / 4 * 3);
        path.moveTo(getMeasuredWidth() / 2 - STROKEWIDTH * density / 2, getMeasuredHeight());
        canvas.drawPath(path, paint);
    }

}
