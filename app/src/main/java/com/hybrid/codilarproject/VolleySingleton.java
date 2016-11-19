package com.hybrid.codilarproject;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class VolleySingleton {

    // Instance of the this class
    private static VolleySingleton mInstance = null;


    private static RequestQueue mRequestQueue;

    // Constructor
    private VolleySingleton() {
        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
    }

    // Instance getter
    public static VolleySingleton getInstance() {
        if (mInstance == null) {
            mInstance = new VolleySingleton();
        }
        return mInstance;
    }

    // Request queue getter
    public static synchronized RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

}
