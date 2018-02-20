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
        downloadData.execute("http://rss.nytimes.com/services/xml/rss/nyt/World.xml");
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
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                int charsRead;
                char[] inputBuffer = new char[1000];
                while(true){
                    charsRead = reader.read(inputBuffer);
                    if(charsRead < 0){
                        break;
                    }
                    if(charsRead > 0) {
                        xmlResult.append(String.copyValueOf(inputBuffer, 0, charsRead)); //chars read represent the length of the charRead Object
                    }
                }
                reader.close(); //Will close the stream reader and the source

                return xmlResult.toString(); // casts the char array to string
            } catch(MalformedURLException e){
                Log.e(TAG, "downLoadXML: Invalid URL " +e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "downLoadXML: IOException "+ e.getMessage());
            }
            return null; // prevent Android Studio from throwing errors
        }


    }
}
