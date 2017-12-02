package com.example.prash.lpualumini;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class LogonActivity extends AppCompatActivity {

    EditText username, password;
    Button submit,signup,forgotpass;
    CheckBox cb;
    String user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_logon);
        username = (EditText)findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText2);
        forgotpass = (Button)findViewById(R.id.button2);
        submit = (Button)findViewById(R.id.button);
        signup = (Button)findViewById(R.id.button3);
        cb = (CheckBox)findViewById(R.id.checkBox);

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else{
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = username.getText().toString();
                pass = password.getText().toString();
                SQLiteDatabase db=openOrCreateDatabase("AluminiDB",MODE_WORLD_WRITEABLE,null);
                db.execSQL("create table if not exists AluminiData2(name VARCHAR, pass VARCHAR, email VARCHAR, phone VARCHAR, work VARCHAR, rollid VARCHAR, feed VARCHAR)");
                String q1="Select count(*) from AluminiData2 where rollid='"+user+"' and pass='"+pass+"';";
                Cursor c1=db.rawQuery(q1, null);
                c1.moveToFirst();
                int count= c1.getInt(0);

                if(count!=1)
                {
                    Toast.makeText(LogonActivity.this, "Invalid Login Attempt.", Toast.LENGTH_SHORT).show();
                }
                else if(count==1)
                {
                    Intent i = new Intent(LogonActivity.this, HomeActivity.class);

                    //i.putExtra("collegid", user);
                    startActivity(i);
                }

            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager nm = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);

                Notification n = new Notification(
                        android.R.drawable.stat_notify_more,"AlertMessage",
                        System.currentTimeMillis()
                );

                n.defaults=Notification.DEFAULT_ALL; // set light, sound and vibrate
                Intent intent= new Intent(LogonActivity.this, LogonActivity.class);
                PendingIntent pi = PendingIntent.getActivity(LogonActivity.this, 0, intent, 0);

                Notification.Builder builder = new Notification.Builder(LogonActivity.this);

                builder.setAutoCancel(true);
                builder.setTicker("Verification code");
                builder.setContentTitle("New Password");
                builder.setContentText("Link for new passwod is sent to your email ");
                builder.setSmallIcon(R.drawable.lpu);
                builder.setContentIntent(pi);
                builder.setOngoing(false);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    builder.build();
                }

                n = builder.getNotification();
                nm.notify(11, n);

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogonActivity.this, RegistrationActivity.class);
                startActivity(i);
            }
        });



    }
}
