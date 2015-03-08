package com.junjunguo.localfilehandling;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.junjunguo.localfilehandling.model.AppSetting;
import com.junjunguo.localfilehandling.model.OSMmap;

import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private MapView mMapView;
    private MapController mMapController;
    private LocationManager locationManager;
    private Location myCurrentLocation;
    private String bestProvider;
    private Marker startMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activeOpenStreetMap();
        checkGpsAvailability();
    }

    @Override protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(bestProvider, AppSetting.LOCATION_UPDATE_TIME,
                AppSetting.LOCATION_UPDATE_DISTANCE, (android.location.LocationListener) this);
    }

    /**
     * check if GPS enabled and if not send user to the GSP settings
     */
    private void checkGpsAvailability() {
        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
    }

    /**
     * start open street map
     */
    private void activeOpenStreetMap() {
        mMapView = (MapView) findViewById(R.id.mapview);
        mMapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        mMapView.setBuiltInZoomControls(true);
        mMapView.setMultiTouchControls(true);
        mMapController = (MapController) mMapView.getController();
        mMapController.setZoom(16);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Create a criteria object to retrieve provider
        Criteria criteria = new Criteria();
        // Get the name of the best provider
        bestProvider = locationManager.getBestProvider(criteria, false);
        // Get Current Location

        if (myCurrentLocation != null) {
            myCurrentLocation = locationManager.getLastKnownLocation(bestProvider);
            Toast.makeText(getApplicationContext(), "Using Location from your GPS!", Toast.LENGTH_LONG).show();
        } else {
            myCurrentLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Toast.makeText(getApplicationContext(), "No GPS Single, getting Location form your Network Provider!",
                    Toast.LENGTH_LONG).show();
        }
        startMarker = new Marker(mMapView);
        onLocationChanged(myCurrentLocation);
    }


    public void onLocationChanged(Location location) {
        mMapController.setCenter(new GeoPoint(location.getLatitude(), location.getLongitude()));
        //        addMarkersOnMapView();
        updateMyPositionMarker(location);
    }

    /**
     * update coworkers position marker
     */
    public void updateCoworkersPositionMarker() {
        ArrayList<OverlayItem> markersOverlayItemArray =
                new OSMmap().getCoworkerMarkersOverlay(getApplicationContext());
        ItemizedIconOverlay<OverlayItem> markerItemizedIconOverlay =
                new ItemizedIconOverlay(this, markersOverlayItemArray, null);
        mMapView.getOverlays().add(markerItemizedIconOverlay);
        ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(this);
        mMapView.getOverlays().add(myScaleBarOverlay);
    }

    /**
     * update my position marker
     *
     * @param location
     */
    private void updateMyPositionMarker(Location location) {
        startMarker.setPosition(new GeoPoint(location.getLatitude(), location.getLongitude()));
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mMapView.getOverlays().add(startMarker);
        mMapView.invalidate();
        startMarker.setIcon(getResources().getDrawable(R.drawable.mypositionicon));
        startMarker.setTitle("My point");
    }

}
