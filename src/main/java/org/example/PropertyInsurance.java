package org.example;

public class PropertyInsurance implements InsuranceObligation {
    private double cost;

    public PropertyInsurance(double cost) {
        this.cost = cost;
    }

    @Override
    public String getName() {
        return "Property Insurance";
    }

    @Override
    public double getCost() {
        return cost;
    }
}
