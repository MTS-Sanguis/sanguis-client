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

        events.add(new EventModel("06.06.2017", "Маша Семенова срочно нуждается в перелевании крови\n" +
                " 3-я положительная"));
        events.add(new EventModel("09.06.2017", "Москвоскому Хоспису для онкобольных требуется кровь 1-й и 2-й группы"));
        events.add(new EventModel("10.06.2017", "Городская поликлиника №32 нуждается в крови 4-й группы с отрицательным резусфактором"));
        for(int i = 0; i<5; i++){
            events.add(new EventModel("Событие" + i, "Описание события " + i));
        }
    }

}
