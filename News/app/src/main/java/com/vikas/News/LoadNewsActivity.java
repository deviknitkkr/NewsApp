package com.vikas.News;
import android.annotation.*;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.webkit.*;
import android.widget.*;
import com.github.ybq.android.spinkit.sprite.*;
import com.github.ybq.android.spinkit.style.*;
import java.io.*;

import android.support.v7.widget.Toolbar;

public class LoadNewsActivity extends AppCompatActivity
{
  ProgressBar progresbar;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loadnews);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
		
		progresbar=findViewById(R.id.progresbar);
		Sprite doubleBounce = new DoubleBounce();
		progresbar.setIndeterminateDrawable(doubleBounce);
		
        startWebView();
		
	}
	
	public void startWebView()
	{
		Intent intent=getIntent();
		String url=intent.getStringExtra("URL");
		String name=intent.getStringExtra("NAME");

		getSupportActionBar().setTitle(name);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		WebView webview = (WebView) findViewById(R.id.webview);
		webview.getSettings().setJavaScriptEnabled(true); 
		webview.getSettings().setAppCacheEnabled(true);
		webview.getSettings().setEnableSmoothTransition(true);
		webview.getSettings().setSafeBrowsingEnabled(true);
		webview.getSettings().setLoadWithOverviewMode(true);
		webview.setWebViewClient(new MyWebClient());

		webview.loadUrl(url);
		
	}
	class MyWebClient extends WebViewClient
	{
		@Nullable
		@Override
		public WebResourceResponse shouldInterceptRequest(WebView view,String url) 
		{
			String temp=url;
			if(temp.contains("google")||temp.contains("facebook")){
				InputStream textStream = new ByteArrayInputStream("".getBytes());
				return getTextWebResource(textStream);
			}
			return super.shouldInterceptRequest(view, url);
		}

		@Override
		public void onPageCommitVisible(WebView view, String url)
		{
			// TODO: Implement this method
			progresbar.setVisibility(View.GONE);
			super.onPageCommitVisible(view, url);
		}

		
	   
		
	}
	
	private WebResourceResponse getTextWebResource(InputStream data) {
		return new WebResourceResponse("text/plain", "UTF-8", data);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if (item.getItemId() == android.R.id.home) {
			onBackPressed();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
	
	
}
