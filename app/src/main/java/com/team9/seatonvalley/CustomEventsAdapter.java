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
 *  Class that populates the event's CardViews with data and filters the event's title and description
 */
public class CustomEventsAdapter extends BaseAdapter implements Filterable {

    //Fields
    Context c;
    ArrayList<Events> events;
    LayoutInflater inflater;
    ArrayList<Events> filterEventsList;
    CustomEventsFilter filterEvents;

    //Constructor
    public CustomEventsAdapter(Context c, ArrayList<Events> events) {
        this.c = c;
        this.events = events;
        this.filterEventsList = events;
    }

    /**
     * The getCount() function returns the total number of items to be displayed in a list
     * @return events.size() - the list's size/the number of elements
     */
    @Override
    public int getCount() {
        return events.size();
    }

    /**
     * This function is used to Get the data item associated with the specified position in the data set
     * to obtain the corresponding data of the specific location in the collection of data items.
     */
    @Override
    public Object getItem(int position) {
        return events.get(position);
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
            view = inflater.inflate(R.layout.listitem_events, viewGroup, false);
        }

        //get data
        final Events currentEvent = (Events) getItem(position);
        String eventImage = currentEvent.getEventsImage();
        String eventTitle = currentEvent.getEventsTitle();
        String eventDescription = currentEvent.getEventsDescription();

        //set data
        MyEventsHolder holder = new MyEventsHolder(view);
        holder.eventsTitleView.setText(eventTitle);
        holder.eventsDescriptionView.setText(eventDescription);
        Picasso.with(c).load(eventImage).into(holder.eventsImageView);

        //send the user to the event's link web page
        final String eventLink = currentEvent.getEventsLink();
        holder.readMoreView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(eventLink));
                    c.startActivity(myIntent);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        holder.eventsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(eventLink));
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
                Toast.makeText(c, currentEvent.getEventsTitle(), Toast.LENGTH_SHORT).show();
                Toast.makeText(c, currentEvent.getEventsDescription(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    /**
     * return the search results
     */
    @Override
    public Filter getFilter() {
        if(filterEvents == null){
            filterEvents = new CustomEventsFilter(filterEventsList, this);
        }
        return filterEvents;
    }
}

/**
 * Class that holds the Views corresponding to the event's CardView
 */
 class MyEventsHolder implements View.OnClickListener{

    //References for views
    CardView eventsCardView;
    ImageView eventsImageView;
    TextView eventsTitleView;
    TextView eventsDescriptionView;
    Button readMoreView;
    ItemClickListener itemClickListener;

     //get ids for views
    public MyEventsHolder(View view){

        eventsCardView = view.findViewById(R.id.events_card_view);
        eventsImageView = view.findViewById(R.id.eventsImage);
        eventsTitleView = view.findViewById(R.id.eventsTitle);
        eventsDescriptionView = view.findViewById(R.id.eventsDescription);
        readMoreView = view.findViewById(R.id.eventReadMore);

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
