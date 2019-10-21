package com.team9.seatonvalley;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * @Author: Andreea Stirbu, Student Number: 160483710
 * @Since: 13/04/2018
 *
 * Class that manages fragments
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    //Fields
    ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    //Constructor
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Returns the fragment to display for that page
     */
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    /**
     *  Returns total number of pages
     */
    @Override
    public int getCount() {
        return fragments.size();
    }

    /**
     * Add a fragment
     */
    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }

    /**
     * Returns the page title for the top indicator
     */
    public CharSequence getPageTitle(int position){
        return fragments.get(position).toString();
    }
}
