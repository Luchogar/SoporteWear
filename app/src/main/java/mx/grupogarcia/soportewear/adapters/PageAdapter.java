package mx.grupogarcia.soportewear.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Luis García on 03/01/2017.
 */

public class PageAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentos;
    public PageAdapter(FragmentManager fm, ArrayList<Fragment> fragmentos) {
        super(fm);
        this.fragmentos=fragmentos;
    }

    @Override
    public int getCount() {
        return fragmentos.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentos.get(position);
    }
}
