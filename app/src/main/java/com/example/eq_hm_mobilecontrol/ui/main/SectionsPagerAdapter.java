package com.example.eq_hm_mobilecontrol.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.eq_hm_mobilecontrol.Accelerometer;
import com.example.eq_hm_mobilecontrol.HighCut;
import com.example.eq_hm_mobilecontrol.LowCut;
import com.example.eq_hm_mobilecontrol.Peak1;
import com.example.eq_hm_mobilecontrol.Peak2;
import com.example.eq_hm_mobilecontrol.Peak3;
import com.example.eq_hm_mobilecontrol.R;
import com.example.eq_hm_mobilecontrol.SendValuesHere;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    LowCut lowCut;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new LowCut();
                break;
            case 1:
                fragment = new Peak1();
                break;
            case 2:
                fragment = new Peak2();
                break;
            case 3:
                fragment = new Peak3();
                break;
            case 4:
                fragment = new HighCut();
                break;
            case 5:
                fragment = new Accelerometer();
                break;
            case 6:
                fragment = new SendValuesHere();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Low Cut";
            case 1:
                return "Peak no. 1";
            case 2:
                return "Peak no. 2";
            case 3:
                return "Peak no. 3";
            case 4:
                return "High Cut";
            case 5:
                return "Acc...";
            case 6:
                return "SVH";
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 7;
    }
}