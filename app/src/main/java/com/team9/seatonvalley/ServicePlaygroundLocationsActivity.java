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

public class ServicePlaygroundLocationsActivity extends AppCompatActivity {

    // Private fields to ensure not accessed outside class

    // TAG to represent time of discovery
    private static final String TAG = "ServiceLocationsPg";

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

        // Store playground title from values string xml.
        String playgroundTitle = getResources().getString(R.string.playground_title);

        // State in logcat class is preparing playgrounds
        Log.d(TAG, "initServiceLocations: Preparing playgrounds");

        // Set the title to be "Playgrounds"
        serviceLocationsToolbar.setTitle(playgroundTitle);

        // Instantiate a list of playgrounds with their title, description and location by
        // latitude and longitude
        // First, set up the variables of their playground title, description and location
        // Then instantiate an allotment location which is then added to the service location
        // list.

        String playgroundLocationOneTitle = getResources().getString(R.string.location_seaton_crescent_playground);
        String playgroundLocationOneDescription = getResources().getString(R.string.location_seaton_cresent_street);
        LatLng playgroundLocationOneLocation = new LatLng(55.065576, -1.504131);

        ServiceLocation playgroundLocationOne = new ServiceLocation(playgroundLocationOneTitle,
                playgroundLocationOneDescription, playgroundLocationOneLocation);

        String playgroundLocationTwoTitle = getResources().getString(R.string.location_new_hartley_playground);
        String playgroundLocationTwoDescription = getResources().getString(R.string.location_st_michaels_avenue_street);
        LatLng playgroundLocationTwoLocation = new LatLng(55.083606, -1.520390);

        ServiceLocation playgroundLocationTwo = new ServiceLocation(playgroundLocationTwoTitle,
                playgroundLocationTwoDescription, playgroundLocationTwoLocation);

        String playgroundLocationThreeTitle = getResources().getString(R.string.location_new_hartley_welfare_play_area);
        String playgroundLocationThreeDescription = getResources().getString(R.string.location_st_michaels_avenue_street);
        LatLng playgroundLocationThreeLocation = new LatLng(55.083797, -1.518889);

        ServiceLocation playgroundLocationThree = new ServiceLocation(
                playgroundLocationThreeTitle, playgroundLocationThreeDescription,
                playgroundLocationThreeLocation);

        String playgroundLocationFourTitle = getResources().getString(R.string.location_hallington_drive_play_area);
        String playgroundLocationFourDescription = getResources().getString(R.string.location_seaton_delaval_village);
        LatLng playgroundLocationFourLocation = new LatLng(55.069657, -1.517384);

        ServiceLocation playgroundLocationFour = new ServiceLocation(
                playgroundLocationFourTitle, playgroundLocationFourDescription,
                playgroundLocationFourLocation);

        String playgroundLocationFiveTitle = getResources().getString(R.string.location_mitford_avenue);
        String playgroundLocationFiveDescription = getResources().getString(R.string.location_seaton_delaval_village);
        LatLng playgroundLocationFiveLocation = new LatLng(55.071838, -1.530690);

        ServiceLocation playgroundLocationFive = new ServiceLocation(
                playgroundLocationFiveTitle, playgroundLocationFiveDescription,
                playgroundLocationFiveLocation);

        String playgroundLocationSixTitle = getResources().getString(R.string.location_seaton_sluice_dunes_play_area);
        String playgroundLocationSixDescription = getResources().getString(R.string.location_seaton_sluice_village);
        LatLng playgroundLocationSixLocation = new LatLng(55.088377, -1.483113);

        ServiceLocation playgroundLocationSix = new ServiceLocation(
                playgroundLocationSixTitle, playgroundLocationSixDescription,
                playgroundLocationSixLocation);

        String playgroundLocationSevenTitle = getResources().getString(R.string.location_seaton_sluice_welfare_young_persons_play_park);
        String playgroundLocationSevenDescription = getResources().getString(R.string.location_beresford_road_seaton_sluice_street);
        LatLng playgroundLocationSevenLocation = new LatLng(55.081421, -1.471593);

        ServiceLocation playgroundLocationSeven = new ServiceLocation(
                playgroundLocationSevenTitle, playgroundLocationSevenDescription,
                playgroundLocationSevenLocation);

        String playgroundLocationEightTitle = getResources().getString(R.string.location_hartley_square_play_area);
        String playgroundLocationEightDescription = getResources().getString(R.string.location_seaton_sluice_village);
        LatLng playgroundLocationEightLocation = new LatLng(55.076143, -1.470219);

        ServiceLocation playgroundLocationEight = new ServiceLocation(
                playgroundLocationEightTitle, playgroundLocationEightDescription,
                playgroundLocationEightLocation);

        String playgroundLocationNineTitle = getResources().getString(R.string.location_burnlea_gardens_play_area);
        String playgroundLocationNineDescription = getResources().getString(R.string.location_seghill_village);
        LatLng playgroundLocationNineLocation = new LatLng(55.064822, -1.537663);

        ServiceLocation playgroundLocationNine = new ServiceLocation(
                playgroundLocationNineTitle, playgroundLocationNineDescription,
                playgroundLocationNineLocation);

        String playgroundLocationTenTitle = getResources().getString(R.string.location_crescent_play_area);
        String playgroundLocationTenDescription = getResources().getString(R.string.location_seghill_village);
        LatLng playgroundLocationTenLocation = new LatLng(55.062940, -1.548889);

        ServiceLocation playgroundLocationTen = new ServiceLocation(
                playgroundLocationTenTitle, playgroundLocationTenDescription,
                playgroundLocationTenLocation);

        String playgroundLocationElevenTitle = getResources().getString(R.string.location_seghill_welfare_muga_and_skate_park);
        String playgroundLocationElevenDescription = getResources().getString(R.string.location_front_street_seghill_street);
        LatLng playgroundLocationElevenLocation = new LatLng(55.064853, -1.552008);

        ServiceLocation playgroundLocationEleven = new ServiceLocation(
                playgroundLocationElevenTitle, playgroundLocationElevenDescription,
                playgroundLocationElevenLocation);

        // Create an new array list of service locations
        ArrayList<ServiceLocation> serviceLocations = new ArrayList<>();

        // Add all playground locations to the array list of service locations
        serviceLocations.add(playgroundLocationOne);

        serviceLocations.add(playgroundLocationTwo);

        serviceLocations.add(playgroundLocationThree);

        serviceLocations.add(playgroundLocationFour);

        serviceLocations.add(playgroundLocationFive);

        serviceLocations.add(playgroundLocationSix);

        serviceLocations.add(playgroundLocationSeven);

        serviceLocations.add(playgroundLocationEight);

        serviceLocations.add(playgroundLocationNine);

        serviceLocations.add(playgroundLocationTen);

        serviceLocations.add(playgroundLocationEleven);

        // Set toolbar as the action bar for the activity
        setSupportActionBar(serviceLocationsToolbar);

        // Check the supportActionBar is not null to avoid error of trying to assign up button
        // without supportActionBar
        if(getSupportActionBar() != null){

            // Add up button to action bar
            ActionBar servicesLocationActionBar = getSupportActionBar();
            servicesLocationActionBar.setDisplayHomeAsUpEnabled(true);

        }

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
            intent.putExtra("Class", "ServicePlaygroundLocationsActivity");
            startActivity(intent);
            finish();
            return true;
        }
        // User action not recognised, invoke superclass.
        return super.onOptionsItemSelected(item);
    }

    /**
     * Checks if the user has enabled enhanced readability mode
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
     * Needed to ensure theme choices are respected throughout the back stack
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
