import Api.FinctionsApi.FunctionEncountingNodesInterface;
import Entity.network.PredictiveNetwork;
import service.SigmoidFunction;

import java.util.ArrayList;
import java.util.List;

public class RootPoint {
    public static void main(String[] args) {


        FunctionEncountingNodesInterface func = new SigmoidFunction();
        PredictiveNetwork predictiveNetwork = new PredictiveNetwork(1, 1,
               2, 0.1d, 0.05d, func);

        System.out.println(predictiveNetwork + "\n\n\n");


        List<Double> learningData = new ArrayList<>();
        learningData.add(1.0d);
        predictiveNetwork.encountNet(learningData);

        System.out.println(predictiveNetwork + "\n\n\n");

/*
        predictiveNetwork.incrementNodes();
        System.out.println(predictiveNetwork + "\n\n\n");

        predictiveNetwork.incrementNodes();
        System.out.println(predictiveNetwork + "\n\n\n");*/
    }

    }