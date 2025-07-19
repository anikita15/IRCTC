package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLOutputFactory;

import org.example.entities.Train;
import org.example.entities.User;
import org.example.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserBookingService {
    private User user;

    private List<User> userList;

    private ObjectMapper objMapper = new ObjectMapper();

    private static final String USER_PATH = "app/src/main/java/org.example/localDB/users.json";

    public UserBookingService(User user1) throws IOException {
        this.user=user1;
        loadingUsers();
    }

    public Boolean loginUser(){
        Optional<User> userFound = userList.stream().filter(user1 ->{
            return user1.getName().equalsIgnoreCase(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(),user1.getHashedPassword());
        }).findFirst();
        return userFound.isPresent();
    }

    public Boolean signUpUser(User user1){
        try {
            userList.add(user1);
            saveUserLstToFile();
            return Boolean.TRUE;
        } catch (IOException e) {
            return Boolean.FALSE;
        }
    }

    private void saveUserLstToFile() throws IOException{
        File userFile = new File(USER_PATH);
        objMapper.writeValue(userFile, userList);
    }

    public void fetchBooking(){
        user.printTkts();
    }

    public Boolean cancelBooking(String tktID){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ticket id that has to be canceled:");
        tktID=sc.nextLine();
        if(tktID == null || tktID.isEmpty()){
            System.out.println("Ticket ID cannot be empty. Enter correct Ticket ID");
            return  Boolean.FALSE;
        }
        String tktID1=tktID;
        boolean removeTkt=user.getBookedTkt().removeIf(ticket -> ticket.getTktID().equals(tktID1));
        String tktId=tktID;
        user.getBookedTkt().removeIf(Ticket -> Ticket.getTktID().equals(tktId));
        if(removeTkt){
            System.out.println("Ticket " + tktID + " is cancelled.");
            return Boolean.TRUE;
        }
        else {
            System.out.println("No ticket with ID " + tktID + " found.");
            return Boolean.FALSE;
        }
    }

    public UserBookingService() throws IOException{
       loadingUsers();
    }

    public List<User> loadingUsers() throws IOException{
        File users = new File(USER_PATH);
        return objMapper.readValue(users, new TypeReference<List<User>>() {
        });
    }

    public List<Train> getTrains(String source, String destination){
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public List<List<Integer>> fetchSeats(Train train){
        return train.getSeats();
    }

    public Boolean bookSeat(Train train, int row, int seat){
        try {
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if(row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()){
                if (seats.get(row).get(seat) == 0){
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addTrains(train);
                    return true; //Booking Success
                }
            }
            else return false;   //Already booked so unsuccessful
        } catch (IOException e) {
            return Boolean.FALSE;
        }
        return false;
    }
}
