package com.factrack.containers;

public class studentFormData {
    public String name;
    public String email;
    public String rollNo;
    public String mobileNo;
    public String hostel;
    public String roomNo;
    public String imgLink;

    public studentFormData(){

    }

    public studentFormData(String name, String email, String rollNo,String mobileNo,
                           String hostel, String roomNo, String imgLink){
        this.name = name;
        this.email = email;
        this.rollNo = rollNo;
        this.mobileNo = mobileNo;
        this.hostel = hostel;
        this.roomNo = roomNo;
        this.imgLink = imgLink;
    }
}
