package ru.mts.sanguis_client.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import ru.mts.sanguis_client.R;
import ru.mts.sanguis_client.mvp.models.EventModel;
import ru.mts.sanguis_client.mvp.presenters.EventsPresenter;
import ru.mts.sanguis_client.mvp.views.EventsView;
import ru.mts.sanguis_client.ui.adapters.CalendarListAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalendarFragment extends MvpAppCompatFragment implements EventsView {

    @BindView(R.id.fragment_calendar_calendar)
    CompactCalendarView calendarView;
    @BindView(R.id.fragment_calendar_events)
    RecyclerView rvEvents;

    @InjectPresenter(type = PresenterType.GLOBAL, tag = "EventsPresenter")
    EventsPresenter presenter;

    private CalendarListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstance){
        super.onCreateView(inflater, parent, savedInstance);
        return inflater.inflate(R.layout.fragment_calendar, null, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstance){
        ButterKnife.bind(this,view);

        adapter = new CalendarListAdapter();
        rvEvents.setLayoutManager(new LinearLayoutManager(getContext()));
        rvEvents.setAdapter(adapter);
        rvEvents.setNestedScrollingEnabled(false);


        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {

            @Override
            public void onDayClick(Date dateClicked) {

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

            }
        });

        presenter.getEventsForDay();

    }

    private void addEvent(String dateString, Integer color, String title) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(date != null){
            Log.d("event", "Adding event: " + title);
            calendarView.addEvent(new Event(color, date.getTime(), title), true);
        }
    }

    @Override
    public void setEvents(ArrayList<EventModel> events) {
        if (adapter != null)
            adapter.setEvents(events);

        for (EventModel event: events) {
            addEvent(event.getTitle(), Color.WHITE, event.getSubtitle());
        }
    }
}
