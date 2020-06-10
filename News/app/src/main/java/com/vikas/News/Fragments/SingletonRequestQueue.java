package com.vikas.News.Fragments;

import android.content.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;

public class SingletonRequestQueue
 {

	private static SingletonRequestQueue mInstance;
	private Context mContext;
	private RequestQueue mRequestQueue;

	private SingletonRequestQueue(Context context) {
		mContext = context;
		mRequestQueue = getRequestQueue();
	}

	public static synchronized SingletonRequestQueue getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new SingletonRequestQueue(context);
		}
		return mInstance;
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(mContext);
		}
		return mRequestQueue;
	}
}

