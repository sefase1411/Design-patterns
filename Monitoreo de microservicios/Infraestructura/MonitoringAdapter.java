package Infraestructura;

import Aplicacion.MetricProvider;

public class MonitoringAdapter implements MetricProvider {
    private final LegacyMonitoringAPI legacyAPI;

    public MonitoringAdapter(LegacyMonitoringAPI legacyAPI) {
        this.legacyAPI = legacyAPI;
    }

    @Override
    public double getCpuUsage(String serviceName) {
        return legacyAPI.cpu_usage_percent(serviceName);
    }
}