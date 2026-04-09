package Aplicacion;

import java.util.ArrayList;
import java.util.List;

import Dominio.MonitoringPolicy;
import Dominio.ServiceStatus;

public class MonitoringFacade {
    private final MetricProvider metricProvider;
    private final List<AlertNotifier> notifiers;
    private final MonitoringPolicy policy;
    private final double threshold;

    public MonitoringFacade(MetricProvider metricProvider, double threshold) {
        this.metricProvider = metricProvider;
        this.threshold = threshold;
        this.notifiers = new ArrayList<>();
        this.policy = new MonitoringPolicy();
    }

    public void addObserver(AlertNotifier notifier) {
        notifiers.add(notifier);
    }

    public void monitorService(String serviceName) {
        double cpu = metricProvider.getCpuUsage(serviceName);

        ServiceStatus status = new ServiceStatus(serviceName, cpu, threshold);

        System.out.println("Servicio: " + serviceName);
        System.out.println("CPU actual: " + cpu + "%");
        System.out.println("Umbral: " + threshold + "%");

        String result = policy.evaluate(status);

        if (status.isAlert()) {
            for (AlertNotifier notifier : notifiers) {
                notifier.notifyAlert(result);
            }
            System.out.println();
        } else {
            System.out.println(result);
            System.out.println();
        }
    }
}