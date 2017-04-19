package com.imaginecup.bug5.vista.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.github.pwittchen.swipe.library.Swipe;
import com.github.pwittchen.swipe.library.SwipeListener;
import com.imaginecup.bug5.vista.R;
import com.imaginecup.bug5.vista.fragment.MainFragment;
import com.imaginecup.bug5.vista.fragment.caller.CallerFragment;
import com.imaginecup.bug5.vista.fragment.emergency.EmergencyFragment;
import com.imaginecup.bug5.vista.fragment.smarthome.SmartHomeFragment;
import com.imaginecup.bug5.vista.utils.Constants;
import com.mapzen.speakerbox.Speakerbox;

public class MainActivity extends AppCompatActivity {

    Swipe swipe;
    Speakerbox speakerbox;
    String currentFragment;

    final int DIRECTION_NONE = 0;
    final int DIRECTION_UP = 1;
    final int DIRECTION_RIGHT = 2;
    final int DIRECTION_DOWN = 3;
    final int DIRECTION_LEFT = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentFragment = Constants.Fragments.MAIN_FRAGMENT;

        switchFragment(DIRECTION_NONE, currentFragment);

        swipe = new Swipe();
        speakerbox = new Speakerbox(getApplication());
        swipe.addListener(new SwipeListener() {
            @Override
            public void onSwipingLeft(final MotionEvent event) {
                speakerbox.play("SWIPING LEFT");
            }

            @Override
            public void onSwipedLeft(final MotionEvent event) {
                speakerbox.play("SWIPED LEFT");
                switchFragment(DIRECTION_LEFT, currentFragment);
            }

            @Override
            public void onSwipingRight(final MotionEvent event) {
                speakerbox.play("SWIPING RIGHT");
            }

            @Override
            public void onSwipedRight(final MotionEvent event) {
                speakerbox.play("SWIPED RIGHT");
                switchFragment(DIRECTION_RIGHT, currentFragment);
            }

            @Override
            public void onSwipingUp(final MotionEvent event) {
                speakerbox.play("SWIPING up");
            }

            @Override
            public void onSwipedUp(final MotionEvent event) {
                speakerbox.play("SWIPED up");
            }

            @Override
            public void onSwipingDown(final MotionEvent event) {
                speakerbox.play("SWIPING DOWN");
            }

            @Override
            public void onSwipedDown(final MotionEvent event) {
                speakerbox.play("SWIPED DOWN");
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        swipe.dispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }

    private void switchFragment(int direction, String currentFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = MainFragment.newInstance();
        String fragmentTag = Constants.Fragments.MAIN_FRAGMENT;

        switch (currentFragment) {
            case Constants.Fragments.MAIN_FRAGMENT:

                switch (direction) {
                    case DIRECTION_LEFT:
                        fragment = CallerFragment.newInstance();
                        fragmentTag = Constants.Fragments.CALLER_FRAGMENT;
                        fragmentTransaction.setCustomAnimations(R.anim.anim_swipe_from_right, R.anim.anim_swipe_to_left);

                        Toast.makeText(getApplicationContext(), Constants.Fragments.CALLER_FRAGMENT, Toast.LENGTH_SHORT).show();
                        break;
                    case DIRECTION_RIGHT:
                        fragment = SmartHomeFragment.newInstance();
                        fragmentTag = Constants.Fragments.SMART_HOME_FRAGMENT;
                        fragmentTransaction.setCustomAnimations(R.anim.anim_swipe_from_left, R.anim.anim_swipe_to_right);

                        Toast.makeText(getApplicationContext(), Constants.Fragments.SMART_HOME_FRAGMENT, Toast.LENGTH_SHORT).show();
                        break;
                    case DIRECTION_DOWN:

                        speakerbox.play("It is 12 o'clock");

                        break;
                    case DIRECTION_UP:
                        fragment = EmergencyFragment.newInstance();
                        fragmentTag = Constants.Fragments.EMERGENCY_FRAGMENT;
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                        Toast.makeText(getApplicationContext(), Constants.Fragments.SMART_HOME_FRAGMENT, Toast.LENGTH_SHORT).show();
                        break;
                    default:

                        break;
                }

                break;
            case Constants.Fragments.CALLER_FRAGMENT:

                switch (direction) {
                    case DIRECTION_LEFT:

                        break;
                    case DIRECTION_RIGHT:

                        break;
                    case DIRECTION_DOWN:

                        break;
                }

                break;

            case Constants.Fragments.SMART_HOME_FRAGMENT:

                switch (direction) {
                    case DIRECTION_LEFT:

                        break;
                    case DIRECTION_RIGHT:

                        break;
                    case DIRECTION_DOWN:

                        break;
                }

                break;

            case Constants.Fragments.EMERGENCY_FRAGMENT:

                switch (direction) {
                    case DIRECTION_LEFT:

                        break;
                    case DIRECTION_RIGHT:

                        break;
                    case DIRECTION_DOWN:

                        break;
                }

                break;
        }


        fragmentTransaction.replace(R.id.contentContainer, fragment, fragmentTag);
        fragmentTransaction.commit();
        this.currentFragment = fragmentTag;
    }
}
