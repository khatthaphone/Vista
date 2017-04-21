package com.imaginecup.bug5.vista.manager.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.imaginecup.bug5.vista.activity.MainActivity;

/**
 * Created by kataii on 4/21/2017.
 */

public class StartApp extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent mainActivity = new Intent(context, MainActivity.class);
        mainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(mainActivity);
    }
}
