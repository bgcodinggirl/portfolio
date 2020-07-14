package com.example.contacts.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contacts.Model.Contact;
import com.example.contacts.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
   Context context;
   ArrayList<Contact> arrayList;

   public MyAdapter(Context context,ArrayList<Contact> arrayList){
       this.context = context;
       this.arrayList = arrayList;
   }
    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.my_custom_list_view,null);
        ImageView imgIco=(ImageView) convertView.findViewById(R.id.img_ico);
        TextView nameText=(TextView)convertView.findViewById(R.id.name_txt);

        Contact contact = arrayList.get(position);
        imgIco.setImageResource(R.mipmap.ic_contact_round);
        nameText.setText(contact.getFirstName()+" "+contact.getLastName());

        return convertView;
    }
}
