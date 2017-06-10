package ru.mts.sanguis_client.mvp.views;

import butterknife.BindView;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface MapView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void setClinicInfo(String title, String info);

    @StateStrategyType(SkipStrategy.class)
    void checkLocationPermission();

}
