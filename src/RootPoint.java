import Api.FinctionsApi.FunctionEncountingNodesInterface;
import Api.NeuralNetApi.NeuralNetI;
import Entity.network.PredictiveNetwork;
import service.SigmoidFunction;

import java.util.ArrayList;
import java.util.List;

public class RootPoint {
    public static void main(String[] args) {


        FunctionEncountingNodesInterface func = new SigmoidFunction();
        NeuralNetI predictiveNetwork = new PredictiveNetwork(1, 1,
               2, 0.01d, 0.1d, func);

        System.out.println(predictiveNetwork + "\n\n\n");


        List<Double> learningData = new ArrayList<>();
        List<Double> outputData = new ArrayList<>();
        learningData.add(2.0d);
        outputData.add(3.0d);


        for (int i = 0; i < 4; i++) {
            predictiveNetwork.encountNet(learningData);

            System.out.println(predictiveNetwork + "\n\n\n");


            System.out.println("Result net - > \n" + predictiveNetwork.encountNet(learningData) + "\n\n");


            predictiveNetwork.encountDerivatives();
            predictiveNetwork.encountNetErrors(learningData, outputData);
            predictiveNetwork.encountWeight();

            System.out.println(predictiveNetwork + "\n\n\n");
            System.out.println("Result net - > \n" + predictiveNetwork.encountNet(learningData) + "\n\n");
        }
    }

    }