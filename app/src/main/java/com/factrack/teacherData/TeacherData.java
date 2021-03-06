package com.factrack.teacherData;

public class TeacherData {
    String name;
    String image;
    String designation;
    String building;
    String roomNo;
    String uid;
    public TeacherData(String name,String image,String designation,String building,String roomNo,String uid) {
        this.name = name;
        this.image = image;
        this.designation = designation;
        this.building = building;
        this.roomNo = roomNo;
        this.uid = uid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setBuilding(String building) {
        this.building = building;
    }
    public String getBuilding() {
        return this.building;
    }
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }
    public String getRoomNo() {
        return this.roomNo;
    }
    public String  getUid() {return  this.uid;}
    public void setUid(String uid) {this.uid = uid;}
}
