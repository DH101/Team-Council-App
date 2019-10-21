package com.team9.seatonvalley;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * @Author: Dean Hunter, Student Number: 16027456
 * @Since: 26/03/2018.
 *
 * This class sets up the behavior and content for the service locations activity.
 * This includes the app bar which allows the user to navigate to
 * previous activity and to the settings activity.
 * Also, it includes the recycler view with either a list of allotment card views
 * or playground card views, depending on whether the title from the intent of the services
 * activity is allotment or playground.
 * Extends AppCompatActivity for action bar features.
 */

public class ServiceAllotmentLocationsActivity extends AppCompatActivity {

    // Private fields to ensure not accessed outside class

    // TAG to represent time of discovery
    private static final String TAG = "ServiceLocationsAl";

    // To start lists and actionbar at the start of the activities lifecycle.
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // check the theme in case user has switched theme.
        checkTheme();

        // Run the code in addition to existing code of the parent activity
        super.onCreate(savedInstanceState);

        // Set the view of the activity to be the activity_service_playground_location layout
        setContentView(R.layout.activity_service_location);

        // State in logcat for checking there is an intent of a title for a service location title
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        // State in logcat preparing list of service locations
        Log.d(TAG, "ServicePlaygroundLocationsActivity: preparing list of service locations");

        // Instantiate toolbar for service locations activity from the toolbar with
        // service_locations_toolbar id
        Toolbar serviceLocationsToolbar = findViewById(R.id.service_locations_toolbar);

        // State in logcat class is preparing allotments
        Log.d(TAG, "initServiceLocations: Preparing allotments");

        // Store allotment title from values string xml.
        String allotmentTitle = getResources().getString(R.string.allotment_title);

        // Set the title to be "Allotments"
        serviceLocationsToolbar.setTitle(allotmentTitle);

        // Set toolbar as the action bar for the activity
        setSupportActionBar(serviceLocationsToolbar);

