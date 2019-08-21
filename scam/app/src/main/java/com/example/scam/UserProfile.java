package com.example.scam;

public class UserProfile {

    public String studentname;
    public String studentage;
    public String studentclg;
    public String studentgender;
    public Double perdaymoneyleft;
    public Double everdaymoney;



    public UserProfile(){

    }


    public UserProfile(String username, String userage, String userclg, String usergender,Double montlymoneysave,Double usermoney) {
        this.studentname = username;
        this.studentage = userage;
        this.studentclg = userclg;
        this.studentgender = usergender;
        this.perdaymoneyleft = montlymoneysave;
        this.everdaymoney=usermoney;

    }

    public Double getEverdaymoney() {
        return everdaymoney;
    }

    public void setEverdaymoney(Double everdaymoney) {
        this.everdaymoney = everdaymoney;
    }

    public Double getPerdaymoneyleft() {
        return perdaymoneyleft;
    }

    public void setPerdaymoneyleft(Double montlymoneysave) {
        this.perdaymoneyleft= montlymoneysave;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStudentage() {
        return studentage;
    }

    public void setStudentage(String studentage) {
        this.studentage = studentage;
    }

    public String getStudentclg() {
        return studentclg;
    }

    public void setStudentclg(String studentclg) {
        this.studentclg = studentclg;
    }

    public String getStudentgender() {
        return studentgender;
    }

    public void setStudentgender(String studentgender) {
        this.studentgender = studentgender;
    }


}
