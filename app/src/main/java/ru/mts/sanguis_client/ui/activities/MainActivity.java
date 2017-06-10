package ru.mts.sanguis_client.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import com.arellomobile.mvp.MvpAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import devlight.io.library.ntb.NavigationTabBar;
import ru.mts.sanguis_client.R;
import ru.mts.sanguis_client.ui.adapters.ActivityMainPagerAdapter;

public class MainActivity extends MvpAppCompatActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.activity_main_pager) ViewPager vpMainFragments;
    @BindView(R.id.activity_main_tabs) NavigationTabBar ntbMenu;

    private ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.setTitle("Профиль");

        ButterKnife.bind(this);
        vpMainFragments.setAdapter(new ActivityMainPagerAdapter(getSupportFragmentManager()));
        vpMainFragments.addOnPageChangeListener(this);
        vpMainFragments.setCurrentItem(2);
        vpMainFragments.setOffscreenPageLimit(3);

        int color = ContextCompat.getColor(this, R.color.colorPrimaryDark);

        List<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(new NavigationTabBar.Model.Builder(ContextCompat.getDrawable(this, R.drawable.news), color).title("Новости").build());
        models.add(new NavigationTabBar.Model.Builder(ContextCompat.getDrawable(this, R.drawable.calendar), color).title("События").build());
        models.add(new NavigationTabBar.Model.Builder(ContextCompat.getDrawable(this, R.drawable.user), color).title("Профиль").build());
        models.add(new NavigationTabBar.Model.Builder(ContextCompat.getDrawable(this, R.drawable.map), color).title("Карта").build());

        ntbMenu.setModels(models);
        ntbMenu.setViewPager(vpMainFragments, 2);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(actionBar == null){
            Log.w(getClass().getSimpleName(), "For some reason ActionBar is null");
            return;
        }
        String title;

        switch (position){
            case 2:
                title = "Профиль";
                break;
            case 3:
                title = "Карта";
                break;
            case 1:
                title = "События";
                break;
            case 0:
                title = "Новости";
                break;
            default:
                title = "";
                break;
        }

        actionBar.setTitle(title);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setHomePage(){
        vpMainFragments.setCurrentItem(2);
    }

}
