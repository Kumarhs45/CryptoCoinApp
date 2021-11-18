package com.kumar.synconextassignment.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kumar.synconextassignment.tabfragments.AllCoinsTab;
import com.kumar.synconextassignment.tabfragments.MyWatchListTab;

public class TabbLayoutAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public TabbLayoutAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AllCoinsTab allCoinsTab = new AllCoinsTab();
                return allCoinsTab;
            case 1:
                MyWatchListTab myWatchListTab = new MyWatchListTab();
                return myWatchListTab;

            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}