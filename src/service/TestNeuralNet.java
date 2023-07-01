package service;

import Entity.layers.HiddenLayer;
import Entity.network.PredictiveNetwork;
import Serializator.PredictiveNetworkSerializator;

import java.util.ArrayList;
import java.util.List;

public class TestNeuralNet {

    public TestNeuralNet() {

        createTestData();
        testing();
    }

    public  void testing() {

        //service.TestNeuralNet neuralNet = new service.TestNeuralNet();
        PredictiveNetworkSerializator predictiveNetworkSerializator = new PredictiveNetworkSerializator();
        PredictiveNetwork predictiveNetwork = predictiveNetworkSerializator.getPredictiveNetworkFromFile("fibonacci.net");
        HiddenLayer hiddenLayer = (HiddenLayer) predictiveNetwork.getLayers().get(1);
        System.out.println("number of neurons in the hidden layer = " + hiddenLayer.getNeurons().size());
        System.out.println("testing subsequent terms of the Fibonacci series following the training sample...\n" );

        List<List<Double>> inputData = predictiveNetworkSerializator.getInputDataFromFile("fibonacciTestInput.dat");
        List<List<Double>> outputData = predictiveNetworkSerializator.getInputDataFromFile("fibonacciTestOutput.dat");

        Double input;
        Double output;
        Double must;

        for (int i = 0; i < inputData.size() - 1; i++){
            input = inputData.get(i).get(0);
            output = predictiveNetwork.encountNet(inputData.get(i)).get(0);
            must = outputData.get(i).get(0);
            System.out.println("test = " + (input * 1000) +
                    "      output = " + (output * 1000) + "   must = " + (must * 1000));
        }
    }


    public void createTestData(){


        Double [] inputArray = new Double[]
                {987.0, 1597.0, 2584.0, 4181.0, 6765.0, 10946.0, 17711.0//, 34.0, 55.0, 89.0, 144.0, 233.0, 377.0, 610.0
                };
        Double [] outputArray = new Double[]
                {1597.0, 2584.0, 4181.0, 6765.0, 10946.0, 17711.0, 28657.0//, 55.0, 89.0, 144.0, 233.0, 377.0, 610.0, 987.0
                };

            List<List<Double>> inputData = new ArrayList<>();
            List<List<Double>> outputData = new ArrayList<>();


        for (int i = 0 ; i < inputArray.length; i++){
            List<Double> learning = new ArrayList<>();
            learning.add(inputArray[i]/1000);
            inputData.add(learning);
        }


        for (int i = 0 ; i < outputArray.length; i++){
            List<Double> learning = new ArrayList<>();
            learning.add(outputArray[i]/1000);
            outputData.add(learning);
        }

        PredictiveNetworkSerializator predictiveNetworkSerializator = new PredictiveNetworkSerializator();

        predictiveNetworkSerializator.writeInputDataToFile(inputData,"fibonacciTestInput.dat");
        predictiveNetworkSerializator.writeInputDataToFile(outputData,"fibonacciTestOutput.dat");

    }
}
