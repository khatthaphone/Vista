package com.imaginecup.bug5.vista.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.github.pwittchen.swipe.library.Swipe;
import com.github.pwittchen.swipe.library.SwipeListener;
import com.imaginecup.bug5.vista.R;
import com.imaginecup.bug5.vista.fragment.MainFragment;
import com.imaginecup.bug5.vista.fragment.smarthome.SmartHomeFragment;
import com.mapzen.speakerbox.Speakerbox;

public class MainActivity extends AppCompatActivity{

    Swipe swipe;
    Speakerbox speakerbox;

    TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, MainFragment.newInstance())
                .commit();
//
//        tvStatus = (TextView) findViewById(R.id.tvStatus);

        swipe = new Swipe();
        speakerbox = new Speakerbox(getApplication());
        swipe.addListener(new SwipeListener() {
            @Override public void onSwipingLeft(final MotionEvent event) {
                //tvStatus.setText("SWIPING LEFT");
                speakerbox.play("SWIPING LEFT");
            }

            @Override public void onSwipedLeft(final MotionEvent event) {
                //tvStatus.setText("SWIPED LEFT");
                speakerbox.play("SWIPED LEFT");
            }

            @Override public void onSwipingRight(final MotionEvent event) {
                //tvStatus.setText("SWIPING RIGHT");
                speakerbox.play("SWIPING RIGHT");

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, SmartHomeFragment.newInstance()).commit();
            }

            @Override public void onSwipedRight(final MotionEvent event) {
                //tvStatus.setText("SWIPED RIGHT");
                speakerbox.play("SWIPED RIGHT");
            }

            @Override public void onSwipingUp(final MotionEvent event) {
//                tvStatus.setText("SWIPING UP");
                speakerbox.play("SWIPING up");
            }

            @Override public void onSwipedUp(final MotionEvent event) {
//                tvStatus.setText("SWIPED UP");
               speakerbox.play("SWIPED up");
            }

            @Override public void onSwipingDown(final MotionEvent event) {
//                tvStatus.setText("SWIPING DOWN");
                speakerbox.play("SWIPING DOWN");
            }

            @Override public void onSwipedDown(final MotionEvent event) {
//                tvStatus.setText("SWIPED DOWN");
                speakerbox.play("SWIPED DOWN");
            }
        });

  }

    @Override public boolean dispatchTouchEvent(MotionEvent event) {
        swipe.dispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }
}