        // Check the supportActionBar is not null to avoid error of trying to assign up button
        // without supportActionBar
        if(getSupportActionBar() != null){

            // Add up button to action bar
            ActionBar servicesLocationActionBar = getSupportActionBar();
            servicesLocationActionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Instantiate a list of allotments with their title, description and location by
        // latitude and longitude
        // First, set up the variables of their allotment title, description and location
        // Then instantiate an allotment location which is then added to the service location
        // list.

        String allotmentLocationOneTitle = getResources().getString(R.string
                .location_ancroft_road_street);
        String allotmentLocationOneDescription = getResources().getString(R.string
                .location_seaton_delaval_village);
        LatLng allotmentLocationOneLocation = new LatLng(55.069826, -1.532891);

        ServiceLocation allotmentLocationOne = new ServiceLocation(allotmentLocationOneTitle,
                allotmentLocationOneDescription, allotmentLocationOneLocation);

        String allotmentLocationTwoTitle = getResources().getString(R.string
                .location_baxter_place_street);
        String allotmentLocationTwoDescription = getResources().getString(R.string.
                location_seaton_delaval_village);
        LatLng allotmentLocationTwoLocation = new LatLng(55.072725, -1.521034);

        ServiceLocation allotmentLocationTwo = new ServiceLocation(allotmentLocationTwoTitle,
                allotmentLocationTwoDescription, allotmentLocationTwoLocation);

        String allotmentLocationThreeTitle = getResources().getString(R.string
                .location_beresford_road_street);
        String allotmentLocationThreeDescription = getResources().getString(R.string
                .location_seaton_sluice_village);
        LatLng allotmentLocationThreeLocation = new LatLng(55.079049, -1.470517);

        ServiceLocation allotmentLocationThree = new ServiceLocation(
                allotmentLocationThreeTitle, allotmentLocationThreeDescription,
                allotmentLocationThreeLocation);

        String allotmentLocationFourTitle = getResources().getString(R.string
                .location_dartford_close_street);
        String allotmentLocationFourDescription = getResources().getString(R.string
                .location_seaton_delaval_village);
        LatLng allotmentLocationFourLocation = new LatLng(55.070825, -1.516492);

        ServiceLocation allotmentLocationFour = new ServiceLocation(
                allotmentLocationFourTitle, allotmentLocationFourDescription,
                allotmentLocationFourLocation);

        String allotmentLocationFiveTitle = getResources().getString(R.string
                .location_dene_top_street);
        String allotmentLocationFiveDescription = getResources().getString(R.string
                .location_seaton_sluice_village);
        LatLng allotmentLocationFiveLocation = new LatLng(55.078854, -1.475995);

        ServiceLocation allotmentLocationFive = new ServiceLocation(
                allotmentLocationFiveTitle, allotmentLocationFiveDescription,
                allotmentLocationFiveLocation);

        String allotmentLocationSixTitle = getResources().getString(R.string
                .location_gloria_avenue_street);
        String allotmentLocationSixDescription = getResources().getString(R.string
                .location_new_hartley_village);
        LatLng allotmentLocationSixLocation = new LatLng(55.085148, -1.517379);

        ServiceLocation allotmentLocationSix = new ServiceLocation(
                allotmentLocationSixTitle, allotmentLocationSixDescription,
                allotmentLocationSixLocation);

        String allotmentLocationSevenTitle = getResources().getString(R.string
                .location_hall_farm_street);
        String allotmentLocationSevenDescription = getResources().getString(R.string
                .location_holywell_village);
        LatLng allotmentLocationSevenLocation = new LatLng(55.079234, -1.501045);

        ServiceLocation allotmentLocationSeven = new ServiceLocation(
                allotmentLocationSevenTitle, allotmentLocationSevenDescription,
                allotmentLocationSevenLocation);

        String allotmentLocationEightTitle = getResources().getString(R.string
                .location_memorial_playing_fields_street);
        String allotmentLocationEightDescription = getResources().getString(R.string
                .location_new_hartley_village);
        LatLng allotmentLocationEightLocation = new LatLng(55.083982, -1.518292);

        ServiceLocation allotmentLocationEight = new ServiceLocation(
                allotmentLocationEightTitle, allotmentLocationEightDescription,
                allotmentLocationEightLocation);

        String allotmentLocationNineTitle = getResources().getString(R.string
                .location_seaton_terrace_street);
        String allotmentLocationNineDescription = getResources().getString(R.string
                .location_seaton_delaval_village);
        LatLng allotmentLocationNineLocation = new LatLng(55.070417, -1.515730);

        ServiceLocation allotmentLocationNine = new ServiceLocation(
                allotmentLocationNineTitle, allotmentLocationNineDescription,
                allotmentLocationNineLocation);

        String allotmentLocationTenTitle = getResources().getString(R.string
                .location_seghill_road_street);
        String allotmentLocationTenDescription = getResources().getString(R.string
                .location_seaton_delaval_village);
        LatLng allotmentLocationTenLocation = new LatLng(55.068064, -1.530289);

        ServiceLocation allotmentLocationTen = new ServiceLocation(
                allotmentLocationTenTitle, allotmentLocationTenDescription,
                allotmentLocationTenLocation);

        String allotmentLocationElevenTitle = getResources().getString(R.string
                .location_victoria_close_street);
        String allotmentLocationElevenDescription = getResources().getString(R.string
                .location_seaton_delaval_village);
        LatLng allotmentLocationElevenLocation = new LatLng(55.071615, -1.518024);

        ServiceLocation allotmentLocationEleven = new ServiceLocation(
                allotmentLocationElevenTitle, allotmentLocationElevenDescription,
                allotmentLocationElevenLocation);

        String allotmentLocationTwelveTitle = getResources().getString(R.string
                .location_west_terrace_street);
        String allotmentLocationTwelveDescription = getResources().getString(R.string
                .location_seaton_sluice_village);
        LatLng allotmentLocationTwelveLocation = new LatLng(55.083766, -1.471005);

        ServiceLocation allotmentLocationTwelve = new ServiceLocation(
                allotmentLocationTwelveTitle, allotmentLocationTwelveDescription,
                allotmentLocationTwelveLocation);

        String allotmentLocationThirtyTitle = getResources().getString(R.string
                .location_coppergate_street);
        String allotmentLocationThirtyDescription = getResources().getString(R.string
                .location_holywell_village);
        LatLng allotmentLocationThirtyLocation = new LatLng(55.065524, -1.506664);

        ServiceLocation allotmentLocationThirty = new ServiceLocation(
                allotmentLocationThirtyTitle, allotmentLocationThirtyDescription,
                allotmentLocationThirtyLocation);


        // Create an new array list of service locations
        ArrayList<ServiceLocation> serviceLocations = new ArrayList<>();

        // Add all allotment locations to the array list of service locations
        serviceLocations.add(allotmentLocationOne);

        serviceLocations.add(allotmentLocationTwo);

        serviceLocations.add(allotmentLocationThree);

        serviceLocations.add(allotmentLocationFour);

        serviceLocations.add(allotmentLocationFive);

        serviceLocations.add(allotmentLocationSix);

        serviceLocations.add(allotmentLocationSeven);

        serviceLocations.add(allotmentLocationEight);

        serviceLocations.add(allotmentLocationNine);

        serviceLocations.add(allotmentLocationTen);

        serviceLocations.add(allotmentLocationEleven);

        serviceLocations.add(allotmentLocationTwelve);

        serviceLocations.add(allotmentLocationThirty);

        // State in Logcat the recycler view is initialising
        Log.d(TAG, "initRecyclerView: initialisation recycler view");

        // Assign the recycler view widget from the recycler_view_service_locations layout
        RecyclerView mRecyclerView = findViewById(R.id.recycler_view_service_locations);

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager for the mRecycler View
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter for mRecycler view
        RecyclerView.Adapter mAdapter = new ServiceLocationsAdapter(this, serviceLocations);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Create an options menu from the menu layout with the menu_main id
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     *  Actions to take when an option is selected
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Settings pressed
        if (id == R.id.action_settings) {

            // Intent to settings activity
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra("Class", "ServiceAllotmentLocationsActivity");
            startActivity(intent);
            finish();
            return true;
        }
        // User action not recognised, invoke superclass.
        return super.onOptionsItemSelected(item);
    }

    /**
     *  To check if user has changed the theme by switch preference
     */
    private void checkTheme() {
        // get the shared preferences
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        // if preference with key "readability" has been click on, change the theme to the
        // style of dyslexia friendly. If not, set theme to the default style.
        if (settings.getBoolean("readability", false)) {
            setTheme(R.style.DyslexiaFriendly);
            return;
        }
        setTheme(R.style.content);

    }

    /**
     * Override onBackPressed to avoid user being able to get to activity which has not picked up
     * on a change in theme. Intent to HomeActivity
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
