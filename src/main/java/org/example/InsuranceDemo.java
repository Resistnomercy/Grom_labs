package org.example;

public class InsuranceDemo {
    public static void main(String[] args) {
        InsuranceObligation life = new LifeInsurance(1200);
        InsuranceObligation property = new PropertyInsurance(800);
        InsuranceObligation health = new HealthInsurance(950);

        InsuranceDerivative derivative = new InsuranceDerivative();
        derivative.addObligation(life);
        derivative.addObligation(property);
        derivative.addObligation(health);

        System.out.println("Страховые обязательства в деривативе:");
        derivative.printObligations();

        System.out.println("Общая стоимость дериватива: $" + derivative.getTotalCost());
    }
}
