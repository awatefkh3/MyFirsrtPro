package com.example.myfirsrtpro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationReceiver extends BroadcastReceiver {

    //this class is initiated when a broadcast is received from the OS-operating system
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context,NotificationIntentService.class);
        context.startService(intent1);
    }

}
