package Interfaz;
public class EcommerceDemo {
    public static void main(String[] args) {
        EcommerceFacade facade = new EcommerceFacade();

        Order order = facade.createOrder(
                200000,
                10,
                15000,
                "paypal",
                "express"
        );

        facade.addObserver(order, new EmailNotifier());
        facade.addObserver(order, new SMSNotifier());

        facade.checkout(order);
    }
}