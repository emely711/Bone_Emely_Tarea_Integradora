package app;

import modelo.Empleado;
import modelo.Medico;
import modelo.Administrativo;
import servicio.EmpleadoServicio;
import util.Validador;

import java.util.Scanner;

/**
 * Clase principal del Sistema de Gestión de Personal - Clínica Privada.
 * Aplica: excepciones, conversiones, validaciones y menú interactivo.
 */
public class Main {

    static Scanner sc = new Scanner(System.in);
    static EmpleadoServicio servicio = new EmpleadoServicio();

    public static void main(String[] args) {
        int opcion = 0;

        do {
            mostrarMenu();
            opcion = leerOpcionMenu();

            switch (opcion) {
                case 1: registrarMedico();          break;
                case 2: registrarAdministrativo();   break;
                case 3: servicio.mostrarEmpleados(); break;
                case 4: buscarPorCedula();           break;
                case 5: reemplazarInformacion();     break;
                case 6: eliminarRegistro();          break;
                case 7: servicio.calcularPagos();    break;
                case 8: servicio.mostrarEstadisticas(); break;
                case 9: System.out.println("Saliendo del sistema. ¡Hasta luego!"); break;
                default: System.out.println("Error: opción inválida."); break;
            }

        } while (opcion != 9);

        sc.close();
    }

    // ==================== MENÚ ====================

    static void mostrarMenu() {
        System.out.println("\n===== CLÍNICA SALUD TOTAL =====");
        System.out.println("1. Registrar médico");
        System.out.println("2. Registrar administrativo");
        System.out.println("3. Mostrar empleados");
        System.out.println("4. Buscar por cédula");
        System.out.println("5. Reemplazar información");
        System.out.println("6. Eliminar registro");
        System.out.println("7. Calcular pagos");
        System.out.println("8. Mostrar estadísticas");
        System.out.println("9. Salir");
        System.out.print("Ingrese opción: ");
    }

    /**
     * Lee la opción del menú con manejo de excepciones.
     * Valida letras (NumberFormatException) y rango fuera de 1-9.
     */
    static int leerOpcionMenu() {
        while (true) {
            try {
                String entrada = sc.nextLine().trim();
                int opcion = Integer.parseInt(entrada); // Conversión String -> int
                if (opcion < 1 || opcion > 9) {
                    System.out.println("Error: opción inválida. Ingrese un número del 1 al 9.");
                    System.out.print("Ingrese opción: ");
                } else {
                    return opcion;
                }
            } catch (NumberFormatException e) {
                // Captura letras o caracteres no numéricos
                System.out.println("Error: opción inválida. Solo se permiten números del 1 al 9.");
                System.out.print("Ingrese opción: ");
            }
        }
    }

    // ==================== REGISTRO MÉDICO ====================

    static void registrarMedico() {
        System.out.println("\n--- REGISTRAR MÉDICO ---");

        String cedula    = leerTextoNoVacio("Cédula         : ");
        if (servicio.existeCedula(cedula)) {
            System.out.println("Error: la cédula " + cedula + " ya está registrada.");
            return;
        }
        String nombre    = leerTextoNoVacio("Nombre         : ");
        int    edad      = leerEdad();
        String telefono  = leerTelefono();
        String correo    = leerCorreo();
        String especialidad = leerTextoNoVacio("Especialidad   : ");
        int    pacientes = leerEnteroPositivo("Nro. pacientes : ");
        double valorC    = leerDoublePositivo("Valor consulta : ");

        String resultado = servicio.registrarMedico(cedula, nombre, edad, telefono, correo,
                                                     especialidad, pacientes, valorC);
        System.out.println(resultado);
    }

    // ==================== REGISTRO ADMINISTRATIVO ====================

    static void registrarAdministrativo() {
        System.out.println("\n--- REGISTRAR ADMINISTRATIVO ---");

        String cedula   = leerTextoNoVacio("Cédula           : ");
        if (servicio.existeCedula(cedula)) {
            System.out.println("Error: la cédula " + cedula + " ya está registrada.");
            return;
        }
        String nombre   = leerTextoNoVacio("Nombre           : ");
        int    edad     = leerEdad();
        String telefono = leerTelefono();
        String correo   = leerCorreo();
        String depto    = leerTextoNoVacio("Departamento     : ");
        double horas    = leerDoublePositivo("Horas trabajadas : ");
        double valorH   = leerDoublePositivo("Valor por hora   : ");

        String resultado = servicio.registrarAdministrativo(cedula, nombre, edad, telefono, correo,
                                                             depto, horas, valorH);
        System.out.println(resultado);
    }

    // ==================== BÚSQUEDA ====================

    static void buscarPorCedula() {
        System.out.print("\nIngrese cédula a buscar: ");
        String cedula = sc.nextLine().trim();
        Empleado encontrado = servicio.buscarPorCedula(cedula);
        if (encontrado != null) {
            encontrado.mostrarInformacion();
        } else {
            System.out.println("Registro no encontrado.");
        }
    }

    // ==================== REEMPLAZAR ====================

