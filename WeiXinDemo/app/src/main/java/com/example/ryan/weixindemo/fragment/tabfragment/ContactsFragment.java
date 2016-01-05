package com.example.ryan.weixindemo.fragment.tabfragment;

import android.app.LoaderManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ryan.weixindemo.R;
import com.example.ryan.weixindemo.common.AppConfig;
import com.example.ryan.weixindemo.infoobject.Contact;
import com.example.ryan.weixindemo.view.SectionIndexAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryan on 12/30/15.
 */
public class ContactsFragment extends BaseFragmen {
    public static final int CONTACT_QUERY_LOADER = 0;
    public static final String QUERY_KEY = "query";
    private ListView listView;
    private SectionIndexAdapter mSectionIndexAdapter;
    private List<Contact> contacts = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contacts_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.contacts_container);
        mSectionIndexAdapter = new SectionIndexAdapter(getActivity());
        listView.setAdapter(mSectionIndexAdapter);
    }

//    private void initDate() {
//        for (int i = 0 ; i < AppConfig.CONTACTS.length;i++){
//            Contact contact = new Contact();
//            contact.setName(AppConfig.CONTACTS[i]);
//            contacts.add(contact);
//        }
//    }

    public class ContactablesLoaderCallbacks implements LoaderManager.LoaderCallbacks<Cursor> {

        Context mContext;

        public static final String QUERY_KEY = "query";

        public static final String TAG = "ContactablesLoaderCallbacks";

        public ContactablesLoaderCallbacks(Context context) {
            mContext = context;
        }

        @Override
        public Loader<Cursor> onCreateLoader(int loaderIndex, Bundle args) {
            // Where the Contactables table excels is matching text queries,
            // not just data dumps from Contacts db.  One search term is used to query
            // display name, email address and phone number.  In this case, the query was extracted
            // from an incoming intent in the handleIntent() method, via the
            // intent.getStringExtra() method.


            String query = args.getString(QUERY_KEY);
            Uri uri = Uri.withAppendedPath(
                   CommonDataKinds.Contactables.CONTENT_FILTER_URI, query);


            // Easy way to limit the query to contacts with phone numbers.
            String selection =
                    CommonDataKinds.Contactables.HAS_PHONE_NUMBER + " = " + 1;

            // Sort results such that rows for the same contact stay together.
            String sortBy = CommonDataKinds.Contactables.LOOKUP_KEY;

            return new CursorLoader(
                    mContext,  // Context
                    uri,       // URI representing the table/resource to be queried
                    null,      // projection - the list of columns to return.  Null means "all"
                    selection, // selection - Which rows to return (condition rows must match)
                    null,      // selection args - can be provided separately and subbed into selection.
                    sortBy);   // string specifying sort order

        }

        @Override
        public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
            if (cursor.getCount() == 0) {
                return;
            }
            contacts.clear();
            // Pulling the relevant value from the cursor requires knowing the column index to pull
            // it from.

            int phoneColumnIndex = cursor.getColumnIndex(CommonDataKinds.Phone.NUMBER);
            int emailColumnIndex = cursor.getColumnIndex(CommonDataKinds.Email.ADDRESS);
            int nameColumnIndex = cursor.getColumnIndex(CommonDataKinds.Contactables.DISPLAY_NAME);
            int lookupColumnIndex = cursor.getColumnIndex(CommonDataKinds.Contactables.LOOKUP_KEY);
            int typeColumnIndex = cursor.getColumnIndex(CommonDataKinds.Contactables.MIMETYPE);


            cursor.moveToFirst();
            // Lookup key is the easiest way to verify a row of data is for the same
            // contact as the previous row.
            String lookupKey = "";
            do {

                String currentLookupKey = cursor.getString(lookupColumnIndex);
                if (!lookupKey.equals(currentLookupKey)) {
                    String displayName = cursor.getString(nameColumnIndex);
                    Contact contact = new Contact();
                    contact.setName(displayName);
                    contacts.add(contact);
                    lookupKey = currentLookupKey;
                }


                // The data type can be determined using the mime type column.
//                String mimeType = cursor.getString(typeColumnIndex);
//                if (mimeType.equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)) {
//                    tv.append("\tPhone Number: " + cursor.getString(phoneColumnIndex) + "\n");
//                } else if (mimeType.equals(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)) {
//                    tv.append("\tEmail Address: " + cursor.getString(emailColumnIndex) + "\n");
//                }


                // Look at DDMS to see all the columns returned by a query to Contactables.
                // Behold, the firehose!
            } while (cursor.moveToNext());
        }

        @Override
        public void onLoaderReset(Loader<Cursor> cursorLoader) {
        }
    }
}
