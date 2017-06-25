package br.com.willmo.saudebucal.reminderApi;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import org.joda.time.LocalDate;

import java.util.Calendar;

import br.com.willmo.saudebucal.entity.Reminder;

/**
 * Created by @WillianMuniz on 6/28/2016.
 */
public class ScheduleReminder extends WakefulBroadcastReceiver {
    private AlarmManager alarmMgr;

    private PendingIntent alarmIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, ReminderService.class);
        service.setAction(ReminderService.PARAM_SCHEDULED_REMINDER);

        Reminder reminder;
        reminder = (Reminder) intent.getExtras().get(ReminderService.PARAM_SCHEDULED_REMINDER);
        if (reminder != null) {
            service.putExtras(intent);
            Log.d("ON_RECEIVE NOT NULL", reminder.getName());
        }

        startWakefulService(context, service);
    }

    public void setAlarm(Context context, Reminder reminder) {
        alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ScheduleReminder.class);
        intent.putExtra(ReminderService.PARAM_SCHEDULED_REMINDER, reminder);
        alarmIntent = PendingIntent.getBroadcast(context, (int) reminder.getId(), intent, 0);

        LocalDate initialDate = ContactSingleton.getContact(context).getInitialDate();
        initialDate = initialDate.plusDays(reminder.getDateTime().getDayOfMonth() - 1);

        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.YEAR, initialDate.getYear());
        calendar.set(Calendar.MONTH, initialDate.getMonthOfYear() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, initialDate.getDayOfMonth());
        calendar.set(Calendar.HOUR_OF_DAY, reminder.getDateTime().getHourOfDay());
        calendar.set(Calendar.MINUTE, reminder.getDateTime().getMinuteOfHour());
        calendar.set(Calendar.SECOND, reminder.getDateTime().getSecondOfMinute());

        alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
//        Toast.makeText(context, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(calendar.getTime()), Toast.LENGTH_LONG).show();
    }

}
