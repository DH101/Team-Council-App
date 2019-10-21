package com.team9.seatonvalley;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * @Author: Adam Barron, Student Number 160212899
 * @Since: 03/02/2018
 *
 * A class to handle all settings across the app. Any choices made by the user in the settings menu
 * are handled programmatically here. Also responsible for modifying the applications shared
 * preferences file to ensure that changes permeate throughout the app.
 */
public class SettingsActivity extends AppCompatPreferenceActivity {

    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();
            preference.setSummary(stringValue);
            return true;

        }
    };

    /**
     * A preference click listener that resets the user's data.
     * @see #showResetDataDialog()
     */
    private Preference.OnPreferenceClickListener resetDataButtonListener = new Preference.OnPreferenceClickListener() {

        @Override
        public boolean onPreferenceClick(Preference preference) {
            showResetDataDialog();
            return true;
        }
    };

    /**
     * A preference click listener that shows the privacy policy.
     * @see #showPrivacyPolicy()
     */
    private Preference.OnPreferenceClickListener privacyPolicyButtonListener = new Preference.OnPreferenceClickListener() {
        @Override
        public boolean onPreferenceClick(Preference preference) {
            showPrivacyPolicy();
            return true;
        }

    };

    /**
     * A preference click listener that updates the activity to match
     * the chosen theme.
     */
    private Preference.OnPreferenceClickListener themeChangedListener = new Preference.OnPreferenceClickListener() {

        @Override
        public boolean onPreferenceClick(Preference preference) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            return true;
        }
    };

    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     *
     * @see #sBindPreferenceSummaryToValueListener
     */
    private static void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Check whether the default or yellow theme has been chosen.
        checkTheme();
        super.onCreate(savedInstanceState);
        // Add preferences
        addPreferencesFromResource(R.xml.pref_settings);
        PreferenceManager.setDefaultValues(this, R.xml.pref_settings, false);

        // Bind preferences to their respective listeners.
        Preference privacy = findPreference(getString(R.string.pref_key_privacy_policy));
        privacy.setOnPreferenceClickListener(privacyPolicyButtonListener);

        Preference theme = findPreference(getString(R.string.pref_key_improved_readability_background));
        theme.setOnPreferenceClickListener(themeChangedListener);

        Preference reset = findPreference(getString(R.string.pref_key_reset_data));
        reset.setOnPreferenceClickListener(resetDataButtonListener);

        // Bind the summaries of preferences to their values.
        // When their values change, their summaries are updated
        // to reflect the new value, per the Android Design guidelines.
        bindPreferenceSummaryToValue(findPreference("user_full_name"));
        bindPreferenceSummaryToValue(findPreference("user_email"));

    }

    /**
     * Add the toolbar for the settings menu to the page layout.
     * Created programmatically to support earlier API versions.
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        LinearLayout root = (LinearLayout)findViewById(android.R.id.list).getParent().getParent().getParent();
        Toolbar bar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.settings_toolbar, root, false);
        root.addView(bar, 0); // insert at top
        // When the back arrow is pressed
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            if (!super.onMenuItemSelected(featureId, item)) {
                NavUtils.navigateUpFromSameTask(this);
            }
            return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    /**
    * Check if the yellow background has been chosen
    */
    private void checkTheme() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        if (settings.getBoolean("readability", false))
            setTheme(R.style.DyslexiaFriendly);
    }

    /**
     * Creates the privacy policy's dialogue box and handles code for its back button.
     */
    private void showPrivacyPolicy() {
        final Dialog d = new Dialog(this);

        d.setContentView(R.layout.privacy_policy);
        d.setTitle("@string/privacy_policy_title");

        Button b = d.findViewById(R.id.back);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.cancel();
            }
        });

        d.show();
    }

    /**
     * Creates the reset data dialogue box and handles code for its yes and cancel buttons.
     * This box asks the user if they are sure they want to clear their user data.
     */
    private void showResetDataDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirmation_reset_data_title);
        builder.setMessage(R.string.confirmation_dialog);
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Delete user data
                resetData();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // User cancelled dialog
            }
        });
        builder.create().show();
    }

    /**
     * Replaces the user's given name and email address with a blank string
     * and calls the appropriate function to update the preference summaries.
     */
    private void resetData() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_full_name", "");
        editor.putString("user_email", "");
        editor.apply();
        bindPreferenceSummaryToValue(findPreference("user_full_name"));
        bindPreferenceSummaryToValue(findPreference("user_email"));
    }

    /**
     * Modifies behaviour of the back button to ensure that the user's theme choice is respected.
     * An activity's theme can only be modified before any content is added, so when the theme is
     * changed through this screen, it must be restarted for it to update. This method restarts the
     * correct activity.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent newIntent = null;
        Intent intent = getIntent();
        String className = intent.getStringExtra("Class");
        // Required for correct report an issue title to display
        String mReportIssueTitle = intent.getStringExtra("reportIssueTitle");

        switch(className) {
            case "HomeActivity" :
                newIntent = new Intent(this, HomeActivity.class);
                break;
            case "ServicesActivity" :
                newIntent = new Intent(this, ServicesActivity.class);
                break;
            case "ServicePlaygroundLocationsActivity" :
                newIntent = new Intent(this, ServicePlaygroundLocationsActivity.class);
                break;
            case "ServiceAllotmentLocationsActivity" :
                newIntent = new Intent(this, ServiceAllotmentLocationsActivity.class);
                break;
            case "ContactUsActivity" :
                newIntent = new Intent(this, ContactUsActivity.class);
                break;
            case "ReportIssuesActivity" :
                newIntent = new Intent(this, ReportIssuesActivity.class);
                break;
            case "ReportIssueFormActivity" :
                newIntent = new Intent(this, ReportIssueFormActivity.class);
                newIntent.putExtra("report_issue_title", mReportIssueTitle);
                break;
            case "TweetsActivity" :
                newIntent = new Intent(this, TweetsActivity.class);
                break;
            case "FuturesActivity" :
                newIntent = new Intent(this, FuturesActivity.class);
                break;
            case "LatestActivity" :
                newIntent = new Intent(this, LatestActivity.class);
                break;
        }
        startActivity(newIntent);
        finish();
    }
}
