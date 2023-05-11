import Api.NeuralNetApi.NeuralNetI;
import Entity.network.PredictiveNetwork;
import Serializator.PredictiveNetworkSerializator;

import java.util.ArrayList;
import java.util.List;

public class NeuralNet {

    public static void main(String [] args) {
        PredictiveNetworkSerializator predictiveNetworkSerializator = new PredictiveNetworkSerializator();
        PredictiveNetwork predictiveNetwork = predictiveNetworkSerializator.getPredictiveNetworkFromFile();

        List<Double> test = new ArrayList<>();
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

    }
}
