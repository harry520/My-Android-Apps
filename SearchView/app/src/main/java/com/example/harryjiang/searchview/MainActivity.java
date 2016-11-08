package com.example.harryjiang.searchview;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    String[] cNames = {"Fiji", "Finland", "France", "India", "Indonesia", "Iran", "Iraq", "South Africa", "South Korea", "United Arab Emirates", "United Kingdom", "United States of America"};
    int[] cFlags = {R.drawable.fiji, R.drawable.finland, R.drawable.france, R.drawable.india, R.drawable.indonesia, R.drawable.iran, R.drawable.iraq, R.drawable.southafrica, R.drawable.southkorea, R.drawable.unitedarabemirates, R.drawable.unitedkingdom, R.drawable.unitedstatesofamerica};
    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Country> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        int count = 0;
        for (String name : cNames) {
            arrayList.add(new Country(cFlags[count], name));
            count++;
        }
        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<Country> newList = new ArrayList<>();
        for (Country country : arrayList) {
            String name = country.getName().toLowerCase();
            if (name.contains(newText))
                newList.add(country);
        }
        adapter.setFilter(newList);
        return true;
    }
}
