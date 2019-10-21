package com.team9.seatonvalley;

/**
 * @Author: Andreea Stirbu, Student Number: 160483710
 * @Since: 13/04/2018
 *
 * Class that holds information about events
 */
public class Events {

    //Fields
    private String Image;
    private String Title;
    private String Description;
    private String Date;
    private String Link;

    //Constructors
    public Events() {

    }

    public Events(String image, String title, String description, String date, String link) {
        Image = image;
        Title = title;
        Description = description;
        Date = date;
        Link = link;
    }

    //Setters and getters
    public String getEventsImage() {
        return Image;
    }

    public void setEventsImage(String image) {
        Image = image;
    }

    public String getEventsTitle() {
        return Title;
    }

    public void setEventsTitle(String title) {
        Title = title;
    }

    public String getEventsDescription() {
        return Description;
    }

    public void setEventsDescription(String description) {
        Description = description;
    }

    public String getEventsDate() {
        return Date;
    }

    public void setEventsDate(String date) {
        Date = date;
    }

    public String getEventsLink() {
        return Link;
    }

    public void setEventsLink(String link) {
        Link = link;
    }

}
