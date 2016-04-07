/**
 * Created by smith1246 on 2/12/2016.
 */

package com.cbas.spartacrafter.fremdschedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private PageFragment[] pages;

    public SectionsPagerAdapter(FragmentManager fm, int... sectionTypes) {
        super(fm);
        pages = new PageFragment[sectionTypes.length];
        createPages(sectionTypes);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        pages[position].getSchedule().removeUpdates();
    }

    public void createPages(int[] sectionTypes) {
        for(int i = 0; i < pages.length; i++) {
            createPage(i, sectionTypes[i]);
        }
    }

    public void createPage(int position, int scheduleType) {
        pages[position] = PageFragment.newInstance(scheduleType);
        System.out.println("Page " + (position + 1) + " created with " + Main.getScheduleName(scheduleType));
    }

    @Override
    public Fragment getItem(int position) {
        if(pages[position].getSchedule() != null) {
            pages[position].getSchedule().scheduleUpdates();
        }
        return pages[position];
    }

    @Override
    public int getCount() {
        return pages.length;
    }

    @Override
    public CharSequence getPageTitle(int pos) {
        return Main.getScheduleName(pages[pos].getScheduleType());
    }
}