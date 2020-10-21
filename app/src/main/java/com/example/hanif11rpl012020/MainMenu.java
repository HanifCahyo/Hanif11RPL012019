package com.example.hanif11rpl012020;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Calendar;

public class MainMenu extends AppCompatActivity {

    LinearLayout linearLayout;
    CardView menu1;
    CardView menu2;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        linearLayout = findViewById(R.id.container);
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 0 && timeOfDay < 16) {
            //morning
            linearLayout.setBackground(getDrawable(R.drawable.good_morning_img));

        } else if (timeOfDay >= 16 && timeOfDay < 24) {
            //night
            linearLayout.setBackground(getDrawable(R.drawable.good_night_img));
        }

            menu1 = (CardView) findViewById(R.id.menu1);
            menu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(), ListData.class));
                }
            });
            menu2 = (CardView) findViewById(R.id.menu2);
            menu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(), ListDataFavourite.class));
                }
            });

    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainMenu.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}