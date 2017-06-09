package ru.mts.sanguis_client.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.android.gms.maps.GoogleMap;
import ru.mts.sanguis_client.mvp.views.MapView;

@InjectViewState
public class MapPresenter extends MvpPresenter<MapView> {

    public void mapLoaded(GoogleMap map){

    }

}
