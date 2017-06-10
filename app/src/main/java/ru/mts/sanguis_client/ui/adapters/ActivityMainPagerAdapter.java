package ru.mts.sanguis_client.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import ru.mts.sanguis_client.ui.fragments.CalendarFragment;
import ru.mts.sanguis_client.ui.fragments.MapFragment;
import ru.mts.sanguis_client.ui.fragments.NewsFragment;
import ru.mts.sanguis_client.ui.fragments.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

public class ActivityMainPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();

    public ActivityMainPagerAdapter(FragmentManager fm){
        super(fm);
        fragments.add(new NewsFragment());
        fragments.add(new CalendarFragment());
        fragments.add(new ProfileFragment());
        fragments.add(new MapFragment());


    }

    @Override
    public int getCount() {
        return fragments == null?0:this.fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view || (object instanceof Fragment && ((Fragment) object).getView() == view);
    }
}
