package com.delaroystudios.analyticsintegration;


import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.skholingua.android.analyticsintegration.R;

import java.util.HashMap;

public class MyAnalytics extends Application {

    // The following line should be changed to include the correct property id.
    private static final String PROPERTY_ID = "UA-76405824-1";

    public static int GENERAL_TRACKER = 0;
    public enum TrackerName {
        APP_TRACKER, // Tracker used only in this app.
        GLOBAL_TRACKER, // Tracker used by all the apps from a company. eg: roll-up tracking.
        ECOMMERCE_TRACKER, // Tracker used by all ecommerce transactions from a company.
    }

    public HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

    public MyAnalytics() {
        super();
    }

    public synchronized Tracker getTracker(TrackerName trackerId) {
        if (!mTrackers.containsKey(trackerId)) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            Tracker t =
                    (trackerId == TrackerName.APP_TRACKER) ? analytics.newTracker(PROPERTY_ID):
                            (trackerId == TrackerName.GLOBAL_TRACKER) ?
                                    analytics.newTracker(R.xml.global_tracker):
                                    analytics.newTracker(R.xml.ecommerce_tracker);
            mTrackers.put(trackerId, t);

        }
        return mTrackers.get(trackerId);
    }
}