package com.example.gurjitsingh3499.nytimesapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
            return "doInBackground completed.";
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "onPostExecute: parameter is "+s); //debug log
        }


    }
}
