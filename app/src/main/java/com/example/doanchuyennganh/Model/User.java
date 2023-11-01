package com.example.doanchuyennganh.Model;

public class User {
    private String name;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String phone;
    private String password;
    private String role;
    public User(){

    }
    public User(String name,String password,String phone) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        role = "false";
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
