package com.example.harryjiang.slidingmenudemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harryjiang.slidingmenudemo.R;
import com.example.harryjiang.slidingmenudemo.model.ItemSlidingMenu;


import java.util.List;

public class SlidingMenuAdapter extends BaseAdapter {

    private Context context;
    private List<ItemSlidingMenu> listItem;

    public SlidingMenuAdapter(Context context, List<ItemSlidingMenu> listItem) {
        this.context = context;
        this.listItem = listItem;
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int position) {
        return listItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, R.layout.item_sliding_menu, null);
        ImageView img = (ImageView) v.findViewById(R.id.item_img);
        TextView tv = (TextView) v.findViewById(R.id.item_title);
        ItemSlidingMenu item = listItem.get(position);
        img.setImageResource(item.getImgId());
        tv.setText(item.getTitle());
        return v;
    }
}
