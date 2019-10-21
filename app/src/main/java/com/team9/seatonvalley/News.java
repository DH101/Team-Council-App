package com.team9.seatonvalley;

/**
 * @Author: Andreea Stirbu, Student Number: 160483710
 * @Since: 13/04/2018
 *
 * Class that holds information about news
 */
public class News {

    //Fields
    private String Image;
    private String Title;
    private String Description;
    private String Link;

    //Constructors
    public News() {

    }

    public News(String image, String title, String description, String link) {
        Image = image;
        Title = title;
        Description = description;
        Link = link;
    }

    //Setters and getters
    public String getNewsImage() {
        return Image;
    }

    public void setNewsImage(String image) {
        Image = image;
    }

    public String getNewsTitle() {
        return Title;
    }

    public void setNewsTitle(String title) {
        Title = title;
    }

    public String getNewsDescription() {
        return Description;
    }

    public void setNewsDescription(String description) {
        Description = description;
    }

    public String getNewsLink() {
        return Link;
    }

    public void setNewsLink(String link) {
        Link = link;
    }

}
