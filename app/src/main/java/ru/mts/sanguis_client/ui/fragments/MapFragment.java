package ru.mts.sanguis_client.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import ru.mts.sanguis_client.R;

public class MapFragment extends MvpAppCompatFragment implements OnMapReadyCallback {

    private FrameLayout sFL;
    private GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstance){
        super.onCreateView(inflater, parent, savedInstance);

        this.sFL = new FrameLayout(getActivity());
        return this.sFL;
    }

    public void onViewCreated(View view, Bundle savedInstance){
        super.onViewCreated(view, savedInstance);
        ButterKnife.bind(this, view);

        SupportMapFragment map = SupportMapFragment.newInstance();
        this.sFL.setId(0x148813);
        getChildFragmentManager().beginTransaction().replace(this.sFL.getId(), map).commit();

        map.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        //здесь карта впервые появляется.
    }
}
