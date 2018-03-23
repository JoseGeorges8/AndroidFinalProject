package com.example.josegeorges.paintit.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.example.josegeorges.paintit.ProfileFragment;
import com.example.josegeorges.paintit.R;
import com.example.josegeorges.paintit.StoreFragment;

/**
 * Created by josegeorges on 2018-03-19.

 This class will serve of help for swiping in between profile and store tabs

 */
public class SimpleFragmentPagerAdapter extends FragmentStatePagerAdapter {


    //array of fragments
    SparseArray<Fragment> registeredFragments = new SparseArray<>();

    //context for string resources
    private Context mContext;

    /*
    Constructor
     */
    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }



    //fragment for each tab
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ProfileFragment();
            case 1:
                return new StoreFragment();
            default:
                return null;
        }
    }


    //takes care of adding the fragments
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    //amount of tabs
    @Override
    public int getCount() {
        return 2;
    }

    //titles fot the tabs
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return mContext.getString(R.string.profile_tab);
            case 1:
                return mContext.getString(R.string.store_tab);
            default:
                return null;
        }
    }
}