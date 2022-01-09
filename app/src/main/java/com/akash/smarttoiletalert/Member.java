package com.akash.smarttoiletalert;

public class Member {
    private String name,age,phoneno,time,gender;


    public Member(String name, String age,String gender,String phoneno, String time) {
        this.name = name;
        this.age = age;
        this.gender=gender;
        this.phoneno = phoneno;
        this.time = time;
    }
    public Member(){

    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", phoneno='" + phoneno + '\'' +
                ", time='" + time + '\'' +

                '}';
    }
}
