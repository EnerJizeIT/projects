package com.example.enerjizeit.sqliteproject;

public class ItemLab {
    private String name;
    private String email;
    private String phone;
    private String photoPath;
    private int ID;

    public static ItemLab selectedItem;

    public ItemLab() {}

    public ItemLab(int ID, String name, String phone, String photoPath, String email) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.photoPath = photoPath;
        this.ID = ID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String getPhotoPath() {
        return photoPath;
    }
    public int getID() {
        return ID;
    }

}
