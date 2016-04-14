package com.example.administrator.myhorizontalapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/3/31 0031.
 */
public class CRecyclerAdapter extends RecyclerView.Adapter<CRecyclerAdapter.MyHolder> {
    private final List<View> list;
    private final Context context;

    public CRecyclerAdapter(Context context, List<View> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 0 : 1;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if(viewType==0){
            v = LayoutInflater.from(context).inflate(R.layout.item2, null);

        }else{
            v = LayoutInflater.from(context).inflate(R.layout.item, null);
        }
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.v = list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public View v;

        public MyHolder(View itemView) {
            super(itemView);
            v = itemView.findViewById(R.id.tv);
        }
    }
}
