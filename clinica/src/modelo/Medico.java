package modelo;

/**
 * Subclase Medico - hereda de Empleado.
 * Pago = numeroPacientesAtendidos × valorConsulta
 */
public class Medico extends Empleado {

    private String especialidad;
    private int numeroPacientesAtendidos;
    private double valorConsulta;

    // Constructor
    public Medico(String cedula, String nombre, int edad, String telefono, String correo,
                  String especialidad, int numeroPacientesAtendidos, double valorConsulta) {
        super(cedula, nombre, edad, telefono, correo);
        this.especialidad = especialidad;
        this.numeroPacientesAtendidos = numeroPacientesAtendidos;
        this.valorConsulta = valorConsulta;
    }

    // Getters y Setters
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public int getNumeroPacientesAtendidos() { return numeroPacientesAtendidos; }
    public void setNumeroPacientesAtendidos(int numeroPacientesAtendidos) {
        this.numeroPacientesAtendidos = numeroPacientesAtendidos;
    }

    public double getValorConsulta() { return valorConsulta; }
    public void setValorConsulta(double valorConsulta) { this.valorConsulta = valorConsulta; }

    // Implementación del método abstracto - Polimorfismo
    @Override
    public double calcularPago() {
        return numeroPacientesAtendidos * valorConsulta;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("==============================");
        System.out.println("  TIPO        : MÉDICO");
        System.out.println("  Cédula      : " + getCedula());
        System.out.println("  Nombre      : " + getNombre());
        System.out.println("  Edad        : " + getEdad());
        System.out.println("  Teléfono    : " + getTelefono());
        System.out.println("  Correo      : " + getCorreo());
        System.out.println("  Especialidad: " + especialidad);
        System.out.println("  Pacientes   : " + numeroPacientesAtendidos);
        System.out.println("  Val.Consulta: $" + valorConsulta);
        System.out.println("  PAGO TOTAL  : $" + calcularPago());
        System.out.println("==============================");
    }
}
