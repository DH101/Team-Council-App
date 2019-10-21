package com.team9.seatonvalley;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Dalton Yates
 * @Since: 25/03/2018.
 * @Extended by: Adam Barron
 * @Since: 12/04/2018
 *
 * This class is used to make the Contact Us Activity.
 * It takes the values the user has inputted and sends
 * an email (simulates with a toast pop up appearing).
 * All must have values and email must be valid.
 * Also has a button which takes to the map activity.
 *
 */

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        autoComplete();
        setupActionBar();

        final EditText yourName =  findViewById(R.id.your_name);
        final EditText yourEmail =  findViewById(R.id.your_email);
        final EditText yourSubject =  findViewById(R.id.your_subject);
        final EditText yourMessage =  findViewById(R.id.your_message);

        Button email =  findViewById(R.id.post_message);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // retrieve users inputs
                String name = yourName.getText().toString();
                String email = yourEmail.getText().toString();
                String subject = yourSubject.getText().toString();
                String message = yourMessage.getText().toString();

                // check all EditText are not empty and valid
                if (TextUtils.isEmpty(name)) {
                    yourName.setError(getResources().getString(R.string.contact__name_error));
                    yourName.requestFocus();
                    return;
                }

                if (!isValidEmail(email)) {
                    yourEmail.setError(getResources().getString(R.string.contact__email_error));
                    return;
                }

                if (TextUtils.isEmpty(subject)) {
                    yourSubject.setError(getResources().getString(R.string.contact__subject_error));
                    yourSubject.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(message)) {
                    yourMessage.setError(getResources().getString(R.string.contact__message_error));
                    yourMessage.requestFocus();
                    return;
                }

                //Remove phone,issue text once validated and remove name as well as email but add
                //name and email if user has added it in preferences
                yourName.setText("");
                yourEmail.setText("");
                autoComplete();
                yourSubject.setText("");
                yourMessage.setText("");

                //Create alert if fields are filled in
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(
                        ContactUsActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_success_contact_us_form,
                        null);
                Button mConfirm = mView.findViewById(R.id.button_contact_us_form_confirm);

                // Intent to contact us activity when okay button on view or view is clicked
                mConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // All EditText boxes are valid, intent to contact us activity
                        emailSent();
                    }
                });

                mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // All EditText boxes are valid, intent to contact us activity
                        emailSent();
                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

            }
        });
    }
    /**
     * method for intent to home activity once form is sent
     */
    public void emailSent(){
        onBackPressed();
    }

    /**
     * Go to MapMarkerActivity when button is pressed
     */
    public void findOnMap(View view){
        Intent intent = new Intent(this, MapMarkerActivity.class);
        startActivity(intent);
    }

    /**
     * Set up the action bar
     */
    private void setupActionBar() {
        Toolbar contactUsToolbar = findViewById(R.id.contact_us_toolbar);
        contactUsToolbar.setTitle(R.string.contact_us_title);
        setSupportActionBar(contactUsToolbar);
        // Show the Up button in the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
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
            intent.putExtra("Class", "ContactUsActivity");
            startActivity(intent);
            finish();
            return true;
        }
        // User action not recognised, invoke superclass.
        return super.onOptionsItemSelected(item);
    }

    /**
     * Checks if the user has selected enhanced readability mode
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
     * Needed to ensure that theme choice persists through the back stack
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * Validating email
     */

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Auto completes fields based on the user's inputs in settings
     */
    private void autoComplete() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String name = settings.getString("user_full_name", "");
        EditText nameBox = findViewById(R.id.your_name);
        nameBox.setText(name);

        String email = settings.getString("user_email", "");
        EditText emailBox = findViewById(R.id.your_email);
        emailBox.setText(email);

    }
}
