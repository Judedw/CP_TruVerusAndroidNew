package com.clearpicture.Truverus.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clearpicture.Truverus.Adapter.CollectionAdapter;
import com.clearpicture.Truverus.R;
import com.clearpicture.Truverus.models.CollectionListModel;

import java.util.ArrayList;

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


        layoutManager = new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);

        recycler_collection.setLayoutManager(layoutManager);

        mAdapter = new CollectionAdapter(getActivity(), collectionList);

        recycler_collection.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        return  view;
    }

    public void initializeList(){

        collectionList = new ArrayList<>();

        CollectionListModel collectionListModel1 = new CollectionListModel();
        collectionListModel1.setItemName("Addidas jacket");
        collectionListModel1.setImage("https://assets.adidas.com/images/w_280,h_280,f_auto,q_auto:sensitive,fl_lossy/20a3f7a99d4c4f4f89b9a945010dcab0_9366/sst-track-jacket.jpg");

        CollectionListModel collectionListModel2 = new CollectionListModel();
        collectionListModel2.setItemName("Addidas jacket");
        collectionListModel2.setImage("https://assets.adidas.com/images/w_280,h_280,f_auto,q_auto:sensitive/d364530a0ed44c4196fba9890169892f_9366/tiro-track-jacket.jpg");

        CollectionListModel collectionListModel3 = new CollectionListModel();
        collectionListModel3.setItemName("Addidas jacket");
        collectionListModel3.setImage("https://cdn-internetfusion.global.ssl.fastly.net/surfdome.com/673274.jpg?width=500&pad=0,0&bg-color=ffffff");

        CollectionListModel collectionListModel4 = new CollectionListModel();
        collectionListModel4.setItemName("Addidas jacket");
        collectionListModel4.setImage("https://images.finishline.com/is/image/FinishLine/CE2392_BLK?$Grid_rwd_onModel$");


        CollectionListModel collectionListModel5 = new CollectionListModel();
        collectionListModel5.setItemName("Addidas jacket");
        collectionListModel5.setImage("https://assets.adidas.com/images/w_280,h_280,f_auto,q_auto:sensitive/a69276c9c6fe46d08974a96600fbc946_9366/flamestrike-track-jacket.jpg");


        CollectionListModel collectionListModel6 = new CollectionListModel();
        collectionListModel6.setItemName("Addidas jacket");
        collectionListModel6.setImage("https://images.finishline.com/is/image/FinishLine/CE2392_BLK?$Grid_rwd_onModel$");




        collectionList.add(collectionListModel1);
        collectionList.add(collectionListModel2);
        collectionList.add(collectionListModel3);
        collectionList.add(collectionListModel4);
        collectionList.add(collectionListModel5);
        collectionList.add(collectionListModel6);
    }

}
