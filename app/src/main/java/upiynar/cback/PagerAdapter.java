package upiynar.cback;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

/**
 * Created by shock on 10/22/2017.
 */
public class PagerAdapter extends FragmentPagerAdapter{
    public PagerAdapter(FragmentManager fm) {
        super(fm);
        }
    @Override
    public int getCount() {
        return 4;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Etkinlikler();
            case 1:
                return new Haberler();
            case 2:
                return new Video();
            case 3:
                return new Admin_Panel();
            default:
                return null;
        }
    }
    public CharSequence getPageTitle(int position){

        switch (position){
            case 0:
                return "Etkinlikler";
            case 1:
                return "Haberler";
            case 2:
                return "Video";
            case 3:
                return "Panel";
            default:
                return null;
        }
    }
}
