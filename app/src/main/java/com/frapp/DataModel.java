package com.frapp;

public class DataModel {

	private String id;
    private String image_url;
    private String title;
    private String description;
    private String type;
    private String view_count;
    private Boolean isFavourite;

    public DataModel(){

    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

	public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getView_count() {
        return view_count;
    }
    public void setView_count(String view_count) {
        this.view_count = view_count;
    }
    public Boolean getIsFavourite(){return isFavourite; }
    public void setIsFavourite(Boolean isFavourite){
        this.isFavourite = isFavourite;
    }



}
