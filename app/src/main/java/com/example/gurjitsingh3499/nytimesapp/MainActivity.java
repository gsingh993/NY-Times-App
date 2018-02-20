package com.example.gurjitsingh3499.nytimesapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity"; //logging

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: starting Async task");
        DownloadData downloadData = new DownloadData();
        downloadData.execute("URL GOES HERE");
        Log.d(TAG, "onCreate: finished Async Task");
    }

    // The Async task will recieve 3 params -- String, Void, String
    // String - String URL to RSS, Void -- Progress Bar, and String -- Result returns from RSS
    private class DownloadData extends AsyncTask<String, Void, String> { //Use Void because we will not be using it
        private static final String TAG = "DownloadData";
        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: starts with "+strings[0]); // debug log
            String rssFeed = downLoadXML(strings[0]);
            if(rssFeed == null){
                Log.e(TAG, "doInBackground: error downloading the rss feed"); // loge for an actually error, so it remains in the production app
            }
            return rssFeed;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "onPostExecute: parameter is "+s); //debug log
        }

        private String downLoadXML(String urlPath) {
            StringBuilder xmlResult = new StringBuilder();

            try{
                URL url = new URL(urlPath); // Invoking the URL Class to create a new URL
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int response = connection.getResponseCode(); // Response Code from the HTTP Connection -- like 404
                Log.d(TAG, "downLoadXML: The response code is " +response);
//                InputStream inputStream = connection.getInputStream();
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                BufferedReader reader = new BufferedReader(inputStreamReader);
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } catch(MalformedURLException e){
                Log.e(TAG, "downLoadXML: Invalid URL " +e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "downLoadXML: IOException "+ e.getMessage());
            }
            return null;
        }


    }
}
