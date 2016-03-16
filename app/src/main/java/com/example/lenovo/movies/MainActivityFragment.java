package com.example.lenovo.movies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class MainActivityFragment extends Fragment {
    List<String> posters;
    GridView movies;
    ImageAdapter adapter ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        update();
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
         movies = (GridView) root.findViewById(R.id.grid_movies);
        //   update();
//        movies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent i = new Intent(getActivity(), PosterDetails.class);
//                i.putExtra("id", position);
//                startActivity(i);
//            }
//        });
        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            update();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void update() {
        GetMoviesPosters getMovies=new GetMoviesPosters();
        getMovies.execute();
        Log.w("values",  posters.get(0)+posters.get(1)+"end");
        adapter= new ImageAdapter(getActivity(),posters);
        movies.setAdapter(adapter);

    }

    class GetMoviesPosters extends AsyncTask<Void, Integer, String> {

        //      https://api.themoviedb.org/3/movie/550?api_key=fbd50b883df0d6de3344f4566e09783c
        @Override
        protected String doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
// Will contain the raw JSON response as a string.
            String dataJsonStr = null;
            try {
// Construct the URL for the OpenWeatherMap query
// Possible parameters are available at OWM's forecast API page, at
// http://openweathermap.org/API#forecast
                //   String baseUrl = "\"https://api.themoviedb.org/3/movie/550";
                //  String apiKey = "?api_key=" + BuildConfig.MOVIES_API_KEY;
                //  URL url = new URL(baseUrl.concat(apiKey));
                URL url = new URL("http://api.themoviedb.org/3/discover/movie?api_key=fbd50b883df0d6de3344f4566e09783c");
// Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
// Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
// Nothing to do.
                    dataJsonStr = null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
// Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
// But it does make debugging a *lot* easier if you print out the completed
// buffer for debugging.
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
// Stream was empty. No point in parsing.
                    dataJsonStr = null;
                }
                dataJsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
// If the code didn't successfully get the weather data, there's no point in attempting
// to parse it.
                dataJsonStr = null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
               // Log.e("the data", dataJsonStr);
                return dataJsonStr;
            }

        }

        protected void onPostExecute(String result) {

            try {

                for(String filmPath: getDataFromJson(result)) {
                    posters.add(filmPath);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        private String[] getDataFromJson(String dataJsonStr)
                throws JSONException {
            JSONObject jsonObject = new JSONObject(dataJsonStr);
            JSONArray resulte = jsonObject.getJSONArray("results");
            String baseUri = " http://image.tmdb.org/t/p/w185/";
            String[] postersPath = new String[resulte.length()];
            for (int i = 0; i < resulte.length(); i++) {
                JSONObject film = resulte.getJSONObject(i);
                postersPath[i] = baseUri +film.getString("poster_path");
            }
            return postersPath;
        }



    }

}
       /*final String title = "original_title";
            final String poster_path = "poster_path";
            final String overview = "overview";
            final String topRating = "vote_count";
            final String releseDate = "release_date";
            JSONObject moviesJson = new JSONObject(dataJsonStr);
            String[] resultStrs =new String[5];
            resultStrs[0]=moviesJson.getString(title);
            resultStrs[1]=moviesJson.getString(poster_path);
            resultStrs[2]=moviesJson.getString(overview);
            resultStrs[3]=moviesJson.getString(topRating);
            resultStrs[0]=moviesJson.getString(releseDate);*/