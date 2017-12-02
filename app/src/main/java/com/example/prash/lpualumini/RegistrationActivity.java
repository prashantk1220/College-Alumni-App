package com.example.prash.lpualumini;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = RegistrationActivity.class.getSimpleName();
    String sname,suser,spass,sconfirm,semail,sphone,scollegeId,swork;
    EditText name,user,pass,confirm,email,phone,collegeId,work;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name=(EditText)findViewById(R.id.editText3);
        //user=(EditText)findViewById(R.id.editText4);
        pass=(EditText)findViewById(R.id.editText6);
        confirm=(EditText)findViewById(R.id.editText7);
        email=(EditText)findViewById(R.id.editText8);
        phone=(EditText)findViewById(R.id.editText9);
        collegeId=(EditText)findViewById(R.id.editText5);
        work=(EditText)findViewById(R.id.editText10);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          //      Snackbar.make(view, "Some Fields missing or incorrect", Snackbar.LENGTH_LONG).show();

                sname = name.getText().toString();
                //suser = user.getText().toString();
                spass = pass.getText().toString();
                sconfirm = confirm.getText().toString();
                semail = email.getText().toString();
                sphone = phone.getText().toString();
                scollegeId = collegeId.getText().toString();
                swork = work.getText().toString();

                if (sname.equals("") || spass.equals("")||sconfirm.equals("")||semail.equals("")||sphone.equals("")||scollegeId.equals("")||swork.equals("")) {

                    if (sname.equals("")) {

                        Toast.makeText(RegistrationActivity.this, "Name should not be empty", Toast.LENGTH_LONG).show();
                    } else if (spass.equals("")) {

                        Toast.makeText(RegistrationActivity.this, "Password should not be empty", Toast.LENGTH_LONG).show();
                    } else if (sconfirm.equals("")) {

                        Toast.makeText(RegistrationActivity.this, "Confirm password  should not be empty", Toast.LENGTH_LONG).show();
                    } else if (semail.equals("")) {
                        Toast.makeText(RegistrationActivity.this, "email should not be empty", Toast.LENGTH_LONG).show();

                    } else if (sphone.equals("")) {
                        Toast.makeText(RegistrationActivity.this, "phone should not be empty", Toast.LENGTH_LONG).show();

                    } else if (scollegeId.equals("")) {
                        Toast.makeText(RegistrationActivity.this, "College Id should not be empty", Toast.LENGTH_LONG).show();

                    } else if (swork.equals("")) {
                        Toast.makeText(RegistrationActivity.this, "Working in should not be empty", Toast.LENGTH_LONG).show();

                    } else if (!(spass.equals(sconfirm))) {
                        Toast.makeText(RegistrationActivity.this, "password does not match", Toast.LENGTH_LONG).show();

                    }
                }

                else {
                    SQLiteDatabase db=openOrCreateDatabase("AluminiDB",MODE_WORLD_WRITEABLE,null);
                    db.execSQL("create table if not exists AluminiData2(name VARCHAR, pass VARCHAR, email VARCHAR, phone VARCHAR, work VARCHAR, rollid VARCHAR, feed VARCHAR)");
                    String q1="Select count(*) from AluminiData2 where rollid='"+scollegeId+"';";
                    Cursor c1=db.rawQuery(q1, null);
                    c1.moveToFirst();
                    int count= c1.getInt(0);
                    Log.d(TAG, "Count value=="+count);
                    if(count!=0)
                    {
                        Toast.makeText(RegistrationActivity.this, " Username Already Exists", Toast.LENGTH_SHORT).show();
                    }
                    else if(count==0)
                    {
                        ContentValues values = new ContentValues();
                        values.put("name", sname);
                        values.put("pass", spass);
                        values.put("email", semail);
                        values.put("phone", sphone);
                        values.put("work", swork);
                        values.put("rollid", scollegeId);
                        db.insert("AluminiData2", null, values);
                        Toast.makeText(RegistrationActivity.this, " Registered Successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(RegistrationActivity.this, LogonActivity.class);

                        startActivity(i);
                    }
                    c1.close();



                }

            }
        });
    }

}
