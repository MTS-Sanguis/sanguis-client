package ru.mts.sanguis_client.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import ru.mts.sanguis_client.mvp.models.EventModel;

import java.util.ArrayList;

@StateStrategyType(SingleStateStrategy.class)
public interface EventsView extends MvpView {

    void setEvents(ArrayList<EventModel> events);

}
