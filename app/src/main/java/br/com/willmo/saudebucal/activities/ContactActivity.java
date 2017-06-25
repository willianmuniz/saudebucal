package br.com.willmo.saudebucal.activities;

import android.os.Bundle;
import android.app.Activity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import br.com.willmo.saudebucal.R;
import br.com.willmo.saudebucal.entity.Contact;
import br.com.willmo.saudebucal.persistent.dao.ContactDao;
import br.com.willmo.saudebucal.persistent.dao.ReminderDao;
import br.com.willmo.saudebucal.tools.Mask;
import br.com.willmo.saudebucal.tools.SqliteUtils;

public class ContactActivity extends Activity {


    //View Objects
    private EditText txName;
    private EditText txPhone;
    private EditText txInitialDate;

    //objects
    private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        mapObjectsFromView();
        instanceObjects();

    }

    public void save(View v) {
        if (validate()) {
            Contact obj = loadObject();
            contactDao.insert(obj);

            //TODO colocar aqui o redirecionamento pra tela inicial
            Toast.makeText(this, getString(R.string.success), Toast.LENGTH_SHORT).show();
            setResult(Activity.RESULT_OK);
            finish();
        }
    }

    private boolean validate() {
        boolean result = true;

        if ((txName.getText() == null) || (txName.getText().toString().trim().length() == 0)) {
            result = false;
            txName.setError(getString(R.string.name_error));
        }
        if ((txPhone.getText() == null) || (txPhone.getText().toString().trim().length() == 0)) {
            result = false;
            txPhone.setError(getString(R.string.phone_error));
        }
        LocalDate initialDate = null;
        if ((txInitialDate.getText() == null) || (txInitialDate.getText().toString().trim().length() == 0)) {
            result = false;
            txInitialDate.setError(getString(R.string.initial_date_error));
        } else {
            try {
                initialDate = LocalDate.parse(txInitialDate.getText().toString(), SqliteUtils.getDateFormatter("dd/MM/yyyy"));
            } catch (IllegalArgumentException e) {
                result = false;
                txInitialDate.setError(getString(R.string.initial_date_invalid));
            }
        }
        if (result) {
            if ((initialDate != null) && (initialDate.compareTo(LocalDate.now()) < 0)) {
                result = false;
                txInitialDate.setError(getString(R.string.initial_date_after_now));
            }
        }


        return result;
    }

    private Contact loadObject() {
        Contact result = new Contact();
        result.setName(txName.getText().toString().trim());
        result.setPhone(txPhone.getText().toString().trim());
        result.setInitialDate(LocalDate.parse(txInitialDate.getText().toString(), SqliteUtils.getDateFormatter("dd/MM/yyyy")));
        return result;
    }

    private void instanceObjects() {
        contactDao = new ContactDao(this);
    }

    private void mapObjectsFromView() {
        txName = (EditText) findViewById(R.id.txName);
        txPhone = (EditText) findViewById(R.id.txPhone);
        txInitialDate = (EditText) findViewById(R.id.txInitialDate);

        //setando mascaras
        txPhone.addTextChangedListener(Mask.insert(Mask.CELULAR_MASK, txPhone));
        txInitialDate.addTextChangedListener(Mask.insert(Mask.DATE_MASK, txInitialDate));
    }


}