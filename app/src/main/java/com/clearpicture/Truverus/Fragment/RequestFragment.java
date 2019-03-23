package com.clearpicture.Truverus.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clearpicture.Truverus.Adapter.CollectionAdapter;
import com.clearpicture.Truverus.Adapter.RequestAdapter;
import com.clearpicture.Truverus.R;
import com.clearpicture.Truverus.models.CollectionListModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestFragment extends Fragment {

    private RequestAdapter mAdapter;
    private RecyclerView recycler_request;
    private ArrayList<CollectionListModel> collectionList;
    RecyclerView.LayoutManager layoutManager;
    public RequestFragment() {
        // Required empty public constructor
    }
    public RequestFragment newInstance() {
        RequestFragment fragment = new RequestFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_request, container, false);
        recycler_request = (RecyclerView) view.findViewById(R.id.recycler_request);


        layoutManager = new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);

        recycler_request.setLayoutManager(layoutManager);

        mAdapter = new RequestAdapter(getActivity(), collectionList);

        recycler_request.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
         return  view;
    }

}
