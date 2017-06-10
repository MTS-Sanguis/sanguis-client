package ru.mts.sanguis_client.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import com.arellomobile.mvp.MvpAppCompatActivity;

import java.util.HashMap;
import java.util.List;

import ru.mts.sanguis_client.R;

public class StationListActivity extends MvpAppCompatActivity{

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
