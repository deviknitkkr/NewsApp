package com.vikas.News.Fragments;
import android.graphics.*;
import android.support.v7.widget.*;
import android.view.*;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration
{

    private int halfSpace;

    public SpaceItemDecoration(int space,RecyclerView parent)
    {
        this.halfSpace = space / 2;
        parent.setPadding(halfSpace, halfSpace, halfSpace, halfSpace);
        parent.setClipToPadding(false);
        parent.setClipChildren(false);

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        outRect.set(halfSpace, halfSpace, halfSpace, halfSpace);
    }
}
