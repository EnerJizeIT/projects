package it.enerjize.listfragment;

public class ContactItem {
    private String name;
    private String email;
    private String phone;
    private int photoId;

    public ContactItem(String name, String email, String phone, int photoId) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.photoId = photoId;
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
    public void setPhotoId(int photoId) {
        this.photoId = photoId;
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
    public int getPhotoId() {
        return photoId;
    }
}
