package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.DecimalFormatSymbols;

public class CreateContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
        final EditText fName = (EditText)findViewById(R.id.editText3);
        final EditText lName = (EditText)findViewById(R.id.editText4);
        final EditText addPhone = (EditText)findViewById(R.id.editText2);
        final EditText desc = (EditText) findViewById(R.id.editText5);
        final RadioButton familyR = (RadioButton) findViewById(R.id.radioButton);
        final RadioButton familiarR = (RadioButton) findViewById(R.id.radioButton3);
        final RadioButton friendsR = (RadioButton) findViewById(R.id.radioButton2);
        final RadioButton colleaguesR = (RadioButton) findViewById(R.id.radioButton4);

        final Button addDataBtn=(Button) findViewById(R.id.button6);
        final Button cancelBtn=(Button) findViewById(R.id.button7);

        final DatabaseHelper mydb = new DatabaseHelper(this);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent myIntent=new Intent(v.getContext(),MainActivity.class);
                startActivity(myIntent);
            }
        });
        addDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase  sqdb = mydb.getWritableDatabase();
                String firstName=fName.getText().toString();
                String lastName=lName.getText().toString();
                String phone=addPhone.getText().toString();
                String description=desc.getText().toString();
                String category="";

                if(familyR.isChecked()){
                    category = "Family";
                }
                else if(familiarR.isChecked()){
                    category = "Familiar";
                }
                else if(friendsR.isChecked()){category = "Friends";}
                else if(colleaguesR.isChecked()){
                    category="Colleagues";
                }
                else {
                    category="";
                }
                if(!firstName.equals("") && !phone.equals("") && isNumeric(phone) ){
                    String insertQuery = "INSERT INTO "+ DatabaseHelper.TABLE_NAME +
                            " (" + DatabaseHelper.FIRST_NAME+", "+DatabaseHelper.LAST_NAME+", "+
                            DatabaseHelper.PHONE+", "+DatabaseHelper.DESCRIPTION + ", "+ DatabaseHelper.CATEGORY
                            + ") VALUES('"+firstName+"','"+lastName+"','"+phone+"','"+description+"','"+category+"');";
                    sqdb.execSQL(insertQuery);
                    Toast.makeText(CreateContact.this,"New contact added!",Toast.LENGTH_SHORT).show();
                    fName.setText("");
                    lName.setText("");
                    addPhone.setText("");
                    desc.setText("");
                    familiarR.setChecked(false);
                    familyR.setChecked(false);
                    friendsR.setChecked(false);
                    colleaguesR.setChecked(false);
                }
                else {
                    Toast.makeText(CreateContact.this,"Adding failed!",Toast.LENGTH_SHORT).show();
                }
                sqdb.close();
                mydb.close();
                Intent toMain=new Intent(v.getContext(),MainActivity.class);
                startActivity(toMain);
            }
        });
    }
    public static boolean isNumeric(String str)
    {
        char plusSign = '+';
        if ( !Character.isDigit( str.charAt( 0 ) ) && str.charAt( 0 ) != plusSign ) return false;

        for ( char c : str.substring( 1 ).toCharArray() )
        {
            if ( !Character.isDigit( c ) )
            {
                return false;
            }
        }
        return true;
    }
}
