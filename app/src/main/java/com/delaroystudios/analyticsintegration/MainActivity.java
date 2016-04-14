package com.delaroystudios.analyticsintegration;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.skholingua.android.analyticsintegration.R;


public class MainActivity extends ActionBarActivity {

    Tracker t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = ((MyAnalytics)this.getApplication()).getTracker(MyAnalytics.TrackerName.APP_TRACKER);
        t.setScreenName("Home");
        t.send(new HitBuilders.AppViewBuilder().build());

        Button clickButton = (Button) findViewById(R.id.button);
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Build and send an Event.
                t.send(new HitBuilders.EventBuilder()
                        .setCategory("Clicks")  // category i.e. Buttons Action
                        .setAction("Button")    // action i.e.  Button Name
                        .setLabel("clicked")    // label i.e.  any meta-data
                        .build());
            }
        });
    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub

        super.onStart();
        GoogleAnalytics.getInstance(MainActivity.this).reportActivityStart(this);
        //Get an Analytics tracker to report app starts & uncaught exceptions etc.

    }


    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        //Stop the analytics tracking
        GoogleAnalytics.getInstance(MainActivity.this).reportActivityStop(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
