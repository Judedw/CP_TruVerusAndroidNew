package com.clearpicture.Truverus.network;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.net.Uri;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
//import com.google.android.gms.common.api.Response;
//import com.inovaitsys.magri.pojo.MagriPreLoader;
import com.clearpicture.Truverus.util.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import com.inovaitsys.magri.Util.MAgriPreLoader;

/**
 * Created by INOVA on 12/27/2017.
 */

public class CPRequestHandler {

    private static RequestQueue requestQueue;
    private static CPRequestHandler instance;
    private static ProgressBar pbar;
    private Context context;


    private CPRequestHandler(Context context) {
        requestQueue = RequestQueueSingelton.getInstance(context).getRequestQueue();
        this.context = context;
    }

    public static synchronized CPRequestHandler getInstance(Context context) {
        if (instance == null) {
            instance = new CPRequestHandler(context);

        }
        return instance;
    }

    // GSon Request
    public <T> void makeGsonRequest(int type, String URL, Class<T> clazz, Map<String, String> headers,
                                    Map<String, String> params, Response.Listener successListner,
                                    Response.ErrorListener errorListner, final Activity activity, boolean preloader) {
        if(Utility.isNetworkAvailable(activity)){
            if (errorListner == null) {
                errorListner = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        MagriPreLoader.getInstance().dissmiss();
                        if (error instanceof NetworkError) {
                           Toast.makeText(context, "The server could not be found. Please try again after some time!!", Toast.LENGTH_LONG).show();
                      //     Toast.makeText(context, "No Network!!",Toast.LENGTH_LONG).show();
//                            Intent i = new Intent(activity, NetworkErrorAnimationActivity.class);
//                            activity.startActivity(i);
                        } else if (error instanceof ServerError) {
                            Toast.makeText(context, "The server could not be found. Please try again after some time!!", Toast.LENGTH_LONG).show();
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(context,"Cannot connect to Internet...Please check your connection!", Toast.LENGTH_LONG).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(context, "Parsing error! Please try again after some time!!", Toast.LENGTH_LONG).show();
                        } else if (error instanceof NoConnectionError) {
//                            Intent i = new Intent(activity, NetworkErrorAnimationActivity.class);
//                            activity.startActivity(i);
                        } else if (error instanceof TimeoutError) {
                            Toast.makeText(context, "Connection TimeOut! Please check your internet connection.", Toast.LENGTH_LONG).show();
                        } else {
                            if (error.networkResponse.statusCode == 400) {
                                if(error.networkResponse.data!=null) {
                                    try {
                                        String body = new String(error.networkResponse.data, "UTF-8");
                                        JSONObject obj = new JSONObject(body);
                                        if (Integer.parseInt(obj.getString("responseCode")) == 10 || Integer.parseInt(obj.getString("responseCode")) == 15) {
//
                                        }
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }else{
                                    Toast.makeText(context, "Someting Went Wrong, Please try again", Toast.LENGTH_LONG).show();

                                }
                            }
                        }

                    }
                };
            }
            if (preloader) {
//                MagriPreLoader.getInstance().showDialog(activity);
            }
            GsonRequest gsonObject = new GsonRequest(type, URL, clazz, headers, buildJsonBody(params), successListner, errorListner);
            gsonObject.setRetryPolicy(new DefaultRetryPolicy(15 * 1000, 2, 1.0f));
            requestQueue.add(gsonObject);
        }else{
//            Intent i = new Intent(activity, NetworkErrorAnimationActivity.class);
//            activity.startActivity(i);

            Toast.makeText(context, "No network. Please check your internet connection!!", Toast.LENGTH_LONG).show();
        }

    }




    public <T>  void makeGsonRequest2(int type, String URL, Class<T> clazz, Map<String, String> headers,
                                      Map<String, Object> params, Response.Listener successListner, Response.ErrorListener errorListner, final Activity activity, boolean preloader){
        if(Utility.isNetworkAvailable(activity)){
            if(errorListner == null){
                errorListner  = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        MagriPreLoader.getInstance().dissmiss();
                        if ( error instanceof NetworkError) {
//                            Intent i = new Intent(activity, NetworkErrorAnimationActivity.class);
//                            activity.startActivity(i);
                            Toast.makeText(context, "The server could not be found. Please try again after some time!!", Toast.LENGTH_LONG).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(context, "The server could not be found. Please try again after some time!!", Toast.LENGTH_LONG).show();
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(context, "Cannot connect to Internet...Please check your connection!", Toast.LENGTH_LONG).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(context, "Parsing error! Please try again after some time!!", Toast.LENGTH_LONG).show();
                        } else if (error instanceof NoConnectionError) {
//                            Intent i = new Intent(activity, NetworkErrorAnimationActivity.class);
//                            activity.startActivity(i);
                        } else if (error instanceof TimeoutError) {
                            Toast.makeText(context, "Connection TimeOut! Please check your internet connection.", Toast.LENGTH_LONG).show();
                        }else {
                            if (error.networkResponse.statusCode == 400) {
                                if(error.networkResponse.data!=null) {
                                    try {
                                        String body = new String(error.networkResponse.data, "UTF-8");
                                        JSONObject obj = new JSONObject(body);
                                        if (Integer.parseInt(obj.getString("responseCode")) == 10 || Integer.parseInt(obj.getString("responseCode")) == 15) {
//                                            Utility.invalidateLogin(activity, null, null, null);
//                                            Intent logoutIntnt = new Intent(activity, OnbordingActivity.class);
//                                            logoutIntnt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                            ApplicationController.getInstance().clearApplicationData();
//                                            activity.startActivity(logoutIntnt);
                                        }
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }else{
                                    Toast.makeText(context, "Someting Went Wrong, Please try again", Toast.LENGTH_LONG).show();

                                }
                            }
                        }

                    }
                };

            }
            if(preloader){
//                MagriPreLoader.getInstance().showDialog(activity);
            }

            GsonRequest gsonObject = new GsonRequest(type,URL,clazz,headers,buildJsonBody(params),successListner, errorListner);
            gsonObject.setRetryPolicy(new DefaultRetryPolicy(15 * 1000, 2, 1.0f));
            requestQueue.add(gsonObject);

        }else{
//            Intent i = new Intent(activity, NetworkErrorAnimationActivity.class);
//            activity.startActivity(i);

            Toast.makeText(context, "No network. Please check your internet connection!!", Toast.LENGTH_SHORT).show();
        }

    }

    private String buildRequestBody(Object content) {
        String output = null;
        if ((content instanceof String) ||
                (content instanceof JSONObject) ||
                (content instanceof JSONArray)) {
            output = content.toString();
        } else if (content instanceof Map) {
            Uri.Builder builder = new Uri.Builder();
            HashMap hashMap = (HashMap) content;
            if (hashMap != null) {
                Iterator entries = hashMap.entrySet().iterator();
                while (entries.hasNext()) {
                    Map.Entry entry = (Map.Entry) entries.next();
                    builder.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                    entries.remove();
                }
                output = builder.build().getEncodedQuery();
            }
        }

        return output;
    }

    private String buildJsonBody(Object content) {
        if (content != null) {
            HashMap hashMap = (HashMap) content;
            String body = new JSONObject(hashMap).toString();
            return body;
        } else {
            return null;
        }
    }


    public boolean isRunning(Context ctx) {
        ActivityManager activityManager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(Integer.MAX_VALUE);

        for (ActivityManager.RunningTaskInfo task : tasks) {
            if (ctx.getPackageName().equalsIgnoreCase(task.baseActivity.getPackageName()))
                return true;
        }

        return false;
    }

    public <T> void makeGsonRequestWithoutRetry(int type, String URL, Class<T> clazz, Map<String, String> headers,
                                                Map<String, String> params, Response.Listener successListner,
                                                Response.ErrorListener errorListner, final Activity activity, boolean preloader) {
        if(Utility.isNetworkAvailable(activity)){
            if (errorListner == null) {
                errorListner = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        MagriPreLoader.getInstance().dissmiss();
                        if (error instanceof NetworkError) {
//                            Intent i = new Intent(activity, NetworkErrorAnimationActivity.class);
//                            activity.startActivity(i);
                        } else if (error instanceof ServerError) {
                            Toast.makeText(context, "The server could not be found. Please try again after some time!!", Toast.LENGTH_LONG).show();
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(context, "Cannot connect to Internet...Please check your connection!", Toast.LENGTH_LONG).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(context, "Parsing error! Please try again after some time!!", Toast.LENGTH_LONG).show();
                        } else if (error instanceof NoConnectionError) {
//                            Intent i = new Intent(activity, NetworkErrorAnimationActivity.class);
//                            activity.startActivity(i);
                        } else if (error instanceof TimeoutError) {
                            Toast.makeText(context, "Connection TimeOut! Please check your internet connection.", Toast.LENGTH_LONG).show();
                        } else {
                            if (error.networkResponse.statusCode == 400) {
                                if(error.networkResponse.data!=null) {
                                    try {
                                        String body = new String(error.networkResponse.data, "UTF-8");
                                        JSONObject obj = new JSONObject(body);
                                        if (Integer.parseInt(obj.getString("responseCode")) == 10 || Integer.parseInt(obj.getString("responseCode")) == 15) {
//                                            Utility.invalidateLogin(activity, null, null, null);
//                                            Intent logoutIntnt = new Intent(activity, OnbordingActivity.class);
//                                            logoutIntnt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                            ApplicationController.getInstance().clearApplicationData();
//                                            activity.startActivity(logoutIntnt);
                                        }
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }else{
                                    Toast.makeText(context, "Someting Went Wrong, Please try again", Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                    }
                };
            }
            if(preloader){
//                MagriPreLoader.getInstance().showDialog(activity);
            }
            GsonRequest gsonObject = new GsonRequest(type, URL, clazz, headers, buildJsonBody(params), successListner, errorListner);
            gsonObject.setRetryPolicy(new DefaultRetryPolicy(60 * 1000, 0, 1.0f));
            requestQueue.add(gsonObject);
        }else{
//            Intent i = new Intent(activity, NetworkErrorAnimationActivity.class);
//            activity.startActivity(i);
        }

    }


    public <T>  void makeGsonRequest2WithoutRetry(int type, String URL, Class<T> clazz, Map<String, String> headers,
                                                  Map<String, Object> params, Response.Listener successListner, Response.ErrorListener errorListner, final Activity activity, boolean preloader){
        if(Utility.isNetworkAvailable(activity)){
            if(errorListner == null){
                errorListner  = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        MagriPreLoader.getInstance().dissmiss();
                        if ( error instanceof NetworkError) {
//                            Intent i = new Intent(activity, NetworkErrorAnimationActivity.class);
//                            activity.startActivity(i);
                        } else if (error instanceof ServerError) {
                            Toast.makeText(context, "The server could not be found. Please try again after some time!!", Toast.LENGTH_LONG).show();
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(context, "Cannot connect to Internet...Please check your connection!", Toast.LENGTH_LONG).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(context, "Parsing error! Please try again after some time!!", Toast.LENGTH_LONG).show();
                        } else if (error instanceof NoConnectionError) {
//                            Intent i = new Intent(activity, NetworkErrorAnimationActivity.class);
//                            activity.startActivity(i);
                        } else if (error instanceof TimeoutError) {
                            Toast.makeText(context, "Connection TimeOut! Please check your internet connection.", Toast.LENGTH_LONG).show();
                        }else {
                            if (error.networkResponse.statusCode == 400) {
                                if(error.networkResponse.data!=null) {
                                    try {
                                        String body = new String(error.networkResponse.data, "UTF-8");
                                        JSONObject obj = new JSONObject(body);
                                        if (Integer.parseInt(obj.getString("responseCode")) == 10 || Integer.parseInt(obj.getString("responseCode")) == 15) {
//                                            Utility.invalidateLogin(activity, null, null, null);
//                                            Intent logoutIntnt = new Intent(activity, OnbordingActivity.class);
//                                            logoutIntnt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                            ApplicationController.getInstance().clearApplicationData();
//                                            activity.startActivity(logoutIntnt);
                                        }
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }else{
                                    Toast.makeText(context, "Someting Went Wrong, Please try again", Toast.LENGTH_LONG).show();

                                }
                            }
                        }

                    }
                };
            }
            if(preloader){
//                MagriPreLoader.getInstance().showDialog(activity);
            }
            GsonRequest gsonObject = new GsonRequest(type,URL,clazz,headers,buildJsonBody(params),successListner, errorListner);
            gsonObject.setRetryPolicy(new DefaultRetryPolicy(60 * 1000, 0, 1.0f));
            requestQueue.add(gsonObject);

        }else{
//            Intent i = new Intent(activity, NetworkErrorAnimationActivity.class);
//            activity.startActivity(i);
        }

    }
}
