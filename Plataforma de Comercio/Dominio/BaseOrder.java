package Dominio;
public class BaseOrder implements OrderComponent {
    private final double basePrice;

    public BaseOrder(double basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public double getTotal() {
        return basePrice;
    }
}