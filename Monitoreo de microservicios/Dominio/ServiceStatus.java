package Dominio;

public class ServiceStatus {
    private final String serviceName;
    private final double cpuUsage;
    private final double threshold;

    public ServiceStatus(String serviceName, double cpuUsage, double threshold) {
        this.serviceName = serviceName;
        this.cpuUsage = cpuUsage;
        this.threshold = threshold;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public double getThreshold() {
        return threshold;
    }

    public boolean isAlert() {
        return cpuUsage > threshold;
    }
}