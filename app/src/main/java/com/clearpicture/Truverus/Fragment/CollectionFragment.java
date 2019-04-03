package com.clearpicture.Truverus.Fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.clearpicture.Truverus.Adapter.CollectionAdapter;
import com.clearpicture.Truverus.HomeActivity;
import com.clearpicture.Truverus.MainActivity;
import com.clearpicture.Truverus.R;
import com.clearpicture.Truverus.SignInActivity;
import com.clearpicture.Truverus.models.CollectionListModel;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.clearpicture.Truverus.SignInActivity.MY_PREFS_NAME;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionFragment extends Fragment implements View.OnClickListener {

    private CollectionAdapter mAdapter;
    private RecyclerView recycler_collection;
    private ArrayList<CollectionListModel> collectionList;
    RecyclerView.LayoutManager layoutManager;
    private LinearLayout noCollectionContainer;
    private Button signinBtn;
    String signInStatus = "";
    public CollectionFragment( ) {
        // Required empty public constructor
    }
    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

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
        noCollectionContainer = (LinearLayout) view.findViewById(R.id.noCollectionContainer);
        signinBtn = (Button)view.findViewById(R.id.signinBtn);

        signinBtn.setOnClickListener(this);

        SharedPreferences prefs = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("loginSatus", "false");
        if (restoredText != null) {
            signInStatus = prefs.getString("loginSatus", "false");//"No name defined" is the default value.
            System.out.print("the status is == "+signInStatus);

        }


        if (signInStatus .equals("false")){
            noCollectionContainer.setVisibility(View.VISIBLE);
            recycler_collection.setVisibility(View.INVISIBLE);
        }
        if (signInStatus .equals("true")){
            noCollectionContainer.setVisibility(View.INVISIBLE);
            recycler_collection.setVisibility(View.VISIBLE);
        }

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

    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.signinBtn:
        Intent i = new Intent(getActivity(), SignInActivity.class);
        startActivity(i);
        break;
}
    }
}
