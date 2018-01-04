package com.project.roy.cobabelajar.data.model;

/**
 * Created by roy on 1/2/2018.
 */

public class Car {
    private int id;
    private String year;
    private String make;
    private String model;
    private String imgUrl= "http://cdn.illinois-liver.org/2017/04/04/2013-audi-a6-3-0-t-quattro-tiptronic-premium-sedan-front-view.png";

    public Car(String year, String make, String model) {
        this.year = year;
        this.make = make;
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public String getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
