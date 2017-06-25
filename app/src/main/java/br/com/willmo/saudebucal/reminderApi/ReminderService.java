package br.com.willmo.saudebucal.reminderApi;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import br.com.willmo.saudebucal.R;
import br.com.willmo.saudebucal.activities.MainActivity;
import br.com.willmo.saudebucal.entity.Reminder;
import br.com.willmo.saudebucal.tools.Utils;

/**
 * Created by @WillianMuniz on 6/28/2016.
 */
public class ReminderService extends IntentService {

    public final static String PARAM_SCHEDULED_REMINDER = "SCHEDULED_REMINDER";
    public final static String PARAM_SETALL_ALARMS = "SETALL_ALARMS";


    public ReminderService() {
        super("ReminderService");
    }

    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent.getAction().equals(PARAM_SETALL_ALARMS)) {
            //quando inicia o android
            new SaudeBucalBO().setAllAlarmsAfterNow(this);
            Log.d("ReminderService", "iniciadoooo com aandroid");

        } else {
            Reminder reminder = (Reminder) intent.getExtras().get(ReminderService.PARAM_SCHEDULED_REMINDER);
            if (reminder != null) {
                sendNotification(reminder);
            }
            ScheduleReminder.completeWakefulIntent(intent);
        }
    }

    // Post a notification indicating whether a doodle was found.
    private void sendNotification(Reminder reminder) {
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        mainActivityIntent.putExtra(ReminderService.PARAM_SCHEDULED_REMINDER, reminder);
        mainActivityIntent.setAction(reminder.getName());
        PendingIntent contentIntent = PendingIntent.getActivity(this, (int) reminder.getId(), mainActivityIntent
                , PendingIntent.FLAG_UPDATE_CURRENT);

        Log.d("sendnotification", reminder.getName() + " notification:" + reminder.getNotificationMessage());
        String message = reminder.getNotificationMessage() != null && reminder.getNotificationMessage().length() > 0 ? Utils.getStringByName(reminder.getNotificationMessage()) : "";
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(Utils.getStringByName(reminder.getName()))
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(message))
                        .setContentText(message);
        mBuilder.setAutoCancel(true);
        mBuilder.setContentIntent(contentIntent);
//        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        mBuilder.setSound(alarmSound);
        Notification note = new Notification();
        note.defaults |= Notification.DEFAULT_VIBRATE;
        note.defaults |= Notification.DEFAULT_SOUND;
        mBuilder.setDefaults(note.defaults);

//        NotificationCompat.InboxStyle inboxStyle =
//                new NotificationCompat.InboxStyle();
//        inboxStyle.setBigContentTitle(Utils.getStringByName(reminder.getName()));
//        inboxStyle.addLine(message);
//        mBuilder.setStyle(inboxStyle);

        mBuilder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

        mNotificationManager.notify((int) reminder.getId(), mBuilder.build());
    }
}
