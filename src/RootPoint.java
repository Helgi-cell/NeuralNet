import Api.FinctionsApi.FunctionEncountingNodesInterface;
import Api.NeuralNetApi.NeuralNetI;
import Entity.layers.OutputLayer;
import Entity.network.PredictiveNetwork;
import service.SigmoidFunction;

import java.util.ArrayList;
import java.util.List;

public class RootPoint {
    public static void main(String[] args) {


        FunctionEncountingNodesInterface func = new SigmoidFunction();
        NeuralNetI predictiveNetwork = new PredictiveNetwork(1, 1,
               4, 0.000000000000000009d, 0.4d, func);

        System.out.println(predictiveNetwork + "\n\n\n");


        List<List<Double>> learningData = new ArrayList<>();
        List<List<Double>> outputData = new ArrayList<>();
        List<Double> inputD = new ArrayList<>();
        List<Double> outputD = new ArrayList<>();
        inputD.add(1.0d);
        outputD.add(2.0d);
        learningData.add(inputD);
        outputData.add(outputD);
        inputD = new ArrayList<>();
        outputD = new ArrayList<>();
        inputD.add(2.0d);
        outputD.add(3.0d);
        learningData.add(inputD);
        outputData.add(outputD);

        inputD = new ArrayList<>();
        outputD = new ArrayList<>();
        inputD.add(3.0d);
        outputD.add(5.0d);
        learningData.add(inputD);
        outputData.add(outputD);

        inputD = new ArrayList<>();
        outputD = new ArrayList<>();
        inputD.add(5.0d);
        outputD.add(8.0d);
        learningData.add(inputD);
        outputData.add(outputD);

        inputD = new ArrayList<>();
        outputD = new ArrayList<>();
        inputD.add(8.0d);
        outputD.add(13.0d);
        learningData.add(inputD);
        outputData.add(outputD);


        Double midSqr = 0.0d;
/*

        predictiveNetwork.incrementNodes();
        predictiveNetwork.incrementNodes();
        predictiveNetwork.incrementNodes();
        predictiveNetwork.incrementNodes();
        predictiveNetwork.incrementNodes();
*/
        List<List<Double>> inpData = new ArrayList<>();
        List<List<Double>> outData = new ArrayList<>();
        List<Double> inputNet = new ArrayList<>();
        List<Double> outputNet = new ArrayList<>();


        for (int i = 0; i < learningData.size(); i++){
            inpData.add(learningData.get(i));
            outData.add(outputData.get(i));

            int score = 0;

            do {
                midSqr = 0.0d;
                for (int j = 0; j < inpData.size(); j++){
                    inputNet = inpData.get(j);
                    outputNet = outData.get(j);
                    predictiveNetwork.encountNet(inputNet);
                    predictiveNetwork.encountDerivatives();
                    predictiveNetwork.encountNetErrors(inputNet, outputNet);
                    predictiveNetwork.encountWeight();
                }

                midSqr = predictiveNetwork.encountMidSquareError(inpData, outData);
                System.out.println("error = " + midSqr + "\n\n\n");
                score++;
                if (score > 0){
                    predictiveNetwork.incrementNodes();
                    score = 0;
                    //break;
                }

                score++;
            } while(midSqr > 0.15);


        }





            System.out.println(predictiveNetwork + "\n\n\n");
            System.out.println("error = " + midSqr + "\n\n\n");
            for (List<Double> learn : learningData){
                System.out.println("input = " + learn + "      output = " + predictiveNetwork.encountNet(learn));
            }

            List<Double> test = new ArrayList<>();
            test.add(8.0d);
        System.out.println("test = " + test + "      output = " + predictiveNetwork.encountNet(test));
            test.remove(0);
            test.add(13.0d);
        System.out.println("test = " + test + "      output = " + predictiveNetwork.encountNet(test));

        test.remove(0);
        test.add(21.0d);
        System.out.println("test = " + test + "      output = " + predictiveNetwork.encountNet(test));

        test.remove(0);
        test.add(33.0d);
        System.out.println("test = " + test + "      output = " + predictiveNetwork.encountNet(test));
    }


    public void learningNetwork(int numNeuronsInput, int numNeuronsOutput, int numHiddenLayers
                            , Double stepLearning, Double midSquareError, FunctionEncountingNodesInterface func,
                                List<Double> inputLearningData, List<Double> outputLearningData){

        PredictiveNetwork predictiveNetwork = new PredictiveNetwork(numNeuronsInput, numNeuronsOutput,
                numHiddenLayers, stepLearning, midSquareError, func);

        List<Double> inputLearn = new ArrayList<>();
        List<Double> outputLearn = new ArrayList<>();


    }

    }