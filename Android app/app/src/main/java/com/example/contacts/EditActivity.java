package com.example.contacts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
         final EditText updFName = (EditText)findViewById(R.id.editText7);
        final EditText updLName = (EditText)findViewById(R.id.editText8);
        final EditText updPhone = (EditText)findViewById(R.id.editText11);
        final EditText updDesc = (EditText) findViewById(R.id.editText9);
        final EditText updCtg = (EditText) findViewById(R.id.editText12);
        updCtg.setVisibility(View.INVISIBLE);

        final RadioButton familyRB = (RadioButton) findViewById(R.id.radioButton5);
        final RadioButton familiarRB = (RadioButton) findViewById(R.id.radioButton6);
        final RadioButton friendsRB = (RadioButton) findViewById(R.id.radioButton7);
        final RadioButton colleaguesRB = (RadioButton) findViewById(R.id.radioButton8);

        final Button saveBtn=(Button) findViewById(R.id.button9);
        final Button backBtn=(Button) findViewById(R.id.button10);
        final ImageButton deleteBtn=(ImageButton)findViewById(R.id.imageButton);

        final DatabaseHelper mydb = new DatabaseHelper(this);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMain = new Intent(v.getContext(),MainActivity.class);
                startActivity(backToMain);
            }
        });
       Intent getDataIntent = getIntent();
        final int selectedId = getDataIntent.getIntExtra("ITEM_ID",-1);
        String oldFName = getDataIntent.getStringExtra("FIRST_NAME");
        String oldLName = getDataIntent.getStringExtra("LAST_NAME");
        String oldPhone = getDataIntent.getStringExtra("PHONE");
        String oldDesc = getDataIntent.getStringExtra("DESCRIPTION");
        String oldCtg = getDataIntent.getStringExtra("CATEGORY");
       switch (oldCtg){
           case "Familiar":{ familiarRB.setChecked(true);break; }
           case "Family":{ familyRB.setChecked(true);break; }
           case "Colleagues":{ colleaguesRB.setChecked(true);break; }
           case "Friends":{ friendsRB.setChecked(true);break; }
       }
        updFName.setText(oldFName);
        updLName.setText(oldLName);
        updPhone.setText(oldPhone);
        updDesc.setText(oldDesc);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton5:{ updCtg.setText("Family");break; }
                    case R.id.radioButton6:{updCtg.setText("Familiar");break; }
                    case R.id.radioButton7:{ updCtg.setText("Friends");break; }
                    case R.id.radioButton8:{ updCtg.setText("Colleagues");break; }
                }
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(isNumeric(updPhone.getText().toString()) && mydb.updateContact(selectedId,
                        (updFName.getText().toString()),
                        (updLName.getText().toString()),(updPhone.getText().toString()),
                        (updDesc.getText().toString()), updCtg.getText().toString()))
                { Toast.makeText(v.getContext(),"Contact updated!",Toast.LENGTH_SHORT).show();
                    Intent backToMain = new Intent(v.getContext(),MainActivity.class);
                    startActivity(backToMain);
                }
                else{ Toast.makeText(v.getContext(),"Update failed!",Toast.LENGTH_SHORT).show(); }
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
              new AlertDialog.Builder(v.getContext())
                      .setTitle("Delete contact")
                      .setMessage("Do you want to delete the person from your contacts?")
                      .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                              SQLiteDatabase sqdb = mydb.getWritableDatabase();
                              String deleteQuery="DELETE FROM "+DatabaseHelper.TABLE_NAME+
                                      " WHERE "+DatabaseHelper.ID+" = "+String.valueOf(selectedId);
                              sqdb.execSQL(deleteQuery);
                              Toast.makeText(EditActivity.this,"Contact deleted!",
                                      Toast.LENGTH_SHORT).show();
                              sqdb.close();
                              mydb.close();
                              Intent back=new Intent(v.getContext(),MainActivity.class);
                              startActivity(back);
                          }
                      })
                      .setNegativeButton(android.R.string.no,null).setIcon(android.R.drawable.ic_dialog_alert).show();
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
