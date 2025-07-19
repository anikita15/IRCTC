package org.example.entities;



import java.util.Date;

public class Ticket {
    private String tktID;

    private String UserUID;

    private String source;

    private String destination;

    private Date dateOfTravel;

    private Train train;

    public Ticket(){}

    public Ticket(String tktID, String UserUID, String source, String destination, Date dateOfTravel, Train train){
        this.tktID=tktID;
        this.UserUID=UserUID;
        this.source=source;
        this.destination=destination;
        this.dateOfTravel=dateOfTravel;
        this.train=train;
    }

    public Date getDateOfTravel() {
        return dateOfTravel;
    }

    public String getDestination() {
        return destination;
    }

    public String getSource() {
        return source;
    }

    public String getTktID() {
        return tktID;
    }

    public String getUserUID() {
        return UserUID;
    }

    public Train getTrain() {
        return train;
    }

    public void setDateOfTravel(Date dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setTktID(String tktID) {
        this.tktID = tktID;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public void setUserUID(String userUID) {
        UserUID = userUID;
    }

    public String getTktInfo(){
        return String.format("Ticket ID: %s belongs to User: %s from %s to %s on %s", tktID, UserUID, source, destination, dateOfTravel);
    }
}
