package com.imaginecup.bug5.vista.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.imaginecup.bug5.vista.activity.MainActivity;

/**
 * Created by kataii on 4/19/2017.
 */

public class StartApp extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Intent mainActivityIntent = new Intent(context, MainActivity.class);
            context.startService(mainActivityIntent);
        }
    }
}
