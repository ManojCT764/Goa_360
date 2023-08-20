package com.example.goa360;

public class placeModel {
    String placeName;
    String description;
    int image;
    String links;

    public placeModel(String placeName,String description,String links , int image) {
        this.placeName = placeName;
        this.image = image;
        this.description = description;
        this.links = links;
    }

    public String getLinks() { return links; }

    public String getDescription() {
        return description;
    }

    public String getPlaceName() {
        return placeName;
    }

    public int getImage() {
        return image;
    }
}
