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
               4, 0.0001d, 0.4d, func);

        System.out.println(predictiveNetwork + "\n\n\n");


        List<List<Double>> learningData = new ArrayList<>();
        List<List<Double>> outputData = new ArrayList<>();
        List<Double> inputD = new ArrayList<>();
        List<Double> outputD = new ArrayList<>();
        inputD.add(3.0d);
        outputD.add(5.0d);
        learningData.add(inputD);
        outputData.add(outputD);

        Double midSqr = 0.0d;

       /* predictiveNetwork.incrementNodes();
        predictiveNetwork.incrementNodes();
        predictiveNetwork.incrementNodes();
        predictiveNetwork.incrementNodes();
        predictiveNetwork.incrementNodes();
*/

        do  {
            midSqr = 0.0d;
  predictiveNetwork.incrementNodes();

            predictiveNetwork.incrementNodes();
            //predictiveNetwork.incrementNodes();
            //predictiveNetwork.incrementNodes();
            //predictiveNetwork.incrementNodes();



            predictiveNetwork.encountNet(learningData.get(0));

          /*  System.out.println(predictiveNetwork + "\n\n\n");


            System.out.println("Result net - > \n" + predictiveNetwork.encountNet(learningData.get(0)) + "\n\n");*/


            predictiveNetwork.encountDerivatives();
            predictiveNetwork.encountNetErrors(learningData.get(0), outputData.get(0));
            predictiveNetwork.encountWeight();
            predictiveNetwork.encountNet(learningData.get(0));
            midSqr = predictiveNetwork.encountMidSquareError(learningData, outputData);

            System.out.println("error = " + midSqr + "\n\n\n");

           /* System.out.println(predictiveNetwork + "\n\n\n");
            System.out.println("Result net - > \n" + predictiveNetwork.encountNet(learningData.get(0)) + "\n\n");*/

        } while (midSqr > 0.005);

        System.out.println(predictiveNetwork + "\n\n\n");
        System.out.println("Result net - > \n" + predictiveNetwork.encountNet(learningData.get(0)) + "\n\n");

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