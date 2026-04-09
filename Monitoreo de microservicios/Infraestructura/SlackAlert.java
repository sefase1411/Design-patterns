package Infraestructura;

import Aplicacion.AlertNotifier;

public class SlackAlert implements AlertNotifier {

    @Override
    public void notifyAlert(String message) {
        System.out.println("Slack: " + message);
    }
}