package com.bawei.wangshihao0404mn.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bawei.wangshihao0404mn.ui.fragment.FragmentCar;
import com.bawei.wangshihao0404mn.ui.fragment.FragmentHome;
import com.bawei.wangshihao0404mn.ui.fragment.MyFragment;

public class FragPageAdapter extends FragmentPagerAdapter {

    private String name[] = new String[]{
            "首页","购物车","我的"
    };


    public FragPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new FragmentHome();
            case 1:
                return new FragmentCar();
                default:
                    return new MyFragment();
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
