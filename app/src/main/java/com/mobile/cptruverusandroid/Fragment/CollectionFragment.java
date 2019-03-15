package com.mobile.cptruverusandroid.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobile.cptruverusandroid.Adapter.CollectionAdapter;
import com.mobile.cptruverusandroid.R;
import com.mobile.cptruverusandroid.models.CollectionListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionFragment extends Fragment {
    private CollectionAdapter mAdapter;
    private RecyclerView recycler_collection;
    private ArrayList<CollectionListModel> collectionList;
    RecyclerView.LayoutManager layoutManager;
    public CollectionFragment() {
        // Required empty public constructor
    }

    public CollectionFragment newInstance() {
        CollectionFragment fragment = new CollectionFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collection, container, false);
        initializeList();
        recycler_collection = (RecyclerView) view.findViewById(R.id.recycler_collection);

        layoutManager = new LinearLayoutManager(getActivity());

        recycler_collection.setLayoutManager(layoutManager);

        mAdapter = new CollectionAdapter(getActivity(), collectionList);

        recycler_collection.setAdapter(mAdapter);

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

        //productPojo3.setProductRating(4.5f);

        collectionList.add(collectionListModel1);
        collectionList.add(collectionListModel2);
        collectionList.add(collectionListModel3);
        collectionList.add(collectionListModel4);
        collectionList.add(collectionListModel5);
        collectionList.add(collectionListModel6);
    }

}
