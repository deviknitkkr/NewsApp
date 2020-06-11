package com.vikas.News;
import android.content.*;
import android.os.*;
import android.support.v4.app.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import com.vikas.News.Fragments.*;


public class SearchTopicsActivity extends AppCompatActivity implements android.support.v7.widget.SearchView.OnQueryTextListener
{


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchtopics);
		
		Toolbar toolbar=findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		Intent intent=getIntent();
		
		
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu)
	{
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
		MenuItem menuitem=menu.findItem(R.id.menu_search);
		menuitem.expandActionView();
		android.support.v7.widget.SearchView searchView=(android.support.v7.widget.SearchView)menuitem.getActionView();
		searchView.setQueryHint("Search Topics...");
		searchView.setOnQueryTextListener(this);
        searchView.setIconified(false);
		//searchView.setFocusedByDefault(true);
		//searchView.requestFocus();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
	{

        int id = item.getItemId();

		switch (id)
		{
			case R.id.menu_search:
				return true;
			case android.R.id.home:
				finish();
				return true;
		}

        return super.onOptionsItemSelected(item);
    }
	
	@Override
	public boolean onQueryTextSubmit(String p1)
	{
		loadFragmemt(p1);
		return false;
	}

	@Override
	public boolean onQueryTextChange(String p1)
	{
		// TODO: Implement this method
		return false;
	}
	
	public void loadFragmemt(String quary)
	{
		String url="https://newsapi.org/v2/everything?q="+quary+"&page=1&sortBy=publishedAt&apiKey=6ba1d1f3d3014533b07eef3056947791";
		Fragment f=new Headlines();
		Bundle b=new Bundle();
		b.putString("url", url);
		f.setArguments(b);
		FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.container,f);
		ft.addToBackStack(null);
		ft.commit();
	}
	
}
