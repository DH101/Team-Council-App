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
 * @Since: 13/04/2018
 *
 * Fragment that holds a listView and a search view
 */
public class NewsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        //display layout
        View rootView = inflater.inflate(R.layout.activity_news, container, false);

        //initialize and set listView
        ListView listView = (ListView) rootView.findViewById(R.id.newsList);
        final CustomNewsAdapter newsAdapter = new CustomNewsAdapter(this.getActivity(), getNews());
        listView.setAdapter(newsAdapter);

        //add search view
        final SearchView searchEvent = (SearchView)rootView.findViewById(R.id.newsSearchBar);
        searchEvent.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newsAdapter.getFilter().filter(newText);
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
     * Add news' information
     */
    private ArrayList<News> getNews(){

        ArrayList<News> news = new ArrayList<News>();

        News news1 = new News("http://seatonvalleycommunitycouncil.gov.uk.gridhosted.co.uk/wp-content/uploads/2017/12/bins.jpg","Easter 2018 Bin Collections", "Residents are reminded that all bin collections across the county will be one day late during the week of the Easter Monday bank holiday.", "http://seatonvalleycommunitycouncil.gov.uk/2018/03/29/easter-2018-bin-collections/");
        News news2 = new News("http://seatonvalleycommunitycouncil.gov.uk.gridhosted.co.uk/wp-content/uploads/2018/03/SV-NP-Area.jpg", "Northumberland Local Plan Survey", "The County Council has launched the first public consultation on a new Northumberland Local Plan.", "http://seatonvalleycommunitycouncil.gov.uk/2018/03/29/northumberland-local-plan-survey/");
        News news3 = new News("http://seatonvalleycommunitycouncil.gov.uk.gridhosted.co.uk/wp-content/uploads/2018/03/Easter-2018-Header.jpg", "Easter Holiday Activities 2018","There are a number of activities being provided by Active Northumberland over the Easter Holidays in Seaton Valley.", "http://seatonvalleycommunitycouncil.gov.uk/2018/03/28/easter-holiday-activities-2018/");
        News news4 = new News("http://seatonvalleycommunitycouncil.gov.uk.gridhosted.co.uk/wp-content/uploads/2018/03/Roadworks-03042018-640x450.jpg", "Planned Roadworks for the A192 in Seaton Delaval", "There are planned roadworks on the A192 in Seaton Delaval between the 3 – 7 April 2018.", "http://seatonvalleycommunitycouncil.gov.uk/2018/03/27/planned-roadworks-a192-seaton-delaval/");
        News news5 = new News("http://seatonvalleycommunitycouncil.gov.uk.gridhosted.co.uk/wp-content/uploads/2018/02/Bus.jpg", "Evening Services on 57A Bus Route Secured", "Following a public meeting held at Seghill Institute Community Centre on Monday 19th February where local residents expressed their concerns about draft proposals to cut the number of evening journeys on the 57A Bus Route between Cramlington and Whitley Bay", "http://seatonvalleycommunitycouncil.gov.uk/2018/03/22/evening-services-57a-bus-route-secured/");
        News news6 = new News("http://seatonvalleycommunitycouncil.gov.uk.gridhosted.co.uk/wp-content/uploads/2018/01/Photo-Comp-20181.jpg", "Photography Competition 2018", "We are holding our Photography Competition again in 2018.", "http://seatonvalleycommunitycouncil.gov.uk/2018/03/12/photography-competition-2018/");
        News news7 = new News("http://seatonvalleycommunitycouncil.gov.uk.gridhosted.co.uk/wp-content/uploads/2018/03/P1040022-640x450.jpg", "Pink Poo Day", "In an effort to highlight dog fouling in the area Councillors and Staff from the Council were joined by the Northumberland Partnership Team on a Pink Poo Day in Seaton Valley.","http://seatonvalleycommunitycouncil.gov.uk/2018/03/12/pink-poo-day/");
        News news8 = new News("http://seatonvalleycommunitycouncil.gov.uk.gridhosted.co.uk/wp-content/uploads/2018/03/SSMS-OFSTED-2-627x450.jpg", "Ofsted confirm that Seaton Sluice Middle continues to be ‘Good’", "On 8 February Ofsted published its report confirming that Seaton Sluice Middle School continues to be ‘Good’ following its recent Short Inspection.", "http://seatonvalleycommunitycouncil.gov.uk/2018/03/12/ofsted-confirm-seaton-sluice-middle-continues-good/");

        news.add(news1);
        news.add(news2);
        news.add(news3);
        news.add(news4);
        news.add(news5);
        news.add(news6);
        news.add(news7);
        news.add(news8);
        return news;
    }

    /**
     * Tab's title
     */
    public String toString(){
        return "News";
    }
}
