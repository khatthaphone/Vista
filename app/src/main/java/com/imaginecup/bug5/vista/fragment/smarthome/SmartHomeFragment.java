package com.imaginecup.bug5.vista.fragment.smarthome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imaginecup.bug5.vista.R;
import com.mapzen.speakerbox.Speakerbox;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class SmartHomeFragment extends Fragment {

    Speakerbox speakerbox;

    public SmartHomeFragment() {
        super();
    }

    public static SmartHomeFragment newInstance() {
        SmartHomeFragment fragment = new SmartHomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        speakerbox = new Speakerbox(getActivity().getApplication());
        speakerbox.play("Smart Home Screen");

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);

        speakerbox = new Speakerbox(getActivity().getApplication());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_smart_home, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState
        speakerbox.play("You are on Smart Home");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
    }

}
