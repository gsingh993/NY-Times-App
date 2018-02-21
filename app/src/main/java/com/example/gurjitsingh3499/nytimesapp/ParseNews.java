package com.example.gurjitsingh3499.nytimesapp;


import android.util.Log;

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

    public boolean parse(String xmlData) {
        boolean status = true;
        FeedEntry currentRecord = null;
        boolean inEntry = false;
        String textValue = "";
        boolean gotImage = false;

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xmlData));
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                       // Log.d(TAG, "parse: Starting tag for " + tagName);
                        if ("item".equalsIgnoreCase(tagName)) {
                            inEntry = true;
                            currentRecord = new FeedEntry();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        //Log.d(TAG, "parse: Ending tag for" + tagName);
                        if (inEntry) {
                            if ("item".equalsIgnoreCase(tagName)) {
                                news.add(currentRecord);
                                inEntry = false;
                            } else if ("title".equalsIgnoreCase(tagName)) {
                                currentRecord.setName(textValue);
                            } else if ("link".equalsIgnoreCase(tagName)) {
                                currentRecord.setAuthor(textValue);
                            } else if ("pubdate".equalsIgnoreCase(tagName)) {
                                currentRecord.setPubDate(textValue);
                            } else if ("description".equalsIgnoreCase(tagName)) {
                                currentRecord.setSummary(textValue);
                            }
                        }
                        break;

                    default:
                        //Nothing to do
                }
                eventType = xpp.next();
            }
            for (FeedEntry news : news) {
                Log.d(TAG, "****************************");
                Log.i(TAG, news.toString());
            }

        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return status;
    }
}
