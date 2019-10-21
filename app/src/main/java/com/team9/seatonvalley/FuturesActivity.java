package com.team9.seatonvalley;

/**
 * @Author: Andreea Stirbu, Student Number: 160483710
 * @Since: 21/03/2018
 *
 *  Main activity that holds a RecyclerView with CardViews that are populated using Firebase
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FuturesActivity extends AppCompatActivity {

    //Firebase references
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private List<Future> issuesList;
    private RecyclerView recycle;

    private FuturesAdapter futuresAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futures);

        setupActionBar();

        checkConnection();

        setUpListOfFutures();
    }

    /**
     * Set up the action bar
     **/
    private void setupActionBar() {
        Toolbar futuresToolbar = findViewById(R.id.futures_toolbar);
        futuresToolbar.setTitle(R.string.seaton_valley_futures_title);
        setSupportActionBar(futuresToolbar);
        // Show the Up button in the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void checkConnection(){

        ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context
                .CONNECTIVITY_SERVICE);

       if(conMgr != null) {

           if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State
                   .CONNECTED || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() ==
                   NetworkInfo.State.CONNECTED) {

               findViewById(android.R.id.empty).setVisibility(View.GONE);
               findViewById(R.id.issueRecyclerView).setVisibility(View.VISIBLE);

           } else {

               findViewById(android.R.id.empty).setVisibility(View.VISIBLE);
               findViewById(R.id.issueRecyclerView).setVisibility(View.GONE);

           }

       }

    }

    /**
     * Function that initialize/set a RecyclerView and retrieve data from firebase
     **/
    private void setUpListOfFutures(){
        recycle = (RecyclerView) findViewById(R.id.issueRecyclerView);

        // Get a reference to our database table Future
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Future");

        issuesList = new ArrayList<>();
        futuresAdapter = new FuturesAdapter(issuesList, FuturesActivity.this);

        // Attach a listener to read the data from the database table reference
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    //Create Future type objects populated with the info from the firebase table "Future"
                    Future value = dataSnapshot1.getValue(Future.class);
                    Future fire = new Future();

                    //Retrieve data from firebase table
                    String title = value.getTitle();
                    String description = value.getDescription();
                    String image = value.getImage();
                    String pdf = value.getPDF();

                    //Populate the object
                    fire.setTitle(title);
                    fire.setDescription(description);
                    fire.setImage(image);
                    fire.setPDF(pdf);

                    //Add the populated object in the list
                    issuesList.add(fire);
                }
                futuresAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });

        //Setting the RecyclerView
        recycle.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(FuturesActivity.this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(mLayoutManager);
        recycle.setAdapter(futuresAdapter);

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
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra("Class", "FuturesActivity");
            startActivity(intent);
            finish();
            return true;
        }
        // User action not recognised, invoke superclass.
        return super.onOptionsItemSelected(item);
    }

    /**
     * Change theme if user has selected to do so in settings
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

