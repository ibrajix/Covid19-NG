package com.snappmi.covid19_ng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class LearnSkillActivity extends AppCompatActivity {

    private ImageView learn_skill, blogger, child_psychology, cooking, drawing, graphic_design, leadership, marketing, meditation,
            language, photography, programming, public_speaking, time_management, writing, back_icon;

    private Button read_book;

    private LinearLayout block_chain_layout, blogging_layout, cooking_layout, drawing_layout, graphic_design_layout, language_layout,
    leadershop_layout, management_layout, marketing_layout, meditation_layout, photography_layout, programming_layout, psychology_layout,
    public_speaking_layout, writing_layout;


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
        read_book = findViewById(R.id.readBook);

        block_chain_layout = findViewById(R.id.blockChain);
        blogging_layout = findViewById(R.id.blogging);
        cooking_layout = findViewById(R.id.cooking);
        drawing_layout = findViewById(R.id.drawing);
        graphic_design_layout = findViewById(R.id.graphicDesign);
        language_layout = findViewById(R.id.language);
        leadershop_layout = findViewById(R.id.leadership);
        management_layout = findViewById(R.id.timeManagemnt);
        marketing_layout = findViewById(R.id.marketing);
        meditation_layout = findViewById(R.id.meditation);
        photography_layout = findViewById(R.id.photography);
        programming_layout = findViewById(R.id.programming);
        psychology_layout = findViewById(R.id.psychology);
        public_speaking_layout = findViewById(R.id.publicSpeaking);
        writing_layout = findViewById(R.id.writing);

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

        read_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LearnSkillActivity.this, ReadBooksActivity.class);
                startActivity(intent);
            }
        });

        block_chain_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.youtube.com/watch?v=SyVMma1IkXM";
                Intent intent = new  Intent(LearnSkillActivity.this, WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });

        blogging_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.youtube.com/watch?v=aNP27MRVXgI";
                Intent intent = new  Intent(LearnSkillActivity.this, WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });


        cooking_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.youtube.com/user/foodwishes";
                Intent intent = new  Intent(LearnSkillActivity.this, WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });

        drawing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.youtube.com/channel/UCaM5d3fh_ADywNUjVut4RHQ";
                Intent intent = new  Intent(LearnSkillActivity.this, WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });

        graphic_design_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.youtube.com/playlist?list=PLpQQipWcxwt9U7qgyYkvNH3Mp8XHXCMmQ";
                Intent intent = new  Intent(LearnSkillActivity.this, WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });

        language_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.duolingo.com/";
                Intent intent = new  Intent(LearnSkillActivity.this, WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });

        language_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.duolingo.com/";
                Intent intent = new  Intent(LearnSkillActivity.this, WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });

        leadershop_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.edx.org/course/leadership-and-influence";
                Intent intent = new  Intent(LearnSkillActivity.this, WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });

        management_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.edx.org/course/knowledge-management-and-big-data-in-business";
                Intent intent = new  Intent(LearnSkillActivity.this, WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });

        marketing_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.youtube.com/watch?v=nU-IIXBWlS4&t=8s";
                Intent intent = new  Intent(LearnSkillActivity.this, WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });

       meditation_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.youtube.com/watch?v=wirV265ZYSw";
                Intent intent = new  Intent(LearnSkillActivity.this, WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });

       photography_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.udemy.com/course/free-photography-course-for-beginners/";
                Intent intent = new  Intent(LearnSkillActivity.this, WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });

       programming_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.udacity.com/school-of-programming";
                Intent intent = new  Intent(LearnSkillActivity.this, WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });

        psychology_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.youtube.com/watch?v=vo4pMVb0R6M";
                Intent intent = new  Intent(LearnSkillActivity.this, WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });


        public_speaking_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.youtube.com/watch?v=dHAbmoFHqgA";
                Intent intent = new  Intent(LearnSkillActivity.this, WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });

        writing_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.udemy.com/course/secret-sauce-of-great-writing/";
                Intent intent = new  Intent(LearnSkillActivity.this, WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });




    }
}
