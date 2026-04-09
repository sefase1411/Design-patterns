package Dominio;
public class PercentageDiscountDecorator extends OrderDecorator {
    private final double percentage;

    public PercentageDiscountDecorator(OrderComponent order, double percentage) {
        super(order);
        this.percentage = percentage;
    }

    @Override
    public double getTotal() {
        return order.getTotal() - (order.getTotal() * percentage / 100);
    }
}