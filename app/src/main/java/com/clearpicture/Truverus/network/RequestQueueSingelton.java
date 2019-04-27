package com.clearpicture.Truverus.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Suranga Fernando on 8/14/17.
 */

public class RequestQueueSingelton {
    private static RequestQueueSingelton rInstance;
    private static RequestQueue rRequestQueue;
    //private ImageLoader mImageLoader;
    private static Context mCtx;

    private RequestQueueSingelton(Context context){
        mCtx = context;
        rRequestQueue = getRequestQueue();

    }

    public static synchronized RequestQueueSingelton getInstance(Context context) {
        if (rInstance == null) {
            rInstance = new RequestQueueSingelton(context);
        }
        return rInstance;
    }

    public RequestQueue getRequestQueue() {
        if (rRequestQueue == null) {
            rRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return rRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


}
