package Infraestructura;

import Aplicacion.AlertNotifier;

public class EmailAlert implements AlertNotifier {

    @Override
    public void notifyAlert(String message) {
        System.out.println("Email: " + message);
    }
}