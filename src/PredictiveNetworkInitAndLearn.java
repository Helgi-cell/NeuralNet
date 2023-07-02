import Api.FinctionsApi.FunctionEncountingNodesInterface;
import Api.NeuralNetApi.NeuralNetI;
import Entity.network.PredictiveNetwork;
import Serializator.PredictiveNetworkSerializator;
import service.BipolarSigmoidFunction;
import service.SigmoidFunction;
import service.TestNeuralNet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PredictiveNetworkInitAndLearn implements Serializable {
    List<List<Double>> learningData = new ArrayList<>();
    List<List<Double>> outputData = new ArrayList<>();

    public PredictiveNetworkInitAndLearn() {
        createLearningData();
    }

    public static void main(String[] args) {


        PredictiveNetworkInitAndLearn rootPoint = new PredictiveNetworkInitAndLearn();

        FunctionEncountingNodesInterface func = new SigmoidFunction();
        //FunctionEncountingNodesInterface func = new BipolarSigmoidFunction();

        rootPoint.learningNetwork(1, 1,
                2, 0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000999d
                , 0.00000019d, func);
    }


    public void learningNetwork(int numNeuronsInput, int numNeuronsOutput, int numHiddenLayers
                            , Double stepLearning, Double midSquareError, FunctionEncountingNodesInterface func) {

        NeuralNetI predictiveNetwork = new PredictiveNetwork(numNeuronsInput, numNeuronsOutput,
                numHiddenLayers, stepLearning, midSquareError, func);

        Double midSqr = 0.0d;
        Double maxError = Double.MAX_VALUE;

        List<List<Double>> inpData = new ArrayList<>();
        List<List<Double>> outData = new ArrayList<>();
        List<Double> inputNet;
        List<Double> outputNet;


        for (int i = 0; i < this.learningData.size(); i++) {
            inpData.add(this.learningData.get(i));
            outData.add(this.outputData.get(i));
            int score = 0;
            do {
                midSqr = 0.0d;
                for (int j = 0; j < inpData.size(); j++) {
                    inputNet = inpData.get(j);
                    outputNet = outData.get(j);
                    predictiveNetwork.encountNet(inputNet);
                    predictiveNetwork.encountDerivatives();
                    predictiveNetwork.encountNetErrors(inputNet, outputNet);
                    predictiveNetwork.encountWeight();
                }
                midSqr = predictiveNetwork.encountMidSquareError(inpData, outData);
                if (maxError > midSqr) {
                    score = 0;
                    maxError = midSqr;
                } else {
                    score ++;
                    maxError = midSqr;
                }

                if (score > 5) {
                    System.out.println("error = " + midSqr + "\n num = " + (i + 1) + "\n  score = " + score + "\n\n\n");
                    predictiveNetwork.incrementNodes();

                    score = 0;
                }

            } while (midSqr > (predictiveNetwork.getMidSquareError() // * (i + 1)
            ));
        }

        System.out.println("Number neurons in the each hidden layer = " + predictiveNetwork.getNumberNeuronsInHiddenLayer() + "\n\n");
        System.out.println("Number layers in hidden layers = " + predictiveNetwork.getNumberHiddenLayers() + "\n\n");
        System.out.println("error = " + midSqr + "\n\n\n");
        System.out.println("Learning sample of the Fibonacci series.. \n");

        Double input;
        Double output;
        Double realdata;
        for (int i = 0; i < this.learningData.size(); i++){
                input = this.learningData.get(i).get(0);
                output = this.outputData.get(i).get(0);
                realdata = predictiveNetwork.encountNet(this.learningData.get(i)).get(0);
            System.out.println("input = " + (input * 1000) + "      output = "
                        + (realdata * 1000) + "      realdata = " + (output * 1000));
        }

        System.out.println("\n\n");
        PredictiveNetworkSerializator predictiveNetworkSerializator = new PredictiveNetworkSerializator();
        predictiveNetworkSerializator.writePredictiveNetworkToFile((PredictiveNetwork) predictiveNetwork, "fibonacci.net");
        TestNeuralNet neuralNet = new TestNeuralNet();
    }

    public void createLearningData(){

        Double [] inputArray = new Double[]
                {1.0, 2.0, 3.0, 5.0, 8.0, 13.0, 21.0, 34.0, 55.0, 89.0, 144.0, 233.0, 377.0, 610.0
                         };
        Double [] outputArray = new Double[]
                {2.0, 3.0, 5.0, 8.0, 13.0, 21.0, 34.0, 55.0, 89.0, 144.0, 233.0, 377.0, 610.0, 987.0
                         };

        for (int i = 0 ; i < inputArray.length; i++){
             List<Double> learning = new ArrayList<>();
             learning.add(inputArray[i]/1000);
             this.learningData.add(learning);
         }

        for (int i = 0 ; i < outputArray.length; i++){
            List<Double> learning = new ArrayList<>();
            learning.add(outputArray[i]/1000);
            this.outputData.add(learning);
        }
        PredictiveNetworkSerializator predictiveNetworkSerializator = new PredictiveNetworkSerializator();
        predictiveNetworkSerializator.writeInputDataToFile(this.learningData,"fibonacciInput.dat");
        predictiveNetworkSerializator.writeInputDataToFile(this.outputData,"fibonacciOutput.dat");
    }

  }