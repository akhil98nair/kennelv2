package com.miniproject.kennelv2;

public class GroceryRecyclerViewItem {

    // Save car name.
    private String name;
    private String contact_no;
    private String location;
    private String comment;
    private String email;


    // Save car image resource id.
    private int dogImage;

    public GroceryRecyclerViewItem(String name, String contact_no, String location, String comment, String email, int dogImage) {
      this.name = name;
      this.contact_no = contact_no;
      this.location = location;
      this.comment = comment;
      this.email = email;
      this.dogImage = dogImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDogImage() {
        return dogImage;
    }

    public void setDogImage(int carImageId) {
        this.dogImage = dogImage;
    }
}