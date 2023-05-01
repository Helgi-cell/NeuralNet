package Entity.network;

import Api.FinctionsApi.FunctionEncountingNodesInterface;
import Api.LayersApi.LayerCommonI;
import Api.NeuralNetApi.NeuralNetI;
import Entity.layers.HiddenLayer;
import Entity.layers.InputLayer;
import Entity.layers.OutputLayer;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PredictiveNetwork implements NeuralNetI {

    private List<LayerCommonI> layers = new ArrayList<>();
    private Double stepLearnimg;
    private Double midSquareError;
    private FunctionEncountingNodesInterface func;

    private List<List<Double>> derivatives = new ArrayList<>();

    private List<List<Double>> netErrors = new ArrayList<>();

    private Stack<List<Double>> derivateStack = new Stack<>();
    private Stack<List<Double>> errorsStack = new Stack<>();
    private Stack<List<List<Double>>> weigthStack = new Stack<>();

   /* List<Double> inputTemplateOfLearning;
    List<Double> outputTemplateOfLearning;*/

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

        for(int i = 0; i < numHiddenLayers; i++){
            List<Double> errorNode = new ArrayList<>();
            for (int j = 0; j < numNeu; j++){
                errorNode.add(0.0d);
            }
            this.netErrors.add(errorNode);
            this.derivatives.add(errorNode);
        }

        List<Double> errorNode = new ArrayList<>();
        for(int i = 0; i < nimNeuronsOutputLayer; i++){
                errorNode.add(0.0d);
        }
        this.netErrors.add(errorNode);
        this.derivatives.add(errorNode);

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
    public List<List<Double>> encountNetErrors(List<Double> inputTemplateOfLearning, List<Double> outputTemplateOfLearning) {
        this.errorsStack.clear();
        this.derivateStack.clear();
        this.weigthStack.clear();
        List<Double> errors = new ArrayList<>();
        OutputLayer outputLayer = (OutputLayer) this.layers.get(this.layers.size() - 1);

        for (int i = 0; i < outputLayer.getNeurons().size(); i++){
            errors.add(outputLayer.getNeurons().get(i) * inputTemplateOfLearning.get(i) - outputTemplateOfLearning.get(i));
        }
        this.netErrors.set(this.netErrors.size() - 1, errors);
        OutputLayer layerCommon = (OutputLayer) this.layers.get(this.layers.size() - 1);
        List<List<Double>> weigths = layerCommon.getWeigths();
        this.netErrors.set(this.netErrors.size() - 1, errors);
            fillStack();
            Double sumGamma = 0.0d;
        List<Double> listDeriv = this.derivateStack.pop();
        List<Double> nextErrors = this.errorsStack.pop();
        List<List<Double>> nextWeigths = this.weigthStack.pop();

        while (!this.errorsStack.empty()) {
            errors = this.errorsStack.pop();
                for (int j = 0; j < errors.size(); j++ ) {
                    for (int i = 0; i < nextErrors.size(); i++){
                        sumGamma += listDeriv.get(i) * nextErrors.get(i) * nextWeigths.get(i).get(j);
                    }
                    errors.set(j, sumGamma);
                    sumGamma = 0.0d;
                }
        }



        return this.netErrors;
    }


    private void fillStack(){

        for (List<Double> err : this.netErrors){ this.errorsStack.push(err); }
        for (List<Double> der : this.derivatives){ this.derivateStack.push(der);}

        HiddenLayer hiddenLayer;
        for(int i = 2; i < this.layers.size() - 1; i++){
            hiddenLayer = (HiddenLayer)this.layers.get(i);
            this.weigthStack.push(hiddenLayer.getWeigths());
        }
        OutputLayer outputLayer;
        outputLayer = (OutputLayer) this.layers.get(this.layers.size() - 1);
        this.weigthStack.push(outputLayer.getWeigths());
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

        for (int i = 1; i < this.layers.size() -1; i++ ){
            this.netErrors.get(i - 1).add(0.0d);
        }


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
                /*", stepLearnimg=" + stepLearnimg +*/
                "\n\t, netErrors=" + netErrors +
                /*", func=" + func +*/
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
