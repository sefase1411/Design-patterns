package Interfaz;

import Aplicacion.MetricProvider;
import Aplicacion.MonitoringFacade;
import Infraestructura.EmailAlert;
import Infraestructura.LegacyMonitoringAPI;
import Infraestructura.MonitoringAdapter;
import Infraestructura.MonitoringConfig;
import Infraestructura.SlackAlert;

public class MonitoringDemo {

    public static void main(String[] args) {
        LegacyMonitoringAPI legacyAPI = new LegacyMonitoringAPI();
        MetricProvider adapter = new MonitoringAdapter(legacyAPI);

        MonitoringFacade facade = new MonitoringFacade(
                adapter,
                MonitoringConfig.getInstance().getCpuThreshold()
        );

        facade.addObserver(new EmailAlert());
        facade.addObserver(new SlackAlert());

        facade.monitorService("Auth-Service");
        facade.monitorService("Payment-Service");
    }
}