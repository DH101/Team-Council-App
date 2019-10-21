package com.team9.seatonvalley;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * @Author: Dean Hunter, Student Number: 16027456
 * @Since: 26/03/2018.
 *
 * This class sets up each item for the service locations list. This includes adding a card view
 * for each item of the service locations list as well as title and description for each service
 * card view and each card view takes the user to the next activity.
 * Extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> as a base class for the adapter.
 * This provides binding from the data set to views displayed on a recycler view.
 */

public class ServiceLocationsAdapter extends RecyclerView.Adapter<ServiceLocationsAdapter.ViewHolder> {

    // Private fields to ensure not accessed outside class

    // Tag to represent time of discovery
    private static final String TAG = "ServicesRecyclerViewAd";

    // An empty list of ServiceLocation type to contain service locations
    private ArrayList<ServiceLocation> serviceLocations;

    // To store the activity context
    private Context context;

    // Class to describe the item view and metadata within the recycler view
    // Extends  RecyclerView.ViewHolder to do this
    static class ViewHolder extends RecyclerView.ViewHolder{

        // Fields for each item view

        // Card view for the service location in the item view
        CardView cardView;

        // Text view for service location title in the item view
        TextView serviceLocationsTitle;

        // Text view for service location description in the item view
        TextView serviceLocationsDescription;

        // Button for the card view in the item view
        Button servicesLocationsButton;

        // Constructor to create item view
        private ViewHolder(View itemView) {

            super(itemView);

            // Assigns the card view widget with id of services_locations_card_view
            cardView = itemView.findViewById(R.id.service_locations_card_view);

            // Assigns the text view widget with id of services_locations_title
            this.serviceLocationsTitle = itemView.findViewById(R.id.services_locations_title);

            // Assigns the text view widget with id of services_locations_description
            this.serviceLocationsDescription = itemView.findViewById(R.id.
                    services_locations_description);

            // Assigns the button widget with id of services_locations_button
            this.servicesLocationsButton = itemView.findViewById(R.id.services_locations_button);

        }

    }

    /**
     *  Constructor to create service locations adapter, including the list of service locations
     */
    // and activity context.
    ServiceLocationsAdapter(Context context, ArrayList<ServiceLocation> serviceLocations) {

        this.serviceLocations = serviceLocations;
        this.context = context;

    }

    /**
     *  Creates new RecyclerView.ViewHolder to represent an item. Makes use of parent
     *  where the new view is added and the view type of the new view.
     */
    @NonNull
    @Override
    public ServiceLocationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // describes the UI layout for the item.
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.listitem_service_location, parent, false);
        // Instantiates item view in is place withing recycler view.
        return new ViewHolder(view);

    }

    /**
     * To fill the created View Holder with the data to represent the service locations
     * and its intent.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder serviceLocationsViewHolder, int position) {

        // Show in Logcat onBindViewHolder has been called.
        Log.d(TAG, "onBindViewHolder: called.");

        // Assign the position parameter to a final int variable.
        final int serviceAllotmentLocationPosition = position;

        // Set the title in the View Holder to the title at the position
        // the user chose.
        serviceLocationsViewHolder.serviceLocationsTitle.setText(serviceLocations
                .get(position).title);
        // Set the description in the View Holder to the description at the position
        // the user chose.
        serviceLocationsViewHolder.serviceLocationsDescription.setText(serviceLocations
                .get(position).description);

        // Set an onclick listener to the card view so when clicked, show in logcat the view
        // has been clicked and intent to the service location map activity,
        // sending the title of the service location and its latitude and longitude to the activity.
        serviceLocationsViewHolder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: clicked on: " + view);

                Intent intent = new Intent(context, ServiceLocationAllotmentMapActivity.class);
                LatLng location = serviceLocations.get(serviceAllotmentLocationPosition).location;
                String locationTitle = serviceLocations.get(serviceAllotmentLocationPosition).title;
                intent.putExtra("service_location", location);
                intent.putExtra("service_location_title", locationTitle);
                context.startActivity(intent);

            }
        });

        // Same as above but adds functionality to the button.
        serviceLocationsViewHolder.servicesLocationsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: clicked on: " + view);

                Intent intent = new Intent(context, ServiceLocationAllotmentMapActivity.class);
                LatLng location = serviceLocations.get(serviceAllotmentLocationPosition).location;
                String locationTitle = serviceLocations.get(serviceAllotmentLocationPosition).title;
                intent.putExtra("service_location", location);
                intent.putExtra("service_location_title", locationTitle);
                context.startActivity(intent);

            }
        });

    }


    /**
     *  get the size of the service locations list.
     */
    @Override
    public int getItemCount() {
        return serviceLocations.size();
    }

}
