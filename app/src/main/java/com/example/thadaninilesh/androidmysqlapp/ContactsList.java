package com.example.thadaninilesh.androidmysqlapp;

/**
 * Created by thadaninilesh on 10-02-2016.
 */
public class ContactsList {
    private String name, email, mobile;

    public ContactsList(String name, String email, String mobile){
        this.setName(name);
        this.setEmail(email);
        this.setMobile(mobile);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
