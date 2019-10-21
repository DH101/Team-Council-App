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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @Author: Dean Hunter, Student Number: 16027456
 * @Since: 23/03/2018
 *
 * This class sets up each item for the services list. This includes adding a card view for each
 * item of the service list as well as an icon, title, description for each service card view
 * as well as a button for each card view to take the user to the next activity.
 * Extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> as a base class for the adapter.
 * This provides binding from the data set to views displayed on a recycler view.
 */

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder>{

    // Private fields to ensure not accessed outside class

    // Tag to represent time of discovery
    private static final String TAG = "ServicesRecyclerViewAd";

    // An empty list of Service type to contain services
    private ArrayList<Service> services;

    // To store the activity context
    private Context context;

    /**
     * Class to describe the item view and metadata within the recycler view
     * Extends  RecyclerView.ViewHolder to do this
     */
    static class ViewHolder extends RecyclerView.ViewHolder{

        // Fields for each item view

        // Card view for the service in the item view
        CardView cardView;

        // Image view for icon of service in the item view
        ImageView servicesIcon;

        // Text view for service title in the item view
        TextView servicesTitle;

        // Text view for service description in the item view
        TextView servicesDescription;

        // Button for the card view in the item view
        Button servicesButton;

        // Constructor to create item view
        private ViewHolder(View itemView) {

            super(itemView);

            // Assigns the card view widget with id of services_card_view
            cardView = itemView.findViewById(R.id.services_card_view);

            // Assigns the image view widget with id of services_icon
            this.servicesIcon = itemView.findViewById(R.id.services_icon);

            // Assigns the text view widget with id of services_title
            this.servicesTitle = itemView.findViewById(R.id.services_title);

            // Assigns the text view widget with id of services_description
            this.servicesDescription = itemView.findViewById(R.id.services_description);

            // Assigns the button widget with id of services_description
            this.servicesButton = itemView.findViewById(R.id.services_button);

        }

    }

    /**
     * Constructor to create services adapter, including the list of services and activity context.
     */
    ServicesAdapter(Context context, ArrayList<Service> services) {

        this.services = services;
        this.context = context;

    }


    /**
     * Creates new RecyclerView.ViewHolder to represent an item. Makes use of parent
     * where the new view is added and the view type of the new view.
     */
    @NonNull
    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // describes the UI layout for the item.
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.listitem_service, parent, false);
        // Instantiates item view in is place withing recycler view.
        return new ViewHolder(view);

    }

    /**
     *  To fill the created View Holder with the data to represent the service and its intent.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder servicesViewHolder, int position) {

        // Show in Logcat onBindViewHolder has been called.
        Log.d(TAG, "onBindViewHolder: called.");

        // Assign the position parameter to a final int variable.
        final int servicePosition = position;


        // Set the icon in the View Holder to the drawable with the icon resource id at the position
        // the user chose.
        // REFERENCE - In order to make icons, I made use of
        // https://romannurik.github.io/AndroidAssetStudio/
        servicesViewHolder.servicesIcon.setImageResource(services.get(position).iconResourceId);
        // Set the title in the View Holder to the title at the position
        // the user chose.
        servicesViewHolder.servicesTitle.setText(services.get(position).title);
        // Set the description in the View Holder to the description at the position
        // the user chose.
        servicesViewHolder.servicesDescription.setText(services.get(position).description);

        // Set an onclick listener to the card view so when clicked, show in logcat the view
        // has been clicked, make a toast appear at the bottom of the application of the title of
        // the service clicked and intent to the service playground location activity or service
        // allotment location activity.
        servicesViewHolder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: clicked on: "+ view);

                // Get playground title from string values
                String playgroundTitle = context.getResources().getString
                        (R.string.playground_title);

                // Get allotment title from string values
                String allotmentTitle = context.getResources().getString(R.string.allotment_title);

                // check the button the user clicked equals allotmentTitle
                if(services.get(servicePosition).title.equals(allotmentTitle)) {

                    // Intent to ServiceAllotmentLocationActivity and send the title to the
                    // activity
                    Intent intent = new Intent(context, ServiceAllotmentLocationsActivity.class);
                    context.startActivity(intent);

                }  // check the button the user clicked equals playgroundTitle
                else if(services.get(servicePosition).title.equals(playgroundTitle)) {

                    // Intent to ServicePlaygroundLocationActivity and send the title to the
                    // activity
                    Intent intent = new Intent(context, ServicePlaygroundLocationsActivity.class);
                    context.startActivity(intent);

                }

            }
        });

        // Same as above but adds functionality to the button in the view.
        servicesViewHolder.servicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: clicked on button");

                // Get playground title from string values
                String playgroundTitle = context.getResources().getString
                        (R.string.playground_title);

                // Get allotment title from string values
                String allotmentTitle = context.getResources().getString(R.string.allotment_title);

                // check the button the user clicked equals allotmentTitle
                if(services.get(servicePosition).title.equals(allotmentTitle)) {

                    // Intent to ServiceAllotmentLocationActivity and send the title to the
                    // activity
                    Intent intent = new Intent(context, ServiceAllotmentLocationsActivity.class);
                    context.startActivity(intent);

                }  // check the button the user clicked equals playgroundTitle
                else if(services.get(servicePosition).title.equals(playgroundTitle)) {

                    // Intent to ServicePlaygroundLocationActivity and send the title to the
                    // activity
                    Intent intent = new Intent(context, ServicePlaygroundLocationsActivity.class);
                    context.startActivity(intent);

                }

            }
        });
    }

    /**
     * get the size of the services list.
     */
    @Override
    public int getItemCount() { return services.size(); }

}
