package Infraestructura;

public class MonitoringConfig {
    private static MonitoringConfig instance;
    private final double cpuThreshold = 80.0;

    private MonitoringConfig() {
    }

    public static MonitoringConfig getInstance() {
        if (instance == null) {
            instance = new MonitoringConfig();
        }
        return instance;
    }

    public double getCpuThreshold() {
        return cpuThreshold;
    }
}