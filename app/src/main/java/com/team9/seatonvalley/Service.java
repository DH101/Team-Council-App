package com.team9.seatonvalley;

/**
 * @Author: Dean Hunter, Student Number: 16027456
 * @Since: 23/03/2018
 *
 * This class is used to make a Service. Service includes a title and
 * description of the service for a card view in the recycler list.
 */

public class Service {

    // Title of the service
    String title;

    // Description of the service location
    String description;

    // Resource id for icon associated to service
    int iconResourceId;

    /**
     * Constructor to create services, including its title, description and icon resource id.
     */
    Service(String title, String description, int iconResourceId) {
        this.title = title;
        this.description = description;
        this.iconResourceId = iconResourceId;
    }
}
