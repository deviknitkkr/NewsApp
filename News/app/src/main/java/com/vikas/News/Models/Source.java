package com.vikas.News.Models;

public class Source
{
	//public String id;

	public String name;


//	public void setId(String id)
//	{
//		this.id = id;
//	}
//
//	public String getId()
//	{
//		return id;
//	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		// TODO: Implement this method
		return "\nname:"+name;
	}



}
