package com.snappmi.covid19_ng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

public class AboutActivity extends AppCompatActivity {

    private ImageView ib_image, ma_image;
    private LinearLayout contact_ib, contact_ma;
    private ImageView back_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            Window w = getWindow();
            w.setStatusBarColor(Color.TRANSPARENT);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ib_image = findViewById(R.id.ibImage);
        ma_image = findViewById(R.id.maImage);
        contact_ib = findViewById(R.id.contactIb);
        contact_ma = findViewById(R.id.contactMa);
        back_icon = findViewById(R.id.backIcon);

        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        contact_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String subject = "";
                String body = "";
                Uri data = Uri.parse("mailto:ibrahimcodes@gmail.com?subject=" + subject + "&body=" + body);
                intent.setData(data);
                startActivity(intent);
            }
        });

        contact_ma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String subject = "";
                String body = "";
                Uri data = Uri.parse("mailto:reachtoabdul@gmail.com?subject=" + subject + "&body=" + body);
                intent.setData(data);
                startActivity(intent);
            }
        });

        Glide.with(getApplicationContext())
                .load("https://i.ibb.co/QrzsJXw/me.jpg")
                .circleCrop()
                .override(200,200)
                .into(ib_image);
        Glide.with(getApplicationContext())
                .load("https://i.ibb.co/sPsZ4M8/maraya.jpg")
                .circleCrop()
                .override(200,200)
                .into(ma_image);

    }
}
