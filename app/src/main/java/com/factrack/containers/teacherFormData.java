package com.factrack.containers;

public class teacherFormData {
    public String name;
    public String designation;
    public String department;
    public String email;
    public String mobileNo;
    public String officeNo;
    public String homepage;
    public String imageLink;
    public String building;
    public String roomNo;

    public teacherFormData() {

    }

    public teacherFormData(String name, String email, String designation, String department, String mobileNo, String officeNo, String building, String roomNo,String homepage, String imageLink) {
        this.name = name;
        this.designation = designation;
        this.department = department;
        this.email = email;
        this.mobileNo = mobileNo;
        this.officeNo = officeNo;
        this.building = building;
        this.roomNo = roomNo;
        this.homepage = homepage;
        this.imageLink = imageLink;
    }
}
