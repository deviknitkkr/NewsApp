package com.vikas.News.Models;

public class Article{

	public Source source;

	public String author;

	public String title;

	public String description;

	public String url;

	public String urlToImage;

	public String publishedAt;

	public String content;

	public boolean isRotated;
	
	public boolean isVisible;

	public void setSource(Source source)
	{
		this.source = source;
	}

	public Source getSource()
	{
		return source;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getTitle()
	{
		return title;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getDescription()
	{
		return description;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrlToImage(String urlToImage)
	{
		this.urlToImage = urlToImage;
	}

	public String getUrlToImage()
	{
		return urlToImage;
	}

	public void setPublishedAt(String publishedAt)
	{
		this.publishedAt = publishedAt;
	}

	public String getPublishedAt()
	{
		return publishedAt;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getContent()
	{
		return content;
	}

	@Override
	public String toString()
	{
		return "source:"+source.toString()+"\nauthor:"+author+"\ntitle:"+title+"\n";
	}



}
