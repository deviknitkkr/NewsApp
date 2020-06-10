package com.vikas.News.Models;
import android.arch.lifecycle.*;
import java.io.*;
import android.content.*;
import java.util.*;

public class MyViewModel extends ViewModel
{
	Context mContenxt;
	MutableLiveData<List<?>> livedata;
	
	public MyViewModel(Context c)
	{
		mContenxt=c;
	}
	

	
}
