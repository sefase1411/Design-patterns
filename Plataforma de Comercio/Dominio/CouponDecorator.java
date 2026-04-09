package Dominio;
public class CouponDecorator extends OrderDecorator {
    private final double couponValue;

    public CouponDecorator(OrderComponent order, double couponValue) {
        super(order);
        this.couponValue = couponValue;
    }

    @Override
    public double getTotal() {
        double total = order.getTotal() - couponValue;
        return Math.max(total, 0);
    }
}