# Punto 1 - Plataforma de Comercio Electrónico Escalable

## Descripción
Este ejercicio desarrolla una plataforma de comercio electrónico aplicando patrones de diseño para desacoplar los métodos de pago, los tipos de envío, los descuentos y las notificaciones post-compra. El objetivo es permitir que el sistema crezca sin modificar la lógica principal del pedido. 

## Patrones implementados
- **Strategy**: para métodos de pago y tipos de envío.
- **Factory Method**: para crear objetos de pago y envío.
- **Decorator**: para aplicar descuentos acumulativos.
- **Observer**: para enviar notificaciones al finalizar la compra. 

## Objetivo de la solución
La solución busca que nuevas funcionalidades, como métodos de pago, promociones o notificaciones, puedan agregarse sin cambiar la clase principal del pedido. Esto hace que el sistema sea más flexible, mantenible y extensible. 

## Respuestas a las preguntas de reflexión

### ¿Qué ocurre si el sistema crece a 15 métodos de pago?
Gracias a **Strategy** y **Factory Method**, no es necesario modificar la lógica central. Cada nuevo método de pago se implementa como una clase independiente y su creación se centraliza en la fábrica. 

### ¿Cómo evitar modificar la clase principal al agregar promociones?
Se utiliza **Decorator**, ya que este patrón permite envolver el pedido con descuentos o promociones sin alterar la clase base. 

### ¿Qué ventajas ofrece Observer frente a llamadas directas?
**Observer** reduce el acoplamiento entre el pedido y los sistemas de notificación. El pedido no necesita conocer directamente si existe un correo, SMS o push; simplemente emite el evento y los observadores reaccionan. 

### ¿Qué patrón garantiza apertura para extensión?
Principalmente **Strategy** y **Decorator**, porque permiten agregar nuevas variantes de comportamiento y nuevas promociones sin modificar el núcleo del sistema. 

### ¿Qué riesgos hay si se mezclan responsabilidades?
El mayor riesgo es crear una clase demasiado grande y difícil de mantener, donde pagos, envíos, descuentos y notificaciones queden acoplados. Esto aumenta la fragilidad del sistema y dificulta las pruebas. 

## Conclusión
En este ejercicio se construyó una solución para una plataforma de comercio electrónico aplicando **Strategy, Factory Method, Decorator y Observer**. Estos patrones permitieron desacoplar pagos, envíos, promociones y notificaciones, evitando que toda la lógica quede concentrada en una sola clase. Como resultado, el sistema puede crecer de forma ordenada y agregar nuevas funcionalidades sin modificar la lógica principal del pedido. 

## Ejecucion
sefase@fedora:~/Documents/Design-patterns/Plataforma de Comercio$ javac EcommerceDemo.java
sefase@fedora:~/Documents/Design-patterns/Plataforma de Comercio$ java EcommerceDemo