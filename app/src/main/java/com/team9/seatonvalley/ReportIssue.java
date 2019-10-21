package com.team9.seatonvalley;

/**
 * @Author: Dean Hunter, Student Number: 16027456
 * @Since: 10/04/2018
 *
 * This class is used to make a Report Issue. Report Issue includes a title and
 * description of the issue for a card view in the recycler list.
 */

public class ReportIssue {

    // Title of the report issue
    String title;

    // Description of the report issue
    String description;

    // Resource id for icon associated to report issue
    int iconResourceId;

    // Constructor to create report issues, including its title, description and icon resource id.
    ReportIssue(String title, String description, int iconResourceId) {
        this.title = title;
        this.description = description;
        this.iconResourceId = iconResourceId;
    }

}
