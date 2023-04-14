package Entity;

import Api.LayersApi.LayerFinalI;
import java.util.ArrayList;
import java.util.List;

public class OutputLayer implements LayerFinalI {

    private List<Double> neurons = new ArrayList<>();

    private List <List<Double>> weigths = new ArrayList<>();

    public OutputLayer(Integer numNeurons, Integer numNeuronsPrevLayer) {
        initLayer(numNeurons, numNeuronsPrevLayer);
    }


    private void initLayer(Integer numNeurons, Integer numNeuronsPrevLayer) {
        for (int i = 0; i < numNeurons; i++) {
            this.neurons.add(0.0);
        }


        for (int i = 0; i < numNeurons; i++){
            List<Double> weightsToNeuron = new ArrayList<>();
            for (int j = 0; j < numNeuronsPrevLayer; j++){
                Double wght = Math.random() / 10.0 - 0.015;
                weightsToNeuron.add(wght);
            }
            this.weigths.add(weightsToNeuron);
        }

    }


    @Override
    public List<Double> addWeightsByNewNeuronInPrevLayer(Integer prevNeurons) {
       /* if (prevNeurons == this.weigths.get(0).size()) {
            List<Double> weightsToNeuron = new ArrayList<>();
            for (int i = 0; i < prevNeurons; i++){
                Double wght = Math.random() / 10.0 - 0.015;
                weightsToNeuron.add(wght);
            }
            this.weigths.add(weightsToNeuron);
        } else {*/

                for (int i = 0; i < this.weigths.size(); i++) {
                    Double wght = Math.random() / 10.0 - 0.015;
                    List<Double> weighToNeuron = this.weigths.get(i);
                    weighToNeuron.add(wght);
                    this.weigths.set(i, weighToNeuron);
                }
/*

                List<Double> weightsToNeuron = new ArrayList<>();
                for (int i = 0; i < prevNeurons; i++) {
                    Double wght = Math.random() / 10.0 - 0.015;
                    weightsToNeuron.add(wght);
                }
                this.weigths.add(weightsToNeuron);
*/


        //}
        //this.neurons.add(0.0);

        return this.neurons;
    }


    @Override
    public void changeWeigths() {

    }

    public List<Double> getNeurons() {
        return neurons;
    }

    public void setNeurons(List<Double> neurons) {
        this.neurons = neurons;
    }

    public List<List<Double>> getWeigths() {
        return weigths;
    }

    public void setWeigths(List<List<Double>> weigths) {
        this.weigths = weigths;
    }

    @Override
    public String toString() {
        return "OutputLayer{" +
                "neurons=" + neurons +
                "\n, weigths=" + weigths +
                '}';
    }
}
