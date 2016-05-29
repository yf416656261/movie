package com.example.movie;

/**
 * Created by yy on 2016/5/28.
 */
public class User {
    private int id;
    private String password;
    private String name;
    private int picture_id;

    public void setId(int a) {id = a;}
    public void setPassword(String a) {password = a;}
    public void setName(String a) {name = a;}
    public void setPicture_id(int a) {picture_id = a;}

    public int getId() {return id;}
    public int getPicture_id() {return picture_id;}
    public String getPassword() {return password;}
    public String getName() {return name;}
}
