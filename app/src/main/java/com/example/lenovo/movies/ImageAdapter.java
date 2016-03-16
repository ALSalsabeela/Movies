package com.example.lenovo.movies;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
     List<String>posters;
     Context myContext;
     ImageView image;
    public ImageAdapter(Context c,List l) {
       myContext=c;
       posters=l;

    }
//     // this method I creat for insert data into []posters
//    public void setPosters(String[] posters) {
//       this.posters=posters;
//    }

    @Override
    public int getCount() {
        return posters.size();
    }

    @Override
    public Object getItem(int position) {
        return posters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            image = new ImageView(myContext);
            image.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            image.setScaleType(ImageView.ScaleType.CENTER);
            image.setPadding(8, 8, 8, 8);
        }
        else
        {
            image = (ImageView) convertView;
        }
         Picasso.with(myContext).load(posters.get(position)).into(image);
        return image;
    }

}

