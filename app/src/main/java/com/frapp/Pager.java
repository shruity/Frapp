package com.frapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class Pager extends FragmentStatePagerAdapter {

    private int tabCount;

    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                List list= new List();
                return list;
            case 1:
                Favourites favourites = new Favourites();
                return favourites;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
