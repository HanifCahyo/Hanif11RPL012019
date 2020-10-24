package com.example.hanif11rpl012020;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {



    SharedPreferences pref;
    SharedPreferences.Editor editor;

    ConstraintLayout constraintLayout;
    TextView tvTimeMsg;
    EditText txtemail;
    EditText txtpassword;
    Button btnsignin;
    Button btnsignup;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences("login", MODE_PRIVATE);

        constraintLayout = findViewById(R.id.container);
        tvTimeMsg = (TextView) findViewById(R.id.tv_time_msg);

        Calendar c = Calendar.getInstance();

        int timeOfDay=c.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 0 && timeOfDay <16) {
            //morning
            constraintLayout.setBackground(getDrawable(R.drawable.good_morning_img));
            tvTimeMsg.setText("Good Morning");
        }
        else if(timeOfDay >=16 && timeOfDay <24) {
            //night
            constraintLayout.setBackground(getDrawable(R.drawable.good_night_img));
            tvTimeMsg.setText("Good Night");
        }

        getSupportActionBar().hide();

        txtemail = (EditText)findViewById(R.id.txtemail);
        txtpassword = (EditText)findViewById(R.id.txtpassword);
        btnsignin = (Button) findViewById(R.id.btnsignin);
        btnsignup = (Button) findViewById(R.id.btnsignup);
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtemail.getText().toString().equalsIgnoreCase("admin")
                        && txtpassword.getText().toString().equalsIgnoreCase("admin")){
                    //saving ke SP
                    editor = pref.edit();
                    editor.putString("username", txtemail.getText().toString());
                    editor.putString("status", "login");
                    editor.apply();
                    //menuju ke main menu
                    startActivity(new Intent(getApplicationContext(), MainMenu.class));
                    finish();
                }
            }
        });

    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
    @Override public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present. getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}