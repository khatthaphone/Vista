package com.imaginecup.bug5.vista.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.Toast;

import com.github.pwittchen.swipe.library.Swipe;
import com.github.pwittchen.swipe.library.SwipeListener;
import com.imaginecup.bug5.vista.R;
import com.imaginecup.bug5.vista.fragment.MainFragment;
import com.imaginecup.bug5.vista.fragment.caller.CallerFragment;
import com.imaginecup.bug5.vista.fragment.emergency.EmergencyFragment;
import com.imaginecup.bug5.vista.fragment.smarthome.SmartHomeFragment;
import com.imaginecup.bug5.vista.util.Constants;
import com.imaginecup.bug5.vista.util.CustomDateTime;
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

        getSupportFragmentManager().beginTransaction().add(R.id.contentContainer, MainFragment.newInstance(), Constants.Fragments.MAIN_FRAGMENT).commit();
        currentFragment = Constants.Fragments.MAIN_FRAGMENT;

        swipe = new Swipe();
        speakerbox = new Speakerbox(getApplication());
        swipe.addListener(new SwipeListener() {
            @Override
            public void onSwipingLeft(final MotionEvent event) {
            }

            @Override
            public void onSwipedLeft(final MotionEvent event) {
                switchFragment(DIRECTION_LEFT, currentFragment);
            }

            @Override
            public void onSwipingRight(final MotionEvent event) {
            }

            @Override
            public void onSwipedRight(final MotionEvent event) {
                switchFragment(DIRECTION_RIGHT, currentFragment);
            }

            @Override
            public void onSwipingUp(final MotionEvent event) {
            }

            @Override
            public void onSwipedUp(final MotionEvent event) {
                switchFragment(DIRECTION_UP, currentFragment);
            }

            @Override
            public void onSwipingDown(final MotionEvent event) {
            }

            @Override
            public void onSwipedDown(final MotionEvent event) {
                switchFragment(DIRECTION_DOWN, currentFragment);
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
        Fragment fragment = null;
        String fragmentTag = null;

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

                        speakerbox.play("It's " + CustomDateTime.formatDate(CustomDateTime.getCurrentDateTime()) + "minute");

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
                        fragment = MainFragment.newInstance();
                        fragmentTag = Constants.Fragments.MAIN_FRAGMENT;
                        fragmentTransaction.setCustomAnimations(R.anim.anim_swipe_from_left, R.anim.anim_swipe_to_right);

                        Toast.makeText(getApplicationContext(), Constants.Fragments.MAIN_FRAGMENT, Toast.LENGTH_SHORT).show();
                        break;
                    case DIRECTION_DOWN:

                        break;
                }

                break;

            case Constants.Fragments.SMART_HOME_FRAGMENT:

                switch (direction) {
                    case DIRECTION_LEFT:
                        fragment = MainFragment.newInstance();
                        fragmentTag = Constants.Fragments.MAIN_FRAGMENT;
                        fragmentTransaction.setCustomAnimations(R.anim.anim_swipe_from_right, R.anim.anim_swipe_to_left);

                        Toast.makeText(getApplicationContext(), Constants.Fragments.MAIN_FRAGMENT, Toast.LENGTH_SHORT).show();
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

        if (fragment != null && fragmentTag != null) {
            fragmentTransaction.replace(R.id.contentContainer, fragment, fragmentTag);
            fragmentTransaction.commit();
            this.currentFragment = fragmentTag;
        }
    }
}
