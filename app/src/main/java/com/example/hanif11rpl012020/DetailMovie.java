package com.example.hanif11rpl012020;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailMovie extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    ModelMovieRealm movieModel;

    LinearLayout linearLayout;

    Bundle extras;
    String title;
    String date;
    String deskripsi;
    String path;
    String id;
    Boolean adult;
    Integer vote;

    TextView tvjudul;
    ImageView ivposter;
    TextView tvdesc;
    TextView tvdate;
    TextView tvadult;
    TextView tvvote;
    Button btnbookmark;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

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

        extras = getIntent().getExtras();
        tvjudul = (TextView)findViewById(R.id.tvjudul);
        tvdesc = (TextView)findViewById(R.id.tvdeskripsi);
        tvdate = (TextView)findViewById(R.id.tvdate);
        ivposter = (ImageView) findViewById(R.id.ivposter);
        tvadult = (TextView)findViewById(R.id.tvumur);
        tvvote = (TextView)findViewById(R.id.tvvote);
        btnbookmark = (Button) findViewById(R.id.btnbookmark);

        if (extras != null) {
            title = extras.getString("judul");
            id = extras.getString("id");
            date = extras.getString("date");
            deskripsi = extras.getString("deskripsi");
            path = extras.getString("path");
            adult = extras.getBoolean("umur");
            vote = extras.getInt("vote");
            tvjudul.setText(title);
            tvdesc.setText(deskripsi);
            tvdate.setText("Release date :"+date);
            tvadult.setText("Adult :"+adult);
            tvvote.setText("Vote :"+vote);
            Glide.with(DetailMovie.this)
                    .load(path)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivposter);
            // and get whatever type user account id is
        }

        //Set up Realm
        Realm.init(DetailMovie.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);


        btnbookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieModel = new ModelMovieRealm();
                movieModel.setDesc(deskripsi);
                movieModel.setJudul(title);
                movieModel.setPath(path);
                movieModel.setReleaseDate(date);


                realmHelper = new RealmHelper(realm);
                realmHelper.save(movieModel);

            }
        });
    }
}
