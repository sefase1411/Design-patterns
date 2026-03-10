import java.util.ArrayList;
import java.util.List;

// Archivo: MonitoringDemo.java
public class MonitoringDemo {

    public static void main(String[] args) {
        MonitoringFacade facade = new MonitoringFacade();

        facade.addObserver(new EmailAlert());
        facade.addObserver(new SlackAlert());

        facade.monitorService("Auth-Service");
        facade.monitorService("Payment-Service");
    }

    // SINGLETON - CONFIGURACIÓN
    static class MonitoringConfig {
        private static MonitoringConfig instance;
        private final double cpuThreshold = 80.0;

        private MonitoringConfig() {}

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

    // ADAPTER - API EXTERNA
    interface MetricProvider {
        double getCpuUsage(String serviceName);
    }

    // API externa simulada
    static class LegacyMonitoringAPI {
        public double cpu_usage_percent(String serviceName) {
            if (serviceName.equals("Auth-Service")) {
                return 72.5;
            } else if (serviceName.equals("Payment-Service")) {
                return 91.3;
            }
            return 50.0;
        }
    }

    static class MonitoringAdapter implements MetricProvider {
        private final LegacyMonitoringAPI legacyAPI;

        public MonitoringAdapter(LegacyMonitoringAPI legacyAPI) {
            this.legacyAPI = legacyAPI;
        }

        @Override
        public double getCpuUsage(String serviceName) {
            return legacyAPI.cpu_usage_percent(serviceName);
        }
    }

    // OBSERVER - ALERTAS
    interface AlertObserver {
        void update(String message);
    }

    static class EmailAlert implements AlertObserver {
        @Override
        public void update(String message) {
            System.out.println("Email: " + message);
        }
    }

    static class SlackAlert implements AlertObserver {
        @Override
        public void update(String message) {
            System.out.println("Slack: " + message);
        }
    }

    // MONITOR PRINCIPAL
    static class MicroserviceMonitor {
        private final MetricProvider metricProvider;
        private final List<AlertObserver> observers = new ArrayList<>();

        public MicroserviceMonitor(MetricProvider metricProvider) {
            this.metricProvider = metricProvider;
        }

        public void addObserver(AlertObserver observer) {
            observers.add(observer);
        }

        public void checkService(String serviceName) {
            double cpu = metricProvider.getCpuUsage(serviceName);
            double threshold = MonitoringConfig.getInstance().getCpuThreshold();

            System.out.println("Servicio: " + serviceName);
            System.out.println("CPU actual: " + cpu + "%");
            System.out.println("Umbral: " + threshold + "%");

            if (cpu > threshold) {
                notifyObservers("Alerta: " + serviceName + " superó el umbral de CPU con " + cpu + "%");
            } else {
                System.out.println("Estado normal.\n");
            }
        }

        private void notifyObservers(String message) {
            for (AlertObserver observer : observers) {
                observer.update(message);
            }
            System.out.println();
        }
    }

    // FACADE - INTERFAZ SIMPLE
    static class MonitoringFacade {
        private final MicroserviceMonitor monitor;

        public MonitoringFacade() {
            LegacyMonitoringAPI legacyAPI = new LegacyMonitoringAPI();
            MetricProvider adapter = new MonitoringAdapter(legacyAPI);
            this.monitor = new MicroserviceMonitor(adapter);
        }

        public void addObserver(AlertObserver observer) {
            monitor.addObserver(observer);
        }

        public void monitorService(String serviceName) {
            monitor.checkService(serviceName);
        }
    }
}