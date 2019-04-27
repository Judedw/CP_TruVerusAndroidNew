package com.clearpicture.Truverus.Fragment;


import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.clearpicture.Truverus.Adapter.CollectionAdapter;
import com.clearpicture.Truverus.Adapter.ImageSliderAdapter;
import com.clearpicture.Truverus.Adapter.InboxAdapter;
import com.clearpicture.Truverus.R;
import com.clearpicture.Truverus.listeners.GridItemClickListener;
import com.clearpicture.Truverus.models.CollectionListModel;
import com.clearpicture.Truverus.models.InboxModel;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class InboxFragment extends Fragment implements GridItemClickListener {

    private InboxAdapter mAdapter;
    private RecyclerView inbox_reciclerView;
    private ArrayList<InboxModel> messageList;
    RecyclerView.LayoutManager layoutManager;
    private BottomSheetDialog mBottomSheetDialog;

    int[] Images =  new int[]{R.drawable.new_promo, R.drawable.upcoming_event, R.drawable.new_msg, R.drawable.new_transfer};
    String[] Title = new String[]{"NEW PROMOTIONS", "UPCOMING EVENTS", "NEW MESSEGE", "NEW TRANSFER REQUEST"};
    String[] body = new String[]{"Nike Air Max 270 (25% Off)", "Jane Sent you a Messge", "Jane Sent you a Messge", "Victor Sent you Transfer Request"};

    private ArrayList<Integer> HOTDEALSArray = new ArrayList<Integer>();

    public InboxFragment newInstance() {
        InboxFragment fragment = new InboxFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_inbox, container, false);
        initializeList();

        inbox_reciclerView = view.findViewById(R.id.inbox_reciclerView);

        layoutManager = new GridLayoutManager(getActivity(),1,GridLayoutManager.VERTICAL,false);
        inbox_reciclerView.setLayoutManager(layoutManager);
        mAdapter = new InboxAdapter(getActivity(), messageList,InboxFragment.this);
        inbox_reciclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        return view;
    }

    public void initializeList(){
        int i =0;
        messageList = new ArrayList<>();

        for( i=0;i<Images.length;i++){
            InboxModel info = new InboxModel();
            info.setIcon(Images[i]);
            info.setTitle(Title[i]);
            info.setBody(body[i]);

            messageList.add(info);

        }
//        messageList = new ArrayList<>();
//
//        InboxModel inboxModel1 = new InboxModel();
//        inboxModel1.setTitle("NEW PROMOTIONS");
//        inboxModel1.setBody("Nike Air Max 270 (25% Off)");
//        inboxModel1.setDate("09-02-2019");
//        inboxModel1.setTime("9.30 P.M");
//        inboxModel1.setIcon(R.drawable.new_promo);
//
//
//        InboxModel inboxModel2 = new InboxModel();
//        inboxModel2.setTitle("UPCOMING EVENTS");
//        inboxModel2.setBody("Nike Nike Special Event");
//        inboxModel2.setDate("03-02-2019");
//        inboxModel2.setTime("11.10 A.M");
//        inboxModel1.setIcon(R.drawable.new_promo);
//
//
//        InboxModel inboxModel3 = new InboxModel();
//        inboxModel3.setTitle("NEW MESSEGE");
//        inboxModel3.setBody("Jane Sent you a Messge");
//        inboxModel3.setDate("28-01-2019");
//        inboxModel3.setTime("01.10 P.M");
//        inboxModel1.setIcon(R.drawable.new_promo);
//
//        InboxModel inboxModel4 = new InboxModel();
//        inboxModel4.setTitle("NEW TRANSFER REQUEST");
//        inboxModel4.setBody("Victor Sent you Transfer Request");
//        inboxModel4.setDate("18-01-2019");
//        inboxModel4.setTime("03.05 P.M");
//        inboxModel1.setIcon(R.drawable.new_promo);
//
//        messageList.add(inboxModel1);
//        messageList.add(inboxModel2);
//        messageList.add(inboxModel3);
//        messageList.add(inboxModel4);

    }


    @Override
    public void onItemClick(Object object) {
        showBottomSheetDialog();
    }

    @Override
    public void onItemClick(String id) {

    }

    @Override
    public void onItemClick(String id, String msgId) {

    }

    private void showBottomSheetDialog() {
        final View bottomSheetLayout = getLayoutInflater().inflate(R.layout.bottom_sheet_dialog, null);

        mBottomSheetDialog = new BottomSheetDialog(getActivity());
        mBottomSheetDialog.setContentView(bottomSheetLayout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        mBottomSheetDialog.show();
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });
    }
}
