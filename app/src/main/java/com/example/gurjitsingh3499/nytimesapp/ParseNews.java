package com.example.gurjitsingh3499.nytimesapp;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class ParseNews {
    private static final String TAG = "ParseNews";
    private ArrayList<FeedEntry> news; // Creates an Array list object, named new

    public ParseNews() {
        this.news = new ArrayList<>();
    }

    public ArrayList<FeedEntry> getNews() {
        return news;
    }

    public boolean parse(String xmlData){
        boolean status = true;
        FeedEntry currentRecord;
        boolean inEntry = false;
        String textValue = "";

        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xmlData));
            int eventType = xpp.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){

            }

        }catch (Exception e){
            status = false;
            e.printStackTrace();
        }
        return status;
    }
}
