# Punto 2 - Sistema de Monitoreo de Microservicios

## Descripción
Este ejercicio desarrolla un sistema de monitoreo para microservicios, capaz de centralizar la configuración, integrar APIs externas de métricas, emitir alertas y ofrecer una interfaz simplificada para el usuario. El objetivo es construir una solución escalable y desacoplada para supervisar múltiples servicios. 

## Patrones implementados
- **Singleton**: para mantener una única configuración global.
- **Observer**: para emitir alertas a múltiples canales.
- **Adapter**: para integrar APIs externas con formatos distintos.
- **Facade**: para simplificar el acceso al sistema de monitoreo. 

## Objetivo de la solución
La solución busca centralizar parámetros importantes, desacoplar la obtención de métricas de los servicios externos y facilitar el crecimiento del sistema sin afectar su estructura principal. 

## Respuestas a las preguntas de reflexión

### ¿Qué problema resuelve Singleton aquí?
**Singleton** garantiza que exista una única instancia de configuración global, por ejemplo para umbrales de CPU o memoria, evitando inconsistencias en distintos puntos del sistema. 

### ¿Cómo desacoplar servicios externos?
Se desacoplan con **Adapter**, ya que este patrón traduce el formato de una API externa al formato esperado internamente por el sistema de monitoreo. 

### ¿Por qué Facade mejora mantenibilidad?
**Facade** concentra en un solo punto de entrada la lógica de monitoreo, como registrar servicios, consultar métricas y lanzar alertas, reduciendo la complejidad para el cliente. 

### ¿Cómo escalar el sistema a 100 microservicios?
La arquitectura desacoplada permite agregar más servicios sin romper el núcleo. **Facade** simplifica la operación, **Observer** facilita sumar nuevos canales de alerta y **Adapter** permite conectar nuevas fuentes externas de métricas. 

### ¿Dónde se produciría alto acoplamiento si no se aplican patrones?
El alto acoplamiento aparecería si el monitor dependiera directamente de las APIs externas, de cada canal de alerta y de múltiples configuraciones dispersas. Eso haría el sistema más difícil de mantener. 

## Conclusión
En este ejercicio se diseñó un sistema de monitoreo de microservicios usando **Singleton, Observer, Adapter y Facade**. Esta combinación permitió centralizar la configuración, integrar APIs externas con formatos distintos, desacoplar las alertas y ofrecer una interfaz unificada. Como resultado, el sistema queda preparado para escalar y mantenerse de forma más ordenada a medida que aumenta la cantidad de servicios monitoreados. 