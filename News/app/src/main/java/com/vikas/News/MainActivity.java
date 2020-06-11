package com.vikas.News;

import android.content.*;
import android.net.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.app.*;
import android.support.v4.view.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.telephony.*;
import android.view.*;
import android.widget.*;
import com.vikas.News.Adapters.*;
import com.vikas.News.Fragments.*;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity
{

    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager mViewPager;
	private String API_KEY="8cfd5953c4564098af22f732bdb27675";
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		toolbar.setNavigationIcon(R.drawable.ic_search_white_24dp);
		
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
		TelephonyManager tm = (TelephonyManager)this.getSystemService(this.TELEPHONY_SERVICE);
        String countryCode = tm.getNetworkCountryIso();
		
		//addFragmentForCategory("Covid-19", "https://newsapi.org/v2/everything?q=covid-19&apiKey="+ API_KEY);
		addFragmentForCategory("Headlines", "https://newsapi.org/v2/top-headlines?country=" + countryCode + "&page=1&apiKey=" + API_KEY);
		//addFragmentForCategory("Business", "https://newsapi.org/v2/top-headlines?country=" + countryCode + "&page=1&category=business&apiKey=" + API_KEY);
		addFragmentForCategory("Technology", "https://newsapi.org/v2/top-headlines?country=" + countryCode + "&page=1&category=technology&apiKey=" + API_KEY);
		//addFragmentForCategory("Science", "https://newsapi.org/v2/top-headlines?country=" + countryCode + "&page=1&category=science&apiKey=" + API_KEY);
		//addFragmentForCategory("Politics", "https://newsapi.org/v2/top-headlines?country=" + countryCode + "&category=politics&apiKey=" + API_KEY);
		//addFragmentForCategory("Sports", "https://newsapi.org/v2/top-headlines?country=" + countryCode + "&page=1&category=sports&apiKey=" + API_KEY);
		//addFragmentForCategory("Bollywood", "https://newsapi.org/v2/top-headlines?country=" + countryCode + "&page=1&category=gaming&apiKey=" + API_KEY);
		
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mViewPagerAdapter);
		mViewPager.setOffscreenPageLimit(6);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
		
		isInternetOn();
    }
	
	public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =  
			(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

		// Check for network connections
		if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
			connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
			connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
			connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

			// if connected with internet

			//Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
			return true;

		} else if ( 
			connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
			connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

			Toast.makeText(this, "You're offline", Toast.LENGTH_LONG).show();
			return false;
		}
		return false;
	}
	
	

	public void addFragmentForCategory(String title, String url)
	{
		Fragment f=new Headlines();
		Bundle b=new Bundle();
		b.putString("url", url);
		f.setArguments(b);
		mViewPagerAdapter.addFragment(f, title);

	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
	{
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
	   // searchView.setMenuItem(menu.getItem(android.R.id.home));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
	{

        int id = item.getItemId();

		switch (id)
		{
			case R.id.action_settings:
				Toast.makeText(this,item.getTitle(), Toast.LENGTH_SHORT).show();
				return true;
			case android.R.id.home:
				startActivity(new Intent(this,SearchTopicsActivity.class));
				return true;
		}

        return super.onOptionsItemSelected(item);
    }

}
