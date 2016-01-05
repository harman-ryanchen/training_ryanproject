package com.example.ryan.weixindemo.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.ryan.weixindemo.R;
import com.example.ryan.weixindemo.common.AppConfig;
import com.example.ryan.weixindemo.infoobject.Contact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ryan on 1/1/16.
 */
public class SectionIndexAdapter extends BaseAdapter implements SectionIndexer {
    private Context mContext;
    private Map<String, Integer> alphaIndexer;
    private String[] sections;

    public  String[] CONTACTS = new String[]{"Agwegew","Agwgegsd","BBqwrwqfe","Bodsogsd","Agwgegsd","BBqwrwqfe","Bodsogsd","BBqwrwqfe","Bodsogsd","BBqwrwqfe","Bodsogsd","Clsgds","Clsgds","Clsgds","Clsgds","Clsgds","Clsgds","Clsgds","Dzadsaf","Dzadsaf","Dzadsaf","Dzadsaf","Dzadsaf","Dzadsaf","Dzadsaf","Dzadsaf","Dzadsaf","Fvdsbsd","Fvdsbsd","Fvdsbsd","Fvdsbsd","Fvdsbsd","Fvdsbsd","Fvdsbsd","Fvdsbsd","Fvdsbsd","Fvdsbsd","Fvdsbsd","Fvdsbsd","Fmgewhgwe","HHafsdgds","HHafsdgds","HHafsdgds","HHafsdgds","HHafsdgds","HHafsdgds","HHafsdgds","HHafsdgds","HHafsdgds","HHafsdgds","GGsdgdsb","GGhfeh","GGufwegew","fsaga","qfqwr","xvxv","nbngfn","vdbr","wfwqetewt","iuyluyoyu","ouyouy","lpuytjrt","dqwrewt","dfbsdgsd","hfdgsd","jfgjdgj","fdsadas","rewtewtew","tetewtwe","mghmgf","ndfhhwr","nefhrw","fqwff","gsdgag","safasfa","wqewqrwq","gdsgewger","rewteryre","ytrtrh","hdfbdfbdf",};

    private String[] mSections = {"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};//"#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public SectionIndexAdapter(Context context) {
        this.mContext = context;

        alphaIndexer = new HashMap<>();
        int size = CONTACTS.length;
        for (int i = 0; i < size  ; i++) {
            String matchString = CONTACTS[i].substring(0, 1).toUpperCase();
            if (!alphaIndexer.containsKey(matchString)) {
                alphaIndexer.put(matchString, i);
            }
        }
        Set<String> alStrings = alphaIndexer.keySet();
        ArrayList<String> list = new ArrayList<>(alStrings);
        Collections.sort(list);
        sections = new String[list.size()];
        list.toArray(sections);
    }


    @Override
    public Object[] getSections() {
        return sections;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return alphaIndexer.get(sections[sectionIndex]);
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        return CONTACTS.length;
    }

    @Override
    public Object getItem(int position) {
        return CONTACTS[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.contact_list_item,null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.contact_tv);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(CONTACTS[position]);

        return convertView;
    }
    private static class ViewHolder{
        private TextView name;
    }
}
