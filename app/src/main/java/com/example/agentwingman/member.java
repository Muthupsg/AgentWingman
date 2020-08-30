package com.example.agentwingman;

public class member {
    public member() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }


    private String Name;
    private String Email;
    private String Pass;
    private String Aid;

    public String getAid() {
        return Aid;
    }

    public void setAid(String aid) {
        Aid = aid;
    }
}
