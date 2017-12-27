package com.example.tushar.guardiantop20;

/**
 * Created by tushar on 26-12-2017.
 */

public class News {

    private String mNewsPosition;
    private String mNewsHeadlines;
    private String mURL;

    public News(String news_position, String news_headlines, String url){
        mNewsPosition = news_position;
        mNewsHeadlines = news_headlines;
        mURL = url;
    }

    public String getNewsPosition(){return mNewsPosition;}
    public String getNewsTitle() {return mNewsHeadlines;}
    public String getURL() {return  mURL;}
}
