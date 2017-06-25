package br.com.willmo.saudebucal.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.willmo.saudebucal.R;
import br.com.willmo.saudebucal.entity.DayType;
import br.com.willmo.saudebucal.entity.Reminder;
import br.com.willmo.saudebucal.persistent.dao.ReminderDao;

public class HistoryActivity extends Activity {

    private ReminderDao reminderDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        reminderDao = new ReminderDao(this);

        ListView listView
                = (ListView) findViewById(R.id.listView);


        List<Reminder> listReminders = getReminders();

        ArrayAdapter<Reminder> list = new ArrayAdapter<Reminder>(this, android.R.layout.simple_list_item_1, listReminders);

        listView.setAdapter(list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Reminder r = (Reminder) parent.getItemAtPosition(position);
                if (r != null)
                    Log.d("REMINDER CLICKED", r.getName());
                else
                    Log.d("REMINDER CLICKED", "NULL");
                action(r);
            }
        });

    }

    private List<Reminder> getReminders() {
        return reminderDao.getList(DayType.TEMPLATE);
    }

    private void action(Reminder reminder) {
        Intent intent = new Intent(getApplicationContext(), ReminderActivity.class);
        intent.putExtra(ReminderActivity.PARAM_SHOW_REMINDER, reminder);
        startActivity(intent);

    }
}
