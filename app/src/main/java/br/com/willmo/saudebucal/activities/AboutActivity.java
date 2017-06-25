package br.com.willmo.saudebucal.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import br.com.willmo.saudebucal.R;
import br.com.willmo.saudebucal.entity.ReminderDateTime;
import br.com.willmo.saudebucal.reminderApi.ReminderService;
import br.com.willmo.saudebucal.reminderApi.ScheduleReminder;
import br.com.willmo.saudebucal.entity.Contact;
import br.com.willmo.saudebucal.entity.Day;
import br.com.willmo.saudebucal.entity.DayType;
import br.com.willmo.saudebucal.entity.Reminder;
import br.com.willmo.saudebucal.persistent.dao.DayDao;
import br.com.willmo.saudebucal.persistent.dao.ReminderDao;
import br.com.willmo.saudebucal.reminderApi.ContactSingleton;
import br.com.willmo.saudebucal.reminderApi.SaudeBucalBO;
import br.com.willmo.saudebucal.tools.Constants;
import br.com.willmo.saudebucal.tools.SendMail;
import br.com.willmo.saudebucal.tools.SqliteUtils;

public class AboutActivity extends Activity {

    private SaudeBucalBO saudeBucalBO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        saudeBucalBO
                = new SaudeBucalBO();

        verifyLoggedContact();

//        notify("teste title", "teste messageasdfasdfas;lkdjfas;djkfa");

//        new ReminderDao(this);
//        new DayDao(this);
//        new ContactDao(this);

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                action();
            }
        });
        Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                actionBt2();
            }
        });

        try {
            Reminder reminder;
            reminder = (Reminder) getIntent().getExtras().get(ReminderService.PARAM_SCHEDULED_REMINDER);
            if (reminder != null) {
                Log.d("REMINDER SCHEDULED", reminder.getName());
                openReminder(reminder);
            }
        } catch (Exception e) {
            Log.e("ERROR/ReminderActivity", e.getMessage() + " eerrror");
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_SING_IN_CONTACT) {
            if (resultCode == Activity.RESULT_OK) {
                ReminderDao reminderDao = new ReminderDao(this);
                DayDao dayDao = new DayDao(this);
                ScheduleReminder scheduler = new ScheduleReminder();
                //Carrega os dados iniciais
                List<Reminder> reminders = saudeBucalBO.getInitialData();
                for (Reminder r : reminders) {
                    r.getDay().setId(dayDao.insert(r.getDay()));
                    r.setId(reminderDao.insert(r));

                    scheduler.setAlarm(this, r);
                }

                Contact contact = ContactSingleton.getContact(this);

                //enviando email
                //Getting content for email
                String email = Constants.PARAM_EMAIL_USER;
                String subject = "[SaúdeBucalApp]";
                String message = "Nome: " + contact.getName() + "<br>"
                        + "Telefone: " + contact.getPhone() + "<br>"
                        + "Data Inicial: " + contact.getInitialDate().toString("dd/MM/yyyy");
                try {

                    //Creating SendEmail object
                    SendMail sm = new SendMail(this, email, subject, message);

                    //Executing sendmail to send email
                    sm.execute();
                } catch (Exception e) {
                    Log.e("MainActivity", "onresulreceive", e);
                }


            } else {
                Toast.makeText(this, getString(R.string.contact_required), Toast.LENGTH_SHORT).show();
                verifyLoggedContact();
            }
        }

    }

    /**
     * Verifica se há um contato cadastrado e exibe a tela de cadastro se não estiver
     */
    private void verifyLoggedContact() {
        try {
            Contact contact = ContactSingleton.getContact(this);

            if ((contact == null)
                    || (contact.getPhone() == null)
                    || (contact.getPhone().trim().length() == 0)) {
                Intent intent = new Intent(this, ContactActivity.class);
                startActivityForResult(intent, Constants.REQUEST_SING_IN_CONTACT);
            }
        } catch (Exception e) {
            Log.e("MainActivity", "verifyLoggedContact", e);
        }
    }

    private void action() {
//        List<Reminder> listReminders = new ArrayList<Reminder>();
//        Reminder r1 = new Reminder("DICA #1");
//        Reminder r2 = new Reminder("DICA #2");
//        Reminder r3 = new Reminder("DICA #3");
//
//        Day d = new Day();
//        d.setType(DayType.TEMPLATE);
//
//        //cadastros teste
//        r1.setText1("Você lembra o que é a placa bacteriana??");
//        r1.setImagePath1("drawable/image1");
//        r1.setText2("Ela causa uma inflamação na gengiva e sangramento gengival.");
//        r1.setImagePath2("drawable/image2");
//        r1.setDay(d);
//        r1.setVideoPath("video1");
//        r2.setText1("Você sabe o que devemos fazer com a placa bacteriana??");
//        r2.setImagePath2("drawable/image1");
//        r2.setText3("Escova e fio dental nela!!");
//        r3.setText1("Assista um vídeo sobre bla bla bla:");
//        r3.setVideoPath("sample");
//
//
//        listReminders.add(r1);
////        listReminders.add(r2);
////        listReminders.add(r3);
//        ScheduleReminder scheduler = new ScheduleReminder();
//
//        for (Reminder r : listReminders) {
//            scheduler.setAlarm(this, r);
//        }
//        openReminder(r1);

        //setar alarmes
        new SaudeBucalBO().setAllAlarms(this);
    }

    private void actionBt2() {
        ReminderDao reminderDao = new ReminderDao(this);
        DayDao dayDao = new DayDao(this);
        //Carrega os dados iniciais
        List<Reminder> reminders = saudeBucalBO.getInitialData();
        for (Reminder r : reminders) {
            r.getDay().setId(dayDao.insert(r.getDay()));
            r.setId(reminderDao.insert(r));

        }
    }

    public void actionBt4(View v) {
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }


    public void actionBt5(View v) {
        //Getting content for email
        String email = Constants.PARAM_EMAIL_USER;
        String subject = "[SaúdeBucalApp]";
        String message = "tsete esse aplicativo agoras <br>teste";

        //Creating SendEmail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();


    }

    public void actionBt6(View v) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    public void actionBt7(View v) {

        int param = 0;
        Day d1 = new Day();
        d1.setName("Dia " + (++param));
        d1.setType(DayType.TEMPLATE);
        d1.setDay(param);
        param = 0;
        Reminder r1 = new Reminder();
        r1.setName("tip" + (++param));
        r1.setText1("tip" + param + "_text1");
        r1.setText2("tip" + param + "_text2");
        r1.setDay(d1);
        r1.setDateTime(LocalDateTime.parse(ReminderDateTime.PREFIX.toString()
                        + d1.getDay() + LocalDateTime.now().plusSeconds(10).toString(" HH:mm:ss"),
                SqliteUtils.getDateTimeFormatter()));

        ScheduleReminder scheduler = new ScheduleReminder();
        scheduler.setAlarm(this, r1);

    }


    private void notify(String notificationTitle, String notificationMessage) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        @SuppressWarnings("deprecation")

        Notification notification = new Notification(R.mipmap.ic_launcher, "New Message", System.currentTimeMillis());
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        notification.setLatestEventInfo(AboutActivity.this, notificationTitle, notificationMessage, pendingIntent);
        notificationManager.notify(1000, notification);
    }

    private void openReminder(Reminder reminder) {
        Intent intent = new Intent(getApplicationContext(), ReminderActivity.class);
        intent.putExtra(ReminderActivity.PARAM_SHOW_REMINDER, reminder);
        startActivity(intent);

    }
}
