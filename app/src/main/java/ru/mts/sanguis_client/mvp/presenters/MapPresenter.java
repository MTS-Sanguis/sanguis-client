package ru.mts.sanguis_client.mvp.presenters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import android.view.View;
import android.widget.Toast;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import ru.mts.sanguis_client.common.GetNearbyPlacesData;
import ru.mts.sanguis_client.mvp.views.MapView;

@InjectViewState
public class MapPresenter extends MvpPresenter<MapView> implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    Context mContext;
    private GoogleApiClient mGoogleApiClient;

    GoogleMap mGoogleMap;//FIXME потенциальная утечка памяти
    Marker currentMarker;
    Location location;

    private LocationManager locationManager;
    private final Criteria criteria = new Criteria();

    public String bestAvailableProvider;
    public GetNearbyPlacesData getNearbyPlacesData;

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

    public void mapLoaded(GoogleMap googleMap, final Activity activity) {

        Log.i("maps", "Buidlding GoogleMap API...");
        //Initialize Google Play Services
        this.mGoogleMap = googleMap;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                Log.d("permission", "Already Granted");
                buildGoogleApiClient(activity);
                mGoogleMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                Log.d("permission", "Request");
                getViewState().checkLocationPermission();
            }
        }
        else {
            Log.d("permission", "Old SDK");

            getViewState().checkLocationPermission();
            buildGoogleApiClient(activity);
            mGoogleMap.setMyLocationEnabled(true);
        }



        mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(final Marker marker) {

                if (marker.getId().equals(currentMarker.getId()))
                    return;

                Comparator<HashMap<String, String>> PlaceComparator
                        = new Comparator<HashMap<String, String>>() {

                    public int compare(HashMap<String, String> o1, HashMap<String, String> o2) {
                        double lat = marker.getPosition().latitude;
                        double lng = marker.getPosition().longitude;

                        double lat1 = Double.parseDouble(o1.get("lat"));
                        double lng1 = Double.parseDouble(o1.get("lng"));
                        double lat2 = Double.parseDouble(o2.get("lat"));
                        double lng2 = Double.parseDouble(o2.get("lng"));

                        return Double.compare(
                                Math.sqrt((lat1 - lat) * (lat1 - lat) + (lng1 - lng) * (lng1 - lng)),
                                Math.sqrt((lat2 - lat) * (lat2 - lat) + (lng2 - lng) * (lng2 - lng))
                        );
                    }

                };

                Log.d(getClass().getSimpleName(), "Click!");
                getViewState().setTitle(marker.getTitle());

                Collections.sort(getNearbyPlacesData.nearbyPlacesList, PlaceComparator);

                String closestPlaceId = getNearbyPlacesData.nearbyPlacesList.get(0).get("place_id");

                for (HashMap<String, String> place: getNearbyPlacesData.nearbyPlacesList) {
                    Log.i("place", place.get("place_name"));
                }

                Places.GeoDataApi.getPlaceById(mGoogleApiClient, closestPlaceId)
                        .setResultCallback(new ResultCallback<PlaceBuffer>() {
                            @Override
                            public void onResult(PlaceBuffer places) {
                                if (places.getStatus().isSuccess() && places.getCount() > 0) {
                                    final Place nearestPlace = places.get(0);
                                    Log.i("nearest", "Place found: " + nearestPlace.getId());

                                    getViewState().setAdditionalTitle(
                                            (nearestPlace.getPhoneNumber()!=null?nearestPlace.getPhoneNumber() + "\n":"") +
                                            (nearestPlace.getAddress()!=null?nearestPlace.getAddress() + "\n":"") +
                                            (nearestPlace.getWebsiteUri()!=null?nearestPlace.getWebsiteUri():"")
                                    );


                                } else {
                                    Log.e("nearest", "Place not found");
                                }
                                places.release();
                            }
                        });

                getViewState().showClinicInfo();
            }
        });

        mGoogleMap = googleMap;

        bestAvailableProvider = locationManager.getBestProvider(criteria, true);

        Log.i("maps", "Provider: " + bestAvailableProvider);

        Log.i("maps", "Map loaded...");

        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);

    }

    private synchronized void buildGoogleApiClient(Activity activity) {
        mGoogleApiClient = new GoogleApiClient.Builder(activity)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        Log.d("location", "Request update");
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location newLocation) {
        location = newLocation;

        findNearestBloodStation(newLocation);
    }

    public void findNearestBloodStation(Location location) {
        findNearest(location, "станция,переливания,крови,blood,transfusion");
    }

    public void findNearest(Location location, String input) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        //place marker where user just clicked
        Log.i("maps", "Placing current location marker...");
        currentMarker = mGoogleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("Вы здесь")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));

        Log.i("maps", "Zooming in...");
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(14));

        Log.i("maps", "Getting nearest objects...");
        String url = getUrl(location.getLatitude(), location.getLongitude(), input);
        Object[] DataTransfer = new Object[2];
        DataTransfer[0] = mGoogleMap;
        DataTransfer[1] = url;
        Log.i("maps", url);
        getNearbyPlacesData = new GetNearbyPlacesData();
        getNearbyPlacesData.execute(DataTransfer);

    }

    private String getUrl(double latitude, double longitude, String nearbyPlace) {
        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/textsearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        //googlePlacesUrl.append("&radius=" + 50000);
        googlePlacesUrl.append("&rankby=distance");
        googlePlacesUrl.append("&query=" + nearbyPlace);
        googlePlacesUrl.append("&language=ru");
        googlePlacesUrl.append("&key=" + "AIzaSyCxSBD3QAbaylJLHZd57N-98gVQArjMfxY");
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults, Activity activity, Context context) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(context,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient(activity);
                        }
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(context, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }

    public void stationSearch(CharSequence input){
        Log.d(getClass().getSimpleName(), "SEARCH!");

        findNearest(location, input.toString());
    }



}
