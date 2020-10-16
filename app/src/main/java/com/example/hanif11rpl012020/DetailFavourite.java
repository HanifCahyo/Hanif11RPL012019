package com.example.hanif11rpl012020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailFavourite extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    ModelMovieRealm movieModel;


    Bundle extras;
    String title;
    String date;
    String deskripsi;
    String path;
    String id;

    TextView tvjudul;
    ImageView ivposter;
    TextView tvdesc;
    TextView tvdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favourite);

        getSupportActionBar().hide();

        extras = getIntent().getExtras();
        tvjudul = (TextView) findViewById(R.id.tvjudul);
        tvdesc = (TextView) findViewById(R.id.tvdeskripsi);
        tvdate = (TextView) findViewById(R.id.tvdate);
        ivposter = (ImageView) findViewById(R.id.ivposter);

        if (extras != null) {
            title = extras.getString("judul");
            id = extras.getString("id");
            date = extras.getString("date");
            deskripsi = extras.getString("deskripsi");
            path = extras.getString("path");
            tvjudul.setText(title);
            tvdesc.setText(deskripsi);
            tvdate.setText("Release date :"+date);
            Glide.with(DetailFavourite.this)
                    .load(path)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivposter);
            // and get whatever type user account id is
        }

        //Set up Realm
        Realm.init(DetailFavourite.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
            }

    }
