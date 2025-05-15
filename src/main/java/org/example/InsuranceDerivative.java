package org.example;

import java.util.ArrayList;
import java.util.List;

public class InsuranceDerivative {
    private List<InsuranceObligation> obligations = new ArrayList<>();

    public void addObligation(InsuranceObligation obligation) {
        obligations.add(obligation);
    }

    public double getTotalCost() {
        return obligations.stream().mapToDouble(InsuranceObligation::getCost).sum();
    }

    public void printObligations() {
        for (InsuranceObligation o : obligations) {
            System.out.println(o.getName() + " - $" + o.getCost());
        }
    }
}
