package com.imaginecup.bug5.vista.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import com.github.pwittchen.swipe.library.Swipe;
import com.github.pwittchen.swipe.library.SwipeListener;
import com.imaginecup.bug5.vista.R;
import com.imaginecup.bug5.vista.fragment.MainFragment;
import com.imaginecup.bug5.vista.utils.Constants;

public class MainActivity extends AppCompatActivity {

    Swipe swipe;
    TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainer, MainFragment.newInstance(), Constants.Fragments.MAIN_FRAGMENT)
                .commit();*/

        tvStatus = (TextView) findViewById(R.id.tvStatus);
        swipe = new Swipe();
        swipe.addListener(new SwipeListener() {
            @Override public void onSwipingLeft(final MotionEvent event) {
                tvStatus.setText("SWIPING_LEFT");
            }

            @Override public void onSwipedLeft(final MotionEvent event) {
                tvStatus.setText("SWIPED_LEFT");
            }

            @Override public void onSwipingRight(final MotionEvent event) {
                tvStatus.setText("SWIPING_RIGHT");
            }

            @Override public void onSwipedRight(final MotionEvent event) {
                tvStatus.setText("SWIPED_RIGHT");
            }

            @Override public void onSwipingUp(final MotionEvent event) {
                tvStatus.setText("SWIPING_UP");
            }

            @Override public void onSwipedUp(final MotionEvent event) {
                tvStatus.setText("SWIPED_UP");
            }

            @Override public void onSwipingDown(final MotionEvent event) {
                tvStatus.setText("SWIPING_DOWN");
            }

            @Override public void onSwipedDown(final MotionEvent event) {
                tvStatus.setText("SWIPED_DOWN");
            }
        });

    }

    @Override public boolean dispatchTouchEvent(MotionEvent event) {
        swipe.dispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }
}
