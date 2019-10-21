package com.team9.seatonvalley;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * @Author: Adam Barron
 * @Since: 27/11/2017.
 * @Extended by: Joseph Dudgeon
 * @Since: 20/03/2018.
 *
 * The main menu for the application, contains buttons to allow the user to navigate to each
 * section as well as a toolbar for accessing settings.
 */

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gridView = findViewById(R.id.activity_menu_buttons);
        gridView.setAdapter(new ImageAdapter(this));
        gridView.setOnItemClickListener(this);

    }

    //Button actions
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position){
            //Latest
            case(0):{
                startActivity(new Intent(this,LatestActivity.class));
                break;
            }
            //Tweets
            case(1):{
                startActivity(new Intent(this,TweetsActivity.class));
                break;
            }
            //Report it
            case(2):{
                startActivity(new Intent(this,ReportIssuesActivity.class));
                break;
            }
            //Futures
            case(3):{
                startActivity(new Intent(this,FuturesActivity.class));
                break;
            }
            //Contact us
            case(4):{
                startActivity(new Intent(this,ContactUsActivity.class));
                break;
            }
            //Services
            case(5):{
                startActivity(new Intent(this,ServicesActivity.class));
                break;
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }
    // Options bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Settings pressed
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra("Class", "HomeActivity");
            startActivity(intent);
            finish();
            return true;
        }
        // User action not recognised, invoke superclass.
        return super.onOptionsItemSelected(item);
    }
    //Change theme if user has selected to do so in settings
    private void checkTheme() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        if (settings.getBoolean("readability", false)) {
            setTheme(R.style.DyslexiaFriendly);
            return;
        }
        setTheme(R.style.activity_layout);

    }

}

