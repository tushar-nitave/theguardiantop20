package com.example.tushar.guardiantop20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {


    //private static final String TG_NEWS_REQUEST_URL = "http://content.guardianapis.com/search?sections=news&page-size=20&show-most-viewed=true&&api-key=a1a13451-6dc6-482b-91d2-00659597ba05"



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        ArrayList<News> feeds = new ArrayList<>();
        feeds.add(new News("1", "Ashes 2017-18: Australia v England fourth Test, day one – as it happened."));
        feeds.add(new News("2", "Consumer complaints: what you can do to fix the most common ones."));
        feeds.add(new News("3", "José Mourinho says ‘£300m not enough’ for Manchester United to compete."));
        feeds.add(new News("4", "Panic on Oxford Street as armed police respond to reports of gunshots"));
        feeds.add(new News("5", "Police investigate claims of fox being killed during Boxing Day hunt."));
        feeds.add(new News("6", "Harry Kane breaks record in style as Tottenham brush aside Southampton"));

        ListView newsfeedListView = (ListView) findViewById(R.id.list);

        NewsAdapter adapter = new NewsAdapter(this, feeds);
        newsfeedListView.setAdapter(adapter);
    }
}
