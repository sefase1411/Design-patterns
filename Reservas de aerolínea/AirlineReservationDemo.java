import java.util.ArrayList;
import java.util.List;

// Archivo: AirlineReservationDemo.java
public class AirlineReservationDemo {

    public static void main(String[] args) {
        Reservation reservation = new ReservationBuilder()
                .setPassenger("David Valderrama")
                .setFlight("AV204")
                .setSeat("12A")
                .setBasePrice(350000)
                .build();

        reservation.setPricingStrategy(new EconomyPricing());
        reservation.addObserver(new EmailNotifier());
        reservation.addObserver(new SMSNotifier());

        System.out.println("Precio calculado: $" + reservation.calculatePrice());

        reservation.confirm();
        reservation.cancel();
    }

    // BUILDER
    static class ReservationBuilder {
        private String passenger;
        private String flight;
        private String seat;
        private double basePrice;

        public ReservationBuilder setPassenger(String passenger) {
            this.passenger = passenger;
            return this;
        }

        public ReservationBuilder setFlight(String flight) {
            this.flight = flight;
            return this;
        }

        public ReservationBuilder setSeat(String seat) {
            this.seat = seat;
            return this;
        }

        public ReservationBuilder setBasePrice(double basePrice) {
            this.basePrice = basePrice;
            return this;
        }

        public Reservation build() {
            return new Reservation(passenger, flight, seat, basePrice);
        }
    }

    // STRATEGY - PRECIOS
    interface PricingStrategy {
        double calculate(double basePrice);
    }

    static class EconomyPricing implements PricingStrategy {
        @Override
        public double calculate(double basePrice) {
            return basePrice;
        }
    }

    static class PremiumPricing implements PricingStrategy {
        @Override
        public double calculate(double basePrice) {
            return basePrice * 1.5;
        }
    }

    // OBSERVER - NOTIFICACIONES
    interface ReservationObserver {
        void update(String message);
    }

    static class EmailNotifier implements ReservationObserver {
        @Override
        public void update(String message) {
            System.out.println("Email: " + message);
        }
    }

    static class SMSNotifier implements ReservationObserver {
        @Override
        public void update(String message) {
            System.out.println("SMS: " + message);
        }
    }

    // STATE - ESTADOS
    interface ReservationState {
        void confirm(Reservation reservation);
        void cancel(Reservation reservation);
        String getName();
    }

    static class PendingState implements ReservationState {
        @Override
        public void confirm(Reservation reservation) {
            reservation.setState(new ConfirmedState());
            reservation.notifyObservers("La reserva fue confirmada.");
        }

        @Override
        public void cancel(Reservation reservation) {
            reservation.setState(new CancelledState());
            reservation.notifyObservers("La reserva fue cancelada.");
        }

        @Override
        public String getName() {
            return "Pendiente";
        }
    }

    static class ConfirmedState implements ReservationState {
        @Override
        public void confirm(Reservation reservation) {
            System.out.println("La reserva ya está confirmada.");
        }

        @Override
        public void cancel(Reservation reservation) {
            reservation.setState(new CancelledState());
            reservation.notifyObservers("La reserva confirmada fue cancelada.");
        }

        @Override
        public String getName() {
            return "Confirmada";
        }
    }

    static class CancelledState implements ReservationState {
        @Override
        public void confirm(Reservation reservation) {
            System.out.println("No se puede confirmar una reserva cancelada.");
        }

        @Override
        public void cancel(Reservation reservation) {
            System.out.println("La reserva ya está cancelada.");
        }

        @Override
        public String getName() {
            return "Cancelada";
        }
    }

    // CLASE PRINCIPAL
    static class Reservation {
        private String passenger;
        private String flight;
        private String seat;
        private double basePrice;

        private PricingStrategy pricingStrategy;
        private ReservationState state;
        private List<ReservationObserver> observers = new ArrayList<>();

        public Reservation(String passenger, String flight, String seat, double basePrice) {
            this.passenger = passenger;
            this.flight = flight;
            this.seat = seat;
            this.basePrice = basePrice;
            this.state = new PendingState();
        }

        public void setPricingStrategy(PricingStrategy pricingStrategy) {
            this.pricingStrategy = pricingStrategy;
        }

        public double calculatePrice() {
            if (pricingStrategy == null) {
                return basePrice;
            }
            return pricingStrategy.calculate(basePrice);
        }

        public void addObserver(ReservationObserver observer) {
            observers.add(observer);
        }

        public void notifyObservers(String message) {
            for (ReservationObserver observer : observers) {
                observer.update(message);
            }
            System.out.println("Estado actual: " + state.getName());
            System.out.println();
        }

        public void confirm() {
            state.confirm(this);
        }

        public void cancel() {
            state.cancel(this);
        }

        public void setState(ReservationState state) {
            this.state = state;
        }

        public ReservationState getState() {
            return state;
        }
    }
}