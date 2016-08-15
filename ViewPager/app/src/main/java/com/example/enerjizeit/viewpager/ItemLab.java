package com.example.enerjizeit.viewpager;

public class ItemLab {
    private String name, phone, about;
    private int photoID;

    public ItemLab(String name, String phone, String about, int photoID) {
        this.name = name;
        this.phone = phone;
        this.about = about;
        this.photoID = photoID;
    }

    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getAbout() {
        return about;
    }
    public int getPhotoID() {
        return photoID;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setAbout(String about) {
        this.about = about;
    }
    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    public static ItemLab selectedItem;
}
