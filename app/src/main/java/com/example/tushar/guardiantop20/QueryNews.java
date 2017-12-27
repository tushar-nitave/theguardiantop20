package com.example.tushar.guardiantop20;

import android.text.TextUtils;
import android.util.Log;
import android.util.MalformedJsonException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tushar on 27-12-2017.
 */

public final class QueryNews {


    private static final String LOG_TAG = NewsActivity.class.getName();

    private QueryNews(){

    }

    public static List<News> fetchNewsData(String requestUrl) {
        // Create URL object
        URL url = createURL(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<News> earthquakes = extractNewsFromJson(jsonResponse);

        // Return the list of {@link Earthquake}s
        return earthquakes;
    }

    private static URL createURL(String stringurl){
        URL url = null;
        try{
            url = new URL(stringurl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse = "";
         if(url == null){
             return jsonResponse;
         }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }


        }catch (IOException e){
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    private static List<News> extractNewsFromJson(String newsJSON){
        if(TextUtils.isEmpty(newsJSON)){
            return null;
        }

        List<News> feeds = new ArrayList<>();

        try{
            JSONObject baseJsonResponse = new JSONObject(newsJSON);
            JSONObject responseObject = baseJsonResponse.getJSONObject("response");
            JSONArray newsArray = responseObject.getJSONArray("results");

            for(int i=0; i<newsArray.length(); i++){
                //JSONObject currentNews = newsArray.getJSONObject(i);

                String newsTitle = newsArray.getJSONObject(i).getString("webTitle");
                String newsURL = newsArray.getJSONObject(i).getString("webUrl");
                int val = i+1;
                String position = String.valueOf(val);
                News feed = new News(position, newsTitle, newsURL);
                feeds.add(feed);
            }

        }catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        return feeds;
    }
}
