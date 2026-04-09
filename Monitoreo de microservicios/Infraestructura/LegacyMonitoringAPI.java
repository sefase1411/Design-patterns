package Infraestructura;

public class LegacyMonitoringAPI {

    public double cpu_usage_percent(String serviceName) {
        if (serviceName.equals("Auth-Service")) {
            return 72.5;
        } else if (serviceName.equals("Payment-Service")) {
            return 91.3;
        }
        return 50.0;
    }
}