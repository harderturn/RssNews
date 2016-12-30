package com.burakkacar.rssnews.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.burakkacar.rssnews.Model.Haber;
import com.burakkacar.rssnews.R;

import java.util.List;

/**
 * Created by MuhammedBurak on 29.12.2016.
 */

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.MyHolder>
{
    List<Haber> mHaberList;
    Context mContext;

    public RecyclerListAdapter(Context context, List<Haber> haberList)
    {
        mHaberList = haberList;
        mContext = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
        MyHolder holder = new MyHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.doldur(mHaberList.get(position));
    }

    @Override
    public int getItemCount() {
        return mHaberList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder
    {
        TextView baslik,detay;

        public MyHolder(View itemView) {
            super(itemView);
            //Wiring up yap
            baslik = (TextView)itemView.findViewById(R.id.txtOzet);
            detay = (TextView)itemView.findViewById(R.id.txtDetay);
        }

        public void doldur(Haber haber)
        {
            //ozet.setText();
            baslik.setText(haber.getBaslik());
            detay.setText(haber.getOzet());
        }
    }
}
