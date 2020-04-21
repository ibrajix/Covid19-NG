package adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import fragments.IntroFragment1;
import fragments.IntroFragment2;
import fragments.IntroFragment3;

public class SliderPagerAdapter extends FragmentPagerAdapter {
    public SliderPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override public Fragment getItem(int position) {
        switch (position) {
            case 0:
                IntroFragment1 fragment1 = new IntroFragment1();
                return fragment1;
            case 1:
                IntroFragment2 fragment2 = new IntroFragment2();
                return fragment2;
            case 2:
                IntroFragment3 fragment3 = new IntroFragment3();
                return fragment3;
            default:
                return null;
        }
    }
    // size is hardcoded
    @Override public int getCount() {
        return 3;
    }
}
