package org.example;

public class HealthInsurance implements InsuranceObligation {
    private double cost;

    public HealthInsurance(double cost) {
        this.cost = cost;
    }

    @Override
    public String getName() {
        return "Health Insurance";
    }

    @Override
    public double getCost() {
        return cost;
    }
}
