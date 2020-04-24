package com.snappmi.covid19_ng;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class LearnSkillActivity extends AppCompatActivity {

    private ImageView learn_skill, blogger, child_psychology, cooking, drawing, graphic_design, leadership, marketing, meditation,
            language, photography, programming, public_speaking, time_management, writing, back_icon;

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
        setContentView(R.layout.activity_learn_skill);

        //bind views

        learn_skill = findViewById(R.id.blockChainIcon);
        blogger = findViewById(R.id.bloggingIcon);
        child_psychology = findViewById(R.id.psychologyIcon);
        cooking = findViewById(R.id.cookingIcon);
        drawing = findViewById(R.id.drawingIcon);
        graphic_design = findViewById(R.id.graphicDesignIcon);
        leadership = findViewById(R.id.leadershipIcon);
        marketing = findViewById(R.id.marketingIcon);
        meditation = findViewById(R.id.meditationIcon);
        language = findViewById(R.id.languageIcon);
        photography = findViewById(R.id.photographyIcon);
        programming = findViewById(R.id.programmingIcon);
        public_speaking = findViewById(R.id.publicSpeakingIcon);
        time_management = findViewById(R.id.timeManagemntIcon);
        writing = findViewById(R.id.writingIcon);
        back_icon = findViewById(R.id.backIcon);

        Glide.with(this).load(R.drawable.ic_blockchain).apply(RequestOptions.circleCropTransform()).into(learn_skill);
        Glide.with(this).load(R.drawable.ic_blogging).apply(RequestOptions.circleCropTransform()).into(blogger);
        Glide.with(this).load(R.drawable.ic_family).apply(RequestOptions.circleCropTransform()).into(child_psychology);
        Glide.with(this).load(R.drawable.ic_cook).apply(RequestOptions.circleCropTransform()).into(cooking);
        Glide.with(this).load(R.drawable.ic_drawing).apply(RequestOptions.circleCropTransform()).into(drawing);
        Glide.with(this).load(R.drawable.ic_graphic).apply(RequestOptions.circleCropTransform()).into(graphic_design);
        Glide.with(this).load(R.drawable.ic_leadership).apply(RequestOptions.circleCropTransform()).into(leadership);
        Glide.with(this).load(R.drawable.ic_marketing).apply(RequestOptions.circleCropTransform()).into(marketing);
        Glide.with(this).load(R.drawable.ic_meditation).apply(RequestOptions.circleCropTransform()).into(meditation);
        Glide.with(this).load(R.drawable.ic_language).apply(RequestOptions.circleCropTransform()).into(language);
        Glide.with(this).load(R.drawable.ic_camera).apply(RequestOptions.circleCropTransform()).into(photography);
        Glide.with(this).load(R.drawable.ic_code).apply(RequestOptions.circleCropTransform()).into(programming);
        Glide.with(this).load(R.drawable.ic_public_speaking).apply(RequestOptions.circleCropTransform()).into(public_speaking);
        Glide.with(this).load(R.drawable.ic_time_management).apply(RequestOptions.circleCropTransform()).into(time_management);
        Glide.with(this).load(R.drawable.ic_writing).apply(RequestOptions.circleCropTransform()).into(writing);

        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
