package com.vikas.News.Models;

import java.util.*;

public class News
{
	public String status;

	public int totalResults;

	public List<Article> articles;


	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getStatus()
	{
		return status;
	}

	public void setTotalResults(int totalResults)
	{
		this.totalResults = totalResults;
	}

	public int getTotalResults()
	{
		return totalResults;
	}

	public void setArticles(List<Article> articles)
	{
		this.articles = articles;
	}

	public List<Article> getArticles()
	{
		return articles;
	}

	@Override
	public String toString()
	{
		// TODO: Implement this method
		return "status:"+status+"\ntotalResults:"+totalResults+"\narticles:"+articles.toString();
	}


}




