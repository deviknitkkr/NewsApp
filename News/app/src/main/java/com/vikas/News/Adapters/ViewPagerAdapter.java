package com.vikas.News.Adapters;
import android.content.*;
import android.support.v4.app.*;
import com.vikas.News.Fragments.*;
import java.util.*;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    
	List<Fragment> fragments;
	List<String> titles;
    private Context context;

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
		fragments=new ArrayList<Fragment>();
		titles=new ArrayList<String>();
		
    }

	public void addFragment(Fragment f,String title)
	{
		fragments.add(f);
		titles.add(title);
	}
	
    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
