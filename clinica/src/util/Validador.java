package util;

/**
 * Clase de utilidad para validaciones del sistema.
 * Centraliza todas las validaciones de datos de entrada.
 */
public class Validador {

    /**
     * Valida que un String no sea nulo ni vacío.
     */
    public static boolean esVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    /**
     * Valida que el correo contenga @ y punto.
     */
    public static boolean esCorreoValido(String correo) {
        return correo != null && correo.contains("@") && correo.contains(".");
    }

    /**
     * Valida que el teléfono contenga solo dígitos.
     */
    public static boolean esTelefonoValido(String telefono) {
        return telefono != null && telefono.matches("\\d+");
    }

    /**
     * Valida que la edad esté en rango válido (1-149).
     */
    public static boolean esEdadValida(int edad) {
        return edad > 0 && edad < 150;
    }

    /**
     * Valida que un número sea mayor a cero.
     */
    public static boolean esMayorACero(double valor) {
        return valor > 0;
    }

    /**
     * Valida que un entero sea mayor a cero.
     */
    public static boolean esMayorACero(int valor) {
        return valor > 0;
    }

    /**
     * Convierte String a int de forma segura.
     * Retorna -999 si falla la conversión (señal de error).
     */
    public static int convertirInt(String texto) {
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            return -999; // valor centinela para detectar error
        }
    }

    /**
     * Convierte String a double de forma segura.
     */
    public static double convertirDouble(String texto) {
        try {
            return Double.parseDouble(texto.trim());
        } catch (NumberFormatException e) {
            return -999.0;
        }
    }
}
