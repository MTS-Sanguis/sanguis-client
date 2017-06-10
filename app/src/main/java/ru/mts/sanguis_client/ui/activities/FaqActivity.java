package ru.mts.sanguis_client.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import com.arellomobile.mvp.MvpAppCompatActivity;
import ru.mts.sanguis_client.R;

public class FaqActivity extends MvpAppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        setContentView(R.layout.activity_faq);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Справочник");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        onBackPressed();
        return true;
    }

}
