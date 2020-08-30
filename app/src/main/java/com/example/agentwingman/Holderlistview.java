package com.example.agentwingman;

public class Holderlistview {
    public Holderlistview() {
    }
    private String Name;
    private String PolicyNumber;
    private String Phone;
    private String Email;
    private String DueDuration;
    private String Sdate;

    public String getDueDuration() {
        return DueDuration;
    }

    public void setDueDuration(String dueDuration) {
        DueDuration = dueDuration;
    }

    public String getSdate() {
        return Sdate;
    }

    public void setSdate(String sdate) {
        Sdate = sdate;
    }

    public String getEdate() {
        return Edate;
    }

    public void setEdate(String edate) {
        Edate = edate;
    }

    private String Edate;


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPolicyNumber() {
        return PolicyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        PolicyNumber = policyNumber;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
