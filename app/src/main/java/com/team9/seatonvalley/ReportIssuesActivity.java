package com.team9.seatonvalley;

/**
 * @Author: Dean Hunter, Student Number: 16027456
 * @Since: 10/04/2018.
 *
 * This class sets up the behavior and content for the report issue activity.
 * This includes the app bar which allows the user to navigate to
 * previous activity and to the settings activity.
 * Also, it includes the recycler view with either a list of report issue card views.
 * Extends AppCompatActivity for action bar features.
 */

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


public class ReportIssuesActivity extends AppCompatActivity {


    // Private fields to ensure not accessed outside class

    // TAG to represent time of discovery
    private static final String TAG = "ReportIssuePg";

    // An empty array list of ReportIssue type to contain issues
    private ArrayList<ReportIssue> reportIssues= null;

    // View for list of report issue
    private RecyclerView mRecyclerView;
    // Provides access to report issue items and makes view for each item
    private RecyclerView.Adapter mAdapter;
    // Responsible for positioning item views in a recycler view
    private RecyclerView.LayoutManager mLayoutManager;

    // To start lists and actionbar at the start of the activities lifecycle.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkTheme();
        // Run the code in addition to existing code of the parent activity
        super.onCreate(savedInstanceState);

        // Set the view of the activity to be the activity_report_issue layout
        setContentView(R.layout.activity_report_issue);

        // State in logcat for checking there is an intent of a title for a report issue title
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        // State in logcat preparing list of report issue
        Log.d(TAG, "ServicePlaygroundLocationsActivity: preparing list of report issues");

        // Instantiate toolbar for report issue activity from the toolbar with
        // report_issues_toolbar id
        Toolbar reportIssueToolbar = findViewById(R.id.report_issues_toolbar);

        // Store report issue from values string xml.
        String reportIssueTitle = getResources().getString(R.string.report_issue_title);

        // State in logcat class is preparing report issue
        Log.d(TAG, "initServiceLocations: Preparing report issues list");

        // Set the title to be "Report Issue"
        reportIssueToolbar.setTitle(reportIssueTitle);

        // Instantiate a list of issues with their title, description and icon
        // Set up the variables of their issue title, description and icon

        String reportIssueOneTitle = getResources().getString(R.string.dog_fouling_title);
        String reportIssueOneDescription = getResources().getString(R.string
                .dog_fouling_description);
        int reportIssueOneIcon = R.drawable.ic_paw_outline_white_report_it;

        ReportIssue reportIssueOne = new ReportIssue(reportIssueOneTitle,
                reportIssueOneDescription, reportIssueOneIcon);

        String reportIssueTwoTitle = getResources().getString(R.string.littering_title);
        String reportIssueTwoDescription = getResources().getString(R.string.littering_description);

        int reportIssueTwoIcon = R.drawable.ic_bin_outline_white_report_it;
        ReportIssue reportIssueTwo = new ReportIssue(reportIssueTwoTitle,
                reportIssueTwoDescription, reportIssueTwoIcon);

        String reportIssueThreeTitle = getResources().getString(R.string.bus_shelters_title);
        String reportIssueThreeDescription = getResources().getString(R.string
                .bus_shelters_description);
        int reportIssueThreeIcon = R.drawable.ic_bus_shelter_outline_white_report_it;

        ReportIssue reportIssueThree = new ReportIssue(
                reportIssueThreeTitle, reportIssueThreeDescription,
                reportIssueThreeIcon);

        String reportIssueFourTitle = getResources().getString(R.string.playground_title);
        String reportIssueFourDescription = getResources().getString(R.string
                .report_it_playground_description);
        int reportIssueFourIcon = R.drawable.ic_playground_outline_white_report_it;

        ReportIssue reportIssueFour = new ReportIssue(
                reportIssueFourTitle, reportIssueFourDescription,
                reportIssueFourIcon);

        // Create an new array list of issues
        reportIssues= new ArrayList<>();

        // Add all issues to the array list of issues
        reportIssues.add(reportIssueOne);

        reportIssues.add(reportIssueTwo);

        reportIssues.add(reportIssueThree);

        reportIssues.add(reportIssueFour);

        // Set toolbar as the action bar for the activity
        setSupportActionBar(reportIssueToolbar);

        // Add up button to action bar
        ActionBar reportIssueActionBar = getSupportActionBar();
        reportIssueActionBar.setDisplayHomeAsUpEnabled(true);

        // State in Logcat the recycler view is initialising
        Log.d(TAG, "initRecyclerView: initialisation recycler view");

        // Assign the recycler view widget from the recycler_view_report_issues layout
        mRecyclerView = findViewById(R.id.recycler_view_report_issues);

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager for the mRecycler View
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter for mRecycler view
        mAdapter = new ReportIssuesAdapter(this, reportIssues);
        mRecyclerView.setAdapter(mAdapter);


    }

    // Create an options menu from the menu layout with the menu_main id
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Actions to take when an option is selected
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
            intent.putExtra("Class", "ReportIssuesActivity");
            startActivity(intent);
            finish();
            return true;
        }
        // User action not recognised, invoke superclass.
        return super.onOptionsItemSelected(item);
    }

    /**
     * Checks if the user has selected enhanced readablilty mode
     */
    private void checkTheme() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        if (settings.getBoolean("readability", false)) {
            setTheme(R.style.DyslexiaFriendly);
            return;
        }
        setTheme(R.style.content);

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
