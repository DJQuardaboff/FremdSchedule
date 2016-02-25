/*
 * Created by smith1246 on 2/12/2016.
 */

package com.cbas.spartacrafter.fremdschedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class PageFragment extends Fragment {
    public static final String PAGE_NUM = "PAGE_NUM";

    public static PageFragment newInstance(int pageNum) {
        Bundle args = new Bundle();
        args.putInt(PAGE_NUM, pageNum);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        switch (getArguments().getInt(PAGE_NUM)) {
            case 0:
                return inflater.inflate(R.layout.fragment_section_1, container, false);
            case 1:
                return inflater.inflate(R.layout.fragment_section_2, container, false);
            case 2:
                return inflater.inflate(R.layout.fragment_section_3, container, false);
            case 3:
                return inflater.inflate(R.layout.fragment_section_4, container, false);
        }
        return null;
    }
}