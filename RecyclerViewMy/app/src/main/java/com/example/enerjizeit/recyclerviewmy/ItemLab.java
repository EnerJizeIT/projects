package com.example.enerjizeit.recyclerviewmy;

/**
 * Created by EnerJize It on 29.06.2016.
 */

public class ItemLab {
    String title;
    String pass;
    int image;

    public ItemLab(String title, String pass, int image) {
        this.title = title;
        this.pass = pass;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }
}
