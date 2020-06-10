package com.vikas.News.Fragments;
import android.content.*;
import android.os.*;
import android.support.v4.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.github.ybq.android.spinkit.sprite.*;
import com.github.ybq.android.spinkit.style.*;
import com.google.gson.*;
import com.vikas.News.*;
import com.vikas.News.Adapters.*;
import com.vikas.News.Models.*;
import java.util.*;
import me.everything.android.ui.overscroll.*;
import org.json.*;
import android.support.v4.widget.*;

public class Headlines extends Fragment
{

	SingletonRequestQueue mSingletonRequestQueue;
	RequestQueue mRequestQueue;
    Context mContext;
    List<Article> headlines;
    RecyclerViewAdapter adapter;
   // private String API_KEY="6ba1d1f3d3014533b07eef3056947791";
    SwipeRefreshLayout mSwipeRefreshLayout;
    String url;
	boolean isLoading=false;
	int TOTAL_PAGE_COUNT=0;
	int CURRENT_PAGE=1;
	
    public Headlines()
	{

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		
		mContext = container.getContext();
		url=this.getArguments().getString("url");
		mSingletonRequestQueue=SingletonRequestQueue.getInstance(mContext);
		mRequestQueue=mSingletonRequestQueue.getRequestQueue();
		
        View v=inflater.inflate(R.layout.fragment_headlines, container, false);
      //  progressbar = v.findViewById(R.id.progressbar);
		Sprite doubleBounce = new DoubleBounce();
		//progressbar.setIndeterminateDrawable(doubleBounce);
		
        final RecyclerView recyclerview=v.findViewById(R.id.rvheadlines);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.addItemDecoration(new SpaceItemDecoration(20, recyclerview));

        headlines = new ArrayList<Article>();

        adapter = new RecyclerViewAdapter(headlines, mContext);
		
        recyclerview.setAdapter(adapter);

		recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener(){
			
				@Override
				public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
					super.onScrolled(recyclerView, dx, dy);
					int totalItemCount =headlines.size();
					int lastVisibleItem = ((LinearLayoutManager)recyclerview.getLayoutManager()).findLastVisibleItemPosition();
					if (!isLoading && totalItemCount <= (lastVisibleItem+1)) {
						loadMore();
						isLoading = true;
					}
				}
		});
		//OverScrollDecoratorHelper.setUpOverScroll(recyclerview, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
		mSwipeRefreshLayout=v.findViewById(R.id.swipe_refresh);
		mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

				@Override
				public void onRefresh()
				{
					// TODO: Implement this method
					//Toast.makeText(mContext,"Refreshing..",Toast.LENGTH_SHORT).show();
					CURRENT_PAGE=1;
					isLoading=false;
					getNews();
					
				}
			});
		getNews();
		
        return v;
    }

    public void getNews()
	{
	  mSwipeRefreshLayout.setRefreshing(true);
		
		JsonObjectRequest 
			jsonObjectRequest 
			= new JsonObjectRequest( 
			Request.Method.GET, 
			url, 
			null, 
			new Response.Listener<JSONObject>() { 
				@Override
				public void onResponse(JSONObject response) 
				{ 
				  //Toast.makeText(mContext,"response'"+response.toString(),Toast.LENGTH_SHORT).show();
					News news=(new Gson()).fromJson(response.toString(), News.class);
					TOTAL_PAGE_COUNT=news.totalResults/20+1;
					
					headlines.clear();
					headlines.addAll(news.getArticles());
					headlines.add(null);
					adapter.notifyDataSetChanged();
					mSwipeRefreshLayout.setRefreshing(false);
				} 
			}, 
			new Response.ErrorListener() { 
				@Override
				public void onErrorResponse(VolleyError error) 
				{ 
					mSwipeRefreshLayout.setRefreshing(false);
					Toast.makeText(mContext,error.toString(),Toast.LENGTH_SHORT).show();
				} 
			}); 
		mRequestQueue.add(jsonObjectRequest);
		CURRENT_PAGE++;
	}
	
	public void loadMore()
	{
		if(CURRENT_PAGE<=TOTAL_PAGE_COUNT)
		{
			
			int index=url.indexOf("page");
			String temp=url.substring(0,index+5)+CURRENT_PAGE+url.substring(index+6);
			//Toast.makeText(mContext,temp,Toast.LENGTH_LONG).show();
			JsonObjectRequest 
				jsonObjectRequest 
				= new JsonObjectRequest( 
				Request.Method.GET, 
				temp, 
				null, 
				new Response.Listener<JSONObject>() { 
					@Override
					public void onResponse(JSONObject response) 
					{ 
						//Toast.makeText(mContext,"response'"+response.toString(),Toast.LENGTH_SHORT).show();
						News news=(new Gson()).fromJson(response.toString(), News.class);
						//headlines.clear();
						headlines.remove(headlines.size()-1);
						headlines.addAll(news.getArticles());
						headlines.add(null);
						adapter.notifyDataSetChanged();
						CURRENT_PAGE++;
						isLoading=false;
					} 
				}, 
				new Response.ErrorListener() { 
					@Override
					public void onErrorResponse(VolleyError error) 
					{ 
					} 
				}); 
			mRequestQueue.add(jsonObjectRequest);
			
		}
		else
		{
			headlines.remove(headlines.size()-1);
			adapter.notifyDataSetChanged();
		}
	}
}


