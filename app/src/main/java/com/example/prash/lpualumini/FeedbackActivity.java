package com.example.prash.lpualumini;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {
    EditText et;
    String sfeed,sname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        et = (EditText)findViewById(R.id.editText11);
        //Bundle b =getIntent().getExtras();
        //sname=b.getString("sname");

    }

    public void complete(View v){

        sfeed=et.getText().toString();
        SQLiteDatabase db = openOrCreateDatabase("AluminiDB", MODE_WORLD_WRITEABLE, null);

        db.execSQL("create table if not exists AluminiData2(name VARCHAR, user VARCHAR, pass VARCHAR, email VARCHAR, phone VARCHAR, work VARCHAR, rollid VARCHAR, feed VARCHAR)");

        ContentValues values = new ContentValues();
        values.put("feed", sfeed);

        db.insert("AluminiData2", null, values);

        Toast.makeText(FeedbackActivity.this, " Feed Submit", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(FeedbackActivity.this,HomeActivity.class);
        //i.putExtra("sfeed",sfeed);
        //i.putExtra("sname",sname);
        startActivity(i);
    }
}
