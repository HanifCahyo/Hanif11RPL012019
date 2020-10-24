package com.example.hanif11rpl012020;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ListDataFavourite extends AppCompatActivity {

    LinearLayout linearLayout;

    Realm realm;
    RealmHelper realmHelper;
    private RecyclerView recyclerView;
    private DataAdapterFavourite adapter;
    private List<ModelMovieRealm> DataArrayList; //kit add kan ke adapter


    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

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

        getSupportActionBar().hide();

        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        DataArrayList = new ArrayList<>();
        // Setup Realm
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
        DataArrayList = realmHelper.getAllMovie();
        adapter = new DataAdapterFavourite(DataArrayList, new DataAdapterFavourite.Callback() {
            @Override
            public void onClick(int position) {
                Intent move = new Intent(getApplicationContext(), DetailFavourite.class);
                move.putExtra("judul",DataArrayList.get(position).getJudul());
                move.putExtra("path",DataArrayList.get(position).getPath());
                move.putExtra("date",DataArrayList.get(position).getReleaseDate());
                move.putExtra("deskripsi",DataArrayList.get(position).getDesc());

                startActivity(move);
            }


            @Override
            public void test() {

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListDataFavourite.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


}