package com.team9.seatonvalley;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TimelineResult;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import java.lang.ref.WeakReference;

/**
 * @Author: Adrienne Hui
 * @Since: 26/04/2018
 *
 * A class to display Seaton Valley Community Council's Twitter feed (@SeatonValleyCC) to the user
 */

public class TweetsActivity extends AppCompatActivity {

    final WeakReference<Activity> activityRef = new WeakReference<Activity>(TweetsActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        checkTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets);

        Toolbar servicesToolbar = findViewById(R.id.services_toolbar);
        servicesToolbar.setTitle(R.string.tweets_title);
        setSupportActionBar(servicesToolbar);

        ActionBar servicesLocationActionBar = getSupportActionBar();

        servicesLocationActionBar.setDisplayHomeAsUpEnabled(true);

        final SwipeRefreshLayout swipeLayout = findViewById(R.id.swipe_layout);
        final View emptyView = findViewById(android.R.id.empty);
        final ListView listView = findViewById(android.R.id.list);
        listView.setEmptyView(emptyView);

        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("SeatonValleyCC")//any screen name
                .includeReplies(true)
                .maxItemsPerRequest(50)
                .build();

        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .setViewStyle(R.style.tw__TweetLightStyle)
                .build();
        listView.setAdapter(adapter);

        swipeLayout.setColorSchemeResources(R.color.colorTwitterBlue, R.color.colorTwitterDark);

        // set custom scroll listener to enable swipe refresh layout only when at list top
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            boolean enableRefresh = false;
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {}
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                if (listView != null && listView.getChildCount() > 0) {
                    // check that the first item is visible and that its top matches the parent
                    enableRefresh = listView.getFirstVisiblePosition() == 0 &&
                            listView.getChildAt(0).getTop() >= 0;
                } else {
                    enableRefresh = false;
                }
                swipeLayout.setEnabled(enableRefresh);
            }
        });

        // Allows the user to refresh the feed when pulling down at the top of the feed
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(true);
                adapter.refresh(new Callback<TimelineResult<Tweet>>() {
                    @Override
                    public void success(Result<TimelineResult<Tweet>> result) {
                        swipeLayout.setRefreshing(false);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        swipeLayout.setRefreshing(false);
                        final Activity activity = activityRef.get();
                        if (activity != null && !activity.isFinishing()) {
                            Toast.makeText(activity, exception.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    /**
     * Set up toolbar menu
     */
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
            intent.putExtra("Class", "TweetsActivity");
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
        setTheme(R.style.activity_layout);

    }

    /**
     * Needed to ensure that theme choice is respected throughout the back stack.
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
