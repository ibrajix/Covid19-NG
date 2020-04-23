package com.snappmi.covid19_ng;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class ShopActivity extends AppCompatActivity {

    private Spinner states_spinner;
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
        setContentView(R.layout.activity_shop);

        //bind views
        states_spinner = findViewById(R.id.statesSpinner);
        back_icon = findViewById(R.id.backIcon);

        ArrayAdapter<CharSequence> statesAdapter = ArrayAdapter.createFromResource(this, R.array.states_array, R.layout.spinner_text);
        statesAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        states_spinner.setAdapter(statesAdapter);

        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
