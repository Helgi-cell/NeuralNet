package Api.NeuralNetApi;

import Api.LayersApi.LayerCommonI;

import java.util.List;

public interface NeuralNetI {
    List<LayerCommonI> initNetwork(Integer numNeuronsInputLayer, Integer nimNeuronsOutputLayer,
                                          Integer numHiddenLayers);
    List<Double> encountNet(List<Double> templateOfLearning);
    List<List<Double>> encountDerivatives();
    Double encountWeight();
    List<LayerCommonI> incrementNodes();
}
