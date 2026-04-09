package Infraestructura;
public class ExpressShipping implements ShippingStrategy {
    @Override
    public double calculateCost() {
        return 25000;
    }

    @Override
    public String getType() {
        return "Envío express";
    }
}