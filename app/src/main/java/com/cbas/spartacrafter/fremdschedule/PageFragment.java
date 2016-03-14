/**
 * Created by smith1246 on 2/12/2016.
 */

package com.cbas.spartacrafter.fremdschedule;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

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
        final RelativeLayout v = (RelativeLayout) inflater.inflate(R.layout.schedule_fragment, container, false);
        final RecyclerView recyclerView = ((RecyclerView) v.findViewById(R.id.scheduleList));
        final LinearLayoutManager layoutManager = new LinearLayoutManager(Main.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        switch (getArguments().getInt(PAGE_NUM)) {
            case 0:
                recyclerView.setAdapter(new ClassListAdapter(Schedule.SCHEDULE_TYPE_NORMAL));
                return v;
            case 1:
                recyclerView.setAdapter(new ClassListAdapter(Schedule.SCHEDULE_TYPE_LATE_START));
                return v;
            case 2:
                recyclerView.setAdapter(new ClassListAdapter(Schedule.SCHEDULE_TYPE_EARLY_DISMISSAL));
                return v;
            case 3:
                recyclerView.setAdapter(new ClassListAdapter(Schedule.SCHEDULE_TYPE_PEP_ASSEMBLY));
                return v;
        }
        return null;
    }
}