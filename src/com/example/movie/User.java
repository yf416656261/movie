package com.example.movie;

import java.lang.String;

/**
 * Created by yy on 2016/5/28.
 */
public class User {
    private int id;
    private String password;
    private String name;
    private int picture_id = 1;
    private String sex;
    private String phone;
    private String email_address;
    private String address;
    
    public User() {}
    public User(String name, String password, String sex, String phone, String e_add, String add) {
    	this.password = password;
    	this.name = name;
    	this.sex = sex;
    	this.phone = phone;
    	this.email_address = e_add;
    	this.address = add;
    }

    public void setPassword(String a) {password = a;}
    public void setName(String a) {name = a;}
    public void setPicture_id(int a) {picture_id = a;}
    public void setSex(String a){sex = a;}
    public void setPhone(String a){phone = a;}
    public void setEmail_address(String a){email_address = a;}
    public void setAddress(String a){address = a;}

    public int getId() {return id;}
    public int getPicture_id() {return picture_id;}
    public String getPassword() {return password;}
    public String getName() {return name;}
    public String getSex() {return sex;}
    public String getPhone() {return phone;}
    public String getEmail_address() {return email_address;}
    public String getAddress() {return address;}
}
