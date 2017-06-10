package ru.mts.sanguis_client.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpAppCompatFragment;
import ru.mts.sanguis_client.R;
import ru.mts.sanguis_client.mvp.models.EventModel;
import ru.mts.sanguis_client.mvp.views.EventsView;
import ru.mts.sanguis_client.ui.adapters.CalendarListAdapter;

import java.util.ArrayList;
import java.util.Date;

public class CalendarFragment extends MvpAppCompatFragment implements EventsView {

    @BindView(R.id.fragment_calendar_calendar) CalendarView calendarView;
    @BindView(R.id.fragment_calendar_list) RecyclerView rvList;

    private CalendarListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstance){
        super.onCreateView(inflater, parent, savedInstance);
        return inflater.inflate(R.layout.fragment_calendar, null, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance){
        ButterKnife.bind(this, view);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CalendarListAdapter();
        rvList.setAdapter(adapter);
        calendarView.setFirstDayOfWeek(2);

    }

    @Override
    public void setEvents(ArrayList<EventModel> events) {
        if(adapter != null)
            adapter.setEvents(events);
    }
}
