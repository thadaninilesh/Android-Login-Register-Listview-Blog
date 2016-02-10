package com.example.thadaninilesh.androidmysqlapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thadaninilesh on 10-02-2016.
 */
public class ContactAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public ContactAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(ContactsList object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        ContactHolder contactHolder;

        if(row==null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            contactHolder = new ContactHolder();
            contactHolder.tx_name = (TextView)row.findViewById(R.id.et_name);
            contactHolder.tx_email = (TextView)row.findViewById(R.id.et_email);
            contactHolder.tx_mobile = (TextView)row.findViewById(R.id.et_mobile);
            row.setTag(contactHolder);
        }
        else{
            contactHolder = (ContactHolder)row.getTag();
        }

        ContactsList contactsList = (ContactsList)this.getItem(position);
        contactHolder.tx_name.setText(contactsList.getName());
        contactHolder.tx_email.setText(contactsList.getEmail());
        contactHolder.tx_mobile.setText(contactsList.getMobile());
        return row;
    }

    static class ContactHolder{

        TextView tx_name, tx_email, tx_mobile;

    }
}
