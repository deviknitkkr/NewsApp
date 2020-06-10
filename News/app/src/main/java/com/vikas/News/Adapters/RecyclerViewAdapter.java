package com.vikas.News.Adapters;
import android.content.*;
import android.support.v7.widget.*;
import android.telephony.*;
import android.view.*;
import android.widget.*;
import com.bumptech.glide.*;
import com.vikas.News.*;
import com.vikas.News.Models.*;
import java.util.*;
import org.ocpsoft.prettytime.*;
import android.icu.text.*;
import com.github.ybq.android.spinkit.sprite.*;
import com.github.ybq.android.spinkit.style.*;
import android.view.animation.*;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private List<Article> list;
    Context context;
	String countryCode;
	private static int View_News=1;
	private static int View_Progress=2;
	Animation forward_anim;
    Animation backward_anim;
	
    public RecyclerViewAdapter(List<Article> list, Context context)
    {
        this.list = list;
        this.context = context;
		forward_anim=AnimationUtils.loadAnimation(context, R.anim.rotate_forward);
		backward_anim=AnimationUtils.loadAnimation(context, R.anim.rotate_backward);
		
		TelephonyManager tm = (TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);
        countryCode = tm.getNetworkCountryIso();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup p1, int p2)
    {
		if (p2 == View_News)
		{
			return new NewsViewHolder(LayoutInflater.from(p1.getContext()).inflate(R.layout.row_item, null));
		}
        else
		{
			return new ProgressViewHolder(LayoutInflater.from(p1.getContext()).inflate(R.layout.row_progress, null));
		}
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder p1, int p2)
    {
		if (getItemViewType(p2) == View_News)
		{

			Article temp=list.get(p2);
			((NewsViewHolder)p1).article_source_name.setText(temp.source.getName());
			((NewsViewHolder)p1).article_title.setText(temp.title);
			((NewsViewHolder)p1).article_publish_time.setText(getFormattedTime(temp.publishedAt));
			((NewsViewHolder)p1).article_description.setText(temp.description);

			Glide.with(context)
				.load(temp.urlToImage)					  
				.into(((NewsViewHolder)p1).article_image);

			p1.setIsRecyclable(true);
		}
		else
		{
			Sprite drawable=new Wave();
			((ProgressViewHolder)p1).progressbar.setIndeterminateDrawable(drawable);
		}

    }

	public String getFormattedTime(String str)
	{

		try
		{
			PrettyTime mPrettyTime=new PrettyTime(new Locale(Locale.getDefault().getCountry()));
			SimpleDateFormat mSimpleDateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:", Locale.getDefault());
			Date date=mSimpleDateFormat.parse(str);
			str = mPrettyTime.format(date);
		}
		catch (Exception e)
		{}

		return str;
	}

    @Override
    public int getItemCount()
    {
        return list.size();
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder
    {
		public TextView article_source_name;
        public TextView article_title;
        public TextView article_publish_time;
		public ImageView article_image;
		public ImageButton article_explore;
        public TextView article_description;
		
        public NewsViewHolder(final View v)
        {
            super(v);
            article_source_name = v.findViewById(R.id.article_source_name);
            article_title = v.findViewById(R.id.article_title);
            article_publish_time = v.findViewById(R.id.article_publish_time);
			article_image = v.findViewById(R.id.article_image);
            article_explore=v.findViewById(R.id.article_explore);
			article_description=v.findViewById(R.id.article_content);
			article_explore.setOnClickListener(new View.OnClickListener(){

					@Override
					public void onClick(View p1)
					{
						Article a=list.get(getPosition());
						article_explore.setRotation(article_explore.getRotation()+180);
						//article_explore.startAnimation((a.isRotated=!a.isRotated)?forward_anim:backward_anim);
						article_description.setVisibility((a.isVisible=!a.isVisible)? View.VISIBLE:View.GONE);
					}
				});
			v.setOnClickListener(new View.OnClickListener(){

					@Override
					public void onClick(View p1)
					{
						String url=list.get(getPosition()).url;
						Intent intent=new Intent(context, LoadNewsActivity.class);
						intent.putExtra("URL", url);
						intent.putExtra("NAME", list.get(getPosition()).source.getName());
						context.startActivity(intent);
					}
				});


        }
    }

	public class ProgressViewHolder extends RecyclerView.ViewHolder
	{
		ProgressBar progressbar;
		public  ProgressViewHolder(View v)
		{
			super(v);
			progressbar=v.findViewById(R.id.progressbar);
		}
	}

	@Override
	public int getItemViewType(int position)
	{
		if (list.get(position)==null)
		{
			return View_Progress;
		}
		else
		{
			return View_News;
		}
		//return super.getItemViewType(position);
	}



}
