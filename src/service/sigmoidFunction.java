package service;

import Api.FinctionsApi.FunctionEncountingNodesInterface;

import static java.lang.Math.exp;

public class sigmoidFunction implements FunctionEncountingNodesInterface {
    @Override
    public Double nodeResult(Double argument) {
        return 1/(1 + exp(-1.0d * argument ));
    }
}
