package com.example.administrator.myhorizontalapplication;

import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private HorRecyclerView rv;
    private ArrayList<View> list;
    private MyAinmView tv_title;
    private TextView tv_time_start;
    private TextView tv_time_end;
    private static final String[] week = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    int xxx = 0;
    private int tottleWidth = 0;
    int widthPixels;
    int eachWidth;
    int viewWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();
        initParameter();
        initView();
        mSetScrollListener();//给recyclerview添加scroll监听
    }

    private void mSetScrollListener() {
        rv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            AudioManager mAudioMgr;
            int oldDay = 0;
            int minsTottle = 0;//显示用的分钟数
            int tottleMins;//总分钟数
            int currentDay;//总距离除以当前距离得到第几天
            double timesOf15Mins;//15分钟的倍数，eachWidth * 2,2个小格为15分钟,dx/15分钟

            {
                mAudioMgr = (AudioManager) MainActivity.this.getSystemService(MainActivity.this.AUDIO_SERVICE);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                initThisParameter(dx);//初始化相关参数
                ifMinsChanged();//分钟数发生变化
                ifDayChanged();//天数发生变化
            }

            public void ifDayChanged() {
                if (oldDay != currentDay) {//如果天数有变化
                    if (currentDay > oldDay) {//如果是向前划动
                        tv_title.ToggleShowAdd(week[xxx++ % 7]);
                    } else {//如果是向后划动
                        tv_title.ToggleShowMinus(week[6 - (xxx++ % 7)]);
                    }
                    oldDay = currentDay;//更新设置当前天
                }
            }

            /**
             * 分钟数发生变化
             */
            public void ifMinsChanged() {
                if (minsTottle != tottleMins) {
                    minsTottle = tottleMins;
                    tv_time_start.setText(getTime());//一天共1440分钟，一个循环
                    tv_time_end.setText((getTimeAddOne()));
//                    playSystemVioce();//播放系统音效
                }
            }

            /**
             * 初始化滑动的总距离，
             * @param dx
             */
            private void initThisParameter(int dx) {
                tottleWidth += dx;//算出当前所走的总距离
                timesOf15Mins = tottleWidth / (eachWidth * 2);//得到有多少个15分钟
                tottleMins = (int) (timesOf15Mins * 15);//和15分钟相乘得到一共多少分钟
                currentDay = tottleWidth / viewWidth;//得到现在相对以第一天是第几天
            }

            private String getTimeMin() {
                if ((getHour()) % 60 == 0) {
                    return "0" + (getHour()) % 60;
                } else {
                    return "" + (getHour()) % 60;
                }
            }

            private String getTime() {
                if ((getHour()) / 60 > 9) {
                    return "" + (((getHour()) / 60) > 11 ?
                            ((getHour()) / 60) + ":" + getTimeMin() + "pm" : ((getHour()) / 60)
                            + ":" + getTimeMin() + "am");
                } else {
                    return "0" + (((getHour()) / 60) > 11 ?
                            ((getHour()) / 60) + ":" + getTimeMin() + "pm" : ((getHour()) / 60)
                            + ":" + getTimeMin() + "am");
                }
            }

            private String getTimeAddOne() {
                if (getHour() / 60 + 1 > 9) {
                    return "" + (((getHour()) / 60 + 1) > 11 ?
                            ((getHour()) / 60 + 1) + ":" + getTimeMin() + "pm" : ((getHour()) / 60 + 1)
                            + ":" + getTimeMin() + "am");
                } else {
                    return "0" + (((getHour()) / 60 + 1) > 11 ?
                            ((getHour()) / 60 + 1) + ":" + getTimeMin() + "pm" : ((getHour()) / 60 + 1)
                            + ":" + getTimeMin() + "am");
                }
            }

            private int getHour() {
                return minsTottle % 1440;
            }

            private void playSystemVioce() {
                mAudioMgr.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD, 2);
            }
        });
    }

    /**
     * 初始化窗口宽度，每个小格宽度，代表每一天的控件的宽度
     */
    private void initParameter() {
        widthPixels = this.getResources().getDisplayMetrics().widthPixels;
        eachWidth = widthPixels / 90;
        viewWidth = (widthPixels / 90) * 192;
    }

    private void initView() {
        rv = (HorRecyclerView) findViewById(R.id.rv);
        tv_title = (MyAinmView) findViewById(R.id.tv_title);
        tv_time_start = (TextView) findViewById(R.id.tv_time_start);
        tv_time_start.setOnClickListener(this);
        tv_time_end = (TextView) findViewById(R.id.tv_time_end);
        tv_time_end.setOnClickListener(this);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv.setAdapter(new CRecyclerAdapter(this, list));
    }

    /**
     * 初始化365个代表一天的控件
     */
    private void initList() {
        this.list = new ArrayList<>();
        list.add(new EmptyView(this));
        for (int i = 0; i < 3; i++) {
            list.add(new CHorizontalView(this));
        }
        Log.i("aaaaaaaa","list.size"+list.size());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_time_start://选择开始时间
                chooseTimeStart();
                break;
            case R.id.tv_time_end://选择结束时间
                chooseTimeEnd();
                break;
        }
    }


    /**
     * 选择开始时间
     */
    private void chooseTimeStart() {
        rv.setDrawWhiteShadow(false);
        rv.invalidate();
    }

    /**
     * 选择结束时间
     */
    private void chooseTimeEnd() {
        rv.resetEndShadowLeft();
        rv.setDrawWhiteShadow(true);
        rv.smoothScrollBy(eachWidth * 8, 0);
    }
}