    static void reemplazarInformacion() {
        System.out.print("\nIngrese cédula del empleado a actualizar: ");
        String cedula = sc.nextLine().trim();
        Empleado existente = servicio.buscarPorCedula(cedula);

        if (existente == null) {
            System.out.println("Registro no encontrado.");
            return;
        }

        System.out.println("Empleado encontrado:");
        existente.mostrarInformacion();

        if (existente instanceof Medico) {
            System.out.println("Ingrese nuevos datos para el médico:");
            String nombre    = leerTextoNoVacio("Nombre         : ");
            int    edad      = leerEdad();
            String telefono  = leerTelefono();
            String correo    = leerCorreo();
            String especialidad = leerTextoNoVacio("Especialidad   : ");
            int    pacientes = leerEnteroPositivo("Nro. pacientes : ");
            double valorC    = leerDoublePositivo("Valor consulta : ");

            Medico nuevoMedico = new Medico(cedula, nombre, edad, telefono, correo,
                                            especialidad, pacientes, valorC);
            System.out.println(servicio.reemplazarEmpleado(cedula, nuevoMedico));

        } else if (existente instanceof Administrativo) {
            System.out.println("Ingrese nuevos datos para el administrativo:");
            String nombre   = leerTextoNoVacio("Nombre           : ");
            int    edad     = leerEdad();
            String telefono = leerTelefono();
            String correo   = leerCorreo();
            String depto    = leerTextoNoVacio("Departamento     : ");
            double horas    = leerDoublePositivo("Horas trabajadas : ");
            double valorH   = leerDoublePositivo("Valor por hora   : ");

            Administrativo nuevoAdmin = new Administrativo(cedula, nombre, edad, telefono, correo,
                                                           depto, horas, valorH);
            System.out.println(servicio.reemplazarEmpleado(cedula, nuevoAdmin));
        }
    }

    // ==================== ELIMINAR ====================

    static void eliminarRegistro() {
        System.out.print("\nIngrese cédula del empleado a eliminar: ");
        String cedula = sc.nextLine().trim();
        System.out.println(servicio.eliminarEmpleado(cedula));
    }

    // ==================== MÉTODOS DE ENTRADA CON VALIDACIONES ====================

    /**
     * Lee un texto no vacío con ciclo de reintento.
     */
    static String leerTextoNoVacio(String etiqueta) {
        String valor;
        do {
            System.out.print(etiqueta);
            valor = sc.nextLine().trim();
            if (Validador.esVacio(valor)) {
                System.out.println("Error: el campo no puede estar vacío.");
            }
        } while (Validador.esVacio(valor));
        return valor;
    }

    /**
     * Lee la edad con validación de rango y excepción por letras.
     */
    static int leerEdad() {
        while (true) {
            System.out.print("Edad           : ");
            try {
                String entrada = sc.nextLine().trim();
                int edad = Integer.parseInt(entrada); // Conversión String -> int
                if (!Validador.esEdadValida(edad)) {
                    System.out.println("Error: edad inválida. Ingrese entre 1 y 149.");
                } else {
                    return edad;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: ingrese un número válido para la edad.");
            }
        }
    }

    /**
     * Lee y valida el teléfono (solo números).
     */
    static String leerTelefono() {
        while (true) {
            System.out.print("Teléfono       : ");
            String tel = sc.nextLine().trim();
            if (Validador.esVacio(tel)) {
                System.out.println("Error: el teléfono no puede estar vacío.");
            } else if (!Validador.esTelefonoValido(tel)) {
                System.out.println("Error: el teléfono solo debe contener números.");
            } else {
                return tel;
            }
        }
    }

    /**
     * Lee y valida el correo (debe contener @ y punto).
     */
    static String leerCorreo() {
        while (true) {
            System.out.print("Correo         : ");
            String correo = sc.nextLine().trim();
            if (Validador.esVacio(correo)) {
                System.out.println("Error: el correo no puede estar vacío.");
            } else if (!Validador.esCorreoValido(correo)) {
                System.out.println("Error: el correo debe contener @ y un punto.");
            } else {
                return correo;
            }
        }
    }

    /**
     * Lee un entero positivo con excepción por texto.
     */
    static int leerEnteroPositivo(String etiqueta) {
        while (true) {
            System.out.print(etiqueta);
            try {
                String entrada = sc.nextLine().trim();
                int valor = Integer.parseInt(entrada); // Conversión String -> int
                if (!Validador.esMayorACero(valor)) {
                    System.out.println("Error: el valor debe ser mayor a cero.");
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: ingrese un número entero válido.");
            }
        }
    }

    /**
     * Lee un double positivo con excepción por texto.
     */
    static double leerDoublePositivo(String etiqueta) {
        while (true) {
            System.out.print(etiqueta);
            try {
                String entrada = sc.nextLine().trim();
                double valor = Double.parseDouble(entrada); // Conversión String -> double
                if (!Validador.esMayorACero(valor)) {
                    System.out.println("Error: el valor debe ser mayor a cero.");
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: ingrese un número decimal válido (ej: 25.50).");
            }
        }
    }
}
