package com.example.retrieveimage;

public class Message {
    //Model Class
    int imageId;
    String fileName;
    String url;

    //Construction:

    public Message() {
    }

    public Message(int imageId, String fileName, String url) {
        this.imageId = imageId;
        this.fileName = fileName;
        this.url = url;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
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
}
