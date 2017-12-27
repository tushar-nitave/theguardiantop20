package com.example.tushar.guardiantop20;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {


    private static final String TG_NEWS_REQUEST_URL = "https://content.guardianapis.com/search?sections=news&page-size=20&show-most-viewed=true&&api-key=a1a13451-6dc6-482b-91d2-00659597ba05";
    //private static final String TG_NEWS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";
    private NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        /*ArrayList<News> feeds = new ArrayList<>();
        feeds.add(new News(1, "Ashes 2017-18: Australia v England fourth Test, day one – as it happened.", ""));
        feeds.add(new News(2, "Consumer complaints: what you can do to fix the most common ones.", " ."));
        feeds.add(new News(3, "José Mourinho says ‘£300m not enough’ for Manchester United to compete.", " "));
        feeds.add(new News(4, "Panic on Oxford Street as armed police respond to reports of gunshots", " "));
        feeds.add(new News(5, "Police investigate claims of fox being killed during Boxing Day hunt.", " "));
        feeds.add(new News(6, "Harry Kane breaks record in style as Tottenham brush aside Southampton", " "));*/


        ListView newsfeedListView = (ListView) findViewById(R.id.list);
        mAdapter = new NewsAdapter(this, new ArrayList<News>());

        newsfeedListView.setAdapter(mAdapter);

        newsfeedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                News currentNews = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentNews.getURL());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(TG_NEWS_REQUEST_URL);
    }

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<News>> {

        @Override
        protected List<News> doInBackground(String... urls) {

            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<News> result = QueryNews.fetchNewsData(urls[0]);
            return result;

        }

        @Override
        protected void onPostExecute(List<News> data) {

            // Clear the adapter of previous earthquake data
            mAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }

    }
}
