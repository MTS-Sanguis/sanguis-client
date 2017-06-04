package ru.mts.sanguis_client.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpAppCompatFragment;
import ru.mts.sanguis_client.R;
import ru.mts.sanguis_client.ui.adapters.CalendarListAdapter;

public class CalendarFragment extends MvpAppCompatFragment {

    @BindView(R.id.fragment_calendar_list) RecyclerView rvList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstance){
        super.onCreateView(inflater, parent, savedInstance);
        return inflater.inflate(R.layout.fragment_calendar, null, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance){
        ButterKnife.bind(this, view);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvList.setAdapter(new CalendarListAdapter());
    }
}
