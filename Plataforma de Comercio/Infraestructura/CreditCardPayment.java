package Infraestructura;
public class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Pago realizado con tarjeta: $" + amount);
    }
}