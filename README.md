Sistema de Gestión de Personal – Clínica Salud Total
Materia: Programación Orientada a Objetos – Java  
Institución: Escuela Politécnica Nacional – ESFOT  
Docente: Ing. Yadira Franco Rocha
---
Descripción
Sistema de consola en Java para gestionar el personal (médicos y administrativos) de una clínica privada. Implementa CRUD completo, validaciones robustas y los pilares de la POO.
---
Estructura de paquetes
```
src/
├── modelo/
│   ├── Empleado.java        ← Clase padre abstracta
│   ├── Medico.java          ← Subclase médico
│   └── Administrativo.java  ← Subclase administrativo
├── servicio/
│   └── EmpleadoServicio.java ← CRUD con ArrayList
├── util/
│   └── Validador.java       ← Validaciones centralizadas
└── app/
    └── Main.java            ← Menú principal
```
---
Pilares de POO aplicados
Herencia
`Medico` y `Administrativo` heredan de `Empleado`. Comparten cédula, nombre, edad, teléfono y correo. Cada subclase agrega atributos propios (especialidad, departamento, etc.).
Encapsulamiento
Todos los atributos de `Empleado` son `private`. El acceso se realiza únicamente a través de getters y setters públicos, protegiendo la integridad de los datos.
Polimorfismo
El `ArrayList<Empleado>` almacena objetos de tipo `Medico` y `Administrativo`. Al llamar `e.calcularPago()` o `e.mostrarInformacion()`, Java ejecuta la versión correspondiente a cada subclase en tiempo de ejecución.
Abstracción
`Empleado` es una clase abstracta. Los métodos `calcularPago()` y `mostrarInformacion()` son abstractos, obligando a cada subclase a implementar su propia lógica de negocio.
---
Excepciones aplicadas
Excepción	Dónde se aplica
`NumberFormatException`	Menú, edad, pacientes, horas, valor consulta, valor hora
`IllegalArgumentException`	Validaciones de campos vacíos y rangos inválidos
Se usan bloques `try-catch` en todos los puntos de entrada numérica para capturar texto inválido y solicitar reingreso al usuario.
---
Conversiones
`Integer.parseInt()`: convierte la opción del menú, edad y número de pacientes desde String a int.
`Double.parseDouble()`: convierte valor de consulta, horas trabajadas y valor por hora desde String a double.
---
Validaciones
Menú: solo acepta números del 1 al 9.
Edad: entre 1 y 149.
Cédula: no se permiten duplicados.
Teléfono: solo dígitos numéricos.
Correo: debe contener `@` y `.`
Campos de texto: no pueden estar vacíos.
Valores numéricos (pacientes, horas, valor): deben ser mayores a cero.
---
Cálculo de pagos
Médico: `pago = numeroPacientesAtendidos × valorConsulta`
Administrativo: `pago = horasTrabajadas × valorHora`
---
Cómo compilar y ejecutar
```bash
# Compilar
javac -d out -sourcepath src src/app/Main.java

# Ejecutar
java -cp out app.Main
```
