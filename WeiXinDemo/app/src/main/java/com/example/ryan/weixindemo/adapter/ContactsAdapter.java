package com.example.ryan.weixindemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.ryan.weixindemo.R;
import com.example.ryan.weixindemo.infoobject.Contact;

import java.util.HashMap;
import java.util.List;

public class ContactsAdapter extends BaseAdapter implements SectionIndexer {
    public static final String TAG = "ContactsAdapter";
    protected HashMap<String, Integer> indexerHashMap = new HashMap<>();
    protected Context mContext = null;
    protected LayoutInflater mInflater;
    private String[] mSections = {"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};//"#ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String[] SPECIAL_CONTACT = {"Recommended Friends", "Group Chats", "Tags", "Official Accounts"};
    private String[] PUBLISH_CONTACT = {"乱七八糟", "明剑馆", "偶去无", "诶红蛇一"};
    private final String[] mBeginningStrings = {"a ", "A ", "an ", "An ", "the ", "The "};
    private List<Contact> contacts;

    public ContactsAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setInitData(List<Contact> data) {
        Log.e(TAG, "setInitData=" + data.size());
        this.contacts = data;
        for (int i = 0; i < contacts.size(); i++) {
            String stringResult = contacts.get(i).getName();
            for (String string : mBeginningStrings) {
                if (stringResult.startsWith(string)) {
                    stringResult = stringResult.substring(string.length());
                    break;
                }
            }
            String musicTitleFirstLetter = stringResult.substring(0, 1).toUpperCase();
            if (!indexerHashMap.containsKey(musicTitleFirstLetter)) {
                indexerHashMap.put(musicTitleFirstLetter, i);
                Log.i(TAG, "musicTitleFirstLetter=" + musicTitleFirstLetter);
                Contact contact = new Contact();
                contact.setName(musicTitleFirstLetter);
                contact.setTitle(true);
                contacts.add(i, contact);
            }
        }
        for (int i = 0; i < PUBLISH_CONTACT.length; i++) {
            Contact contact = new Contact();
            contact.setName(PUBLISH_CONTACT[i]);
            contacts.add(i, contact);
        }
        Contact contact2 = new Contact();
        contact2.setName("公众号");
        contact2.setTitle(true);
        contacts.add(0, contact2);

        for (int i = 0; i < SPECIAL_CONTACT.length; i++) {
            Contact contact = new Contact();
            contact.setName(SPECIAL_CONTACT[i]);
            contacts.add(i, contact);
        }

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return contacts.get(i);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.contact_list_item, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.contact_tv);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.contact_iv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Contact contact = contacts.get(position);
        if (contact.isTitle()) {
            viewHolder.icon.setVisibility(View.GONE);
            convertView.setEnabled(false);
        } else {
            viewHolder.icon.setVisibility(View.VISIBLE);
            convertView.setEnabled(true);
        }
        viewHolder.name.setText(contact.getName());
        return convertView;

    }

    private static class ViewHolder {
        private TextView name;
        private ImageView icon;
    }

    @Override
    public Object[] getSections() {
        return mSections;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        //This for loop is to return the previous section if the section we want has no tracks
        if (indexerHashMap.containsKey(mSections[sectionIndex])) {
            //if the musicTitle matches the section of
            return indexerHashMap.get(mSections[sectionIndex]);
        }
        return 0; // Couldn't find anything
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }
}
