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
    public static final String SCHEDULE_TYPE = "SCHEDULE_TYPE";

    public static PageFragment newInstance(int scheduleType) {
        System.out.println("public static PageFragment newInstance(int scheduleType)");
        Bundle args = new Bundle();
        args.putInt(SCHEDULE_TYPE, scheduleType);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("public void onCreate(Bundle savedInstanceState)");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)");
        final RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.schedule_fragment, container, false);
        final RecyclerView recyclerView = ((RecyclerView) view.findViewById(R.id.scheduleList));
        final LinearLayoutManager layoutManager = new LinearLayoutManager(inflater.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ClassListAdapter(getArguments().getInt(SCHEDULE_TYPE)));
        return view;
    }

    public int getScheduleType() {
        System.out.println("public int getScheduleType()");
        return getArguments().getInt(SCHEDULE_TYPE);
    }
}