package com.team9.seatonvalley;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Alex Bewley-Taylor
 * @Since: 10/04/2018
 *
 * A class to display the form for the user to fill in when reporting issues to the
 * community council.
 */

public class ReportIssueFormActivity extends AppCompatActivity {

    private String mReportIssueTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_issue_form);
        autoComplete();

        // Instantiate toolbar for report issue activity from the toolbar with
        // report_issue_form_toolbar id
        Toolbar reportIssueFormToolbar = findViewById(R.id.report_issue_form_toolbar);
        //Fields that are required to be filled in for form to be completed
        final EditText yourName = findViewById(R.id.their_name);
        final EditText yourEmail = findViewById(R.id.their_email);
        final EditText yourPhone = findViewById(R.id.their_phone);
        final EditText describeIssue = findViewById(R.id.their_issue_and_location);
        final CheckBox privacyPolicy = findViewById(R.id.confirm_policy);
        //Pass the right title to the toolbar
        if(getIntent().hasExtra("report_issue_title")) {

            mReportIssueTitle = getIntent().getStringExtra("report_issue_title");
        }else {

            mReportIssueTitle = "No title found";
        }

        // Add report issue title to toolbar
        reportIssueFormToolbar.setTitle(mReportIssueTitle);


        // Set the toolbar as the app bar for activity
        setSupportActionBar(reportIssueFormToolbar);

        // Assign the app bar to this ActionBar variable
        ActionBar servicesLocationActionBar = getSupportActionBar();

        // Add up button to app bar
        servicesLocationActionBar.setDisplayHomeAsUpEnabled(true);

        //Validates the form before returning appropriate errors or submitting the form
        final Button submit = findViewById(R.id.post_issue);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //When button is pressed check fields are filled in
                String name = yourName.getText().toString();
                String email = yourEmail.getText().toString();
                String phone = yourPhone.getText().toString();
                String issueDescription = describeIssue.getText().toString();

                if (TextUtils.isEmpty(name)){
                    yourName.setError(getResources().getString(R.string.no_name));
                    yourName.requestFocus();
                    return;
                }

                Boolean onError = false;
                if (!isValidEmail(email)) {
                    onError = true;
                    yourEmail.setError(getResources().getString(R.string.error_email));
                    yourEmail.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(email)) {
                    yourEmail.setError(getResources().getString(R.string.no_email));
                    yourEmail.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(phone)){
                    yourPhone.setError(getResources().getString(R.string.no_phone));
                    yourPhone.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(issueDescription)){
                    describeIssue.setError(getResources().getString(R.string.no_description));
                    describeIssue.requestFocus();
                    return;
                }

                if (!privacyPolicy.isChecked()){
                    privacyPolicy.setError(getResources().getString(R.string.error_privacy_policy));
                    privacyPolicy.requestFocus();
                    return;
                }

                //Remove phone,describe issue text once validated and remove name as well as email
                // but add name and email if user has added it in preferences.
                yourName.setText("");
                yourEmail.setText("");
                autoComplete();
                yourPhone.setText("");
                describeIssue.setText("");


                //Create alert if fields are filled in
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(
                        ReportIssueFormActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_success_report_form,
                        null);
                Button mConfirm = mView.findViewById(R.id.button_report_form_confirm);

                // Intent to report issue form activity when okay button on view or view is clicked
                mConfirm.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // All EditText boxes are valid, intent to report issue form activity
                        submitIssue();
                    }
                });

                mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // All EditText boxes are valid, intent to report issue form activity
                        submitIssue();
                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }

    /**
     * Return the use to the home page when issue is submitted with a pop up confirming completion
     */
    public void submitIssue() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * Validate email
     */
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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
            // Intent to settings activity
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra("Class", "ReportIssueFormActivity");
            intent.putExtra("reportIssueTitle", mReportIssueTitle);
            startActivity(intent);
            finish();
            return true;
        }
        // User action not recognised, invoke superclass.
        return super.onOptionsItemSelected(item);
    }
    /**
     * Check if user has checked for dyslextia friendly mode
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
     * Auto complete form if user has data saved
     */
    private void autoComplete() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String name = settings.getString("user_full_name", "");
        EditText nameBox = findViewById(R.id.their_name);
        nameBox.setText(name);

        String email = settings.getString("user_email", "");
        EditText emailBox = findViewById(R.id.their_email);
        emailBox.setText(email);

    }

    /**
     * Needed to ensure that theme choice is respected throughout the back stack
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ReportIssuesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
