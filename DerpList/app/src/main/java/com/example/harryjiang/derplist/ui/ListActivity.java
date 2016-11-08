package com.example.harryjiang.derplist.ui;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.transition.Fade;
import android.view.View;
import android.widget.Button;

import com.example.harryjiang.derplist.R;
import com.example.harryjiang.derplist.adapter.DerpAdapter;
import com.example.harryjiang.derplist.model.DerpData;
import com.example.harryjiang.derplist.model.ListItem;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements DerpAdapter.ItemClickCallback {

    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_QUOTE = "EXTRA_QUOTE";
    private static final String EXTRA_ATTR = "EXTRA_ATTR";
    private static final String EXTRA_IMAGE_RES_ID = "EXTRA_IMAGE_RES_ID";
    private RecyclerView recyclerView;
    private DerpAdapter adapter;
    private ArrayList listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listData = (ArrayList) DerpData.getListData();
        recyclerView = (RecyclerView) findViewById(R.id.rec_list);
        //LayoutManager: GridLayoutManager or StaggeredGridLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DerpAdapter(listData, this);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickCallback(this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);
        Button addItem = (Button) findViewById(R.id.btn_add_item);
        addItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addItemToList();
            }
        });
    }

    private ItemTouchHelper.Callback createHelperCallback() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        moveItem(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                        return true;
                    }

                    @Override
                    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        deleteItem(viewHolder.getAdapterPosition());
                    }
                };
        return simpleItemTouchCallback;
    }

    private void addItemToList() {
        ListItem item = DerpData.getRandomListItem();
        listData.add(item);
        adapter.notifyItemInserted(listData.indexOf(item));
    }

    private void moveItem(int oldPos, int newPos) {

        ListItem item = (ListItem) listData.get(oldPos);
        listData.remove(oldPos);
        listData.add(newPos, item);
        adapter.notifyItemMoved(oldPos, newPos);
    }

    private void deleteItem(final int position) {
        listData.remove(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void onItemClick(View v, int p) {
        ListItem item = (ListItem) listData.get(p);
        Intent i = new Intent(this, DetailActivity.class);
        Bundle extras = new Bundle();
        extras.putString(EXTRA_QUOTE, item.getTitle());
        extras.putString(EXTRA_ATTR, item.getSubTitle());
        extras.putInt(EXTRA_IMAGE_RES_ID, item.getImageResId());
        i.putExtra(BUNDLE_EXTRAS, extras);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Fade(Fade.IN));
            getWindow().setExitTransition(new Fade(Fade.OUT));
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this,
                    new Pair<View, String>(v.findViewById(R.id.im_item_icon),
                            getString(R.string.transition_image)),
                    new Pair<View, String>(v.findViewById(R.id.lbl_item_text),
                            getString(R.string.transition_quote)),
                    new Pair<View, String>(v.findViewById(R.id.lbl_item_sub_title),
                            getString(R.string.transition_attribution))
            );
            ActivityCompat.startActivity(this, i, options.toBundle());
        } else {
            startActivity(i);
        }
    }

    @Override
    public void onSecondaryIconClick(int p) {
        ListItem item = (ListItem) listData.get(p);
        //Update our data
        if (item.isFavorite())
            item.setFavorite(false);
        else
            item.setFavorite(true);
        // Pass new data to the adapter and update
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();
    }
}
