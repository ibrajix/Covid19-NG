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

import com.snappmi.covid19_ng.R;

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
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.who.int/emergencies/diseases/novel-coronavirus-2019/events-as-they-happen"));
                startActivity(intent);
            }
        });

        ncdc_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://covid19.ncdc.gov.ng/"));
                startActivity(intent);
            }
        });

        bbc_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.bbc.com/news/coronavirus"));
                startActivity(intent);
            }
        });

        al_jazeera_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.aljazeera.com/topics/events/coronavirus-outbreak.html"));
                startActivity(intent);
            }
        });

        vanguard_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.vanguardngr.com/category/coronavirus-updates/"));
                startActivity(intent);
            }
        });

        nytimes_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.nytimes.com/2020/04/08/world/coronavirus-live-news-updates.html"));
                startActivity(intent);
            }
        });

        wikipedia_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://en.wikipedia.org/wiki/2019%E2%80%9320_coronavirus_pandemic"));
                startActivity(intent);
            }
        });

        return root;
    }
}
