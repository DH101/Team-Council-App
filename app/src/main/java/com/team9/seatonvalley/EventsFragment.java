package com.team9.seatonvalley;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import java.util.ArrayList;


/**
 * @Author: Andreea Stirbu, Student Number: 160483710
 * @Since 13/04/2018
 *
 * Fragment that holds a listView and a search view
 */
public class EventsFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        //display layout
        View rootView = inflater.inflate(R.layout.activity_events, container, false);

        //initialize ans set the listView
        ListView listView = (ListView) rootView.findViewById(R.id.eventsList);
        final CustomEventsAdapter eventsAdapter = new CustomEventsAdapter(this.getActivity(), getEvents());
        listView.setAdapter(eventsAdapter);

        //add search view
        final SearchView searchEvent = (SearchView)rootView.findViewById(R.id.eventSearchBar);
        searchEvent.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                eventsAdapter.getFilter().filter(newText);
                return false;
            }
        });
        searchEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                searchEvent.setIconified(false);
            }
        });
        return rootView;
    }

    /**
     * Add events' information
     */
    private ArrayList<Events> getEvents(){

        ArrayList<Events> events = new ArrayList<Events>();

        Events events1 = new Events("http://seatonvalleycommunitycouncil.gov.uk.gridhosted.co.uk/wp-content/uploads/2018/03/April5.jpg", "Planning Sub-Committee Meeting", "The Seaton Valley Council Planning Sub-Committee meeting will be held on Thursday 5 April 2018 at the Seaton Valley Council Offices, 20-22 Astley Road, Seaton Delaval, commencing at 6:30 pm.", "Thursday 5th April 2018", "http://seatonvalleycommunitycouncil.gov.uk/2018/03/29/planning-sub-committee-meeting-5-april-2018/");
        Events events2 = new Events("http://seatonvalleycommunitycouncil.gov.uk.gridhosted.co.uk/wp-content/uploads/2018/03/Maintenance-Man-Header-565x450.jpg", "The Maintenance Man", "The Valley Players are doing a production of The Maintenance Man, a comedy-drama by Richard Harris.", "Tuesday 24th 2018", "http://seatonvalleycommunitycouncil.gov.uk/2018/03/13/the-maintenance-man/");
        Events events3 = new Events("http://seatonvalleycommunitycouncil.gov.uk.gridhosted.co.uk/wp-content/uploads/2018/03/March21.jpg", "Full Council Meeting", "The Seaton Valley Full Council meeting will be held on Wednesday 21 March 2018 at the Seaton Valley Council Offices, 20-22 Astley Road, Seaton Delaval, commencing at 7 pm.", "Wednesday 21th March 2018", "http://seatonvalleycommunitycouncil.gov.uk/2018/03/13/full-council-meeting-21-march-2018/");
        Events events4 = new Events("http://seatonvalleycommunitycouncil.gov.uk.gridhosted.co.uk/wp-content/uploads/2018/03/8March.jpg", "Planning Sub-Committee Meeting", "The Seaton Valley Council Planning Sub-Committee meeting will be held on Thursday 8 March 2018 at the Seaton Valley Council Offices, 20-22 Astley Road, Seaton Delaval, commencing at 6:30 pm.", "Thursday 8th March 2018", "http://seatonvalleycommunitycouncil.gov.uk/2018/03/01/planning-sub-committee-meeting-8-march/");
        Events events5 = new Events("http://seatonvalleycommunitycouncil.gov.uk.gridhosted.co.uk/wp-content/uploads/2018/02/Feb28.jpg", "Tonight’s Finance and General Purposes Committee Meeting – Cancelled", "Due to the extreme weather conditions we are currently experiencing tonight’s Finance and General Purposes Committee Meeting  (Wednesday 28th February) has been cancelled.", "Wednesday 28th February 2018", "http://seatonvalleycommunitycouncil.gov.uk/2018/02/28/tonights-finance-general-purposes-committee-meeting-cancelled/");
        Events events6 = new Events("http://seatonvalleycommunitycouncil.gov.uk.gridhosted.co.uk/wp-content/uploads/2018/02/February20.jpg", "Local Area Council Meeting", "The next meeting of the Cramlington, Bedlington and Seaton Valley Local Area Council will take place on Tuesday 20 February 2018 at Netherton Social Club, 1a Netherton Lane, Bedlington, NE22 6DP starting at 5:30 pm.", "Tuesday 20th February 2018", "http://seatonvalleycommunitycouncil.gov.uk/2018/02/13/local-area-council-meeting-20-february-2018/");
        Events events7 = new Events("http://seatonvalleycommunitycouncil.gov.uk.gridhosted.co.uk/wp-content/uploads/2018/02/February19.jpg", "Public Meeting for Proposed Changes to Services on 57A Bus Route.", "There will be a Public Meeting at the Seghill Institute Community Centre on Monday 19 February 2018 at 6.30pm.", "Monday 19th February 2018", "http://seatonvalleycommunitycouncil.gov.uk/2018/02/02/public-meeting-proposed-changes-services-57a-bus-route/");
        Events events8 = new Events("http://seatonvalleycommunitycouncil.gov.uk.gridhosted.co.uk/wp-content/uploads/2018/01/February7.jpg", "Planning Sub-Committee Meeting", "The Seaton Valley Council Planning Sub-Committee meeting will be held on Wednesday 7 February 2018 at the Seaton Valley Council Offices, 20-22 Astley Road, Seaton Delaval, commencing at 6.30 pm.", "Wednesday 7th February 2018", "http://seatonvalleycommunitycouncil.gov.uk/2018/01/30/planning-sub-committee-meeting-7-february-2018/");

        events.add(events1);
        events.add(events2);
        events.add(events3);
        events.add(events4);
        events.add(events5);
        events.add(events6);
        events.add(events7);
        events.add(events8);
        return events;
    }

    /**
     * Tab's title
     */
    public String toString(){
        return "Events";
    }
}

