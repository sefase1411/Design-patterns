package Infraestructura;
public class ShippingFactory {
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