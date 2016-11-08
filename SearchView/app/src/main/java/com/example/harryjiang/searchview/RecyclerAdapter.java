package com.example.harryjiang.searchview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    ArrayList<Country> arrayList = new ArrayList<>();

    RecyclerAdapter(ArrayList<Country> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.cFlag.setImageResource(arrayList.get(position).getFlagId());
        holder.cName.setText(arrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView cFlag;
        TextView cName;

        public MyViewHolder(View itemView) {
            super(itemView);
            cFlag = (ImageView) itemView.findViewById(R.id.flag);
            cName = (TextView) itemView.findViewById(R.id.name);
        }
    }

    public void setFilter(ArrayList<Country> newList) {
        arrayList = new ArrayList<>();
        arrayList.addAll(newList);
        notifyDataSetChanged();
    }
}
