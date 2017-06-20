package com.android.jsonpars_withimage_using_volley;

/**
 * Created by peacock on 3/29/16.
 */
public class Forbsbillenior {
    private String Id;
    private String Name;
    private String Photo;
    private int Year;
    private String Sources;
    private String Worth;

    public Forbsbillenior() {

    }

    public Forbsbillenior(String id, String name, String worth, String source, String gender, int year, String photo) {
        this.Id = id;
        this.Name = name;
        this.Worth = worth;
        this.Sources = source;

    }

    public Forbsbillenior(String name, String worth, String source, int year, String photo) {

    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getName() {
        return Name;

    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getId() {
        return Id;
    }

    public void setPhoto(String photo) {
        this.Photo = photo;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setYear(int year) {
        this.Year = year;
    }

    public int getYear() {
        return Year;
    }

    public void setSources(String sources) {
        this.Sources = sources;
    }

    public String getSources() {
        return Sources;
    }

    public void setWorth(String worth) {
        this.Worth = worth;
    }

    public String getWorth() {
        return Worth;
    }


}

