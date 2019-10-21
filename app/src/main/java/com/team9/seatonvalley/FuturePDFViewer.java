package com.team9.seatonvalley;

/**
 * @Author: Andreea Stirbu, Student Number: 160483710
 * @Since: 22/03/2018
 *
 *  Class that loads the PDF files
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FuturePDFViewer extends AppCompatActivity {

    //Fields
    private PDFView pdfVIEW;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        checkTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_pdfviewer);

        // Instantiate toolbar for FuturePDFViewer activity from the toolbar with
        // pdf_viewer_toolbar id
        Toolbar issuePDFToolbar = findViewById(R.id.pdf_viewer_toolbar);
        issuePDFToolbar.setTitle(R.string.future_title);
        setSupportActionBar(issuePDFToolbar);

        // Show the Up button in the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        checkConnection();

        //get the PDF link sent by the FutureAdapter class
        intent = getIntent();
        pdfVIEW = findViewById(R.id.pdfView);
        Uri url = Uri.parse(intent.getExtras().getString("PDF Link"));
        new RetrievePDFStream().execute(String.valueOf(url));
    }

    /**
     * Load a PDF file
     */
    class RetrievePDFStream extends AsyncTask<String, Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                if(urlConnection.getResponseCode() == 200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            }
            catch (IOException e){
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream){
            pdfVIEW.fromStream(inputStream).load();
        }

    }

    private void checkConnection(){

        ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context
                .CONNECTIVITY_SERVICE);

        if(conMgr != null) {

            if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State
                    .CONNECTED || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() ==
                    NetworkInfo.State.CONNECTED) {

                findViewById(android.R.id.empty).setVisibility(View.GONE);

            } else {

                findViewById(android.R.id.empty).setVisibility(View.VISIBLE);

            }

        }

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
        Intent intent = new Intent(this, FuturesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
