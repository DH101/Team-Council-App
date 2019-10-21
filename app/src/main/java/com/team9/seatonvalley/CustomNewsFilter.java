package com.team9.seatonvalley;

import android.widget.Filter;

import java.util.ArrayList;

/**
 * @Author: Andreea Stirbu, Student Number: 160483710
 * @Since: 16/04/2018
 *
 * Class that sets a SearchView to filter a news' title and description
 */
public class CustomNewsFilter extends Filter {

    //Fields
    private ArrayList<News>  filterNewsList;
    private CustomNewsAdapter newsAdapter;

    //Constructor
    public CustomNewsFilter(ArrayList<News> filterNewsList, CustomNewsAdapter newsAdapter) {
        this.filterNewsList = filterNewsList;
        this.newsAdapter = newsAdapter;
    }

    /**
     * Filters title and description
     */
    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {

        FilterResults results = new FilterResults();

        if(charSequence != null && charSequence.length() > 0){
            charSequence = charSequence.toString().toUpperCase();

            ArrayList<News> filteredNews = new ArrayList<>();

            for(int i = 0; i < filterNewsList.size(); i++){
                if(filterNewsList.get(i).getNewsTitle().toUpperCase().contains(charSequence)){
                    filteredNews.add(filterNewsList.get(i));
                }
                if(filterNewsList.get(i).getNewsDescription().toUpperCase().contains(charSequence)){
                    filteredNews.add(filterNewsList.get(i));
                }
            }

            results.count = filteredNews.size();
            results.values = filteredNews;
        }else {
            results.count = filterNewsList.size();
            results.values = filterNewsList;
        }
        return results;
    }

    /**
     * Synchronize results
     */
    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        newsAdapter.news = (ArrayList<News>)filterResults.values;
        newsAdapter.notifyDataSetChanged();
    }
}
