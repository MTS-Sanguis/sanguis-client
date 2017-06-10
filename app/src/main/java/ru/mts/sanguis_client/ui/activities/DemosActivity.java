package ru.mts.sanguis_client.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpAppCompatActivity;
import ru.mts.sanguis_client.R;

public class DemosActivity extends MvpAppCompatActivity  implements View.OnClickListener{

    @BindView(R.id.activity_demos_login) TextView tvLoginDemo;
    @BindView(R.id.activity_demos_test) TextView tvTestDemo;

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_demos);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Слежебные демки");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        ButterKnife.bind(this);
        tvLoginDemo.setOnClickListener(this);
        tvTestDemo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.activity_demos_login:
                startActivity(new Intent(this, AuthActivity.class));
                break;
            case R.id.activity_demos_test:
                startActivity(new Intent(this, TestActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        onBackPressed();
        return true;
    }
}
