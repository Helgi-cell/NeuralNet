import Api.FinctionsApi.FunctionEncountingNodesInterface;
import Api.NeuralNetApi.NeuralNetI;
import Entity.layers.OutputLayer;
import Entity.network.PredictiveNetwork;
import service.SigmoidFunction;
import service.SinusFunction;

import java.util.ArrayList;
import java.util.List;

public class RootPoint {
    List<List<Double>> learningData = new ArrayList<>();
    List<List<Double>> outputData = new ArrayList<>();

    public static void main(String[] args) {


        RootPoint rootPoint = new RootPoint();

        FunctionEncountingNodesInterface func = new SigmoidFunction();
        //FunctionEncountingNodesInterface func = new SinusFunction();

        NeuralNetI predictiveNetwork = new PredictiveNetwork(1, 1,
               4, 0.000000000000000000000000000000000000000000000000000000001d, 0.15d, func);

        System.out.println(predictiveNetwork + "\n\n\n");


        rootPoint.createLearningData();


        Double midSqr = 0.0d;





        List<List<Double>> inpData = new ArrayList<>();
        List<List<Double>> outData = new ArrayList<>();
        List<Double> inputNet = new ArrayList<>();
        List<Double> outputNet = new ArrayList<>();


        for (int i = 0; i < rootPoint.learningData.size(); i++){
            inpData.add(rootPoint.learningData.get(i));
            outData.add(rootPoint.outputData.get(i));

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
                //System.out.println("error = " + midSqr + "\n\n\n");
                score++;
                if (score > 100){
                    System.out.println("error = " + midSqr + "\n\n\n");
                    predictiveNetwork.incrementNodes();

                    score = 0;
                    //break;
                }

                score++;
            } while(midSqr > predictiveNetwork.getMidSquareError() * (i + 1));


        }





            System.out.println(predictiveNetwork + "\n\n");
            System.out.println("Number neurons in the each hidden layer = " + predictiveNetwork.getNumberNeuronsInHiddenLayer() + "\n\n");
            System.out.println("error = " + midSqr + "\n\n\n");
            for (List<Double> learn : rootPoint.learningData){
                System.out.println("input = " + learn + "      output = " + predictiveNetwork.encountNet(learn));
            }

            List<Double> test = new ArrayList<>();
            test.add(987.0d);
        System.out.println("test = " + test + "      output = " + predictiveNetwork.encountNet(test) + "   must = " + 1597);
            test.remove(0);
           /* test.add(10.0d);
        System.out.println("test = " + test + "      output = " + predictiveNetwork.encountNet(test) + "   must = " + 100);

        test.remove(0);
        test.add(7.0d);
        System.out.println("test = " + test + "      output = " + predictiveNetwork.encountNet(test) + "   must = " + 49);

        test.remove(0);*/
        test.add(21.0d);
        System.out.println("test = " + test + "      output = " + predictiveNetwork.encountNet(test) + "   must = " + 34);

        test.remove(0);
        test.add(34.0d);
        System.out.println("test = " + test + "      output = " + predictiveNetwork.encountNet(test) + "   must = " + 55);

        test.remove(0);
        test.add(55.0d);
        System.out.println("test = " + test + "      output = " + predictiveNetwork.encountNet(test) + "   must = " + 89);
    }


    public void learningNetwork(int numNeuronsInput, int numNeuronsOutput, int numHiddenLayers
                            , Double stepLearning, Double midSquareError, FunctionEncountingNodesInterface func,
                                List<Double> inputLearningData, List<Double> outputLearningData){

        PredictiveNetwork predictiveNetwork = new PredictiveNetwork(numNeuronsInput, numNeuronsOutput,
                numHiddenLayers, stepLearning, midSquareError, func);

        List<Double> inputLearn = new ArrayList<>();
        List<Double> outputLearn = new ArrayList<>();


    }


    public void createLearningData(){
        Double [] inputArray = new Double[]
                {1.0, 2.0, 3.0, 5.0, 8.0, 13.0, 21.0, 34.0, 55.0, 89.0, 144.0, 233.0, 377.0, 610.0};
        Double [] outputArray = new Double[]
                {2.0, 3.0, 5.0, 8.0, 13.0, 21.0, 34.0, 55.0, 89.0, 144.0, 233.0, 377.0, 610.0, 987.0};


/*
        Double [] inputArray = new Double[]
                {1.0, 2.0, 3.0, 4.0, 5.0, 6.0};
        Double [] outputArray = new Double[]
                {1.0, 4.0, 9.0, 16.0, 25.0, 36.0};

*/

        for (int i = 0 ; i < inputArray.length; i++){
             List<Double> learning = new ArrayList<>();
             learning.add(inputArray[i]);
             this.learningData.add(learning);
         }


        for (int i = 0 ; i < outputArray.length; i++){
            List<Double> learning = new ArrayList<>();
            learning.add(outputArray[i]);
            this.outputData.add(learning);
        }
    }

    }