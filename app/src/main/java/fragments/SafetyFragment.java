package fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.snappmi.covid19_ng.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

public class SafetyFragment extends Fragment {

    private CarouselView carouselView;
    private TextView safety_description;
    private TextView safety_title;
    private ImageView safety_images;

    private int[] sampleImages = {R.drawable.ic_undraw_wash_hands, R.drawable.ic_undraw_social_distancing, R.drawable.ic_undraw_dont_touch_face, R.drawable.ic_undraw_medical_help, R.drawable.ic_undraw_respiratory_hygiene, R.drawable.ic_undraw_stay_home};

    private String[] titles = {"Wash your hands regularly", "Observe social distancing", "Avoid touching your face"
            , "Seek medical help when feeling unwell", "Practice respiratory hygiene", "Stay at home"};


    private String[] description = {
            "Hands touch many surfaces and can pick up viruses. Once contaminated, hands can transfer the virus to " +
                    "your eyes, nose or mouth.",

            "Maintain at least 1 meter distance between yourself and other people when in public, especially " +
                    "people who are showing signs of unwell.",

            "This may be hard, but your hands can pick up viruses unknown to you and by touching parts of your faces, " +
                    "the virus may end up entering your body.",

            "If you have a fever, cough and difficulty breathing, seek medical attention and call in advance. " +
                    "Follow the directions giving by the ministry of health.",

            "This means covering your mouth and nose with your bent elbow or tissue when you cough or sneeze. " +
                    "Then dispose off immediately.",

            "Right now, staying at home is paramount in preventing the spread of the virus. "
    };

    public SafetyFragment() {
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
        View root = inflater.inflate(R.layout.fragment_safety, container, false);
        carouselView = root.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setViewListener(viewListener);

        return root;
    }

    ViewListener viewListener = new ViewListener() {
        int i;
        @Override
        public View setViewForPosition(int position) {
            View customView = getLayoutInflater().inflate(R.layout.fragment_safety, null);

            safety_title = customView.findViewById(R.id.safetyTextTitle);
            safety_description = customView.findViewById(R.id.safetyTextDescription);

            safety_images = customView.findViewById(R.id.safetyImages);

            Glide.with(SafetyFragment.this).load(sampleImages[position]).into(safety_images);


            safety_title.setText(titles[position]);
            safety_description.setText(description[position]);


            carouselView.setIndicatorGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);

            return customView;
        }
    };
}
