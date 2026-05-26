package servicio;

import modelo.Empleado;
import modelo.Medico;
import modelo.Administrativo;
import util.Validador;

import java.util.ArrayList;

/**
 * Servicio CRUD para gestionar empleados de la clínica.
 * Usa ArrayList<Empleado> - Polimorfismo en acción.
 */
public class EmpleadoServicio {

    // ArrayList polimórfico - almacena Medico y Administrativo bajo tipo Empleado
    private ArrayList<Empleado> empleados = new ArrayList<>();

    // ==================== CREATE ====================

    /**
     * Registra un médico en el sistema.
     * @return mensaje de resultado
     */
    public String registrarMedico(String cedula, String nombre, int edad, String telefono,
                                   String correo, String especialidad,
                                   int pacientes, double valorConsulta) {
        if (existeCedula(cedula)) {
            return "ERROR: La cédula " + cedula + " ya está registrada.";
        }
        Medico m = new Medico(cedula, nombre, edad, telefono, correo,
                              especialidad, pacientes, valorConsulta);
        empleados.add(m);
        return "Médico registrado con éxito.";
    }

    /**
     * Registra un administrativo en el sistema.
     */
    public String registrarAdministrativo(String cedula, String nombre, int edad, String telefono,
                                           String correo, String departamento,
                                           double horas, double valorHora) {
        if (existeCedula(cedula)) {
            return "ERROR: La cédula " + cedula + " ya está registrada.";
        }
        Administrativo a = new Administrativo(cedula, nombre, edad, telefono, correo,
                                              departamento, horas, valorHora);
        empleados.add(a);
        return "Administrativo registrado con éxito.";
    }

    // ==================== READ ====================

    /**
     * Muestra todos los empleados registrados.
     */
    public void mostrarEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        System.out.println("\n===== LISTA DE EMPLEADOS =====");
        for (Empleado e : empleados) {
            e.mostrarInformacion(); // Polimorfismo: llama al método correspondiente
        }
    }

    /**
     * Busca un empleado por cédula.
     * @return el Empleado encontrado o null si no existe
     */
    public Empleado buscarPorCedula(String cedula) {
        for (Empleado e : empleados) {
            if (e.getCedula().equals(cedula)) {
                return e;
            }
        }
        return null;
    }

    // ==================== UPDATE ====================

    /**
     * Reemplaza un empleado existente con nuevos datos.
     * Se busca por cédula y se reemplaza el registro completo.
     */
    public String reemplazarEmpleado(String cedula, Empleado nuevoEmpleado) {
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getCedula().equals(cedula)) {
                empleados.set(i, nuevoEmpleado);
                return "Empleado actualizado con éxito.";
            }
        }
        return "ERROR: No se encontró empleado con cédula " + cedula;
    }

    // ==================== DELETE ====================

    /**
     * Elimina un empleado por cédula.
     */
    public String eliminarEmpleado(String cedula) {
        Empleado encontrado = buscarPorCedula(cedula);
        if (encontrado != null) {
            empleados.remove(encontrado);
            return "Empleado eliminado con éxito.";
        }
        return "ERROR: No se encontró empleado con cédula " + cedula;
    }

    // ==================== PAGOS ====================

    /**
     * Muestra el cálculo de pago de todos los empleados.
     */
    public void calcularPagos() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        System.out.println("\n===== CÁLCULO DE PAGOS =====");
        for (Empleado e : empleados) {
            System.out.printf("%-30s | Pago: $%.2f%n", e.getNombre(), e.calcularPago());
        }
    }

    // ==================== ESTADÍSTICAS ====================

    /**
     * Muestra estadísticas del personal de la clínica.
     */
    public void mostrarEstadisticas() {
        int totalMedicos = 0;
        int totalAdministrativos = 0;
        double pagoTotalMedicos = 0;
        double pagoTotalAdmin = 0;
        Empleado mayorIngreso = null;
        double maxPago = 0;

        for (Empleado e : empleados) {
            double pago = e.calcularPago();
            if (e instanceof Medico) {
                totalMedicos++;
                pagoTotalMedicos += pago;
            } else if (e instanceof Administrativo) {
                totalAdministrativos++;
                pagoTotalAdmin += pago;
            }
            if (pago > maxPago) {
                maxPago = pago;
                mayorIngreso = e;
            }
        }

        System.out.println("\n========== ESTADÍSTICAS ==========");
        System.out.println("  Total médicos          : " + totalMedicos);
        System.out.println("  Total administrativos  : " + totalAdministrativos);
        System.out.println("  Total empleados        : " + empleados.size());
        System.out.printf ("  Pago total médicos     : $%.2f%n", pagoTotalMedicos);
        System.out.printf ("  Pago total admin.      : $%.2f%n", pagoTotalAdmin);
        if (mayorIngreso != null) {
            System.out.printf("  Mayor ingreso          : %s ($%.2f)%n",
                    mayorIngreso.getNombre(), maxPago);
        } else {
            System.out.println("  Mayor ingreso          : N/A");
        }
        System.out.println("==================================");
    }

    // ==================== UTILIDADES ====================

    /**
     * Verifica si una cédula ya existe en el sistema.
     */
    public boolean existeCedula(String cedula) {
        return buscarPorCedula(cedula) != null;
    }

    /**
     * Retorna el ArrayList de empleados (para uso interno).
     */
    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }
}
