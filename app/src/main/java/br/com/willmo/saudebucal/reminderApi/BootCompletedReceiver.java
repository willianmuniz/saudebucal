package br.com.willmo.saudebucal.reminderApi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by @WillianMuniz on 8/9/2016.
 */
public class BootCompletedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent arg1) {
        try {
            Log.d("BootCompletedReceiver", "iniciadoooo com aandroid");

            Intent service = new Intent(context, ReminderService.class);
            service.setAction(ReminderService.PARAM_SETALL_ALARMS);
            context.startService(service);
        } catch (Exception e) {
            Log.e("BootCompletedReceiver", "onReceive", e);
        }
    }

}
