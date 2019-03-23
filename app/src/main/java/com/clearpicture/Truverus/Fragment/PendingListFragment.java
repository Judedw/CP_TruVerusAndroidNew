package com.clearpicture.Truverus.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clearpicture.Truverus.Adapter.CollectionAdapter;
import com.clearpicture.Truverus.Adapter.PendingListAdapter;
import com.clearpicture.Truverus.R;
import com.clearpicture.Truverus.models.CollectionListModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PendingListFragment extends Fragment {
    private PendingListAdapter mAdapter;
    private RecyclerView recycler_pendingList;
    private ArrayList<CollectionListModel> collectionList;
    RecyclerView.LayoutManager layoutManager;

    public PendingListFragment() {
        // Required empty public constructor
    }
    public PendingListFragment newInstance() {
        PendingListFragment fragment = new PendingListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view  = inflater.inflate(R.layout.fragment_pending_list, container, false);
        initializeList();
        recycler_pendingList = (RecyclerView) view.findViewById(R.id.recycler_pendingList);


        layoutManager = new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);

        recycler_pendingList.setLayoutManager(layoutManager);

        mAdapter = new PendingListAdapter(getActivity(), collectionList);

        recycler_pendingList.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
        return  view;
    }
    public void initializeList(){

        collectionList = new ArrayList<>();

        CollectionListModel collectionListModel1 = new CollectionListModel();
        collectionListModel1.setId(R.drawable.addidas_red);
        collectionListModel1.setItemName("Addidas jacket");


        CollectionListModel collectionListModel2 = new CollectionListModel();
        collectionListModel2.setId(R.drawable.addidas_red);
        collectionListModel2.setItemName("Addidas jacket");

        CollectionListModel collectionListModel3 = new CollectionListModel();
        collectionListModel3.setId(R.drawable.addidas_red);
        collectionListModel3.setItemName("Addidas jacket");

        CollectionListModel collectionListModel4 = new CollectionListModel();
        collectionListModel4.setId(R.drawable.addidas_red);
        collectionListModel4.setItemName("Addidas jacket");

        CollectionListModel collectionListModel5 = new CollectionListModel();
        collectionListModel5.setId(R.drawable.addidas_red);
        collectionListModel5.setItemName("Addidas jacket");

        CollectionListModel collectionListModel6 = new CollectionListModel();
        collectionListModel6.setId(R.drawable.addidas_red);
        collectionListModel6.setItemName("Addidas jacket");



        collectionList.add(collectionListModel1);
        collectionList.add(collectionListModel2);
        collectionList.add(collectionListModel3);
        collectionList.add(collectionListModel4);
        collectionList.add(collectionListModel5);
        collectionList.add(collectionListModel6);
    }

}
