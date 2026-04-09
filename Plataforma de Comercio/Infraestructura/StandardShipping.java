package Infraestructura;
public class StandardShipping implements ShippingStrategy {
    @Override
    public double calculateCost() {
        return 10000;
    }

    @Override
    public String getType() {
        return "Envío estándar";
    }
}