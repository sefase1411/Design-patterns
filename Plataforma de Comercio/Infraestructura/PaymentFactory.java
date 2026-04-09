package Infraestructura;
public class PaymentFactory {
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