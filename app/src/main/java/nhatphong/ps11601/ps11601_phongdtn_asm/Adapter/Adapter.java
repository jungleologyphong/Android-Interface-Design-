package nhatphong.ps11601.ps11601_phongdtn_asm.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends FragmentStatePagerAdapter {
    private final List<Fragment> aFragmentList = new ArrayList<>();
    private final List<String> sFragmentTitleList = new ArrayList<>();

    public Adapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public Adapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    public Fragment getItem(int position) {
        return aFragmentList.get(position);
    }

    public void addFragment(Fragment fragment,String title){
        aFragmentList.add(fragment);
        sFragmentTitleList.add(title);
    }

    public CharSequence getPageTitle(int position){
        return sFragmentTitleList.get(position);
    }

    public int getCount() {
        return aFragmentList.size();
    }
}
