package Api.NeuralNetApi;

import Api.LayersApi.LayerCommonI;

import java.util.List;

public interface NeuralNetI {
    public List<LayerCommonI> initNetwork(Integer numNeuronsInputLayer, Integer nimNeuronsOutputLayer,
                                          Integer numHiddenLayers);
    public List<Double> encountNet(List<Double> templateOfLearning);
    public List<Double> encountDerivatives();
    public Double encountWeight();
    public List<LayerCommonI> incrementNodes();
}
