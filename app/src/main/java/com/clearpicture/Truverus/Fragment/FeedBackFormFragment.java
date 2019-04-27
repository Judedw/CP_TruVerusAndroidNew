package com.clearpicture.Truverus.Fragment;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.clearpicture.Truverus.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedBackFormFragment extends Fragment {


    private RatingBar ratingbar;

    public FeedBackFormFragment newInstance() {
        FeedBackFormFragment fragment = new FeedBackFormFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_back_form, container, false);
        ratingbar = view.findViewById(R.id.ratingbar);
        ratingbar.setRating((float) 0);
        ratingbar.setStepSize((float) 1);

        DrawableCompat.setTint(ratingbar.getProgressDrawable(), ContextCompat.getColor(getActivity(), R.color.tabNotSelected));

        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                LayerDrawable layerDrawable = (LayerDrawable) ratingBar.getProgressDrawable();//#FFDF00
                layerDrawable.getDrawable(2).setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorBlack), PorterDuff.Mode.SRC_ATOP);

            }
        });
        return view;
    }

}
