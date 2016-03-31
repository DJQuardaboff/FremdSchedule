/**
 * Created by smith1246 on 2/12/2016.
 */

package com.cbas.spartacrafter.fremdschedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private PageFragment[] pages;

    public SectionsPagerAdapter(FragmentManager fm, int... sectionTypes) {
        super(fm);
        System.out.println("public SectionsPagerAdapter(FragmentManager fm, int[] sectionTypes)");
        pages = new PageFragment[sectionTypes.length];
        createPages(sectionTypes);
    }

    public void createPages(int[] sectionTypes) {
        System.out.println("public void createPages(int[] sectionTypes)");
        for(int i = 0; i < pages.length; i++) {
            createPage(i, sectionTypes[i]);
        }
    }

    public void createPage(int pos, int scheduleType) {
        System.out.println("public void createPage(int pos, int scheduleType)");
        pages[pos] = PageFragment.newInstance(scheduleType);
        System.out.println("Page " + (pos + 1) + " created");
    }

    @Override
    public Fragment getItem(int pos) {
        System.out.println("public Fragment getItem(int pos)");
        return pages[pos];
    }

    @Override
    public int getCount() {
        return pages.length;
    }

    @Override
    public CharSequence getPageTitle(int pos) {
        System.out.println("public CharSequence getPageTitle(int pos)");
        return Main.getScheduleName(pages[pos].getScheduleType());
    }
}