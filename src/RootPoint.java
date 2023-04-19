import Api.FinctionsApi.FunctionEncountingNodesInterface;
import Entity.network.PredictiveNetwork;
import service.SigmoidFunction;

import java.util.ArrayList;
import java.util.List;

public class RootPoint {
    public static void main(String[] args) {
    /*    InputLayer inputLayer = new InputLayer(1);
        HiddenLayer hiddenLayer1 = new HiddenLayer(2, inputLayer.getNeurons().size());
        HiddenLayer hiddenLayer2 = new HiddenLayer(2, hiddenLayer1.getNeurons().size());
        OutputLayer outputLayer = new OutputLayer(1, hiddenLayer2.getNeurons().size());

        System.out.println(inputLayer + "\n\n");
        System.out.println(hiddenLayer1 + "\n\n");
        System.out.println(hiddenLayer2 + "\n\n");
        System.out.println(outputLayer + "\n\n");

        for (int i = 0; i < 2; i++) {

            hiddenLayer1.addNeuronInLayer(inputLayer.getNeurons().size());
            hiddenLayer2.addNeuronInLayer(hiddenLayer1.getNeurons().size());
            outputLayer.addWeightsByNewNeuronInPrevLayer(hiddenLayer2.getNeurons().size());

            System.out.println("After the neuron add..... -> " + (i + 1)  + " num\n\n");
            System.out.println(inputLayer + "\n\n");
            System.out.println(hiddenLayer1 + "\n\n");
            System.out.println(hiddenLayer2 + "\n\n");
            System.out.println(outputLayer + "\n\n");
        }*/

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