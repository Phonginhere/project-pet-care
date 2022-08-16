package com.example.navigation.ui.models;

public class Image {
    long imageId;
    String fileName;
    String url;
    long petId;

    public Image(long imageId, String fileName, String url, long petId) {
        this.imageId = imageId;
        this.fileName = fileName;
        this.url = url;
        this.petId = petId;
    }

    public Image(String url, long petId) {
        this.url = url;
        this.petId = petId;
    }

    public Image() {
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }
}
