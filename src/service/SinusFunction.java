package service;

import Api.FinctionsApi.FunctionEncountingNodesInterface;

public class SinusFunction implements FunctionEncountingNodesInterface {
    @Override
    public Double nodeResult(Double argument) {
        return Math.sin(argument);
    }

    @Override
    public Double derivativeResultOfNode(Double argument) {
        return Math.cos(argument);
    }
}
