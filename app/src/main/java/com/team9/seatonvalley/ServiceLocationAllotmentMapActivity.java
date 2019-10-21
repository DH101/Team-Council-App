package com.team9.seatonvalley;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * @Author: Dean Hunter, Student Number: 16027456
 * @Since: 29/03/2018.
 *
 * This class is used to make the map activity.
 * This includes the toolbar at the top and the content which is the map.
 * This creates the behavior of the toolbar such as up button and settings
 * as well as title. This also includes the behavior of the floating action buttons such as find
 * marker and intent to directions to Google Maps. Lastly, it adds the marker of the service
 * location clicked.
 * Extends AppCompatActivity for action bar features.
 */
public class ServiceLocationAllotmentMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    // Private fields to ensure not accessed outside class

    // TAG to represent time of discovery
    private static final String TAG = ServicePlaygroundLocationsActivity.class.getSimpleName();

    // To store location (latitude and longitude) of service location
    private LatLng mServiceLocation;

    // To store the service location title
    private String mServiceLocationTitle;

    // The default zoom when the map activity is started
    private float DEFAULT_ZOOM = 18;

    /**
     *  To start map and actionbar at the start of the activities lifecycle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Set the content view of activity to the layout of activity_service_location_map
        setContentView(R.layout.activity_service_location_map);

        // Set the toolbar of activity to the layout of toolbar from service_location_map_toolbar
        Toolbar serviceLocationMapToolbar = findViewById(R.id.service_location_map_toolbar);

        // State in logcat for checking title of service location and actual location
        Log.d(TAG, "onCreate: checking for title and location intent");

        // Check there is a service location title from an intent to then assign it to the
        // mServiceLocationTitle
        if(getIntent().hasExtra("service_location_title")){

            // Add service location title to string
            mServiceLocationTitle = getIntent().getStringExtra("service_location_title");

        } else {

            // State no location found on toolbar title
            mServiceLocationTitle = getString(R.string.no_service_location_title);

        }

        // Add service location title to toolbar
        serviceLocationMapToolbar.setTitle(mServiceLocationTitle);

        // Set the toolbar as the app bar for activity
        setSupportActionBar(serviceLocationMapToolbar);

        // Check the supportActionBar is not null to avoid error of trying to assign up button
        // without supportActionBar
        if(getSupportActionBar() != null){

            // Add up button to action bar
            ActionBar servicesLocationMapActionBar = getSupportActionBar();
            servicesLocationMapActionBar.setDisplayHomeAsUpEnabled(true);

        }

        // get the extras from the intent in order to solve null pointer exception
        Bundle getData = getIntent().getExtras();

        // Check there is a service location from the intent to then assign it to the
        // mServiceLocation and ensure getParcelable cannot return null
        if(getIntent().hasExtra("service_location") && getData != null){

            // Get location from servicesRecyclerViewAdapter when user clicks service location
            mServiceLocation = getData.getParcelable("service_location");

        } else {

            // Set location to default ( roundabout of seaton Delaval ) if no location from
            // serviceLocationsAdapter is found
            mServiceLocation = new LatLng(55.072910, -1.525167);

            // Instantiate snack bar to state no location found and go back
            Snackbar locationNotFoundSnackBar = Snackbar.make(
                    findViewById(R.id.app_bar_map_service_locations),
                    R.string.locations_not_found,
                    Snackbar.LENGTH_LONG);

            // Show the snack bar on the activity
            locationNotFoundSnackBar.show();

        }

        // Place the map from serviceLocationMap activity into the application by its ID
        SupportMapFragment mSupportMapFragment = (SupportMapFragment) getSupportFragmentManager().
                findFragmentById(R.id.map);

        // Set call back object which is triggered when GoogleMap instance is ready to be used
        mSupportMapFragment.getMapAsync(this);

    }

    /**
     * To start the content of the map and the floating action buttons when the map is ready
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {

        // Add marker to googleMap instance with the service location title and the
        // location (longitude and latitude) of the service location specified
        googleMap.addMarker(new MarkerOptions().position(mServiceLocation)
                .title(mServiceLocationTitle));

        // Move camera on google map to the location of the service location
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(mServiceLocation));

        // Set default Google Maps tool bar to be disabled
        googleMap.getUiSettings().setMapToolbarEnabled(false);

        // Add floating action bar to application with layout from directions_fab id in app_bar
        // _map_service_locations
        FloatingActionButton directionButton = findViewById(R.id.directions_fab);

        // Set an onclickListener to floatingActionButton so when clicked, it will call
        // getDirections with the service location.
        // State in logcat that user is getting directions
        directionButton.setOnClickListener(new View.OnClickListener() {

            // state in logcat the floating action bar is leading to directions and
            // call getDirections to the location of the service
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: getting directions");

                getDirections(mServiceLocation);

            }

        });

        // Add floating action bar to application with layout from find_location_fab id in activity
        // _service_location_map
        FloatingActionButton findLocationButton = findViewById(R.id.find_location_fab);

        // Set an onclickListener to floatingActionButton so when clicked, it will move the camera
        // for the googleMap instance to the service location with the default zoom. Also,
        // state in logcat the activity if finding the marker at the service location
        findLocationButton.setOnClickListener(new View.OnClickListener() {

            // state in logcat the floating action bar is finding marker of location and
            // call move camera of google map to the location of marker with the default zoom
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: finding marker of location");

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mServiceLocation,
                        DEFAULT_ZOOM));


            }

        });

        // State the map is ready in the logcat
        Log.d(TAG, "onMapReady: map is ready");

    }

    /**
     * Get directions from user location or what they set in Google Maps application
     * to marker location
     */
    private void getDirections(LatLng destination){

        // State getting directions in logcat when getDirections is called
        Log.d(TAG, "getDirections: Getting directions");

        // Create string of uri to Google Maps application
        // with the destination which is the service location the user clicked
        String uri = "https://www.google.com/maps/dir/?api=1&&destination=" + destination.latitude
                + "," + destination.longitude;

        // Assign intent to the google map application or access google map from browser if
        // user does not google map application
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));

        // Start the activity of the intent
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    /**
     * Options bar
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Settings pressed
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra("Class", "ContactUsActivity");
            startActivity(intent);
            finish();
            return true;
        }
        // User action not recognised, invoke superclass.
        return super.onOptionsItemSelected(item);
    }

    /**
     * Needed to ensure that theme choice is respected throughout the back stack
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}