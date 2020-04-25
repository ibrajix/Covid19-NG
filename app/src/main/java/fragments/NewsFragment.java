package fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snappmi.covid19_ng.LearnSkillActivity;
import com.snappmi.covid19_ng.R;
import com.snappmi.covid19_ng.WebLinksActivity;

public class NewsFragment extends Fragment {


    TextView who_link, ncdc_link, bbc_link, al_jazeera_link, vanguard_link, nytimes_link, wikipedia_link;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_news, container, false);

        who_link = root.findViewById(R.id.who);
        ncdc_link = root.findViewById(R.id.ncdc);
        bbc_link = root.findViewById(R.id.bbc);
        al_jazeera_link = root.findViewById(R.id.aljazeera);
        vanguard_link = root.findViewById(R.id.vanguard);
        nytimes_link = root.findViewById(R.id.nytimes);
        wikipedia_link = root.findViewById(R.id.wikipedia);

        who_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.who.int/emergencies/diseases/novel-coronavirus-2019/events-as-they-happen";
                Intent intent = new  Intent(getContext(), WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });

        ncdc_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://covid19.ncdc.gov.ng/";
                Intent intent = new  Intent(getContext(), WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });

        bbc_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.bbc.com/news/coronavirus";
                Intent intent = new  Intent(getContext(), WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });

        al_jazeera_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.aljazeera.com/topics/events/coronavirus-outbreak.html";
                Intent intent = new  Intent(getContext(), WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });

        vanguard_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.vanguardngr.com/category/coronavirus-updates/";
                Intent intent = new  Intent(getContext(), WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });

        nytimes_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.nytimes.com/2020/04/08/world/coronavirus-live-news-updates.html";
                Intent intent = new  Intent(getContext(), WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });

        wikipedia_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://en.wikipedia.org/wiki/2019%E2%80%9320_coronavirus_pandemic";
                Intent intent = new  Intent(getContext(), WebLinksActivity.class);
                intent.putExtra("link_url", url);
                startActivity(intent);
            }
        });

        return root;
    }
}
