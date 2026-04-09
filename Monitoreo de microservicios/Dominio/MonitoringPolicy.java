package Dominio;

public class MonitoringPolicy {

    public String evaluate(ServiceStatus status) {
        if (status.isAlert()) {
            return "Alerta: " + status.getServiceName()
                    + " superó el umbral de CPU con "
                    + status.getCpuUsage() + "%";
        }
        return "Estado normal.";
    }
}