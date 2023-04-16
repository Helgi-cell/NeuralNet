package Entity.network;

import Api.LayersApi.LayerCommonI;
import Api.LayersApi.LayerInputI;
import Api.NeuralNetApi.NeuralNetI;
import Entity.layers.HiddenLayer;
import Entity.layers.InputLayer;
import Entity.layers.OutputLayer;

import java.util.ArrayList;
import java.util.List;

public class PredictiveNetwork implements NeuralNetI {

    private List<LayerCommonI> layers = new ArrayList<>();
    private Double stepLearnimg;
    private Double midSquareError;

    public PredictiveNetwork(Integer numNeuronsInputLayer, Integer nimNeuronsOutputLayer,
                             Integer numHiddenLayers, Double stepLearnimg, Double midSquareError) {
        this.stepLearnimg = stepLearnimg;
        this.midSquareError = midSquareError;
        initNetwork(numNeuronsInputLayer, nimNeuronsOutputLayer, numHiddenLayers);
    }

    @Override
    public List<LayerCommonI> initNetwork(Integer numNeuronsInputLayer, Integer nimNeuronsOutputLayer,
                                          Integer numHiddenLayers) {

        //LayerCommonI layerInput = new InputLayer(numNeuronsInputLayer);
        this.layers.add(new InputLayer(numNeuronsInputLayer));
        int numNeuprev = numNeuronsInputLayer;
        int numNeu = numNeuronsInputLayer + 1;
        for(int i = 0; i < numHiddenLayers; i++){
            this.layers.add(new HiddenLayer(numNeu, numNeuprev ));
            numNeuprev = numNeu;
        }

       // LayerCommonI layerOutput = new OutputLayer(nimNeuronsOutputLayer, numNeu);
        this.layers.add(new OutputLayer(nimNeuronsOutputLayer, numNeu));
        return this.layers;
    }

    @Override
    public List<Double> encountNet() {
        return null;
    }

    @Override
    public List<Double> encountDerivatives() {
        return null;
    }

    @Override
    public Double encountWeight() {
        return null;
    }

    public List<LayerCommonI> getLayers() {
        return layers;
    }

    public void setLayers(List<LayerCommonI> layers) {
        this.layers = layers;
    }

    public Double getStepLearnimg() {
        return stepLearnimg;
    }

    public void setStepLearnimg(Double stepLearnimg) {
        this.stepLearnimg = stepLearnimg;
    }

    public Double getMidSquareError() {
        return midSquareError;
    }

    public void setMidSquareError(Double midSquareError) {
        this.midSquareError = midSquareError;
    }

    @Override
    public String toString() {
        return "PredictiveNetwork{" +
                "layers=" + layers +
                ", stepLearnimg=" + stepLearnimg +
                ", midSquareError=" + midSquareError +
                '}';
    }
}
