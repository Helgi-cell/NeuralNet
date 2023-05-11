import Api.NeuralNetApi.NeuralNetI;
import Entity.network.PredictiveNetwork;
import Serializator.PredictiveNetworkSerializator;

import java.util.ArrayList;
import java.util.List;

public class NeuralNet {

    public NeuralNet() {
        createLearningData();
    }

    public static void main(String [] args) {

        NeuralNet neuralNet = new NeuralNet();
        PredictiveNetworkSerializator predictiveNetworkSerializator = new PredictiveNetworkSerializator();
        PredictiveNetwork predictiveNetwork = predictiveNetworkSerializator.getPredictiveNetworkFromFile("fibonacci.net");



        List<List<Double>> inputData = predictiveNetworkSerializator.getInputDataFromFile("fibonacciTestInput.dat");
        List<List<Double>> outputData = predictiveNetworkSerializator.getInputDataFromFile("fibonacciTestOutput.dat");


        for (int i = 0; i < inputData.size() - 1; i++){
            System.out.println("test = " + inputData.get(i) +
                    "      output = " + predictiveNetwork.encountNet(inputData.get(i)) + "   must = " + outputData.get(i));
        }




        //List<Double> test = new ArrayList<>();

       /* List<Double> test = new ArrayList<>();
        test.add(987.0d);
        System.out.println("test = " + test + "      output = " + predictiveNetwork.encountNet(test) + "   must = " + 1597);
        test.remove(0);
        test.add(1597.0d);
        System.out.println("test = " + test + "      output = " + predictiveNetwork.encountNet(test) + "   must = " + 2584);

        test.remove(0);
        test.add(2584.0d);
        System.out.println("test = " + test + "      output = " + predictiveNetwork.encountNet(test) + "   must = " + 4181);

        test.remove(0);
        test.add(4181.0d);
        System.out.println("test = " + test + "      output = " + predictiveNetwork.encountNet(test) + "   must = " + 6765);

        test.remove(0);
        test.add(6785.0d);
        System.out.println("test = " + test + "      output = " + predictiveNetwork.encountNet(test) + "   must = " + 10966);

        test.remove(0);
        test.add(55.0d);
        System.out.println("test = " + test + "      output = " + predictiveNetwork.encountNet(test) + "   must = " + 89);
*/




    }


    public void createLearningData(){


        Double [] inputArray = new Double[]
                {987.0, 1597.0, 2584.0, 4181.0, 6765.0, 10946.0//, 21.0, 34.0, 55.0, 89.0, 144.0, 233.0, 377.0, 610.0
                };
        Double [] outputArray = new Double[]
                {1597.0, 2584.0, 4181.0, 6765.0, 10946.0, 17711.0//, 34.0, 55.0, 89.0, 144.0, 233.0, 377.0, 610.0, 987.0
                };

            List<List<Double>> inputData = new ArrayList<>();
            List<List<Double>> outputData = new ArrayList<>();


        for (int i = 0 ; i < inputArray.length; i++){
            List<Double> learning = new ArrayList<>();
            learning.add(inputArray[i]);
            inputData.add(learning);
        }


        for (int i = 0 ; i < outputArray.length; i++){
            List<Double> learning = new ArrayList<>();
            learning.add(outputArray[i]);
            outputData.add(learning);
        }

        PredictiveNetworkSerializator predictiveNetworkSerializator = new PredictiveNetworkSerializator();

        predictiveNetworkSerializator.writeInputDataToFile(inputData,"fibonacciTestInput.dat");
        predictiveNetworkSerializator.writeInputDataToFile(outputData,"fibonacciTestOutput.dat");

    }


}
