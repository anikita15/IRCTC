package org.example.entities;

import java.sql.Time;
import java.util.List;
import java.util.Map;

public class Train {
    private int trainNum;

    private String trainID;

    private List<List<Integer>> seats;

    private Map<String , String> stationArrivalTime;

    public List<String> station;

    public Train(int trainNum, String trainID, List<List<Integer>> seats, Map<String , String> stationArrivalTime, List<String> station){
        this.trainNum = trainNum;
        this.trainID = trainID;
        this.seats = seats;
        this.stationArrivalTime = stationArrivalTime;
        this.station = station;
    }

    public Train(){}

    public int getTrainNum() {
        return trainNum;
    }

    public String getTrainID() {
        return trainID;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public Map<String, String> getStationArrivalTime() {
        return stationArrivalTime;
    }

    public List<String> getStation() {
        return station;
    }

    public void setTrainNum(int trainNum) {
        this.trainNum = trainNum;
    }

    public void setTrainID(String trainID) {
        this.trainID = trainID;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public void setStationArrivalTime(Map<String, String> stationArrivalTime) {
        this.stationArrivalTime = stationArrivalTime;
    }

    public void setStation(List<String> station) {
        this.station = station;
    }

    public String getTrainInfo(){
        return String.format("Train ID: %s Train No.: %s", trainID, trainNum);
    }
}
