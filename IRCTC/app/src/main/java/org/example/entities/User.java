package org.example.entities;

import java.util.List;

public class User {
    private String name;

    private String password;

    private String hashedPassword;

    private List<Ticket> bookedTkt;

    private String userID;

    public User(String name, String password, String hashedPassword, List<Ticket> bookedTkt, String userID){
        this.name = name;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.bookedTkt = bookedTkt;
        this.userID = userID;
    }
    public User(){}

    public String getName(){
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public List<Ticket> getBookedTkt() {
        return bookedTkt;
    }

    public void printTkts(){
        for(int i=0;i<bookedTkt.size();i++){
            System.out.println(bookedTkt.get(i).getTktInfo());
        }
    }

    public String getUserID() {
        return userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setBookedTkt(List<Ticket> bookedTkt) {
        this.bookedTkt = bookedTkt;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
