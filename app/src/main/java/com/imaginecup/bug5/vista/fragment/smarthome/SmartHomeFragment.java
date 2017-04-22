package com.imaginecup.bug5.vista.fragment.smarthome;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imaginecup.bug5.vista.R;
import com.imaginecup.bug5.vista.dao.SmartHome;
import com.mapzen.speakerbox.Speakerbox;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class SmartHomeFragment extends Fragment {

    private Speakerbox speakerbox;

    private MobileServiceClient mClient;
    private MobileServiceTable<SmartHome> mSmartHomeTable;

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

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);

        speakerbox = new Speakerbox(getActivity().getApplication());

        try {
            mClient = new MobileServiceClient(
                    "http://bug5testtodoapp.azurewebsites.net/", getActivity());

            mClient.setAndroidHttpClientFactory(new OkHttpClientFactory() {
                @Override
                public OkHttpClient createOkHttpClient() {


                    OkHttpClient client = new OkHttpClient();
                    client.setReadTimeout(20, TimeUnit.SECONDS);
                    client.setWriteTimeout(20, TimeUnit.SECONDS);

                    return client;
                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        mSmartHomeTable = mClient.getTable("smart_home", SmartHome.class);

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

        speakerbox.play(getResources().getString(R.string.smart_home_screen_txt));
        task.execute();
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

    AsyncTask<Void, Void, List<SmartHome>> task = new AsyncTask<Void, Void, List<SmartHome>>() {
        @Override
        protected List<SmartHome> doInBackground(Void... params) {
            List<SmartHome> result = null;

            try {
                result = mSmartHomeTable.execute().get();
            } catch (InterruptedException | ExecutionException | MobileServiceException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(List<SmartHome> smartHomes) {
            super.onPostExecute(smartHomes);

            if (smartHomes != null) {
                Log.e("onPostExecute", "size: " + smartHomes.size());
            }else {
                Log.e("onPostExecute", "Data = null");
            }
        }
    };

}
