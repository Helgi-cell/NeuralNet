package Api.NeuralNetApi;

import java.util.List;

public interface NeuralNetI {
    public List<Double> encountNet();
    public List<Double> encountDerivatives();
    public Double encountWeight();
}
