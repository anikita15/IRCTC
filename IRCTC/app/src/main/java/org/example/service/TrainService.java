package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Train;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrainService {
    private List<Train> trainList;
    private ObjectMapper objMapper = new ObjectMapper();
    private static final String TRAIN_PATH = "app/src/main/java/org.example/localDB/trains.json";

    public TrainService() throws IOException{
        File train = new File(TRAIN_PATH);
        trainList = objMapper.readValue(train, new TypeReference<List<Train>>() {
        });
    }

    public List<Train> searchTrains(String source, String destination){
        return trainList.stream().filter(train -> validTrains(train, source, destination)).collect(Collectors.toList());
    }

    public Boolean validTrains(Train train, String source, String destination){
        List<String> station = train.getStation();
        int sourceIndex = station.indexOf(source);
        int destinationIndex = station.indexOf(destination);

        return sourceIndex != -1 && destinationIndex != -1 && sourceIndex < destinationIndex;
    }

    void addTrains(Train newTrain){
        Optional<Train> existingTrain = trainList.stream().filter(train -> train.getTrainID().equalsIgnoreCase(newTrain.getTrainID())).findFirst();

        if(existingTrain.isPresent()){
            updateTrain(newTrain);
        }
        else {
            trainList.add(newTrain);
            saveTrainLst();
        }
    }

    public void updateTrain(Train updatedTrain){
        OptionalInt index = IntStream.range(0, trainList.size()).filter(i -> trainList.get(i).getTrainID().equalsIgnoreCase(updatedTrain.getTrainID())).findFirst();
        if(index.isPresent()){
            trainList.set(index.getAsInt(), updatedTrain);
            saveTrainLst();
        }
        else {
            addTrains(updatedTrain);
        }
    }

    public void saveTrainLst(){
        try {
            objMapper.writeValue(new File(TRAIN_PATH), trainList);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


}
