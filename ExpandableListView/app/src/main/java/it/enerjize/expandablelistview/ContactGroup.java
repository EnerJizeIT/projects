package it.enerjize.expandablelistview;

import java.util.ArrayList;

public class ContactGroup {
    private String name;
    private ArrayList<InfoLab> contacts;

    public ContactGroup(String name) {
        this.name = name;
        this.contacts = new ArrayList<InfoLab>();
    }
    public void addContact(InfoLab item){
        contacts.add(item);
    }
    public String getName(){
        return name;
    }
    public ArrayList<InfoLab> getContactsList(){
        return contacts;
    }
    public InfoLab getInfoLab(int position){
        return contacts.get(position);
    }
}
