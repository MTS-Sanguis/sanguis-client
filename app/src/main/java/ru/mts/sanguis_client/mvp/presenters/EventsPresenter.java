package ru.mts.sanguis_client.mvp.presenters;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import ru.mts.sanguis_client.mvp.models.EventModel;
import ru.mts.sanguis_client.mvp.views.EventsView;

import java.util.ArrayList;

@InjectViewState
public class EventsPresenter extends MvpPresenter<EventsView> {

    private ArrayList<EventModel> events;

    public void getEventsForDay(){
        loadEvents();
        getViewState().setEvents(events);
    }

    private void loadEvents(){
        events = new ArrayList<>();

        for(int i = 0; i<10; i++){
            events.add(new EventModel("Событие" + i, "Описание события " + i));
        }
    }

}
