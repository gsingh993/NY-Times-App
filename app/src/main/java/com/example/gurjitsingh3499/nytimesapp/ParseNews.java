package com.example.gurjitsingh3499.nytimesapp;


import java.util.ArrayList;

public class ParseNews {
    private static final String TAG = "ParseNews";
    private ArrayList<FeedEntry> news;

    public ParseNews() {
        this.news = new ArrayList<>();
    }

    public ArrayList<FeedEntry> getNews() {
        return news;
    }
}
