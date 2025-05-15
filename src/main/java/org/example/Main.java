package org.example;


public class Main {
    public static void main(String[] args) {
        InsuranceObligation life = new LifeInsurance(1000);
        InsuranceObligation property = new PropertyInsurance(750);
        InsuranceObligation health = new HealthInsurance(900);

        InsuranceDerivative derivative = new InsuranceDerivative();
        derivative.addObligation(life);
        derivative.addObligation(property);
        derivative.addObligation(health);

        System.out.println("Состав дериватива:");
        derivative.printObligations();

        System.out.println("Итоговая стоимость дериватива: $" + derivative.getTotalCost());
    }
}
