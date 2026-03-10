import java.util.ArrayList;
import java.util.List;

// Archivo: EcommerceDemo.java
public class EcommerceDemo {

    public static void main(String[] args) {
        // Pedido base
        OrderComponent baseOrder = new BaseOrder(200000);

        // Decorator: descuentos
        OrderComponent discountedOrder = new PercentageDiscountDecorator(baseOrder, 10); // 10%
        discountedOrder = new CouponDecorator(discountedOrder, 15000); // descuento fijo

        // Factory Method + Strategy
        PaymentStrategy payment = PaymentFactory.createPayment("paypal");
        ShippingStrategy shipping = ShippingFactory.createShipping("express");

        // Pedido principal
        Order order = new Order(discountedOrder, payment, shipping);

        // Observer: notificaciones
        order.addObserver(new EmailNotifier());
        order.addObserver(new SMSNotifier());

        order.checkout();
    }

    // STRATEGY - PAGO
    interface PaymentStrategy {
        void pay(double amount);
    }

    static class CreditCardPayment implements PaymentStrategy {
        @Override
        public void pay(double amount) {
            System.out.println("Pago realizado con tarjeta: $" + amount);
        }
    }

    static class PayPalPayment implements PaymentStrategy {
        @Override
        public void pay(double amount) {
            System.out.println("Pago realizado con PayPal: $" + amount);
        }
    }

    // FACTORY METHOD - PAGO
    static class PaymentFactory {
        public static PaymentStrategy createPayment(String type) {
            switch (type.toLowerCase()) {
                case "card":
                    return new CreditCardPayment();
                case "paypal":
                    return new PayPalPayment();
                default:
                    throw new IllegalArgumentException("Método de pago no válido");
            }
        }
    }

    // STRATEGY - ENVÍO
    interface ShippingStrategy {
        double calculateCost();
        String getType();
    }

    static class StandardShipping implements ShippingStrategy {
        @Override
        public double calculateCost() {
            return 10000;
        }

        @Override
        public String getType() {
            return "Envío estándar";
        }
    }

    static class ExpressShipping implements ShippingStrategy {
        @Override
        public double calculateCost() {
            return 25000;
        }

        @Override
        public String getType() {
            return "Envío express";
        }
    }

    // FACTORY METHOD - ENVÍO
    static class ShippingFactory {
        public static ShippingStrategy createShipping(String type) {
            switch (type.toLowerCase()) {
                case "standard":
                    return new StandardShipping();
                case "express":
                    return new ExpressShipping();
                default:
                    throw new IllegalArgumentException("Tipo de envío no válido");
            }
        }
    }

    // DECORATOR - PEDIDO Y DESCUENTOS
    interface OrderComponent {
        double getTotal();
    }

    static class BaseOrder implements OrderComponent {
        private final double basePrice;

        public BaseOrder(double basePrice) {
            this.basePrice = basePrice;
        }

        @Override
        public double getTotal() {
            return basePrice;
        }
    }

    static abstract class OrderDecorator implements OrderComponent {
        protected OrderComponent order;

        public OrderDecorator(OrderComponent order) {
            this.order = order;
        }
    }

    static class PercentageDiscountDecorator extends OrderDecorator {
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

    static class CouponDecorator extends OrderDecorator {
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

    // OBSERVER - NOTIFICACIONES
    interface NotificationObserver {
        void update(String message);
    }

    static class EmailNotifier implements NotificationObserver {
        @Override
        public void update(String message) {
            System.out.println("Email enviado: " + message);
        }
    }

    static class SMSNotifier implements NotificationObserver {
        @Override
        public void update(String message) {
            System.out.println("SMS enviado: " + message);
        }
    }

    // CLASE PRINCIPAL DEL PEDIDO
    static class Order {
        private final OrderComponent orderComponent;
        private final PaymentStrategy paymentStrategy;
        private final ShippingStrategy shippingStrategy;
        private final List<NotificationObserver> observers = new ArrayList<>();

        public Order(OrderComponent orderComponent, PaymentStrategy paymentStrategy, ShippingStrategy shippingStrategy) {
            this.orderComponent = orderComponent;
            this.paymentStrategy = paymentStrategy;
            this.shippingStrategy = shippingStrategy;
        }

        public void addObserver(NotificationObserver observer) {
            observers.add(observer);
        }

        public void notifyObservers(String message) {
            for (NotificationObserver observer : observers) {
                observer.update(message);
            }
        }

        public void checkout() {
            double subtotal = orderComponent.getTotal();
            double shippingCost = shippingStrategy.calculateCost();
            double finalTotal = subtotal + shippingCost;

            System.out.println("Subtotal con descuentos: $" + subtotal);
            System.out.println(shippingStrategy.getType() + ": $" + shippingCost);
            System.out.println("Total final: $" + finalTotal);

            paymentStrategy.pay(finalTotal);
            notifyObservers("Pedido confirmado. Total pagado: $" + finalTotal);
        }
    }
}