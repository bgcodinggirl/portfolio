package com.example.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.contacts.Model.Contact;
import java.util.ArrayList;
import java.util.Collections;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static  final String DATABASE_NAME="ContactsApp.db";
    private static final int DATABASE_VERSION=1;
    public   static final String TABLE_NAME="Contacts";
    public  static final String ID = "Id";
    public  static final String FIRST_NAME ="FirstName";
    public  static final String LAST_NAME ="LastName";
    public  static final String PHONE ="Phone";
    public  static final String DESCRIPTION ="Description";
    public  static final String CATEGORY ="Category";

    private  static final String sqlCreate="CREATE TABLE " + TABLE_NAME+
            " ("+ ID +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" + FIRST_NAME +" NVARCHAR(255) NOT NULL,\n" +
            LAST_NAME +" NVARCHAR(255),\n " +
            PHONE + " NVARCHAR(255) NOT NULL,\n" +
            DESCRIPTION +" NVARCHAR(255),\n" +
            CATEGORY +" NVARCHAR(255) " +
            ")";
    private  static final String sqlDropIfExists="DROP TABLE IF EXISTS "+TABLE_NAME;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("LOG_TAG","Upgrading database from version"+oldVersion+" to version "+newVersion);
        db.execSQL(sqlDropIfExists);
        onCreate(db);
    }
    public boolean updateContact(int id, String fName,String lName,String phone,String description,String category){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIRST_NAME,fName);
        contentValues.put(LAST_NAME,lName);
        contentValues.put(PHONE,phone);
        contentValues.put(DESCRIPTION,description);
        contentValues.put(CATEGORY,category);
        long result = db.update(TABLE_NAME, contentValues, ID+" = ?",new String[]{String.valueOf(id)});
        return  result!=-1;
    }

    public ArrayList<Contact> readData(String sqlQuery){
        ArrayList<Contact> contactsList=new ArrayList<>();
        SQLiteDatabase sqdb=this.getReadableDatabase();
        Cursor cursor = sqdb.rawQuery(sqlQuery,null);
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String fName=cursor.getString(1);
            String lName=cursor.getString(2);
            String phone= cursor.getString(3);
            String desc= cursor.getString(4);
            String category= cursor.getString(5);
            Contact contact = new Contact(id,fName,lName,phone,desc,category);
            contactsList.add(contact);
        }
        Collections.sort(contactsList,Contact.nameComparator);
        return contactsList;
    }
    public ArrayList<Contact> getAllContacts(){
        String selectQuery="SELECT* FROM " +TABLE_NAME;
        return readData(selectQuery);
    }
    public ArrayList<Contact> searchByName(String value){
        String selectQuery="SELECT* FROM "+TABLE_NAME+
                " WHERE "+FIRST_NAME+" LIKE '%" +value+ "%'";
        return readData(selectQuery);
    }
    public ArrayList<Contact> getAllFamilyMembers(){
        String selectQuery="SELECT * FROM "+TABLE_NAME+" "+
                "WHERE "+ CATEGORY+" = 'Family'";
        return readData(selectQuery);
    }
    public ArrayList<Contact> getAllFriends(){
        String selectQuery="SELECT * FROM "+TABLE_NAME+" "+
                "WHERE "+ CATEGORY+" = 'Friends'";
        return readData(selectQuery);
    }
    public ArrayList<Contact> getAllColleagues(){
        String selectQuery="SELECT * FROM "+TABLE_NAME+" "+
                "WHERE "+ CATEGORY+" = 'Colleagues'";
        return readData(selectQuery);
    }
    public ArrayList<Contact> getAllFamiliar(){
        String selectQuery="SELECT * FROM "+TABLE_NAME+" "+
                "WHERE "+ CATEGORY+" = 'Familiar'";
        return readData(selectQuery);
    }

}
