package com.example.tushar.guardiantop20;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tushar on 26-12-2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {


    public NewsAdapter(Context context, List<News> feeds) {
        super(context, 0, feeds);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_feed_list, parent, false);
        }

        News currentNews = getItem(position);

        TextView numberView = (TextView) listItemView.findViewById(R.id.news_position);
        numberView.setText(currentNews.getNewsPosition());

        TextView newsheadlinesView = (TextView) listItemView.findViewById(R.id.news_headlines);
        newsheadlinesView.setText(currentNews.getNewsTitle());

        return listItemView;
    }
}
