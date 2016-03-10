/**
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
        View v;
        switch (getArguments().getInt(PAGE_NUM)) {
            case 0:
                v = inflater.inflate(R.layout.schedule_fragment, container, false);
                ((ListView) v.findViewById(R.id.scheduleList)).setAdapter(new ClassListAdapter(Schedule.SCHEDULE_TYPE_NORMAL));
                return v;
            case 1:
                v = inflater.inflate(R.layout.schedule_fragment, container, false);
                ((ListView) v.findViewById(R.id.scheduleList)).setAdapter(new ClassListAdapter(Schedule.SCHEDULE_TYPE_LATE_START));
                return v;
            case 2:
                v = inflater.inflate(R.layout.schedule_fragment, container, false);
                ((ListView) v.findViewById(R.id.scheduleList)).setAdapter(new ClassListAdapter(Schedule.SCHEDULE_TYPE_EARLY_DISMISSAL));
                return v;
            case 3:
                v = inflater.inflate(R.layout.schedule_fragment, container, false);
                ((ListView) v.findViewById(R.id.scheduleList)).setAdapter(new ClassListAdapter(Schedule.SCHEDULE_TYPE_PEP_ASSEMBLY));
                return v;
        }
        return null;
    }
}