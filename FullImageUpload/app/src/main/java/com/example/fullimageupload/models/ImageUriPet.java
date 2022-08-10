package com.example.fullimageupload.models;

import android.net.Uri;

public class ImageUriPet {
    public Uri uri;

    public ImageUriPet() {
    }

    public ImageUriPet(Uri uri) {
        this.uri = uri;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
