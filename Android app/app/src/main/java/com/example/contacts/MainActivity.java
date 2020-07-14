package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.contacts.Adapters.MyAdapter;
import com.example.contacts.Model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyAdapter myAdapter;
    ListView listView;
    ArrayList<Contact> contactsList;
    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button createBtn=(Button) findViewById(R.id.button);
        final Button familyContactsBtn=(Button) findViewById(R.id.button3);
        final Button friendContactsBtn=(Button) findViewById(R.id.button4);
        final Button clgContactsBtn=(Button) findViewById(R.id.button8);
        final Button allContactsBtn=(Button) findViewById(R.id.button11);
        final Button familiarContactsBtn=(Button) findViewById(R.id.button5);
        final EditText searchText=(EditText)findViewById(R.id.editText);

        listView = (ListView)findViewById(R.id.listView);
        mydb=new DatabaseHelper(this);
        contactsList = new ArrayList<>();
        loadContactsInListView();

        searchText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction()==KeyEvent.ACTION_DOWN && keyCode==KeyEvent.KEYCODE_ENTER){
                   search(searchText.getText().toString());
                }
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int itemId = contactsList.get(position).getId();
                String fName = contactsList.get(position).getFirstName();
                String lName = contactsList.get(position).getLastName();
                String phone = contactsList.get(position).getPhone();
                String desc = contactsList.get(position).getDescription();
                String category = contactsList.get(position).getCategory();
                Intent editActivity = new Intent(MainActivity.this,EditActivity.class);
                editActivity.putExtra("ITEM_ID",itemId);
                editActivity.putExtra("FIRST_NAME",fName);
                editActivity.putExtra("LAST_NAME",lName);
                editActivity.putExtra("PHONE",phone);
                editActivity.putExtra("DESCRIPTION",desc);
                editActivity.putExtra("CATEGORY",category);
                startActivity(editActivity);

            }
        });

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent myIntent=new Intent(v.getContext(),CreateContact.class);
                startActivity(myIntent);
            }
        });
        allContactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadContactsInListView();
            }
        });
        familyContactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFamily();
            }
        });
        friendContactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFriends();
            }
        });
        familiarContactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showFamiliar();
            }
        });
        clgContactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showColleagues();
            }
        });

    }
    public void loadContactsInListView(){
        contactsList = mydb.getAllContacts();
        myAdapter = new MyAdapter(this,contactsList);
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
    public void search(String value){
        contactsList = mydb.searchByName(value);
        myAdapter = new MyAdapter(this,contactsList);
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
    public void showFriends(){
        contactsList = mydb.getAllFriends();
        myAdapter = new MyAdapter(this,contactsList);
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
    public void showFamily(){
        contactsList = mydb.getAllFamilyMembers();
        myAdapter = new MyAdapter(this,contactsList);
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
    public void showColleagues(){
        contactsList = mydb.getAllColleagues();
        myAdapter = new MyAdapter(this,contactsList);
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
    public void showFamiliar(){
        contactsList = mydb.getAllFamiliar();
        myAdapter = new MyAdapter(this,contactsList);
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
}
