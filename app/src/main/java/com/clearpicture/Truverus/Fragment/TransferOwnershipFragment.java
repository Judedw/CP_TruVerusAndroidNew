package com.clearpicture.Truverus.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clearpicture.Truverus.Adapter.EventAdapter;
import com.clearpicture.Truverus.Adapter.SearchAdapter;
import com.clearpicture.Truverus.R;
import com.clearpicture.Truverus.listeners.GridItemClickListener;
import com.clearpicture.Truverus.models.SearchModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransferOwnershipFragment extends Fragment implements GridItemClickListener {
    String[] searchList = new String[]{"john Doug", "john Doe", "Fashion Hoodles", "Fashion outfits", "Fashion outfits"};
    private EditText txtSearch;
    private LinearLayout searchlistlayout;
    private ArrayList<SearchModel> itemsList;
    private SearchAdapter searchAdapter;
    private RecyclerView searchRecycler;
    private GridItemClickListener gridItemClickListener;
    RecyclerView.LayoutManager layoutManager;

    public TransferOwnershipFragment newInstance() {
        TransferOwnershipFragment fragment = new TransferOwnershipFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transfer_ownership, container, false);
        txtSearch = view.findViewById(R.id.txtSearch);
        searchRecycler = view.findViewById(R.id.searchRecycler);
        searchlistlayout = view.findViewById(R.id.searchlistlayout);
        txtSearch.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        txtSearch.getBackground().clearColorFilter();


        itemsList = new ArrayList<SearchModel>();
        for (int i = 0; i < searchList.length; i++) {
            SearchModel searchModel = new SearchModel();
            searchModel.setItemName(searchList[i]);
            itemsList.add(searchModel);
        }
        layoutManager = new GridLayoutManager(getActivity(),1,GridLayoutManager.VERTICAL,false);
        searchRecycler.setLayoutManager(layoutManager);
        searchAdapter = new SearchAdapter(getActivity(), itemsList,TransferOwnershipFragment.this);
        searchRecycler.setAdapter(searchAdapter);
        searchAdapter.notifyDataSetChanged();
        handleSearch();
        return view;
    }

    private void handleSearch() {

       txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    searchAdapter = new SearchAdapter(getActivity(), itemsList,TransferOwnershipFragment.this);
                    searchRecycler.setAdapter(searchAdapter);
                    searchAdapter.notifyDataSetChanged();
                    searchlistlayout.setVisibility(View.VISIBLE);

                }else if(s.length()>=2){
                     searchlistlayout.setVisibility(View.VISIBLE);

                    if (itemsList != null) {

                        final ArrayList<SearchModel> searchModels = new ArrayList<SearchModel>();

                        for (int i = 0; i < itemsList.size(); i++) {
                            if (itemsList.get(i).getItemName().toUpperCase().contains(s.toString().toUpperCase())) {
                                String replacedWith = "<font color='black'><b>" + s.toString() + "</b></font>";

                                SearchModel searchModel = new SearchModel();
                                String modifiedString = itemsList.get(i).getItemName().replaceAll("(?i)"+s.toString(), replacedWith);
                                searchModel.setItemName(modifiedString);
                                searchModel.setItemID(itemsList.get(i).getItemID());
                                searchModels.add(searchModel);
                            }
                        }
                        if (searchModels.size() > 0) {
                           searchlistlayout.setVisibility(View.VISIBLE);
//                           noresultfoundlayout.setVisibility(View.INVISIBLE);
                            searchAdapter = new SearchAdapter(getActivity(), searchModels,TransferOwnershipFragment.this);
                            searchAdapter.notifyDataSetChanged();
                            searchRecycler.setAdapter(searchAdapter);

                        } else {

                            searchlistlayout.setVisibility(View.GONE);
//                          noresultfoundlayout.setVisibility(View.VISIBLE);
                        }

                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

      txtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    txtSearch.setFocusable(false);

                    return true; // Focus will do whatever you put in the logic.
                }
                return false;  // Focus will change according to the actionId
            }
        });
    }

    @Override
    public void onItemClick(Object object) {

    }

    @Override
    public void onItemClick(String id) {

    }

    @Override
    public void onItemClick(String id, String msgId) {

    }
}
