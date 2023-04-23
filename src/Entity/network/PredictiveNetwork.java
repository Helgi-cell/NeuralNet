package Entity.network;

import Api.FinctionsApi.FunctionEncountingNodesInterface;
import Api.LayersApi.LayerCommonI;
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
    private FunctionEncountingNodesInterface func;

    private List<List<Double>> derivatives = new ArrayList<>();

    private List<List<Double>> netErrors = new ArrayList<>();

    public PredictiveNetwork(Integer numNeuronsInputLayer, Integer nimNeuronsOutputLayer,
                             Integer numHiddenLayers, Double stepLearnimg, Double midSquareError,
                             FunctionEncountingNodesInterface func) {
        this.stepLearnimg = stepLearnimg;
        this.midSquareError = midSquareError;
        this.func = func;
        initNetwork(numNeuronsInputLayer, nimNeuronsOutputLayer, numHiddenLayers);

    }

    @Override
    public List<LayerCommonI> initNetwork(Integer numNeuronsInputLayer, Integer nimNeuronsOutputLayer,
                                          Integer numHiddenLayers) {

        this.layers.add(new InputLayer(numNeuronsInputLayer));
        int numNeuprev = numNeuronsInputLayer;
        int numNeu = numNeuronsInputLayer + 1;
        for(int i = 0; i < numHiddenLayers; i++){
            this.layers.add(new HiddenLayer(numNeu, numNeuprev, this.func ));
            numNeuprev = numNeu;
        }
        this.layers.add(new OutputLayer(nimNeuronsOutputLayer, numNeu, this.func));
        return this.layers;
    }

    @Override
    public List<Double> encountNet(List<Double> templateOfLearning) {
        InputLayer inputLayer = (InputLayer) this.layers.get(0);
        List<Double> neuronsActivation = inputLayer.encountNeuron(templateOfLearning);
        for (int i = 1; i < this.layers.size() -1; i++){
            HiddenLayer hiddenLayer = (HiddenLayer)  this.layers.get(i);
            neuronsActivation = hiddenLayer.encountNeuron(neuronsActivation);
        }
        OutputLayer outputLayer = (OutputLayer)  this.layers.get(this.layers.size() - 1);
        neuronsActivation = outputLayer.encountNeuron(neuronsActivation);
        return neuronsActivation;
    }

    @Override
    public List<List<Double>> encountDerivatives() {
        List<Double> layerDerivatives = new ArrayList<>();
        this.derivatives.clear();
        for(int i = 1; i < this.layers.size() -1; i++){
            for (Double neuron: ((HiddenLayer) this.layers.get(i)).getNeurons()) {
                layerDerivatives.add(func.derivativeResultOfNode(neuron));
            }
            this.derivatives.add(layerDerivatives);
            layerDerivatives = new ArrayList<>();
        }
        for (Double neuron:((OutputLayer) this.layers.get(this.layers.size() -1)).getNeurons()) {
        layerDerivatives.add(func.derivativeResultOfNode(neuron));
        }
        this.derivatives.add(layerDerivatives);

        return this.derivatives;
    }


    @Override
    public List<List<Double>> encountNetErrors() {
        return null;
    }


    @Override
    public Double encountWeight() {
        return null;
    }

    @Override
    public List<LayerCommonI> incrementNodes() {
        InputLayer inputLayer = (InputLayer) this.layers.get(0);
        int prevNodes = inputLayer.getNeurons().size();

        for(int i = 1; i < this.layers.size() - 1; i++){
            HiddenLayer hiddenLayer = (HiddenLayer) this.layers.get(i);
            hiddenLayer.setNeurons(hiddenLayer.addNeuronInLayer(prevNodes));
            layers.set(i,hiddenLayer);
            prevNodes = hiddenLayer.getNeurons().size();
        }

        OutputLayer outputLayer = (OutputLayer) this.layers.get(this.layers.size() - 1);
        outputLayer.addWeightsByNewNeuronInPrevLayer(prevNodes);
        return this.layers;
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

    public FunctionEncountingNodesInterface getFunc() {
        return func;
    }

    public void setFunc(FunctionEncountingNodesInterface func) {
        this.func = func;
    }

    public List<List<Double>> getDerivatives() {
        return derivatives;
    }

    public void setDerivatives(List<List<Double>> derivatives) {
        this.derivatives = derivatives;
    }

    @Override
    public String toString() {
        return "PredictiveNetwork{\n" +
                "layers=\n\t" + layers +
               /* ", stepLearnimg=" + stepLearnimg +
                ", midSquareError=" + midSquareError +*/
               /* ", func=" + func +*/
                "\n\t, derivatives=\n\t" + derivatives +
                '}';
    }

    /*  @Override
    public String toString() {
        return "PredictiveNetwork{\n" +
                "layers=" + layers +
                "\n , stepLearnimg=" + stepLearnimg +
                "\n , midSquareError=" + midSquareError +
                "}\n\n";
    }*/
}
