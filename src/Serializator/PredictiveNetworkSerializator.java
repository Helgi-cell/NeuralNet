package Serializator;

import Api.NeuralNetApi.NeuralNetI;
import Entity.network.PredictiveNetwork;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PredictiveNetworkSerializator {

    public void writePredictiveNetworkToFile(PredictiveNetwork neuralNet) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("fibonacci.net"))) {

            oos.writeObject(neuralNet);
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
    }

    public PredictiveNetwork getPredictiveNetworkFromFile(){
        PredictiveNetwork neuralNet = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("fibonacci.net")))
        {
            PredictiveNetwork  neural = (PredictiveNetwork) ois.readObject();
            neuralNet = neural;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        return neuralNet;
    }
}

