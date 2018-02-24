package com.example.gurjitsingh3499.nytimesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gurjitsingh on 2/23/18.
 */

public class FeedAdapter extends ArrayAdapter {
    private static final String TAG = "Feed Adapter";
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<FeedEntry> news;

    public FeedAdapter(Context context, int resource, List<FeedEntry> news) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.news = news;
    }

    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(layoutResource, parent, false);
        }
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvAuthor = (TextView) convertView.findViewById(R.id.tvAuthor);
        TextView tvPubDate = (TextView) convertView.findViewById(R.id.tvPubDate);
        TextView tvSummary = (TextView) convertView.findViewById(R.id.tvSummary);
        TextView tvSummary2 = (TextView) convertView.findViewById(R.id.tvSummary2);

        FeedEntry currentNews = news.get(position);

        //        FeedEntry currentNews = news.get(position);

        tvTitle.setText(currentNews.getName());
        tvAuthor.setText(currentNews.getAuthor());
        tvPubDate.setText(currentNews.getPubDate());
        tvSummary.setText(currentNews.getSummary());
        tvSummary2.setText(currentNews.getImageUrl());

        return convertView;
    }

}
