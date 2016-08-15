package com.example.enerjizeit.sqlitecontactuse;

/**
 * Created by EnerJize It on 22.06.2016.
 */
public class ContactItem {
    private String name;
    private String phone;
    private String about;
    private String photoPath;
    private int ID;// !

    public static ContactItem selectedItem;


    public ContactItem() { //!
    }

    public ContactItem(String name, String phone, String about, String photoPath, int ID) {
        this.name = name;
        this.phone = phone;
        this.about = about;
        this.photoPath = photoPath;
        this.ID = ID;
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
    public String getPhotoPath() {
        return photoPath;
    }
    public int getID() {
        return ID;
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
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
}
