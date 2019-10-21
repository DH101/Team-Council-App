package com.team9.seatonvalley;

import com.google.android.gms.maps.model.LatLng;

/**
 * @Author: Dean Hunter, Student Number: 16027456
 * @Since: 26/03/2018.
 *
 * This class is used to make Service locations. Service locations include its title and
 * description for each card view in the recycler list. The location which is its latitude and
 * longitude is for the map activity.
 */

class ServiceLocation {

    // title of the service location
    String title;

    // description of the service location
    String description;

    // the longitude and latitude of the service location
    LatLng location;

    /**
     * constructor to create service location, including its title, description and location.
     */
     ServiceLocation(String title, String description, LatLng location){
         this.title = title;
         this.description = description;
         this.location = location;
     }

}
