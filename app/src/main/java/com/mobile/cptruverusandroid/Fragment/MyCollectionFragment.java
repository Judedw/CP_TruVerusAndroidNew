package com.mobile.cptruverusandroid.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobile.cptruverusandroid.Adapter.TabAdapter;
import com.mobile.cptruverusandroid.R;

public class MyCollectionFragment extends Fragment {

    private TabAdapter tabAdapter;

    private TabLayout tbTabs;
    private ViewPager vpFrags;

    public MyCollectionFragment newInstance() {
        MyCollectionFragment fragment = new MyCollectionFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_collection, container, false);

        tbTabs =view.findViewById(R.id.tbTabs);
        vpFrags = view.findViewById(R.id.vpFrags);
        tabAdapter = new TabAdapter(getFragmentManager());

        setUpFragments();
        return view;
    }
    public void setUpFragments() {
        tabAdapter.addFragment(new CollectionFragment().newInstance(), "Collection");
        tabAdapter.addFragment(new ProductDetailsFragment().newInstance(), "Pending List");
        tabAdapter.addFragment(new MyCollectionFragment().newInstance(), "Request");

        vpFrags.setAdapter(tabAdapter);
        tbTabs.setupWithViewPager(vpFrags);
    }
}
