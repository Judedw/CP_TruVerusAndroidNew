package com.clearpicture.Truverus.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.clearpicture.Truverus.Adapter.EventAdapter;
import com.clearpicture.Truverus.Adapter.promotionAdapter;
import com.clearpicture.Truverus.R;
import com.clearpicture.Truverus.listeners.GridItemClickListener;
import com.clearpicture.Truverus.models.EventsModel;
import com.clearpicture.Truverus.models.FeedBackModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment implements GridItemClickListener {
    private EventAdapter mAdapter;
    private RecyclerView eventsrecyclerView;
    private ArrayList<EventsModel> eventsModelArrayList;
    RecyclerView.LayoutManager layoutManager;

    public EventsFragment newInstance() {
        EventsFragment fragment = new EventsFragment();
        return fragment;
    }
    int[] brandImages =  new int[]{R.drawable.nikelogo, R.drawable.adidaslogo};
    String[] eventsNamesArray = new String[]{"NIKE COMMUNITY", "UPCOMING EVENTS"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_events, container, false);
        initializeList();
        eventsrecyclerView = view.findViewById(R.id.eventsrecyclerView);
        layoutManager = new GridLayoutManager(getActivity(),1,GridLayoutManager.VERTICAL,false);
        eventsrecyclerView.setLayoutManager(layoutManager);
        mAdapter = new EventAdapter(getActivity(), eventsModelArrayList,EventsFragment.this);
        eventsrecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i("message", "keyCode: " + keyCode);
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    Log.i("message", "onKey Back listener is working!!!");
                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    public void initializeList(){
        int i =0;
        eventsModelArrayList = new ArrayList<>();

        for( i=0;i<brandImages.length;i++){
            EventsModel info = new EventsModel();
            info.setLogo(brandImages[i]);
            info.setEventName(eventsNamesArray[i]);

            eventsModelArrayList.add(info);

        }

    }

    @Override
    public void onItemClick(Object object) {
        ViewEventsFragment newFragment = ViewEventsFragment.newInstance();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.addToBackStack(ViewEventsFragment.TAG);
        ft.replace(R.id.eventContainer, newFragment, ViewEventsFragment.TAG).commit();
    }

    @Override
    public void onItemClick(String id) {

    }

    @Override
    public void onItemClick(String id, String msgId) {

    }

}
