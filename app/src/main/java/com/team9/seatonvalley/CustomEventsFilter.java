package com.team9.seatonvalley;

import android.widget.Filter;

import java.util.ArrayList;

/**
 * @Author: Andreea Stirbu, Student Number: 160483710
 * @Since: 16/04/2018
 *
 * Class that sets a SearchView to filter an event's title and description
 */
public class CustomEventsFilter extends Filter {

    //Fields
    private ArrayList<Events>  filterEventsList;
    private CustomEventsAdapter eventsAdapter;

    //Constructor
    public CustomEventsFilter(ArrayList<Events> filterEventsList, CustomEventsAdapter eventsAdapter) {
        this.filterEventsList = filterEventsList;
        this.eventsAdapter = eventsAdapter;
    }

    /**
     * Filters title and description
     */
    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {

        FilterResults results = new FilterResults();

        if(charSequence != null && charSequence.length() > 0){
            charSequence = charSequence.toString().toUpperCase();

            ArrayList<Events> filteredEvents = new ArrayList<>();

            for(int i = 0; i < filterEventsList.size(); i++){
                if(filterEventsList.get(i).getEventsTitle().toUpperCase().contains(charSequence)){
                    filteredEvents.add(filterEventsList.get(i));
                }
                if(filterEventsList.get(i).getEventsDescription().toUpperCase().contains(charSequence)){
                    filteredEvents.add(filterEventsList.get(i));
                }
            }

            results.count = filteredEvents.size();
            results.values = filteredEvents;
        }else {
            results.count = filterEventsList.size();
            results.values = filterEventsList;
        }
        return results;
    }

    /**
     * Synchronize results
     */
    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        eventsAdapter.events = (ArrayList<Events>)filterResults.values;
        eventsAdapter.notifyDataSetChanged();
    }
}
