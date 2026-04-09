package Infraestructura;
public class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Pago realizado con PayPal: $" + amount);
    }
}