# Punto 6 - Sistema de Reservas para Aerolínea

## Descripción
Este ejercicio desarrolla un sistema de reservas para aerolínea aplicando patrones de diseño para construir reservas complejas, calcular precios dinámicos, manejar estados de reserva y enviar notificaciones a los usuarios. El objetivo es mantener el sistema organizado y flexible frente a futuros cambios. 

## Patrones implementados
- **Builder**: para construir reservas paso a paso.
- **Strategy**: para calcular precios de forma dinámica.
- **State**: para controlar el comportamiento según el estado de la reserva.
- **Observer**: para notificar cambios al usuario. 

## Objetivo de la solución
La solución busca evitar constructores complejos, reducir el uso de condicionales para estados y permitir que las reglas de precio cambien sin afectar la estructura principal de la reserva. 

## Respuestas a las preguntas de reflexión

### ¿Qué problemas genera usar múltiples condicionales para estados?
Usar muchos condicionales vuelve el código difícil de leer, mantener y ampliar. Con **State**, cada estado se maneja en su propia clase y el comportamiento queda mejor organizado. 

### ¿Cómo facilita Builder la creación de reservas complejas?
**Builder** permite construir la reserva paso a paso, agregando datos como pasajero, vuelo, asiento y precio base sin depender de constructores largos y poco claros. 

### ¿Qué ocurre si cambian las reglas de precio?
Con **Strategy**, las reglas de precio pueden cambiar sin modificar la clase principal de reserva. Solo se agrega o reemplaza una estrategia de cálculo. 

### ¿Cómo desacoplar notificaciones?
Se utiliza **Observer**, de manera que la reserva solo emite el cambio de estado y los canales de notificación reaccionan independientemente. 

### ¿Qué patrón mejora cohesión en cambios de estado?
El patrón **State** mejora la cohesión porque concentra en cada clase de estado el comportamiento específico de una reserva pendiente, confirmada o cancelada. 

## Conclusión
En este ejercicio se implementó una solución para reservas de aerolínea utilizando **Builder, Strategy, State y Observer**. Estos patrones permitieron construir reservas complejas paso a paso, calcular precios dinámicos, manejar adecuadamente los estados y notificar cambios al usuario. El resultado es un sistema más claro, modular y fácil de extender frente a nuevas reglas de negocio. 

## Ejucion
sefase@fedora:~/Documents/Design-patterns/Reservas de aerolínea$ javac AirlineReservationDemo.java
sefase@fedora:~/Documents/Design-patterns/Reservas de aerolínea$ java AirlineReservationDemo