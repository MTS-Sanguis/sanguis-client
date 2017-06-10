package ru.mts.sanguis_client.mvp.presenters;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import ru.mts.sanguis_client.common.GetNearbyPlacesData;
import ru.mts.sanguis_client.mvp.views.MapView;

@InjectViewState
public class MapPresenter extends MvpPresenter<MapView> {

    private int PROXIMITY_RADIUS = 10000;

    Context mContext;

    GoogleMap mGoogleMap;
    Marker marker;

    private LocationManager locationManager;
    private final Criteria criteria = new Criteria();

    public String bestAvailableProvider;

    public MapPresenter(Context context) {
        mContext = context;

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAltitudeRequired(true);
        criteria.setBearingRequired(true);
        criteria.setSpeedRequired(true);
        criteria.setCostAllowed(true);
    }

    public void mapLoaded(GoogleMap googleMap) {

        mGoogleMap = googleMap;

        bestAvailableProvider = locationManager.getBestProvider(criteria, true);

        Log.i("maps", "Provider: " + bestAvailableProvider);

        Log.i("maps", "Map loaded...");

        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);

    }

    public void findNearestBloodStation(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        //place marker where user just clicked
        Log.i("maps", "Placing current location marker...");
        marker = mGoogleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("Текущее положение")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));

        Log.i("maps", "Zooming in...");
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(14));

        Log.i("maps", "Getting nearest blood station...");
        String url = getUrl(location.getLatitude(), location.getLongitude(), "blood,transfusion");
        Object[] DataTransfer = new Object[2];
        DataTransfer[0] = mGoogleMap;
        DataTransfer[1] = url;
        Log.i("maps", url);
        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
        getNearbyPlacesData.execute(DataTransfer);

    }

    private String getUrl(double latitude, double longitude, String nearbyPlace) {
        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
//        googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
        googlePlacesUrl.append("&rankby=distance");
        googlePlacesUrl.append("&keyword=" + nearbyPlace);
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&language=ru");
        googlePlacesUrl.append("&key=" + "AIzaSyCxSBD3QAbaylJLHZd57N-98gVQArjMfxY");
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }

}
