package ru.mts.sanguis_client.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpAppCompatActivity;

import java.util.HashMap;
import java.util.List;

import ru.mts.sanguis_client.R;
import ru.mts.sanguis_client.ui.adapters.CalendarListAdapter;

public class StationListActivity extends MvpAppCompatActivity{

    @BindView(R.id.activity_station_list) RecyclerView rvStations;
    private CalendarListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        setContentView(R.layout.activity_station_list);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Пункты сдачи");

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        ButterKnife.bind(this);
        rvStations.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CalendarListAdapter();
        rvStations.setAdapter(adapter);

        Bundle bundle = getIntent().getExtras();
        List<HashMap<String, String>> nearbyPlaces = (List<HashMap<String, String>>) bundle.getSerializable("nearbyPlacesData");

        for(HashMap<String, String> place: nearbyPlaces) {
            Log.i("nearbyPlacesData", place.get("place_name"));
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        onBackPressed();

        return true;
    }

}
