package adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import fragments.SafetyFragment;
import fragments.SymptomsFragment;

public class TabsAdapter extends FragmentPagerAdapter {

    private Context context;
    private int totalTabs;

    public TabsAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        context = c;
        this.totalTabs = totalTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                SafetyFragment openSafetyFragment = new SafetyFragment();
                return openSafetyFragment;
            case 1:
                SymptomsFragment openSymptomsFragment = new SymptomsFragment();
                return openSymptomsFragment;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}