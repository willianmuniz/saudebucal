package br.com.willmo.saudebucal.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.willmo.saudebucal.R;
import br.com.willmo.saudebucal.reminderApi.ReminderService;
import br.com.willmo.saudebucal.reminderApi.ScheduleReminder;
import br.com.willmo.saudebucal.entity.Contact;
import br.com.willmo.saudebucal.entity.DayType;
import br.com.willmo.saudebucal.entity.Reminder;
import br.com.willmo.saudebucal.persistent.dao.DayDao;
import br.com.willmo.saudebucal.persistent.dao.ReminderDao;
import br.com.willmo.saudebucal.reminderApi.ContactSingleton;
import br.com.willmo.saudebucal.reminderApi.SaudeBucalBO;
import br.com.willmo.saudebucal.tools.Constants;
import br.com.willmo.saudebucal.tools.SendMail;

public class MainActivity extends Activity {

    //class objects
    private ReminderDao reminderDao;
    private DayDao dayDao;
    private SaudeBucalBO saudeBucalBO;

    //View objects
    private ListView historyListView;
    private TextView contactName;
    private TextView contactPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mapeia os objetos da tela
        mapObjectsFromView();

        //instancia os objetos de classe
        instanceClassObjects();

        //verifica se ha contato cadastrado
        verifyLoggedContact();

        //verifica se recebeu alguma acao por intent
        verifyExtrasForAction();

        //carrega os reminders do historico
        loadHistoryReminders();

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadHistoryReminders();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_SING_IN_CONTACT) {
            if (resultCode == Activity.RESULT_OK) {

                ScheduleReminder scheduler = new ScheduleReminder();
                //Carrega os dados iniciais
                List<Reminder> reminders = saudeBucalBO.getInitialData();
                for (Reminder r : reminders) {
                    r.getDay().setId(dayDao.insert(r.getDay()));
                    r.setId(reminderDao.insert(r));

                    scheduler.setAlarm(this, r);
                }

                setContactOnView();
                sendEmailContactRegistered();

            } else {
                Toast.makeText(this, getString(R.string.contact_required), Toast.LENGTH_SHORT).show();
                verifyLoggedContact();
            }
        }

    }

    private void loadHistoryReminders() {
        List<Reminder> listReminders = reminderDao.getList(DayType.HISTORY);

        ArrayAdapter<Reminder> list = new ArrayAdapter<Reminder>
                (this, android.R.layout.simple_list_item_1, listReminders);

        historyListView.setAdapter(list);
        //item para histórico vazio
        TextView emptyText = (TextView) findViewById(android.R.id.empty);
        historyListView.setEmptyView(emptyText);

        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Reminder r = (Reminder) parent.getItemAtPosition(position);
                if (r == null)
                    Log.e("MainActivity", "loadHistoryReminders: " + r.getName());
                else
                    historyItemClickAction(r);
            }
        });

    }

    private void historyItemClickAction(Reminder r) {
        Intent intent = new Intent(getApplicationContext(), ReminderActivity.class);
        intent.putExtra(ReminderActivity.PARAM_SHOW_REMINDER, r);
        startActivity(intent);

    }

    /**
     * Chamado no onCreate para instanciar variaveis de classe
     */
    private void instanceClassObjects() {
        saudeBucalBO
                = new SaudeBucalBO();
        reminderDao = new ReminderDao(this);
        dayDao = new DayDao(this);

    }

    private void mapObjectsFromView() {
        historyListView = (ListView) findViewById(R.id.historyListView);
        contactName = (TextView) findViewById(R.id.contact_name);
        contactPhone = (TextView) findViewById(R.id.contact_phone);
    }

    /**
     * Verifica nos extras se há alguma ação para ser executada
     */
    private void verifyExtrasForAction() {
        try {
            Reminder reminder;
            reminder = (Reminder) getIntent().getExtras().get(ReminderService.PARAM_SCHEDULED_REMINDER);
            if (reminder != null) {
                openReminder(reminder);
            }
        } catch (Exception e) {
            Log.e("MainActivity", "verifyIntentForReminder", e);
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
            } else {
                setContactOnView();
            }
        } catch (Exception e) {
            Log.e("MainActivity", "verifyLoggedContact", e);
        }
    }

    /**
     * Verifica se há um contato cadastrado e exibe a tela de cadastro se não estiver
     */
    private void setContactOnView() {
        try {
            Contact contact = ContactSingleton.getContact(this);
            if (contact != null) {
                contactName.setText(contact.getName());
                contactPhone.setText(contact.getPhone());
            }
        } catch (Exception e) {
            Log.e("MainActivity", "setContactOnView", e);
        }
    }


    /**
     * Abre o reminder quando é recebido por intent
     *
     * @param reminder
     */
    private void openReminder(Reminder reminder) {
        Intent intent = new Intent(this, ReminderActivity.class);
        intent.putExtra(ReminderActivity.PARAM_SHOW_REMINDER, reminder);
        startActivity(intent);
    }

    private void sendEmailContactRegistered() {
        Contact contact = ContactSingleton.getContact(this);

        //enviando email
        //Getting content for email
        String email = Constants.PARAM_EMAIL_USER;
        String subject = "[SaúdeBucalApp]";
        String message = "Nome: " + contact.getName() + "<br/>"
                + "Telefone: " + contact.getPhone() + "<br/>"
                + "Data Inicial: " + contact.getInitialDate().toString("dd/MM/yyyy");
        try {

            //Creating SendEmail object
            SendMail sm = new SendMail(this, email, subject, message);

            //Executing sendmail to send email
            sm.execute();
        } catch (Exception e) {
            Log.e("MainActivity", "onresulreceive", e);
        }
    }

    /**
     * Criação do menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item_about:
                itemAboutAction();
                return true;
            case R.id.item_history:
                itemHistoryAction();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void itemAboutAction() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    private void itemHistoryAction() {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
}
