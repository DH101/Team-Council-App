package com.team9.seatonvalley;

/**
 * @Author: Andreea Stirbu, Student Number: 160483710
 * @Since 25/03/2018
 *
 * Class that holds information about Seaton Valley Community Council Issues
 */
public class Future {

    //Fields
    private String Image;
    private String Title;
    private String Description;
    private String PDF;

    //Constructors
    public Future() {
    }

    public Future(String title, String description, String image, String pdf) {
        Image = image;
        Title = title;
        Description = description;
        PDF = pdf;
    }

    //Setters and getters
    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPDF() {
        return PDF;
    }

    public void setPDF(String PDF) {
        this.PDF = PDF;
    }

}
