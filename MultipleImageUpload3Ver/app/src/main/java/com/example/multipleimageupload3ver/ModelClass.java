package com.example.multipleimageupload3ver;

import android.net.Uri;

public class ModelClass {
    public Uri uri;

    public ModelClass() {
    }

    public ModelClass(Uri uri, String imageName) {
        this.uri = uri;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
