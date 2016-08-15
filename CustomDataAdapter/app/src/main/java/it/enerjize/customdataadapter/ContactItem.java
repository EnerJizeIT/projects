package it.enerjize.customdataadapter;

public class ContactItem {
    private String name;
    private String phone;
    private String about;
    private int photoID;

    public ContactItem(String name, String phone, int photoID, String about) {
        this.name = name;
        this.phone = phone;
        this.photoID = photoID;
        this.about = about;
    }

    public String getName() {
        return name;
    }
    public String getAbout() { return about; }
    public String getPhone() {
        return phone;
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
    public void setAbout(String about) { this.about = about; }
    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }
    /* Сохраняем текущий экземпляр контакта */
    public static ContactItem selectedItem;
}