package Aplicacion;
public class EcommerceFacade {

    public Order createOrder(double basePrice, double percentageDiscount, double couponValue,
                             String paymentType, String shippingType) {

        OrderComponent baseOrder = new BaseOrder(basePrice);

        OrderComponent discountedOrder =
                new PercentageDiscountDecorator(baseOrder, percentageDiscount);

        discountedOrder = new CouponDecorator(discountedOrder, couponValue);

        PaymentStrategy payment = PaymentFactory.createPayment(paymentType);
        ShippingStrategy shipping = ShippingFactory.createShipping(shippingType);

        return new Order(discountedOrder, payment, shipping);
    }

    public void addObserver(Order order, NotificationObserver observer) {
        order.addObserver(observer);
    }

    public void checkout(Order order) {
        order.checkout();
    }
}