package br.com.willmo.saudebucal.reminderApi;

import android.content.Context;

import br.com.willmo.saudebucal.entity.Contact;
import br.com.willmo.saudebucal.persistent.dao.ContactDao;

/**
 * Created by @WillianMuniz on 7/12/2016.
 */
public class ContactSingleton {

    private static Contact contact;

    public ContactSingleton(Context context) {
        contact = getContact(context);
    }

    public static Contact getContact(Context context) {
        if (contact == null) {
            contact = new ContactDao(context).getContact();
        }
        return contact;
    }
}
