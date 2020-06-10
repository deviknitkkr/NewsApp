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


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>
{

    private List<Article> list;
    Context context;
	String countryCode;
	
    public RecyclerViewAdapter(List<Article> list, Context context)
    {
        this.list = list;
        this.context = context;
		TelephonyManager tm = (TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);
       countryCode = tm.getNetworkCountryIso();
		
    }

    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup p1, int p2)
    {
        return new MyViewHolder(LayoutInflater.from(p1.getContext()).inflate(R.layout.row_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder p1, int p2)
    {
        Article temp=list.get(p2);
        p1.article_source_name.setText(temp.source.getName());
        p1.article_title.setText(temp.title);
        p1.article_publish_time.setText(getFormattedTime(temp.publishedAt));

        Glide.with(context)
			.load(temp.urlToImage)					  
			.into(p1.article_image);

        p1.setIsRecyclable(true);

    }
	
	public String getFormattedTime(String str)
	{
		
		try
		{
			PrettyTime mPrettyTime=new PrettyTime(new Locale(Locale.getDefault().getCountry()));
			SimpleDateFormat mSimpleDateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:",Locale.getDefault());
			Date date=mSimpleDateFormat.parse(str);
			str=mPrettyTime.format(date);
		}catch(Exception e){}
		
		return str;
	}

    @Override
    public int getItemCount()
    {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
		public TextView article_source_name;
        public TextView article_title;
        public TextView article_publish_time;
		public ImageView article_image;

        public MyViewHolder(final View v)
        {
            super(v);
            article_source_name = v.findViewById(R.id.article_source_name);
            article_title = v.findViewById(R.id.article_title);
            article_publish_time = v.findViewById(R.id.article_publish_time);
			article_image = v.findViewById(R.id.article_image);

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
	
}
