package it.enerjize.myowncustomdataadapter;

public class InfoLab {
    private String name;
    private String phone;
    private String email;
    private int photoID;

    public InfoLab(String name, String phone, String email, int photoID){
        this.name = name;
        this.phone = phone;
        this.email = email;
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
    public void setPhotoID(int val) {
        photoID = val;
    }
}
