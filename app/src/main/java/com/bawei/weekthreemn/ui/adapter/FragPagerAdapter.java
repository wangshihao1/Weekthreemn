package com.bawei.weekthreemn.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bawei.weekthreemn.ui.fragment.FragmentFind;
import com.bawei.weekthreemn.ui.fragment.FragmentHome;
import com.bawei.weekthreemn.ui.fragment.FragmentMine;
import com.bawei.weekthreemn.ui.fragment.FragmentStudy;

public class FragPagerAdapter extends FragmentPagerAdapter {

      private String[] name = new String[]{
              "发现","讲堂","学习","我的"
      };

    public FragPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new FragmentHome();
            case 1:
                return new FragmentFind();
            case 2:
                return new FragmentStudy();

                default:
                    return new FragmentMine();
        }


    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return name[position];
    }
}
