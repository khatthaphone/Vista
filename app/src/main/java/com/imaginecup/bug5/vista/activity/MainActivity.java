package com.imaginecup.bug5.vista.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import com.github.pwittchen.swipe.library.Swipe;
import com.github.pwittchen.swipe.library.SwipeEvent;
import com.github.pwittchen.swipe.library.SwipeListener;
import com.imaginecup.bug5.vista.R;
import com.imaginecup.bug5.vista.fragment.MainFragment;
import com.imaginecup.bug5.vista.fragment.emergency.SmartHomeFragment;
import com.imaginecup.bug5.vista.utils.Constants;
import com.mapzen.speakerbox.Speakerbox;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Swipe swipe;
    Speakerbox speakerbox;

    TextView tvStatus;

    int x = 0;
    ArrayList<Fragment> screenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add fragment to the List
        screenList = new ArrayList<>();
        screenList.add(MainFragment.newInstance());
        screenList.add(SmartHomeFragment.newInstance());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, MainFragment.newInstance())
                .commit();

        tvStatus = (TextView) findViewById(R.id.tvStatus);

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
                x--;
            }

            @Override public void onSwipingRight(final MotionEvent event) {
                //tvStatus.setText("SWIPING RIGHT");
                speakerbox.play("SWIPING RIGHT");

                Log.e("SwipeLog", "x : " + x);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, SmartHomeFragment.newInstance()).commit();
                x++;

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

    private void switchScreenFragment(int position) {

    }
}
