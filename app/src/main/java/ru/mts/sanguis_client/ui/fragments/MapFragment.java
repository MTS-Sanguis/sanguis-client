package ru.mts.sanguis_client.ui.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import ru.mts.sanguis_client.R;
import ru.mts.sanguis_client.mvp.presenters.MapPresenter;
import ru.mts.sanguis_client.mvp.views.MapView;
import ru.mts.sanguis_client.ui.activities.MainActivity;
import ru.mts.sanguis_client.ui.activities.StationListActivity;

import java.io.Serializable;


public class MapFragment extends MvpAppCompatFragment implements OnMapReadyCallback, MapView, View.OnClickListener{

    @InjectPresenter
    MapPresenter presenter;

    @ProvidePresenter
    MapPresenter provideMapPresenter() {
        return new MapPresenter(this.getContext());
    }

    @BindView(R.id.fragment_map_map) FrameLayout flMap;
    @BindView(R.id.fragment_map_additional_info) LinearLayout llClincInfo;
    @BindView(R.id.fragment_map_main_text) TextView tvTitle;
    @BindView(R.id.fragment_map_additional_text) TextView tvAdditionalText;
    @BindView(R.id.fragment_map_search) RelativeLayout rvSearchBtn;
    @BindView(R.id.fragment_map_to_list) RelativeLayout rvListBtn;
    @BindView(R.id.fragment_map_search_input) EditText searchInput;
    @BindView(R.id.fragment_map_back) RelativeLayout rlBackButton;

    private GoogleMap mGoogleMap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstance) {
        super.onCreateView(inflater, parent, savedInstance);
        return inflater.inflate(R.layout.fragment_map, parent, false);

    }

    public void onViewCreated(View view, Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        ButterKnife.bind(this, view);
        rvSearchBtn.setOnClickListener(this);
        rvListBtn.setOnClickListener(this);
        rlBackButton.setOnClickListener(this);
        SupportMapFragment map = SupportMapFragment.newInstance();
        getChildFragmentManager().beginTransaction().replace(flMap.getId(), map).commit();
        map.getMapAsync(this);

        llClincInfo.setOnClickListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        checkLocationPermission();
        mGoogleMap.setMyLocationEnabled(true);

        //здесь карта впервые появляется.
        presenter.mapLoaded(mGoogleMap, getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    public void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)  {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(getActivity())
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MapPresenter.MY_PERMISSIONS_REQUEST_LOCATION );
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MapPresenter.MY_PERMISSIONS_REQUEST_LOCATION );
            }
        }
    }

    @Override
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void setAdditionalTitle(String title) {
        tvAdditionalText.setText(title);
    }

    @Override
    public void showClinicInfo() {
        llClincInfo.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        presenter.onRequestPermissionsResult(requestCode, permissions, grantResults, getActivity(), getContext());
    }



    @Override
    public void setClinicInfo(String title, String info) {
        llClincInfo.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.fragment_map_search:
                presenter.stationSearch(searchInput.getText());
                break;
            case R.id.fragment_map_to_list:
                Intent intent = new Intent(getContext(), StationListActivity.class);
                intent.putExtra("nearbyPlacesData", (Serializable) presenter.getNearbyPlacesData.nearbyPlacesList);
                startActivity(intent);
                break;
            case R.id.fragment_map_back:
                Activity activity = getActivity();
                if(activity instanceof MainActivity)
                    ((MainActivity) activity).setHomePage();
                break;
            case R.id.fragment_map_additional_info:
                v.setVisibility(View.GONE);
            default:
                break;
        }
    }
}
