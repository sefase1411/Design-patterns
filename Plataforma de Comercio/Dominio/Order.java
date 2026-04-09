package Dominio;
import java.util.ArrayList;
import java.util.List;

public class Order {
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