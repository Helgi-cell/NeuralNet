package Api.LayersApi;

import java.util.List;

public interface LayerHiddenI extends LayerCommonI {
    //public void encountNeuron();
    public List<Double> addNeuronInLayer(Integer prevNeuronsNum);
    public void changeWeigths();
}
