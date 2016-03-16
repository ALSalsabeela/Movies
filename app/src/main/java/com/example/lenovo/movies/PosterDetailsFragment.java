package com.example.lenovo.movies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class PosterDetailsFragment extends Fragment {

    public PosterDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View root= inflater.inflate(R.layout.fragment_poster_details, container, false);
       // Intent i = getActivity().getIntent();
       // int position = i.getExtras().getInt("id");
       // ImageAdapter imageAdapter = new ImageAdapter(getActivity());
        //ImageView imageView = (ImageView) root.findViewById(R.id.poster);
        //imageView.setImageResource(imageAdapter.posters[position]);
      //  Picasso.with(getContext()).load("http://i.imgur.com/DvpvklR.png").into(imageView);


        return root;
    }
}
