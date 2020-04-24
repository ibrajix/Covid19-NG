package fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;
import com.hbb20.CountryCodePicker;
import com.snappmi.covid19_ng.CallActivity;
import com.snappmi.covid19_ng.LearnSkillActivity;
import com.snappmi.covid19_ng.R;
import com.snappmi.covid19_ng.ShopActivity;
import com.snappmi.covid19_ng.WatchMovieActivity;

import adapters.TabsAdapter;

public class HomeFragment extends Fragment {

    private CountryCodePicker ccp;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LinearLayout shop_icon, learn_icon, movies_icon, call_icon;

    public HomeFragment() {
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
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ccp = root.findViewById(R.id.ccp);
        tabLayout = root.findViewById(R.id.tabLayout);
        viewPager = root.findViewById(R.id.viewPager);
        shop_icon = root.findViewById(R.id.shopIcon);
        learn_icon = root.findViewById(R.id.learnIcon);
        movies_icon = root.findViewById(R.id.moviesIcon);
        call_icon = root.findViewById(R.id.callIcon);

        final TabsAdapter tabsAdapter = new TabsAdapter(getContext(), getChildFragmentManager(),
                tabLayout.getTabCount());

        //tabs code to switch between tabs
        viewPager.setAdapter(tabsAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //set click listener to icons
        shop_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShopActivity.class);
                startActivity(intent);
            }
        });

        learn_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LearnSkillActivity.class);
                startActivity(intent);
            }
        });

        movies_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WatchMovieActivity.class);
                startActivity(intent);
            }
        });

        call_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CallActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

}
