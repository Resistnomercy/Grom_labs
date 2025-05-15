package org.example;

public class LifeInsurance implements InsuranceObligation {
    private double cost;

    public LifeInsurance(double cost) {
        this.cost = cost;
    }

    @Override
    public String getName() {
        return "Life Insurance";
    }

    @Override
    public double getCost() {
        return cost;
    }
}
