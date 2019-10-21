package com.team9.seatonvalley;

import android.app.Application;
import android.util.Log;

import com.team9.seatonvalley.R;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.tweetui.TweetUi;

/**
 * @Author: Adrienne Hui
 * @Since 26/04/2018
 *
 * Runs on startup to initialise twitter functionality
 */
public class SeatonValleyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //initiate Twitter config
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(getResources().getString(
                        R.string.CONSUMER_KEY),
                        getResources().getString(R.string.CONSUMER_SECRET)))
                .debug(true)
                .build();
        Twitter.initialize(config);

        new Thread(new Runnable() {
            @Override
            public void run() {
                TweetUi.getInstance();
            }
        }).start();

    }
}
