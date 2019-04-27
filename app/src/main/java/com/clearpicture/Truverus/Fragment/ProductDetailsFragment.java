package com.clearpicture.Truverus.Fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clearpicture.Truverus.Adapter.ImageSliderAdapter;
import com.clearpicture.Truverus.util.CustomViewPager;
import com.clearpicture.Truverus.R;
import com.clearpicture.Truverus.util.MySpannable;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailsFragment extends Fragment implements View.OnClickListener {

    private String vedioId = "QIetOk4hmiY";
    private static int currentPage = 0;
    private static final Integer[] HOTDEALS = {R.drawable.addidas_red, R.drawable.adidas, R.drawable.addidas_red, R.drawable.adidas, R.drawable.addidas_red};
    private ArrayList<Integer> HOTDEALSArray = new ArrayList<Integer>();
    private CircleIndicator indicator;
    public CustomViewPager mPager;
    private TextView itemDescription;
    private AppBarLayout mAppBarLayout;
    private NestedScrollView animateScrollView;
    private LinearLayout watchVedio;
    private LinearLayout transferBtnContainer;
    Boolean isCommingFromColletionUi = false;

    public static final String TAG = ViewEventsFragment.class.getSimpleName();

    public static ProductDetailsFragment newInstance() {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

        mPager = view.findViewById(R.id.pager);
        indicator = view.findViewById(R.id.indicator);
        animateScrollView = view.findViewById(R.id.animateScrollView);
        watchVedio = view.findViewById(R.id.watchVedio);
        itemDescription = view.findViewById(R.id.itemDescription);
        transferBtnContainer = view.findViewById(R.id.transferBtnContainer);
        watchVedio.setOnClickListener(this);
        init();
        itemDescription.setText(R.string.product_details);
        makeTextViewResizable(itemDescription, 3, "Read More", true);
        if(isCommingFromColletionUi == true){
            transferBtnContainer.setVisibility(View.VISIBLE);
        }else{
            transferBtnContainer.setVisibility(View.INVISIBLE);
        }
        return view;

    }


    private void init() {
        for (int i = 0; i < HOTDEALS.length; i++)
            HOTDEALSArray.add(HOTDEALS[i]);
        mPager.setAdapter(new ImageSliderAdapter(getActivity(), HOTDEALSArray));
        indicator.setViewPager(mPager);
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == HOTDEALS.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.watchVedio: {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/QIetOk4hmiY" + vedioId));
                startActivity(intent);
                break;
            }
        }
    }
    public static void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {

        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {

                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0) {
                    int lineEndIndex = tv.getLayout().getLineEnd(0);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    int lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else {
                    int lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                }
            }
        });

    }

    private static SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned, final TextView tv,
                                                                            final int maxLine, final String spanableText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {


            ssb.setSpan(new MySpannable(false){
                @Override
                public void onClick(View widget) {
                    if (viewMore) {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, -1, "See Less", false);
                    } else {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, 3, ".. See More", true);
                    }
                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;

    }

}