package com.example.gurjitsingh3499.nytimesapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // The Async task will recieve 3 params -- String, Void, String
    // String - String URL to RSS, Void -- Progress Bar, and String -- Result returns from RSS
    private class DownloadData extends AsyncTask<String, Void, String> { //Use Void because we will not be using it

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
