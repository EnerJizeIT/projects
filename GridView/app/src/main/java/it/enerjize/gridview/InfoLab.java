package it.enerjize.gridview;

public class InfoLab {
    private String name;
    private String phone;
    private String email;
    private String about;
    private int photoID;

    public InfoLab(String name, String phone, String email, int photoID, String about){
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.about = about;
        this.photoID = photoID;
    }

    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public String getAbout() {
        return about;
    }
    public int getPhotoID() {
        return photoID;
    }

    public void setName(String val) {
        name = val;
    }
    public void setPhone(String val) {
        phone = val;
    }
    public void setEmail(String val) {
        email = val;
    }
    public void setAbout(String val) {
        about = val;
    }
    public void setPhotoID(int val) {
        photoID = val;
    }

    public static InfoLab selectedItem; /* Сохраняем текущий экземпляр контакта */
}
