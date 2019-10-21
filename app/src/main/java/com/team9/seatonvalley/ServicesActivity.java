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

import java.util.ArrayList;

/**
 * @Author: Dean Hunter, Student Number: 16027456
 * @Since: 23/03/2018
 *
 * This class sets up the behavior and content for the services activity.
 * This includes the app bar which allows the user to navigate to
 * previous activity and to the settings activity.
 * Also, it includes the recycler view with the allotment and playground service card views
 * with their icon associated to them.
 * Extends AppCompatActivity for action bar features.
 */

public class ServicesActivity extends AppCompatActivity {

    // Private fields to ensure not accessed outside class

    // TAG to represent time of discovery
    private static final String TAG = "ServicesActivity";

    /**
     * To start lists and actionbar at the start of the activities lifecycle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // check the theme in case user has switched theme.
        checkTheme();

        // Run the code below and existing code from the parent activity
        super.onCreate(savedInstanceState);

        // Set the view of the activity to be the activity_service layout
        setContentView(R.layout.activity_service);

        // Instantiate toolbar for service activity from the toolbar with
        // services_toolbar id.
        Toolbar servicesToolbar = findViewById(R.id.services_toolbar);
        // Set the title to be "Services"
        servicesToolbar.setTitle(R.string.service_title);
        // Set toolbar as the action bar for the activity
        setSupportActionBar(servicesToolbar);

        // Check the supportActionBar is not null to avoid error of trying to assign up button
        // without supportActionBar
        if(getSupportActionBar() != null){

            // Add up button to action bar
            ActionBar servicesLocationActionBar = getSupportActionBar();
            servicesLocationActionBar.setDisplayHomeAsUpEnabled(true);

        }

        // Assign the recycler view widget from the recycler_view_services layout
        RecyclerView mRecyclerView = findViewById(R.id.recycler_view_services);

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // Show in Logcat list of services is getting prepared
        Log.d(TAG, "ServicesActivity: preparing list of services");

        // Assign title, description and icon resource to the local variables for
        // representing allotment service
        String allotmentTitle = getResources().getString(R.string.allotment_title);
        String allotmentDescription = getResources().getString(R.string.allotment_description);
        int allotmentIcon = R.drawable.ic_allotment;

        // Assign title, description and icon resource to the local variables for
        // representing playground service
        String playgroundTitle = getResources().getString(R.string.playground_title);
        String playgroundDescription = getResources().getString(R.string.playground_description);
        int playgroundIcon = R.drawable.ic_playground;

        // Instantiate playground and allotment with their title, description and icon resource id
        Service allotment = new Service(allotmentTitle, allotmentDescription, allotmentIcon);

        Service playground = new Service(playgroundTitle, playgroundDescription, playgroundIcon);

        // Instantiate services as an array list and add allotment and playground
        ArrayList<Service>services = new ArrayList<>();

        services.add(allotment);

        services.add(playground);


        // use a linear layout manager for the mRecycler View
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter for mRecycler view
        RecyclerView.Adapter mAdapter = new ServicesAdapter(this, services);
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
     * Actions to take when an option is selected
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
            intent.putExtra("Class", "ServicesActivity");
            startActivity(intent);
            finish();
            return true;
        }
        // User action not recognised, invoke superclass.
        return super.onOptionsItemSelected(item);
    }

    /**
     * Check if the user has selected enhanced readability mode
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
     * Needed to ensure that theme choices are respected throughout the back stack
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
