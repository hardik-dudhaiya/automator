package com.smileparser.automator.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.smileparser.automator.App;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mayur on 7/10/18.
 */

public class Utility {

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static Resources getResource() {
        return App.getInstance().getResources();
    }

    public static HashMap<String, String> getContactList(Context context) {
        HashMap<String, String> contactList = new HashMap<>();
        ContentResolver cr = context.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {

                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                    if (pCur != null) {
                        while (pCur.moveToNext()) {
                            String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            contactList.put(name, phoneNo);
                        }
                        pCur.close();
                    }
                }
            }
        }
        if (cur != null) {
            cur.close();
        }

        return contactList;
    }

    public static ArrayList<String> getContactGroups(Context context) {
        ArrayList<String> groupContacts = new ArrayList<>();
        final String[] GROUP_PROJECTION = new String[]{ContactsContract.Groups._ID, ContactsContract.Groups.TITLE};
        Cursor cursor = context.getContentResolver().query(ContactsContract.Groups.CONTENT_URI, GROUP_PROJECTION, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Groups._ID));
                String gTitle = (cursor.getString(cursor.getColumnIndex(ContactsContract.Groups.TITLE)));
                if (!groupContacts.contains(gTitle))
                    groupContacts.add(gTitle);
            }
        }

        if (cursor != null) {
            cursor.close();
        }
        return groupContacts;
    }
}
