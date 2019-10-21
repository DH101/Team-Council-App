package com.team9.seatonvalley;

/**
 * @Author: Dalton Yates
 * @Since: 29/03/2018.
 *
 *
 * This class is used to make the map activity.
 * It puts a pin on Seaton Valley Community Council and
 * shows it on the map.
 * A custom action bar is set up along with custom floating
 * buttons.
 */


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.team9.seatonvalley.R;


public class MapMarkerActivity extends AppCompatActivity implements OnMapReadyCallback {

    // To store location (latitude and longitude) of seaton valley

    private LatLng mapLocation = new LatLng(55.072876, -1.525926);

    // To store seaton valley title
    private String mapLocationTitle = "Seaton Valley Community Council";

    // The default zoom when the map activity is started
    private float DEFAULT_ZOOM = 18;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_map_marker);
        // Set the toolbar of activity to the layout of toolbar from contact_us_map_toolbar
        Toolbar mapMarkerToolbar = findViewById(R.id.contact_us_map_toolbar);

        // Add map location title to toolbar
        mapMarkerToolbar.setTitle(mapLocationTitle);

        // Set the toolbar as the app bar for activity
        setSupportActionBar(mapMarkerToolbar);

        // Assign the app bar to this ActionBar variable
        ActionBar mapMarkerActionBar = getSupportActionBar();

        // Add up button to app bar
        mapMarkerActionBar.setDisplayHomeAsUpEnabled(true);

        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_marker);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        // Add a marker of Seaton Valley community council
        googleMap.addMarker(new MarkerOptions().position(mapLocation)
                .title(mapLocationTitle));

        // Move camera on google map to the location of the service location
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(mapLocation));

        // zoom in on marker location
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapLocation,
                DEFAULT_ZOOM));

        // Set default Google Maps tool bar to be disabled
        googleMap.getUiSettings().setMapToolbarEnabled(false);

        // Add floating action bar to application with layout from map_directions_fab id in app_bar_map_marker_locations
        FloatingActionButton directionButton = findViewById(R.id.map_directions_fab);

        directionButton.setOnClickListener(new View.OnClickListener() {

            // call getDirections to the location of the service
            @Override
            public void onClick(View v) {

                getDirections(mapLocation);

            }

        });

        // Add floating action bar to application with layout from map_find_location_fab id in activity
        // _map_marker
        FloatingActionButton findLocationButton = findViewById(R.id.map_location_fab);

        // Set an onclickListener to floatingActionButton so when clicked, it will move the camera
        // for the googleMap instance to the map location with the default zoom.
        findLocationButton.setOnClickListener(new View.OnClickListener() {

            // call move camera of google map to the location of marker with the default zoom
            @Override
            public void onClick(View v) {

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapLocation,
                        DEFAULT_ZOOM));


            }

        });


    }

    private void getDirections(LatLng destination) {
        // Create string of uri to Google Maps application
        // with the destination of seaton valley
        String uri = "https://www.google.com/maps/dir/?api=1&&destination=" + destination.latitude
                + "," + destination.longitude;

        // Assign intent to the google map application or access google map from browser if
        // user does not google map application
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));

        // Start the activity of the intent
        startActivity(intent);

    }


}
