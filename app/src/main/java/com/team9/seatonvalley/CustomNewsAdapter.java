package com.team9.seatonvalley;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * @Author: Andreea Stirbu, Student Number: 160483710
 * @Since: 13/04/2018
 *
 *  Class that populates the news' CardViews with data and filters the news' title and description
 */
public class CustomNewsAdapter extends BaseAdapter implements Filterable{

    //Fields
    Context c;
    ArrayList <News> news;
    LayoutInflater inflater;
    ArrayList<News> filterNewsList;
    CustomNewsFilter filterNews;

    //Constructor
    public CustomNewsAdapter(Context c, ArrayList<News> news) {
        this.c = c;
        this.news = news;
        this.filterNewsList = news;
    }

    /**
     * The getCount() function returns the total number of items to be displayed in a list
     * @return news.size() - the list's size/the number of elements
     */
    @Override
    public int getCount() {
        return news.size();
    }

    /**
     * This function is used to Get the data item associated with the specified position in the data set
     * to obtain the corresponding data of the specific location in the collection of data items.
     */
    @Override
    public Object getItem(int position) {
        return news.get(position);
    }

    /**
     * As for the getItemId (int position), it returns the corresponding to the position item ID.
     * The function returns a long value of item position to the adapter.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * This function is automatically called when the list item view is ready to be displayed or about to be displayed.
     * In this function we set the layout for list items using LayoutInflater class and then add the data to the views
     */
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        //set layout for displaying items
        if(inflater == null){
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(view == null){
            view = inflater.inflate(R.layout.listitem_news, viewGroup, false);
        }

        //get data
        final News currentNews = (News)getItem(position);
        String newsImage = currentNews.getNewsImage();
        String newsTitle = currentNews.getNewsTitle();
        String newsDescription = currentNews.getNewsDescription();

        //set data
        MyNewsHolder holder = new MyNewsHolder(view);
        holder.newsTitleView.setText(newsTitle);
        holder.newsDescriptionView.setText(newsDescription);
        Picasso.with(c).load(newsImage).into(holder.newsImageView);

        //send the user to the event's link web page
        final String newsLink =  news.get(position).getNewsLink();
        holder.readMoreView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsLink));
                    c.startActivity(myIntent);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        holder.newsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsLink));
                    c.startActivity(myIntent);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });



        //get the event's title and description for filtering
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v) {
                Toast.makeText(c, currentNews.getNewsTitle(), Toast.LENGTH_SHORT).show();
                Toast.makeText(c, currentNews.getNewsDescription(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    /**
     * return the search results
     */
    @Override
    public Filter getFilter() {
        if(filterNews == null){
            filterNews = new CustomNewsFilter(filterNewsList, this);
        }

        return filterNews;
    }
}
/**
 * Class that holds the Views corresponding to the news' CardView
 */
class MyNewsHolder implements View.OnClickListener{

    //References for views
    CardView newsCardView;
    ImageView newsImageView;
    TextView newsTitleView;
    TextView newsDescriptionView;
    Button readMoreView;
    ItemClickListener itemClickListener;

    //get ids for views
    public MyNewsHolder(View view){
        newsCardView = view.findViewById(R.id.news_card_view);
        newsImageView = view.findViewById(R.id.newsImage);
        newsTitleView = view.findViewById(R.id.newsTitle);
        newsDescriptionView = view.findViewById(R.id.newsDescription);
        readMoreView = view.findViewById(R.id.newsReadMore);

        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(view);
    }

    public void setItemClickListener(ItemClickListener ic){
        this.itemClickListener = ic;
    }
}

